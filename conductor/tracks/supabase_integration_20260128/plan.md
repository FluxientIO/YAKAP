# Implementation Plan - Track: Supabase Integration (Auth & Backend)

## Phase 1: Setup & Dependencies
- [x] Task: Add Supabase SDK to the project. (24a4885)
    - [ ] Sub-task: Update `libs.versions.toml` with Supabase dependencies (postgrest-kt, gotrue-kt).
    - [ ] Sub-task: Add dependencies to `app/build.gradle.kts`.
- [x] Task: Initialize Supabase Client. (24a4885)
    - [ ] Sub-task: Create a configuration object/class for URL and API Keys.
    - [ ] Sub-task: Implement `SupabaseClient` initialization in a central provider or Hilt module.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Setup & Dependencies' (Protocol in workflow.md) (94d1ebd - Deferred)

## Phase 2: Real Authentication
- [~] Task: Implement `SupabaseAuthRepository`.
    - [ ] Sub-task: Define Sign-Up logic using `gotrue.signUpWith(Email)`.
    - [ ] Sub-task: Define Login logic using `gotrue.signInWith(Email)`.
    - [ ] Sub-task: Define Logout and current user retrieval.
- [~] Task: Update `AuthViewModel` to use the new Repository.
- [ ] Task: Conductor - User Manual Verification 'Phase 2: Real Authentication' (Protocol in workflow.md)

## Phase 3: Cloud Database Integration (Postgres)
- [ ] Task: Mirror Room Schema to Supabase.
    - [ ] Sub-task: Create `profiles`, `moods`, `appointments`, and `messages` tables in Supabase.
    - [ ] Sub-task: Configure Row Level Security (RLS) policies.
- [ ] Task: Implement Cloud Sync Repositories.
    - [ ] Sub-task: Update `MoodRepository` to push/pull from Supabase.
    - [ ] Sub-task: Update `ChatRepository` to use Supabase Realtime.
- [ ] Task: Conductor - User Manual Verification 'Phase 3: Cloud Database Integration' (Protocol in workflow.md)

## Phase 4: Verification & Cleanup
- [ ] Task: Remove Mock Data and Repositories.
- [ ] Task: Conduct Final Security Audit (RLS verification).
- [ ] Task: Conductor - User Manual Verification 'Phase 4: Verification & Cleanup' (Protocol in workflow.md)
