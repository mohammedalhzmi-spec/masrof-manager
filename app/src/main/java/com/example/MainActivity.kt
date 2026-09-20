package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.compose.ui.Modifier
import com.example.ui.theme.MyApplicationTheme
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import com.mohammedalhzmi.masrofmanager.ui.AppNavigation
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val db = Room.databaseBuilder(applicationContext, MasrofDatabase::class.java, "masrof-db").build()
    val repository = MasrofRepository(db.documentDao(), db.settingsDao(), db.contactDao())
    val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = MasrofViewModel(repository, db) as T
    })[MasrofViewModel::class.java]

    setContent {
      MyApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigation(viewModel)
          }
        }
      }
    }
  }
}
