package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.forms.*

@Composable
fun AppNavigation(viewModel: MasrofViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(viewModel, { navController.navigate("select_type") }, { navController.navigate("print_preview/$it") }, { token ->
                val parts = token.split(":")
                if (parts.size == 2) navController.navigate("edit/${parts[0]}/${parts[1]}")
            }, { navController.navigate("settings") })
        }
        composable("select_type") { DocumentTypeSelectionScreen { navController.navigate("${it.name.lowercase()}_form") } }
        composable("request_form") { RequestFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("order_form") { PaymentOrderFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("receipt_form") { ReceiptFormScreen(viewModel, onNavigateBack = { navController.popBackStack() }) }
        composable("edit/{type}/{id}") { entry ->
            val type = entry.arguments?.getString("type").orEmpty()
            val id = entry.arguments?.getString("id")?.toLongOrNull()
            val document = viewModel.allDocuments.value.firstOrNull { it.id == id }
            when (type) {
                DocumentType.REQUEST.name.lowercase() -> RequestFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                DocumentType.ORDER.name.lowercase() -> PaymentOrderFormScreen(viewModel, { navController.popBackStack() }, existing = document)
                else -> ReceiptFormScreen(viewModel, { navController.popBackStack() }, existing = document)
            }
        }
        composable("settings") { SettingsScreen { navController.popBackStack() } }
        composable("print_preview/{documentIds}") { entry ->
            PrintPreviewScreen(viewModel, entry.arguments?.getString("documentIds") ?: "") { navController.popBackStack() }
        }
    }
}
