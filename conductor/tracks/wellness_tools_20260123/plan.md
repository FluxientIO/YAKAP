# Implementation Plan - Track: Implement Wellness Tools and Content

## Phase 1: Guided Breathing Animation
- [x] Task: Implement Breathing Animation Component. (4271c71)
    - [ ] Sub-task: Create `BreathingExercise` composable with infinite transition animation.
    - [ ] Sub-task: Add play/pause logic and breathing phase labels (Inhale, Hold, Exhale).
- [x] Task: Conductor - User Manual Verification 'Phase 1: Guided Breathing Animation' (Protocol in workflow.md) (daa12fa - Deferred)

## Phase 2: Self-Assessment Data & Logic
- [x] Task: Define Assessment Models and DAO. (1d8387a)
    - [ ] Sub-task: Create `AssessmentResult` entity and corresponding Room DAO.
    - [ ] Sub-task: Update `AppDatabase` to version 5.
- [x] Task: Implement Quiz Scoring Engine. (67a3cd9)
    - [ ] Sub-task: Create a utility class to define questions and calculate results.
    - [ ] Sub-task: Write unit tests for scoring logic.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Self-Assessment Data & Logic' (Protocol in workflow.md) (0e08f9a - Deferred)

## Phase 3: Assessment UI
- [x] Task: Implement Assessment Screen. (0793fbe)
    - [ ] Sub-task: Create a paginated or state-based quiz UI.
    - [ ] Sub-task: Implement the result summary screen with interpretations.
- [x] Task: Conductor - User Manual Verification 'Phase 3: Assessment UI' (Protocol in workflow.md) (920a996 - Deferred)

## Phase 4: Navigation & Wellness Hub
- [x] Task: Create Wellness Hub Screen. (a1447fd)
    - [ ] Sub-task: Create a menu screen to choose between Breathing and Assessments.
- [x] Task: Integrate into Main Navigation. (a1447fd)
    - [ ] Sub-task: Add "Wellness" tab to `PatientMainScreen` bottom navigation.
    - [ ] Sub-task: Update `NavGraph` with new routes.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Navigation & Wellness Hub' (Protocol in workflow.md) (bdaafc9 - Deferred)
