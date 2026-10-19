package com.example

import android.graphics.Bitmap
import android.graphics.Color as AndroidColor
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import java.io.File
import java.io.FileOutputStream
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
class PdfViewerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent?.data ?: intent?.getParcelableExtra<Uri>(android.content.Intent.EXTRA_STREAM)
        if (uri == null) {
            finish()
            return
        }
        runCatching { contentResolver.takePersistableUriPermission(uri, intent.flags and (IntentFlags.READ or IntentFlags.WRITE)) }
        cachePdf(uri)
        setContent {
            MyApplicationTheme {
                PdfViewer(uri = uri, title = uri.lastPathSegment?.substringAfterLast('/') ?: "PDF") { finish() }
            }
        }
    }

    private fun cachePdf(uri: Uri) {
        runCatching {
            val folder = File(filesDir, "imported_pdfs").apply { mkdirs() }
            val target = File(folder, "pdf-${System.currentTimeMillis()}.pdf")
            contentResolver.openInputStream(uri)?.use { input -> FileOutputStream(target).use { output -> input.copyTo(output) } }
        }
    }
}

private object IntentFlags {
    const val READ = android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
    const val WRITE = android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
}

private data class PdfState(val pages: List<Bitmap> = emptyList(), val error: String? = null, val loading: Boolean = true)

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
private fun PdfViewer(uri: Uri, title: String, onBack: () -> Unit) {
    val context = LocalContext.current
    var state by remember(uri) { mutableStateOf(PdfState()) }
    LaunchedEffect(uri) {
        state = withContext(Dispatchers.IO) { renderPdf(context, uri) }
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(title, maxLines = 1) },
            navigationIcon = { TextButton(onClick = onBack) { Text("رجوع") } }
        )
    }) { padding ->
        when {
            state.loading -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            state.error != null -> Box(Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) { Text(state.error.orEmpty(), color = MaterialTheme.colorScheme.error) }
            else -> LazyColumn(Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color(0xffe9edf1)).padding(padding), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.pages) { page -> Image(page.asImageBitmap(), contentDescription = "صفحة PDF", modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), contentScale = ContentScale.FillWidth) }
            }
        }
    }
}

private fun renderPdf(context: android.content.Context, uri: Uri): PdfState {
    var descriptor: ParcelFileDescriptor? = null
    var renderer: PdfRenderer? = null
    val pages = mutableListOf<Bitmap>()
    return try {
        descriptor = context.contentResolver.openFileDescriptor(uri, "r") ?: error("تعذر فتح ملف PDF.")
        renderer = PdfRenderer(descriptor)
        require(renderer.pageCount > 0) { "ملف PDF فارغ." }
        require(renderer.pageCount <= 200) { "ملف PDF يحتوي على صفحات كثيرة جدًا للعرض الآمن." }
        for (index in 0 until renderer.pageCount) {
            renderer.openPage(index).use { page ->
                val width = 1200
                val height = (width.toFloat() * page.height / page.width).toInt().coerceAtLeast(1)
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                bitmap.eraseColor(AndroidColor.WHITE)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                pages += bitmap
            }
        }
        PdfState(pages = pages, loading = false)
    } catch (e: Exception) {
        pages.forEach(Bitmap::recycle)
        PdfState(error = "تعذر قراءة ملف PDF: ${e.message.orEmpty()}", loading = false)
    } finally {
        renderer?.close()
        descriptor?.close()
    }
}
