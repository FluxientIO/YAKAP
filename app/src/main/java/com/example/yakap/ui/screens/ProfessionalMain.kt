package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
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
import androidx.navigation.toRoute
import com.example.yakap.navigation.Route
import com.example.yakap.ui.viewmodels.AppointmentViewModel
import com.example.yakap.ui.viewmodels.ChatViewModel
import com.example.yakap.ui.viewmodels.ProfessionalViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessionalMainScreen(
    viewModel: ProfessionalViewModel,
    appointmentViewModel: AppointmentViewModel,
    chatViewModel: ChatViewModel
) {
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
                Text("Professional Menu", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                NavigationDrawerItem(
                    label = { Text("Dashboard") },
                    selected = currentDestination?.hasRoute<Route.ProfessionalDashboard>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.ProfessionalDashboard) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Messages") },
                    selected = currentDestination?.hasRoute<Route.Messages>() == true || currentDestination?.hasRoute<Route.ChatDetail>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.Messages) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.Email, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Schedule") },
                    selected = currentDestination?.hasRoute<Route.ProfessionalAvailability>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.ProfessionalAvailability) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Calendar") },
                    selected = currentDestination?.hasRoute<Route.ProfessionalCalendar>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.ProfessionalCalendar) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Patient List") },
                    selected = currentDestination?.hasRoute<Route.PatientList>() == true || currentDestination?.hasRoute<Route.PatientProfile>() == true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Route.PatientList) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Icons.Default.List, contentDescription = null) }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text("YAKAP Professional") },
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
                startDestination = Route.ProfessionalDashboard,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Route.ProfessionalDashboard> {
                    ProfessionalDashboardScreen(
                        viewModel = viewModel,
                        appointmentViewModel = appointmentViewModel
                    )
                }
                composable<Route.ProfessionalAvailability> {
                    AvailabilityManagementScreen(viewModel = appointmentViewModel)
                }
                composable<Route.ProfessionalCalendar> {
                    ProfessionalCalendarScreen(viewModel = appointmentViewModel)
                }
                composable<Route.PatientList> {
                    PatientListScreen(
                        viewModel = viewModel,
                        onPatientClick = { patientId ->
                            navController.navigate(Route.PatientProfile(patientId))
                        }
                    )
                }
                composable<Route.PatientProfile> { backStackEntry ->
                    val profileRoute: Route.PatientProfile = backStackEntry.toRoute()
                    PatientProfileScreen(
                        patientId = profileRoute.patientId,
                        viewModel = viewModel,
                        onChatClick = { patientId ->
                            // Find or create conversation
                            val existing = chatViewModel.conversations.value.find { 
                                it.participantIds.contains(patientId) && it.participantIds.contains("p1")
                            }
                            if (existing != null) {
                                navController.navigate(Route.ChatDetail(existing.id))
                            } else {
                                chatViewModel.startNewConversation(listOf("p1", patientId)) { id ->
                                    navController.navigate(Route.ChatDetail(id))
                                }
                            }
                        }
                    )
                }
                composable<Route.Messages> {
                    ConversationListScreen(
                        viewModel = chatViewModel,
                        onConversationClick = { id ->
                            navController.navigate(Route.ChatDetail(id))
                        }
                    )
                }
                composable<Route.ChatDetail> { backStackEntry ->
                    val detailRoute: Route.ChatDetail = backStackEntry.toRoute()
                    ChatDetailScreen(
                        conversationId = detailRoute.conversationId,
                        currentUserId = "p1", // Mock professional user ID
                        viewModel = chatViewModel
                    )
                }
            }
        }
    }
}