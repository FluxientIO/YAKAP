# Implementation Plan - Track: Implement Professional Dashboard and Patient Management

## Phase 1: Professional Data Layer
- [x] Task: Define Professional Domain Models and DAO. (9e2f2ca)
    - [ ] Sub-task: Create `Patient` and `ConsultationNote` data classes.
    - [ ] Sub-task: Update `AppDatabase` to include `patients` and `notes` tables.
- [x] Task: Implement Professional Repository. (16a4ec5)
    - [ ] Sub-task: Create `ProfessionalRepository` for managing patients and notes.
    - [ ] Sub-task: Write unit tests for data operations.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Professional Data Layer' (Protocol in workflow.md) (4724ab7 - Deferred)

## Phase 2: Professional Dashboard & Navigation
- [x] Task: Set up Professional Navigation Structure. (d23f57f)
    - [ ] Sub-task: Define routes for `ProfessionalMain`, `PatientList`, and `PatientProfile`.
    - [ ] Sub-task: Implement Side Drawer or Tabbed Navigation for Professionals.
- [x] Task: Implement Professional Dashboard Home. (00d5171)
    - [ ] Sub-task: Create a summary view for the professional's day.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Professional Dashboard & Navigation' (Protocol in workflow.md) (eed67a6 - Deferred)

## Phase 3: Patient Management UI
- [x] Task: Implement Patient List Screen. (b049262)
    - [ ] Sub-task: Create searchable list of patients with basic status.
- [x] Task: Implement Patient Profile Screen. (6644e1c)
    - [ ] Sub-task: Create detail view for a specific patient.
    - [ ] Sub-task: Integrate patient mood history view.
- [x] Task: Conductor - User Manual Verification 'Phase 3: Patient Management UI' (Protocol in workflow.md) (5d6e613 - Deferred)

## Phase 4: Session Notes Integration
- [x] Task: Implement Note Editor. (d044a18)
    - [ ] Sub-task: Create UI for writing and saving consultation notes.
- [x] Task: Final Integration & Role Redirection. (b2462dd)
    - [ ] Sub-task: Ensure Professionals are redirected to `ProfessionalMain` after login.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Session Notes Integration' (Protocol in workflow.md) (8cf5878 - Deferred)
