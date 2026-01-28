# Specification: Admin Dashboard and Analytics

## Context
Administrators ensure the safety and effectiveness of the YAKAP platform. This track implements the tools required for user oversight, professional credential verification, and monitoring platform growth.

## Requirements

### 1. Admin Dashboard Home
*   **Stats Overview:** Display high-level metrics (Total Users, Active Professionals, Pending Verifications).
*   **System Health:** Simple indicators for platform uptime or database status.

### 2. User Management
*   **User List:** View all users registered on the platform.
*   **Search/Filter:** Find users by role or name.
*   **Detail View:** View basic account details and status (Verified/Unverified).

### 3. Professional Verification Workflow
*   **Queue:** A list of professionals who have signed up but are not yet verified.
*   **Verification Action:** Ability to review a professional's license number and Approve or Reject their account.

### 4. Platform Analytics (Basic)
*   **Engagement:** Simple counts of mood entries or sessions booked over the last 30 days.
*   **Visuals:** Basic progress bars or summarized data tiles.

## Design
*   **Style:** Modern and Clean (as per guidelines).
*   **Layout:** Side drawer navigation with specialized "Admin" sections.
*   **Feedback:** Toast or SnackBar confirmations for approval/rejection actions.

## Acceptance Criteria
*   [ ] Admin can see a summary of platform activity on the dashboard.
*   [ ] Admin can view a list of all registered users.
*   [ ] Admin can approve or reject pending professional applications.
*   [ ] Successful login as Admin redirects to the Admin Dashboard.
*   [ ] UI provides clear feedback for administrative actions.
