# Specification: Appointment Booking and Scheduling

## Context
A critical part of YAKAP is connecting Patients with Mental Health Professionals. This track implements the booking flow for Patients and the availability management for Professionals.

## Requirements

### 1. Professional Availability (Professional Side)
*   **Manage Slots:** Professionals can define time slots when they are available for consultations.
*   **Calendar View:** A weekly or daily view to see booked appointments.

### 2. Appointment Booking (Patient Side)
*   **Browse Professionals:** List of professionals with their expertise and ratings.
*   **Select Slot:** View available slots for a chosen professional and select one.
*   **Confirmation:** Confirm the booking, which then updates both the Patient's and Professional's calendars.

### 3. Data Model
*   `AppointmentSlot`:
    *   `id`: Unique ID.
    *   `professionalId`: ID of the professional.
    *   `startTime`: Long (timestamp).
    *   `endTime`: Long (timestamp).
    *   `isBooked`: Boolean.
*   `Appointment`:
    *   `id`: Unique ID.
    *   `patientId`: ID of the patient.
    *   `professionalId`: ID of the professional.
    *   `slotId`: ID of the linked slot.
    *   `status`: Enum (PENDING, CONFIRMED, COMPLETED, CANCELLED).

## Design
*   **Patient UI:** Integrated into the Bottom Navigation flow. Simple, step-by-step booking process.
*   **Professional UI:** Integrated into the Side Drawer flow. A dedicated "Calendar" or "Schedule" section.

## Acceptance Criteria
*   [ ] Professional can create an available time slot.
*   [ ] Patient can see a list of available professionals.
*   [ ] Patient can select an available slot and book an appointment.
*   [ ] Booked appointments appear on both the Patient's and Professional's dashboards.
*   [ ] Conflicts (booking the same slot twice) are prevented.
