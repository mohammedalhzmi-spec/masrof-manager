package com.mohammedalhzmi.masrofmanager.ui

import android.net.Uri
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
                viewModel = viewModel,
                onAddDocument = { navController.navigate("select_type") },
                onCreateBook = { navController.navigate("document_book") },
                onBranchAssets = { navController.navigate("branch_assets") },
                onOfficeEditor = { navController.navigate("office_editor") },
                onPrint = { navController.navigate("print_preview/$it") },
                onEdit = { token ->
                    val parts = token.split(":")
                    if (parts.size == 2 && parts[0] == "office") navController.navigate("office_editor/${parts[1]}")
                    else if (parts.size == 2) navController.navigate("edit/${parts[0]}/${parts[1]}")
                },
                onSettings = { navController.navigate("settings") }
            )
        }
        composable("document_book") {
            DocumentBookScreen(viewModel, onNavigateBack = { navController.popBackStack() }) { tag ->
                navController.navigate("book_editor/${Uri.encode(tag)}")
            }
        }
        composable("book_editor/{bookTag}") { entry ->
            val tag = Uri.decode(entry.arguments?.getString("bookTag").orEmpty())
            BookEditorScreen(
                viewModel = viewModel,
                bookTag = tag,
                onPreview = { ids -> navController.navigate("print_preview/$ids") },
                onOpenCanvas = { type -> navController.navigate("canvas/${type.name}") },
                onOpenOffice = { id -> navController.navigate("office_editor/$id") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("branch_assets") {
            BranchAssetsScreen(viewModel, onNavigateBack = { navController.popBackStack() }) { kind ->
                navController.navigate("branch_assets_inventory/$kind")
            }
        }
        composable("branch_assets_inventory/{kind}") { entry ->
            BranchAssetsAnnualInventoryScreen(
                viewModel,
                entry.arguments?.getString("kind").orEmpty(),
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("office_editor") {
            OfficeEditorScreen(viewModel, onBack = { navController.popBackStack() })
        }
        composable("office_editor/{documentId}") { entry ->
            OfficeEditorScreen(viewModel, entry.arguments?.getString("documentId")?.toLongOrNull(), onBack = { navController.popBackStack() })
        }
        composable("select_type") {
            val allowed = userSelectableDocumentTypes().filter { RolePreferences.canCreate(context, it) }.toSet()
            DocumentTypeSelectionScreen(allowedTypes = allowed) { type -> navController.navigate(documentFormRoute(type)) }
        }
        composable("editor/{type}") { entry ->
            val type = runCatching { DocumentType.valueOf(entry.arguments?.getString("type").orEmpty()) }.getOrNull()
            if (type == null || type == DocumentType.EXPENSE_STATEMENT || type == DocumentType.EXPENSE_REPORT || !RolePreferences.canCreate(context, type)) {
                navController.popBackStack()
            } else {
                UniversalDocumentEditorScreen(viewModel, type, onBack = { navController.popBackStack() }) {
                    navController.navigate("canvas/${type.name}")
                }
            }
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
            if (type == null || document == null || !RolePreferences.can(context, AppPermission.EDIT)) {
                navController.popBackStack()
                return@composable
            }
            when (type) {
                DocumentType.EXPENSE_STATEMENT, DocumentType.EXPENSE_REPORT -> ExpenseReportFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                else -> UniversalDocumentEditorScreen(viewModel, type, existing = document, onBack = { navController.popBackStack() }) {
                    navController.navigate("canvas/${type.name}")
                }
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
    DocumentType.EXPENSE_STATEMENT, DocumentType.EXPENSE_REPORT -> "expense_report_form"
    else -> "editor/${type.name}"
}
