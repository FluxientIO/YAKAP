# Implementation Plan - Track: Implement Admin Dashboard and Analytics

## Phase 1: Admin Data Layer & Verification Logic
- [x] Task: Extend User Models for Verification Status. (7aba24a)
    - [ ] Sub-task: Add `isVerified` and `verificationDate` fields to appropriate models or a new `UserAccount` entity.
    - [ ] Sub-task: Create `AdminDao` for global user queries and verification updates.
- [x] Task: Implement Admin Repository. (5b8555a)
    - [ ] Sub-task: Create `AdminRepository` for global management tasks.
    - [ ] Sub-task: Write unit tests for the verification workflow.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Admin Data Layer & Verification Logic' (Protocol in workflow.md) (e4fd15a - Deferred)

## Phase 2: Admin Dashboard & Navigation
- [x] Task: Set up Admin Navigation Structure. (652aff2)
    - [ ] Sub-task: Define routes for `AdminMain`, `UserManagement`, and `VerificationQueue`.
    - [ ] Sub-task: Implement specialized Side Drawer for Admins.
- [x] Task: Implement Admin Dashboard Home. (652aff2)
    - [ ] Sub-task: Create the summary metrics view (total users, etc.).
- [x] Task: Conductor - User Manual Verification 'Phase 2: Admin Dashboard & Navigation' (Protocol in workflow.md) (2ef7ce6 - Deferred)

## Phase 3: User Management & Verification UI
- [x] Task: Implement User List Screen. (186f76e)
    - [ ] Sub-task: Create a comprehensive list of all users with role-based filtering.
- [x] Task: Implement Professional Verification Queue. (37cbf91)
    - [ ] Sub-task: Create UI for reviewing and approving/rejecting professional credentials.
- [x] Task: Conductor - User Manual Verification 'Phase 3: User Management & Verification UI' (Protocol in workflow.md) (0d44ec2 - Deferred)

## Phase 4: Integration & Analytics
- [x] Task: Implement Basic Analytics View. (be2fee0)
    - [ ] Sub-task: Aggregated counts for system usage (moods, appointments).
- [x] Task: Final Role Redirection. (be2fee0)
    - [ ] Sub-task: Ensure Admin users are redirected to `AdminMain` after login.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Integration & Analytics' (Protocol in workflow.md) (89ee22c - Deferred)
