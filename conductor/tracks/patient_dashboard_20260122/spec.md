# Specification: Patient Dashboard and Mood Tracking

## Context
The Patient Dashboard is the heart of the daily user experience for Patients in YAKAP. It provides a quick way to record mental state and view progress over time. This track implements the first functional dashboard features.

## Requirements

### 1. Mood Entry (Daily Tracker)
*   **Emoji Input:** A selection of 5 core emojis representing mood levels (e.g., Great, Good, Neutral, Low, Bad).
*   **Quick Note:** A text field for adding a brief context or journal entry alongside the mood.
*   **Submission:** Save the mood entry locally (with sync capability in mind).

### 2. Dashboard Overview
*   **Current State:** Display the user's latest recorded mood prominently.
*   **Recent History:** A list or scrollable view showing the last 7 days of mood entries.
*   **Visual Feedback:** Each history item should show the emoji, the date, and a snippet of the note.

### 3. Data Model
*   `MoodEntry`:
    *   `id`: Unique ID.
    *   `moodType`: Enum (GREAT, GOOD, NEUTRAL, LOW, BAD).
    *   `note`: String.
    *   `timestamp`: Long (milliseconds).

## Design
*   **Style:** Follow "Calm & Minimalist" guidelines.
*   **Colors:** Use soft color coding for moods (e.g., green for great, yellow for neutral, blue for low).
*   **Navigation:** Accessible via the Bottom Navigation bar (which will be fully established in this track).

## Acceptance Criteria
*   [ ] User can select an emoji to represent their mood.
*   [ ] User can optionally add a note to their mood entry.
*   [ ] Saved moods are displayed correctly on the dashboard history.
*   [ ] Dashboard correctly displays the "latest mood".
*   [ ] UI adheres to the brand guidelines (rounded corners, soft visuals).
