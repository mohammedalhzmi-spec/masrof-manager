package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.forms.*
import com.mohammedalhzmi.masrofmanager.util.RolePreferences
import com.mohammedalhzmi.masrofmanager.util.AppPermission

@Composable
fun AppNavigation(viewModel: MasrofViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(viewModel, { navController.navigate("select_type") }, { navController.navigate("document_book") }, { navController.navigate("print_preview/$it") }, { token ->
                val parts = token.split(":")
                if (parts.size == 2) navController.navigate("edit/${parts[0]}/${parts[1]}")
            }, { navController.navigate("settings") })
        }
        composable("document_book") { DocumentBookScreen(viewModel) { navController.popBackStack() } }
        composable("select_type") { DocumentTypeSelectionScreen(allowedTypes = DocumentType.values().filter { RolePreferences.canCreate(context, it) }.toSet()) { navController.navigate("${it.name.lowercase()}_form") } }
        composable("request_form") { RequestFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("order_form") { PaymentOrderFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("receipt_form") { ReceiptFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("edit/{type}/{id}") { entry ->
            val type = entry.arguments?.getString("type").orEmpty()
            val id = entry.arguments?.getString("id")?.toLongOrNull()
            val document = viewModel.allDocuments.value.firstOrNull { it.id == id }
            if (!RolePreferences.can(context, AppPermission.EDIT)) { navController.popBackStack(); return@composable }
            when (type) {
                DocumentType.REQUEST.name.lowercase() -> RequestFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                DocumentType.ORDER.name.lowercase() -> PaymentOrderFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                else -> ReceiptFormScreen(viewModel, { navController.popBackStack() }, existing = document)
            }
        }
        composable("settings") { SettingsScreen({ navController.popBackStack() }, { navController.navigate("users") }, { navController.navigate("updates") }) }
        composable("users") { if (RolePreferences.can(context, AppPermission.SETTINGS)) UserManagementScreen(viewModel) { navController.popBackStack() } else navController.popBackStack() }
        composable("updates") { UpdateCenterScreen { navController.popBackStack() } }
        composable("print_preview/{documentIds}") { entry ->
            PrintPreviewScreen(viewModel, entry.arguments?.getString("documentIds") ?: "", onOpenCanvas = { type -> navController.navigate("canvas/${type.name}") }) { navController.popBackStack() }
        }
        composable("canvas/{type}") { entry -> CanvasEditorScreen(viewModel, runCatching { DocumentType.valueOf(entry.arguments?.getString("type") ?: "ORDER") }.getOrDefault(DocumentType.ORDER)) { navController.popBackStack() } }
    }
}
