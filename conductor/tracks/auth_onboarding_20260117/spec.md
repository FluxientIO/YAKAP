# Specification: Core Authentication and Onboarding Flow

## Context
This track establishes the entry point for the YAKAP application. It covers the initial user experience from app launch (splash screen) through onboarding education and into the authenticated state. A secure and user-friendly authentication system is crucial for managing the three distinct user roles: Patients, Professionals, and Admins.

## Requirements

### 1. Splash Screen
*   **UI:** Display the YAKAP logo and branding elements.
*   **Behavior:** Show for a minimum of 2 seconds or until initialization is complete, then transition to Onboarding.

### 2. Onboarding Flow
*   **Content:** A series of 3-4 screens explaining YAKAP's value proposition:
    1.  Welcome & Mission (Empathetic tone)
    2.  Privacy & Security Assurance
    3.  Key Features Overview (Mood tracking, Professional help)
*   **Navigation:** "Next", "Skip", and "Get Started" buttons.
*   **Persistence:** Show only on the first launch.

### 3. Role Selection
*   **UI:** A screen allowing the user to identify as a "Patient/User", "Mental Health Professional", or "Admin".
*   **Behavior:** Selection dictates the subsequent registration/login flow.

### 4. Authentication (Sign Up & Login)
*   **Fields:**
    *   **Common:** Email, Password (secure input), Full Name.
    *   **Professional:** License Number (verification step).
*   **Validation:** Email format, password strength (min 8 chars, mixed case), required fields.
*   **Feedback:** Clear error messages for failed attempts; loading indicators during API calls.
*   **State Management:** Maintain session state (logged in vs. logged out) securely.

## Design
*   **Theme:** Use the "Calm & Minimalist" theme with "Warm & Organic" accents.
*   **Typography:** Clear, readable fonts.
*   **Components:** Material 3 buttons, text fields, and navigation components.

## Acceptance Criteria
*   [ ] App launches to Splash Screen.
*   [ ] Onboarding screens are displayed on first launch and can be navigated or skipped.
*   [ ] User can select a role.
*   [ ] User can sign up with valid credentials for each role.
*   [ ] User can log in with valid credentials.
*   [ ] Invalid inputs show appropriate error messages.
*   [ ] Professional sign-up includes a license number field.
*   [ ] Successful authentication redirects to the appropriate dashboard placeholder.
