package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.FormMemory

@Composable
fun RequestFormScreen(viewModel: MasrofViewModel, onNavigateBack: () -> Unit, existing: Document? = null) {
    val context = LocalContext.current
    var requester by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName ?: FormMemory.read(context, "request", "requester")) }
    var directedTo by remember(existing?.id) { mutableStateOf(existing?.purpose ?: FormMemory.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم")) }
    var details by remember(existing?.id) { mutableStateOf(existing?.details ?: FormMemory.read(context, "request", "details")) }
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    val lastNumber by viewModel.lastDocumentNumber.collectAsState()
    OfficialFormShell(if (existing == null) "ورقة تقديم طلب جديدة" else "تعديل ورقة تقديم طلب") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(directedTo, "المخاطب إليه", { directedTo = it })
        OfficialField(requester, "اسم مقدم الطلب", { requester = it })
        OfficialField(details, "تفاصيل الطلب", { details = it }, 6)
        OfficialField(existing?.notes.orEmpty(), "المرفقات / رقم النموذج", { })
        SaveOfficialButton(if (existing == null) "حفظ ورقة التقديم الرسمية" else "حفظ التعديلات") {
            val value = Document(existing?.id ?: 0, DocumentType.REQUEST, existing?.documentNumber ?: ((lastNumber ?: 0) + 1).toString().padStart(4, '0'), hijri, gregorian, null, null, requester, directedTo, details, existing?.notes, DocumentStatus.SUBMITTED, existing?.attachmentsCount ?: 0, existing?.createdAt ?: System.currentTimeMillis())
            if (existing == null) viewModel.addDocument(value) else viewModel.updateDocument(value)
            FormMemory.remember(context, "request", "requester", requester); FormMemory.remember(context, "request", "directed", directedTo); FormMemory.remember(context, "request", "details", details)
            onNavigateBack()
        }
    }
}
