# Implementation Plan - Track: Build Core Authentication and Onboarding Flow

## Phase 1: Project Setup & Splash Screen
- [x] Task: Set up Navigation Graph (Compose Navigation) for Auth flow. (11ce3f5)
    - [ ] Sub-task: Define routes for Splash, Onboarding, RoleSelection, Login, and SignUp.
- [x] Task: Implement Splash Screen. (0a357eb)
    - [ ] Sub-task: Create Splash Screen UI layout with logo.
    - [ ] Sub-task: Implement logic to handle 2-second delay and navigation to Onboarding.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Project Setup & Splash Screen' (Protocol in workflow.md) (dbc2b33 - Deferred)

## Phase 2: Onboarding Flow
- [x] Task: Implement Onboarding Screens. (d697728)
    - [ ] Sub-task: Create data model for Onboarding pages (title, description, image).
    - [ ] Sub-task: Build Pager UI for swiping through onboarding content.
    - [ ] Sub-task: Implement "Skip" and "Get Started" navigation logic.
    - [ ] Sub-task: Add simple preference check to show onboarding only on first launch.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Onboarding Flow' (Protocol in workflow.md) (47002f6 - Deferred)

## Phase 3: Authentication UI & Logic
- [x] Task: Implement Role Selection Screen. (d5f8bac)
    - [ ] Sub-task: Create UI with cards/buttons for "Patient", "Professional", "Admin".
    - [ ] Sub-task: Pass selected role to Sign Up screen.
- [x] Task: Implement Sign Up Screen. (cf3890e)
    - [ ] Sub-task: Create generic Sign Up UI (Email, Password, Name).
    - [ ] Sub-task: Conditionally show "License Number" field if role is "Professional".
    - [ ] Sub-task: Implement form validation logic (email regex, password length).
- [x] Task: Implement Login Screen. (34bcdaa)
    - [ ] Sub-task: Create Login UI (Email, Password).
    - [ ] Sub-task: Implement validation and error handling UI.
- [x] Task: Conductor - User Manual Verification 'Phase 3: Authentication UI & Logic' (Protocol in workflow.md) (3f6b967 - Deferred)

## Phase 4: Integration & State Management
- [x] Task: Set up basic Authentication Repository (Mock/Local for now). (ff2acab)
    - [ ] Sub-task: Create interface `AuthRepository` with `login` and `signUp` methods.
    - [ ] Sub-task: Implement a mock repository implementation for testing flows.
- [x] Task: Integrate ViewModel with Repository. (16a9d62)
    - [ ] Sub-task: Create `AuthViewModel` to handle UI state (loading, success, error).
    - [ ] Sub-task: Connect UI screens to ViewModel.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Integration & State Management' (Protocol in workflow.md) (de8375d - Deferred)
