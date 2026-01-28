# Implementation Plan - Track: Implement Appointment Booking and Scheduling

## Phase 1: Appointment Data Layer
- [x] Task: Define Appointment Domain Models and DAO. (0d8f9ff)
    - [ ] Sub-task: Create `AppointmentSlot` and `Appointment` data classes.
    - [ ] Sub-task: Update `AppDatabase` to include `appointment_slots` and `appointments` tables.
- [x] Task: Implement Appointment Repository. (1e41985)
    - [ ] Sub-task: Create `AppointmentRepository` for managing slots and bookings.
    - [ ] Sub-task: Write unit tests for slot creation and booking logic.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Appointment Data Layer' (Protocol in workflow.md) (a01998e - Deferred)

## Phase 2: Professional Availability UI
- [x] Task: Implement Availability Management Screen. (3b82531)
    - [ ] Sub-task: Create UI for professionals to add/delete time slots.
- [x] Task: Create Professional Calendar View. (49f846b)
    - [ ] Sub-task: Implement a list or calendar view showing booked sessions for the day.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Professional Availability UI' (Protocol in workflow.md) (54bd1fd - Deferred)

## Phase 3: Patient Booking Flow UI
- [x] Task: Implement Professional Directory Screen. (dd9a91e)
    - [ ] Sub-task: Create a list view for patients to browse professionals.
- [x] Task: Implement Booking Screen. (1ac0837)
    - [ ] Sub-task: Create UI to show available slots for a professional and select one.
- [x] Task: Conductor - User Manual Verification 'Phase 3: Patient Booking Flow UI' (Protocol in workflow.md) (6c5bebb - Deferred)

## Phase 4: Integration & Dashboard Updates
- [x] Task: Integrate Bookings with Dashboards. (2343b26)
    - [ ] Sub-task: Update Patient Dashboard to show upcoming appointments.
    - [ ] Sub-task: Update Professional Dashboard to show today's schedule summary.
- [x] Task: Final Navigation & Flow Check. (9a1cc0e)
    - [ ] Sub-task: Ensure booking flow works end-to-end and navigation is smooth.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Integration & Dashboard Updates' (Protocol in workflow.md) (292f9ea - Deferred)
