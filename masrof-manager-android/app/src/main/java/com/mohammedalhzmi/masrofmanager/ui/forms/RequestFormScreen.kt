package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.FormMemory
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering

@Composable
fun RequestFormScreen(viewModel: MasrofViewModel, onNavigateBack: () -> Unit, existing: Document? = null) {
    val context = LocalContext.current
    var requester by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName ?: FormMemory.read(context, "request", "requester")) }
    var directedTo by remember(existing?.id) { mutableStateOf(existing?.purpose ?: FormMemory.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم")) }
    var details by remember(existing?.id) { mutableStateOf(existing?.details ?: FormMemory.read(context, "request", "details")) }
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    var tags by remember(existing?.id) { mutableStateOf(existing?.tags.orEmpty()) }
    val automaticNumber = DocumentNumbering.next(context, DocumentType.REQUEST).toString().padStart(4, '0')
    OfficialFormShell(if (existing == null) "ورقة تقديم طلب جديدة" else "تعديل ورقة تقديم طلب") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(if (existing == null) automaticNumber else existing.documentNumber, "رقم المستند (تلقائي)", { })
        OfficialField(directedTo, "المخاطب إليه", { directedTo = it })
        OfficialField(requester, "اسم مقدم الطلب", { requester = it })
        OfficialField(details, "تفاصيل الطلب", { details = it }, 6)
        OfficialField(existing?.notes.orEmpty(), "المرفقات / رقم النموذج", { })
        OfficialTags(tags, { tags = it })
        SaveOfficialButton(if (existing == null) "حفظ ورقة التقديم الرسمية" else "حفظ التعديلات") {
            val value = Document(id = existing?.id ?: 0, type = DocumentType.REQUEST, documentNumber = existing?.documentNumber ?: automaticNumber, dateHijri = hijri, dateGregorian = gregorian, amount = null, amountWords = null, beneficiaryName = requester, purpose = directedTo, details = details, notes = existing?.notes, status = DocumentStatus.SUBMITTED, attachmentsCount = existing?.attachmentsCount ?: 0, createdAt = existing?.createdAt ?: System.currentTimeMillis(), isArchived = existing?.isArchived ?: false, archivedAt = existing?.archivedAt, updatedAt = System.currentTimeMillis(), tags = tags.split(",").map(String::trim).filter(String::isNotBlank).distinct().joinToString(","))
            if (existing == null) viewModel.addDocument(value) else viewModel.updateDocument(value)
            if (existing == null) DocumentNumbering.consume(context, DocumentType.REQUEST)
            FormMemory.remember(context, "request", "requester", requester); FormMemory.remember(context, "request", "directed", directedTo); FormMemory.remember(context, "request", "details", details)
            onNavigateBack()
        }
    }
}
