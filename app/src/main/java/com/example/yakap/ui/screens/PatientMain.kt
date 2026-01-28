package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.yakap.navigation.Route
import com.example.yakap.ui.viewmodels.AppointmentViewModel
import com.example.yakap.ui.viewmodels.AssessmentViewModel
import com.example.yakap.ui.viewmodels.ChatViewModel
import com.example.yakap.ui.viewmodels.MoodViewModel
import com.example.yakap.ui.screens.*
import kotlinx.coroutines.launch

@Composable
fun PatientMainScreen(
    moodViewModel: MoodViewModel,
    appointmentViewModel: AppointmentViewModel,
    chatViewModel: ChatViewModel,
    assessmentViewModel: AssessmentViewModel
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Route.PatientDashboard>() } == true,
                    onClick = {
                        navController.navigate(Route.PatientDashboard) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Wellness") },
                    label = { Text("Wellness") },
                    selected = currentDestination?.hierarchy?.any { 
                        it.hasRoute<Route.WellnessHub>() || it.hasRoute<Route.Breathing>() || it.hasRoute<Route.Assessment>()
                    } == true,
                    onClick = {
                        navController.navigate(Route.WellnessHub) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Email, contentDescription = "Messages") },
                    label = { Text("Messages") },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Route.Messages>() } == true,
                    onClick = {
                        navController.navigate(Route.Messages) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Find Help") },
                    label = { Text("Find Help") },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Route.ProfessionalDirectory>() } == true,
                    onClick = {
                        navController.navigate(Route.ProfessionalDirectory) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Tracker") },
                    label = { Text("Tracker") },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Route.PatientTracker>() } == true,
                    onClick = {
                        navController.navigate(Route.PatientTracker) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.PatientDashboard,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.PatientDashboard> {
                DashboardScreen(
                    viewModel = moodViewModel,
                    appointmentViewModel = appointmentViewModel
                )
            }
            composable<Route.PatientTracker> {
                MoodTrackerScreen(
                    viewModel = moodViewModel,
                    onSaved = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Mood saved successfully!")
                        }
                        navController.navigate(Route.PatientDashboard) {
                            popUpTo(Route.PatientDashboard) { inclusive = true }
                        }
                    }
                )
            }
            composable<Route.WellnessHub> {
                WellnessHubScreen(
                    assessmentViewModel = assessmentViewModel,
                    onBreathingClick = { navController.navigate(Route.Breathing) },
                    onAssessmentClick = { navController.navigate(Route.Assessment) }
                )
            }
            composable<Route.Breathing> {
                BreathingExerciseScreen()
            }
            composable<Route.Assessment> {
                AssessmentScreen(
                    viewModel = assessmentViewModel,
                    onFinished = { navController.popBackStack() }
                )
            }
            composable<Route.ProfessionalDirectory> {
                ProfessionalDirectoryScreen(
                    onProfessionalClick = { proId ->
                        navController.navigate(Route.Booking(proId))
                    },
                    onChatClick = { proId ->
                        // Find or create conversation
                        val existing = chatViewModel.conversations.value.find { 
                            it.participantIds.contains(proId) && it.participantIds.contains("u1")
                        }
                        if (existing != null) {
                            navController.navigate(Route.ChatDetail(existing.id))
                        } else {
                            chatViewModel.startNewConversation(listOf("u1", proId)) { id ->
                                navController.navigate(Route.ChatDetail(id))
                            }
                        }
                    }
                )
            }
            composable<Route.Booking> { backStackEntry ->
                val bookingRoute: Route.Booking = backStackEntry.toRoute()
                BookingScreen(
                    professionalId = bookingRoute.professionalId,
                    viewModel = appointmentViewModel,
                    onBookingConfirmed = {
                        navController.navigate(Route.PatientDashboard) {
                            popUpTo(Route.PatientDashboard) { inclusive = true }
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
                    currentUserId = "u1", // Mock current user ID
                    viewModel = chatViewModel
                )
            }
        }
    }
}
