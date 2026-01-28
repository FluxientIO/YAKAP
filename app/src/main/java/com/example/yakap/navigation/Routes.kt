package com.example.yakap.navigation

import com.example.yakap.ui.models.UserRole
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object RoleSelection : Route

    @Serializable
    data class Login(val role: UserRole) : Route

    @Serializable
    data class SignUp(val role: UserRole) : Route

    @Serializable
    data object PatientMain : Route

    @Serializable
    data object PatientDashboard : Route

    @Serializable
    data object PatientTracker : Route

    @Serializable
    data object WellnessHub : Route

    @Serializable
    data object Breathing : Route

    @Serializable
    data object Assessment : Route

    @Serializable
    data object ProfessionalDirectory : Route

    @Serializable
    data class Booking(val professionalId: String) : Route

    @Serializable
    data object Messages : Route

    @Serializable
    data class ChatDetail(val conversationId: String) : Route

    @Serializable
    data object ProfessionalMain : Route

    @Serializable
    data object ProfessionalDashboard : Route

    @Serializable
    data object PatientList : Route

    @Serializable
    data object ProfessionalAvailability : Route

    @Serializable
    data object ProfessionalCalendar : Route

    @Serializable
    data class PatientProfile(val patientId: String) : Route

    @Serializable
    data object AdminMain : Route

    @Serializable
    data object AdminDashboard : Route

    @Serializable
    data object UserManagement : Route

    @Serializable
    data object VerificationQueue : Route
}
