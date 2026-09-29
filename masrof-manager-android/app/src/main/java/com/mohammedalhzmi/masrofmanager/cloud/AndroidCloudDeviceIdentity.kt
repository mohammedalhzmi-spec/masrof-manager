package com.mohammedalhzmi.masrofmanager.cloud

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.MessageDigest
import java.security.Signature
import java.security.spec.ECGenParameterSpec
import java.util.UUID

/** Public information for a device key. The private key never leaves Android Keystore. */
data class CloudDeviceIdentity(
    val deviceId: String,
    val publicKeyBase64: String,
    val publicKeyHash: String
)

class AndroidCloudDeviceIdentity(context: Context) {
    private val appContext = context.applicationContext

    fun identity(): CloudDeviceIdentity {
        val keyStore = loadKeyStore()
        if (!keyStore.containsAlias(KEY_ALIAS)) createKeyPair()
        val certificate = keyStore.getCertificate(KEY_ALIAS)
            ?: throw IllegalStateException("تعذر إنشاء مفتاح أمان لهذا الجهاز.")
        val publicKey = certificate.publicKey.encoded
        val deviceId = androidDeviceId()
        return CloudDeviceIdentity(
            deviceId = deviceId,
            publicKeyBase64 = Base64.encodeToString(publicKey, Base64.NO_WRAP),
            publicKeyHash = sha256Hex(publicKey)
        )
    }

    fun signChallenge(challengeBase64: String): String {
        val challenge = try {
            Base64.decode(challengeBase64, Base64.DEFAULT)
        } catch (_: IllegalArgumentException) {
            throw IllegalArgumentException("تحدي التحقق غير صالح.")
        }
        val keyStore = loadKeyStore()
        val privateKey = keyStore.getKey(KEY_ALIAS, null) as? java.security.PrivateKey
            ?: throw IllegalStateException("مفتاح الجهاز غير موجود؛ سجّل الدخول مجددًا لطلب اعتماد جديد.")
        val signer = Signature.getInstance("SHA256withECDSA")
        signer.initSign(privateKey)
        signer.update(challenge)
        return Base64.encodeToString(signer.sign(), Base64.NO_WRAP)
    }

    private fun loadKeyStore(): KeyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply { load(null) }

    private fun createKeyPair() {
        val generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, ANDROID_KEY_STORE)
        generator.initialize(
            KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_SIGN)
                .setAlgorithmParameterSpec(ECGenParameterSpec("secp256r1"))
                .setDigests(KeyProperties.DIGEST_SHA256)
                .build()
        )
        generator.generateKeyPair()
    }

    private fun androidDeviceId(): String {
        val androidId = Settings.Secure.getString(appContext.contentResolver, Settings.Secure.ANDROID_ID)
            ?.takeIf(String::isNotBlank)
        if (androidId != null) return androidId
        val preferences = appContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getString(INSTALLATION_ID, null)
            ?: UUID.randomUUID().toString().also {
                preferences.edit().putString(INSTALLATION_ID, it).apply()
            }
    }

    private fun sha256Hex(bytes: ByteArray): String = MessageDigest.getInstance("SHA-256")
        .digest(bytes)
        .joinToString("") { byte -> "%02x".format(byte) }

    private companion object {
        const val ANDROID_KEY_STORE = "AndroidKeyStore"
        const val KEY_ALIAS = "masrof-cloud-device-signing-v1"
        const val PREFERENCES = "cloud_device_identity"
        const val INSTALLATION_ID = "installation_id"
    }
}
