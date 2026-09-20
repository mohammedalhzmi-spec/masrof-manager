package com.example

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import com.example.ui.theme.MyApplicationTheme
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import com.mohammedalhzmi.masrofmanager.ui.AppLockScreen
import com.mohammedalhzmi.masrofmanager.ui.AppNavigation
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.ui.WelcomeScreen
import com.mohammedalhzmi.masrofmanager.ui.LoginScreen
import com.mohammedalhzmi.masrofmanager.ui.RegisterScreen
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences
import com.mohammedalhzmi.masrofmanager.util.UserSession

class MainActivity : FragmentActivity() {
    private var locked by mutableStateOf(false)
    private var showWelcome by mutableStateOf(true)
    private var loggedIn by mutableStateOf(false)
    private var loginError by mutableStateOf<String?>(null)
    private var registrationError by mutableStateOf<String?>(null)
    private var showRegister by mutableStateOf(false)
    private var initializing by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        locked = AppLockPreferences.enabled(this) && AppLockPreferences.shouldRelock(this)
        val db = Room.databaseBuilder(applicationContext, MasrofDatabase::class.java, "masrof-db").addMigrations(MasrofDatabase.MIGRATION_3_4).build()
        val repository = MasrofRepository(db.documentDao(), db.settingsDao(), db.contactDao(), db.userDao(), db.auditDao())
        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = MasrofViewModel(repository, db) as T
        })[MasrofViewModel::class.java]
        setContent {
            MyApplicationTheme {
                val scope = rememberCoroutineScope()
                LaunchedEffect(Unit) {
                    viewModel.ensureDefaultAdmin()
                    if (viewModel.restoreRememberedUser(this@MainActivity) != null) { loggedIn = true; locked = false }
                    initializing = false
                }
                if (initializing) {
                    WelcomeScreen { }
                } else if (showWelcome) {
                    WelcomeScreen { showWelcome = false }
                } else if (!loggedIn && showRegister) {
                    RegisterScreen(onRegister = { username, password, fullName, role, _, _ ->
                        scope.launch {
                            val created = viewModel.registerUser(username, password, fullName, role.name)
                            if (!created) registrationError = "اسم المستخدم موجود مسبقًا" else {
                                val user = viewModel.authenticate(this@MainActivity, username, password, true)
                                if (user != null) { loggedIn = true; locked = false; showRegister = false; registrationError = null }
                            }
                        }
                    }, onBack = { showRegister = false; registrationError = null }, error = registrationError)
                } else if (!loggedIn) {
                    LoginScreen(onLogin = { username, password, remember ->
                        scope.launch {
                            val user = viewModel.authenticate(this@MainActivity, username, password, remember)
                            if (user == null) loginError = "اسم المستخدم أو كلمة المرور غير صحيحة" else { loggedIn = true; locked = false; loginError = null; AppLockPreferences.markUnlocked(this@MainActivity) }
                        }
                    }, onRegister = { showRegister = true; loginError = null }, error = loginError)
                } else if (locked && AppLockPreferences.enabled(this)) {
                    AppLockScreen(
                        type = AppLockPreferences.type(this),
                        biometricAvailable = biometricAvailable(),
                        onBiometric = { showBiometricPrompt() },
                        onUnlock = { secret -> unlockWithSecret(secret) }
                    )
                } else {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) { AppNavigation(viewModel) }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (AppLockPreferences.enabled(this) && AppLockPreferences.shouldRelock(this)) locked = true
    }

    private fun unlockWithSecret(secret: String): Boolean {
        val minimumLength = if (AppLockPreferences.type(this) == com.mohammedalhzmi.masrofmanager.util.LockType.PATTERN) 4 else 1
        val valid = secret.length >= minimumLength && AppLockPreferences.verify(this, secret)
        if (valid) { AppLockPreferences.markUnlocked(this); locked = false }
        return valid
    }

    private fun biometricAvailable(): Boolean {
        if (!AppLockPreferences.biometricEnabled(this)) return false
        val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
        return BiometricManager.from(this).canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS
    }

    private fun showBiometricPrompt() {
        val executor = mainExecutor
        val prompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                AppLockPreferences.markUnlocked(this@MainActivity); locked = false
            }
        })
        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle("فتح نظام مالية صندوق النظافة")
            .setSubtitle("استخدم بصمة الإصبع أو وسيلة أمان الهاتف")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
        prompt.authenticate(info)
    }
}
