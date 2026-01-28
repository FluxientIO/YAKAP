# Specification: Wellness Tools and Content

## Context
Providing immediate coping mechanisms and diagnostic awareness is core to YAKAP's mission. This track implements "in-the-moment" support through guided breathing and "reflective" support through standardized quizzes.

## Requirements

### 1. Guided Breathing Exercise
*   **Visuals:** A gentle, expanding and contracting shape (circle or lotus) to guide inhalation and exhalation.
*   **Timing:** 4-7-8 breathing pattern or a customizable rhythmic pace.
*   **Controls:** Start, Pause, and Reset functionality.
*   **Tone:** Ultra-calm aesthetic with soft colors.

### 2. Mental Health Self-Assessments (Quizzes)
*   **Variety:** Initially provide one or two standardized quizzes (e.g., simplified GAD-7 for anxiety).
*   **Flow:** Question-by-question navigation with single-choice answers.
*   **Result:** Calculate a score and provide a compassionate interpretation (e.g., "Mild", "Moderate") with a recommendation to speak with a professional.
*   **Persistence:** Save result history locally to track changes over time.

### 3. Data Model
*   `AssessmentResult`:
    *   `id`: Unique ID.
    *   `userId`: ID of the patient.
    *   `quizType`: Enum (GAD7, PHQ9, etc.).
    *   `score`: Int.
    *   `interpretation`: String.
    *   `timestamp`: Long.

## Design
*   **Breathing UI:** Full-screen focused mode with minimal distractions.
*   **Quiz UI:** Clear, readable text with large touch targets for answers.
*   **Navigation:** Accessible from a new "Wellness" tab in the Bottom Navigation.

## Acceptance Criteria
*   [ ] User can start a guided breathing session with smooth animations.
*   [ ] User can complete a self-assessment quiz.
*   [ ] Quiz scores are calculated correctly based on answers.
*   [ ] Assessment results are saved and viewable in a history view.
*   [ ] The UI remains empathetic and gentle throughout.
