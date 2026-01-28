package com.example.yakap.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.room.Room
import com.example.yakap.data.PreferenceManager
import com.example.yakap.data.provideSupabaseClient
import com.example.yakap.data.local.AppDatabase
import com.example.yakap.data.repository.*
import com.example.yakap.ui.models.UserRole
import com.example.yakap.ui.screens.*
import com.example.yakap.ui.viewmodels.*
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    navController: NavHostController,
    preferenceManager: PreferenceManager
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    val supabaseClient = remember { provideSupabaseClient() }
    val authRepository = remember { SupabaseAuthRepository(supabaseClient) }
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(authRepository))
    
    // Set up Database and Repositories
    val db = remember { 
        Room.databaseBuilder(context, AppDatabase::class.java, "yakap-db").build() 
    }
    
    val moodRepository = remember { LocalMoodRepository(db.moodDao()) }
    val moodViewModel: MoodViewModel = viewModel(factory = MoodViewModel.Factory(moodRepository))
    
    val professionalRepository = remember { LocalProfessionalRepository(db.professionalDao()) }
    val professionalViewModel: ProfessionalViewModel = viewModel(
        factory = ProfessionalViewModel.Factory(professionalRepository, moodRepository)
    )
    
    val appointmentRepository = remember { LocalAppointmentRepository(db.appointmentDao()) }
    val appointmentViewModel: AppointmentViewModel = viewModel(
        factory = AppointmentViewModel.Factory(appointmentRepository)
    )
    
    val chatRepository = remember { LocalChatRepository(db.chatDao()) }
    val chatViewModel: ChatViewModel = viewModel(
        factory = ChatViewModel.Factory(chatRepository)
    )

    val assessmentViewModel: AssessmentViewModel = viewModel(
        factory = AssessmentViewModel.Factory(db.assessmentDao())
    )

    val adminRepository = remember { LocalAdminRepository(db.adminDao(), db.moodDao(), db.appointmentDao()) }
    val adminViewModel: AdminViewModel = viewModel(
        factory = AdminViewModel.Factory(adminRepository)
    )

    NavHost(
        navController = navController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            SplashScreen(
                preferenceManager = preferenceManager,
                onSplashFinished = { isCompleted ->
                    val destination = if (isCompleted) Route.RoleSelection else Route.Onboarding
                    navController.navigate(destination) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Route.Onboarding> {
            OnboardingScreen(
                onFinished = {
                    scope.launch {
                        preferenceManager.setOnboardingCompleted(true)
                        navController.navigate(Route.RoleSelection) {
                            popUpTo(Route.Onboarding) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable<Route.RoleSelection> {
            RoleSelectionScreen(
                onRoleSelected = { role ->
                    navController.navigate(Route.Login(role))
                }
            )
        }
        composable<Route.Login> { backStackEntry ->
            val loginRoute: Route.Login = backStackEntry.toRoute()
            LoginScreen(
                role = loginRoute.role,
                uiState = authViewModel.uiState.value,
                onLoginSubmitted = { email, password ->
                    authViewModel.login(email, password)
                },
                onSignUpClick = {
                    navController.navigate(Route.SignUp(loginRoute.role))
                }
            )
            
            if (authViewModel.uiState.value.isLoggedIn) {
                LaunchedEffect(Unit) {
                    when (loginRoute.role) {
                        UserRole.PATIENT -> {
                            navController.navigate(Route.PatientMain) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        UserRole.PROFESSIONAL -> {
                            navController.navigate(Route.ProfessionalMain) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        UserRole.ADMIN -> {
                            navController.navigate(Route.AdminMain) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
        composable<Route.SignUp> { backStackEntry ->
            val signUpRoute: Route.SignUp = backStackEntry.toRoute()
            SignUpScreen(
                role = signUpRoute.role,
                uiState = authViewModel.uiState.value,
                onSignUpSubmitted = { name, email, password, license ->
                    authViewModel.signUp(name, email, password, signUpRoute.role, license)
                },
                onLoginClick = {
                    navController.navigate(Route.Login(signUpRoute.role))
                }
            )

            if (authViewModel.uiState.value.isLoggedIn) {
                LaunchedEffect(Unit) {
                    when (signUpRoute.role) {
                        UserRole.PATIENT -> {
                            navController.navigate(Route.PatientMain) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        UserRole.PROFESSIONAL -> {
                            navController.navigate(Route.ProfessionalMain) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                        UserRole.ADMIN -> {
                            navController.navigate(Route.AdminMain) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
        composable<Route.PatientMain> {
            PatientMainScreen(
                moodViewModel = moodViewModel,
                appointmentViewModel = appointmentViewModel,
                chatViewModel = chatViewModel,
                assessmentViewModel = assessmentViewModel
            )
        }
        composable<Route.ProfessionalMain> {
            ProfessionalMainScreen(
                viewModel = professionalViewModel,
                appointmentViewModel = appointmentViewModel,
                chatViewModel = chatViewModel
            )
        }
        composable<Route.AdminMain> {
            AdminMainScreen(viewModel = adminViewModel)
        }
    }
}