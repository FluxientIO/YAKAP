# Specification: Supabase Integration (Auth & Backend)

## Context
Transitioning YAKAP from a local-first prototype to a production-ready application requires a reliable, scalable cloud backend. Supabase provides an open-source alternative to Firebase, offering Realtime Database (Postgres), Authentication, and Storage.

## Requirements

### 1. Supabase Client Setup
*   **Infrastructure:** Add Supabase Kotlin SDK dependencies (GoTrue, PostgREST).
*   **Configuration:** Securely store Supabase URL and Anon Key using build configuration or a secrets file.
*   **Initialization:** Initialize a singleton Supabase client in the application layer.

### 2. Real Authentication (Supabase Auth)
*   **Flows:** Implement real Sign-Up, Login, and Sign-Out using Supabase GoTrue.
*   **Role Management:** Store and retrieve the user's role (Patient, Professional, Admin) within Supabase `profiles` or custom claims.
*   **Session Persistence:** Handle session auto-refresh and secure storage of the JWT.

### 3. Database Migration (Supabase Postgres)
*   **Schema Sync:** Mirrors current Room entities (`user_accounts`, `mood_entries`, `appointments`, `messages`) into Supabase Postgres tables.
*   **Row Level Security (RLS):** Configure RLS policies to ensure users can only access their own sensitive mental health data.
*   **Real-time Chat:** Replace Room-only chat with Supabase Realtime for instant message delivery.

### 4. Repository Overhaul
*   **AuthRepository:** Implement `SupabaseAuthRepository` to replace `MockAuthRepository`.
*   **Data Repositories:** Extend existing repositories to sync local Room data with Supabase (Offline-first approach).

## Design
*   **Security:** All API calls must use the user's JWT.
*   **UX:** Maintain existing UI flows but handle network delays/errors with the newly polished loading states.

## Acceptance Criteria
*   [ ] User can sign up and login with a real email/password stored in Supabase.
*   [ ] User session persists across app restarts.
*   [ ] Mood entries and chat messages are synced to the Supabase cloud.
*   [ ] Application correctly handles offline states and syncs data when reconnected.
