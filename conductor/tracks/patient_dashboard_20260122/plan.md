# Implementation Plan - Track: Implement Patient Dashboard and Mood Tracking

## Phase 1: Data Layer & Domain Model
- [x] Task: Define Mood Domain Model and Repository Interface. (400a41b)
    - [ ] Sub-task: Create `MoodEntry` data class and `MoodType` enum.
    - [ ] Sub-task: Create `MoodRepository` interface with `saveMood` and `getMoodHistory` methods.
- [x] Task: Implement Local Mood Repository. (b6c0147)
    - [ ] Sub-task: Set up Room Database or simplified local storage for moods.
    - [ ] Sub-task: Implement `LocalMoodRepository` and write unit tests.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Data Layer & Domain Model' (Protocol in workflow.md) (5a7ff53 - Deferred)

## Phase 2: Mood Tracking UI
- [x] Task: Implement Mood Selection Component. (3a22ab6)
    - [ ] Sub-task: Create a row of emoji buttons with selection state.
    - [ ] Sub-task: Add "Add Note" text field.
- [x] Task: Create Mood Tracking Screen. (dd72193)
    - [ ] Sub-task: Assemble components and connect to a new `MoodViewModel`.
    - [ ] Sub-task: Write UI tests for mood entry submission.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Mood Tracking UI' (Protocol in workflow.md) (5918f7e - Deferred)

## Phase 3: Dashboard & History UI
- [x] Task: Implement Dashboard Summary View. (fff775d)
    - [ ] Sub-task: Create a "Latest Mood" card for the top of the dashboard.
- [x] Task: Implement Mood History List. (bff27b2)
    - [ ] Sub-task: Create a list item component for history entries.
    - [ ] Sub-task: Implement a scrollable history list on the dashboard.
- [x] Task: Conductor - User Manual Verification 'Phase 3: Dashboard & History UI' (Protocol in workflow.md) (dd2d5a7 - Deferred)

## Phase 4: Navigation & Integration
- [x] Task: Set up Bottom Navigation for Patient role. (9972a8f)
    - [ ] Sub-task: Update `NavGraph` to include Dashboard and Tracker routes.
    - [ ] Sub-task: Implement a Scaffold with `BottomAppBar` for the Patient flow.
- [x] Task: Connect Authentication to Dashboard. (087a10d)
    - [ ] Sub-task: Ensure successful login redirects to the new Dashboard.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Navigation & Integration' (Protocol in workflow.md) (df8bd5e - Deferred)
