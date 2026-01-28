package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.yakap.navigation.Route
import com.example.yakap.ui.viewmodels.AdminViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMainScreen(viewModel: AdminViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            
            ModalDrawerSheet {
                Text("Administrator Portal", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                NavigationDrawerItem(
                    label = { Text("Dashboard") },
                    selected = currentDestination?.hasRoute<Route.AdminDashboard>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.AdminDashboard) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("User Management") },
                    selected = currentDestination?.hasRoute<Route.UserManagement>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.UserManagement) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Verifications") },
                    selected = currentDestination?.hasRoute<Route.VerificationQueue>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.VerificationQueue) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text("YAKAP Admin") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.AdminDashboard,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Route.AdminDashboard> {
                    AdminDashboardScreen(viewModel = viewModel)
                }
                composable<Route.UserManagement> {
                    UserManagementScreen(viewModel = viewModel)
                }
                composable<Route.VerificationQueue> {
                    VerificationQueueScreen(viewModel = viewModel)
                }
            }
        }
    }
}
