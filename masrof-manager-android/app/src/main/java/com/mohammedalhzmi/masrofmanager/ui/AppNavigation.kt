package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.userSelectableDocumentTypes
import com.mohammedalhzmi.masrofmanager.ui.forms.*
import com.mohammedalhzmi.masrofmanager.util.AppPermission
import com.mohammedalhzmi.masrofmanager.util.RolePreferences

@Composable
fun AppNavigation(viewModel: MasrofViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                viewModel,
                { navController.navigate("select_type") },
                { navController.navigate("document_book") },
                { navController.navigate("print_preview/$it") },
                { token ->
                    val parts = token.split(":")
                    if (parts.size == 2) navController.navigate("edit/${parts[0]}/${parts[1]}")
                },
                { navController.navigate("settings") }
            )
        }
        composable("document_book") { DocumentBookScreen(viewModel) { navController.popBackStack() } }
        composable("select_type") {
            val allowed = userSelectableDocumentTypes().filter { RolePreferences.canCreate(context, it) }.toSet()
            DocumentTypeSelectionScreen(allowedTypes = allowed) { type -> navController.navigate(documentFormRoute(type)) }
        }
        composable("request_form") { RequestFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("order_form") { PaymentOrderFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("receipt_form") { ReceiptFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("violation_report_form") { ViolationReportFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("expense_report_form") { ExpenseReportFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("generic_form/{type}") { entry ->
            val type = runCatching { DocumentType.valueOf(entry.arguments?.getString("type").orEmpty()) }.getOrNull()
            if (type == null || type in setOf(DocumentType.REQUEST, DocumentType.ORDER, DocumentType.RECEIPT, DocumentType.VIOLATION_REPORT, DocumentType.EXPENSE_STATEMENT, DocumentType.EXPENSE_REPORT)) {
                navController.popBackStack()
            } else {
                GenericDocumentFormScreen(viewModel, type, onNavigateBack = { navController.popBackStack() })
            }
        }
        composable("edit/{type}/{id}") { entry ->
            val routeType = entry.arguments?.getString("type").orEmpty()
            val id = entry.arguments?.getString("id")?.toLongOrNull()
            val document = viewModel.allDocuments.value.firstOrNull { it.id == id }
            val type = document?.type ?: runCatching { DocumentType.valueOf(routeType.uppercase()) }.getOrNull()
            if (type == null || !RolePreferences.can(context, AppPermission.EDIT)) {
                navController.popBackStack()
                return@composable
            }
            when (type) {
                DocumentType.REQUEST -> RequestFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                DocumentType.ORDER -> PaymentOrderFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                DocumentType.RECEIPT -> ReceiptFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                DocumentType.VIOLATION_REPORT -> ViolationReportFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                DocumentType.EXPENSE_STATEMENT, DocumentType.EXPENSE_REPORT -> ExpenseReportFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                else -> GenericDocumentFormScreen(viewModel, type, { navController.popBackStack() }, existing = document)
            }
        }
        composable("settings") { SettingsScreen({ navController.popBackStack() }, { navController.navigate("users") }, { navController.navigate("updates") }, { navController.navigate("cloud_sync") }) }
        composable("cloud_sync") { CloudSyncScreen(viewModel) { navController.popBackStack() } }
        composable("users") { if (RolePreferences.can(context, AppPermission.SETTINGS)) UserManagementScreen(viewModel) { navController.popBackStack() } else navController.popBackStack() }
        composable("updates") { UpdateCenterScreen { navController.popBackStack() } }
        composable("print_preview/{documentIds}") { entry ->
            PrintPreviewScreen(viewModel, entry.arguments?.getString("documentIds") ?: "", onOpenCanvas = { type -> navController.navigate("canvas/${type.name}") }) { navController.popBackStack() }
        }
        composable("canvas/{type}") { entry ->
            CanvasEditorScreen(viewModel, runCatching { DocumentType.valueOf(entry.arguments?.getString("type") ?: "ORDER") }.getOrDefault(DocumentType.ORDER)) { navController.popBackStack() }
        }
    }
}

private fun documentFormRoute(type: DocumentType): String = when (type) {
    DocumentType.REQUEST -> "request_form"
    DocumentType.ORDER -> "order_form"
    DocumentType.RECEIPT -> "receipt_form"
    DocumentType.VIOLATION_REPORT -> "violation_report_form"
    DocumentType.EXPENSE_STATEMENT, DocumentType.EXPENSE_REPORT -> "expense_report_form"
    else -> "generic_form/${type.name}"
}
