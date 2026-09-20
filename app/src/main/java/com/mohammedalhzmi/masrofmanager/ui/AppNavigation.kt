package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammedalhzmi.masrofmanager.ui.forms.*

@Composable
fun AppNavigation(viewModel: MasrofViewModel) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") { DashboardScreen(viewModel, { navController.navigate("select_type") }, { navController.navigate("print_preview/$it") }) }
        composable("select_type") { DocumentTypeSelectionScreen { navController.navigate("${it.name.lowercase()}_form") } }
        composable("request_form") { RequestFormScreen(viewModel) { navController.popBackStack() } }
        composable("order_form") { PaymentOrderFormScreen(viewModel) { navController.popBackStack() } }
        composable("receipt_form") { ReceiptFormScreen(viewModel) { navController.popBackStack() } }
        composable("print_preview/{documentIds}") { backStackEntry ->
            val documentIds = backStackEntry.arguments?.getString("documentIds") ?: ""
            PrintPreviewScreen(viewModel, documentIds) { navController.popBackStack() }
        }
    }
}
