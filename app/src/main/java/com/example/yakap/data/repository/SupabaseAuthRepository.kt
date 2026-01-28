package com.example.yakap.data.repository

import com.example.yakap.ui.models.UserRole
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class SupabaseAuthRepository(private val supabase: SupabaseClient) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthResult {
        return try {
            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            AuthResult.Success("Logged in via Supabase")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Authentication failed")
        }
    }

    override suspend fun signUp(name: String, email: String, password: String, role: UserRole, license: String?): AuthResult {
        return try {
            supabase.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            AuthResult.Success("Verification email sent")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Registration failed")
        }
    }

    override suspend fun logout() {
        supabase.auth.signOut()
    }

    override fun getCurrentUser(): AuthUser? {
        val user = supabase.auth.currentUserOrNull()
        return user?.let {
            AuthUser(
                id = it.id,
                name = it.userMetadata?.get("name")?.toString() ?: "User",
                email = it.email ?: "",
                role = UserRole.PATIENT 
            )
        }
    }
}
