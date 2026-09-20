package com.mohammedalhzmi.masrofmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.FileOutputStream
import android.content.Context
import android.net.Uri

class MasrofViewModel(
    private val repository: MasrofRepository,
    private val database: MasrofDatabase
) : ViewModel() {
    val allDocuments = repository.allDocuments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val lastDocumentNumber = repository.getLastDocumentNumber()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addDocument(document: Document) {
        viewModelScope.launch {
            repository.insert(document)
        }
    }

    fun exportDatabase(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val dbFile = context.getDatabasePath("masrof-db")
            context.contentResolver.openOutputStream(uri)?.use { output ->
                FileInputStream(dbFile).use { input ->
                    input.copyTo(output)
                }
            }
        }
    }

    fun importDatabase(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            database.close()
            val dbFile = context.getDatabasePath("masrof-db")
            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            }
            // Room will re-open automatically
        }
    }
}
