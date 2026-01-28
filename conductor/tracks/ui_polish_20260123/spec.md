# Specification: Polish and Refine UI/UX

## Context
After establishing the functional core of YAKAP, the goal is now to elevate the user experience. This involves making interactions feel smoother, ensuring visual consistency with the "Calm & Minimalist" guidelines, and providing more meaningful data visualizations.

## Requirements

### 1. Navigation Animations
*   **Transition Effects:** Implement enter/exit animations for all screen transitions in the `NavGraph` (e.g., slide-in, fade-in).
*   **Shared Element Transitions:** (Optional/Future) Smooth transitions for common elements between screens.

### 2. Theme & Visual Consistency
*   **Color Palette Refinement:** Ensure all primary, secondary, and surface colors strictly follow the soothing palette.
*   **Component Styling:** Standardize card corners, elevation, and padding across all dashboards.
*   **Typography Consistency:** Use defined font styles for headers, subheaders, and body text throughout the app.

### 3. Enhanced Visualizations
*   **Mood History Chart:** Implement a basic line or bar chart to show mood trends over the last 7 days on the Patient Dashboard.
*   **Analytics Visuals:** Use progress indicators or stylized graphs for admin analytics.

### 4. User Feedback & State
*   **SnackBar Notifications:** Implement clear, non-intrusive feedback for actions like "Mood Saved", "Note Deleted", or "Role Verified".
*   **Polished Loading States:** Standardize skeleton loaders or branded progress indicators for all data-fetching operations.

## Design
*   **Animations:** Slow, eased transitions to maintain a "calm" feel.
*   **Responsiveness:** Ensure all polished elements adapt well to different screen sizes (Portrait/Landscape/Tablets).

## Acceptance Criteria
*   [ ] Screen transitions are smooth and animated.
*   [ ] The application theme is applied consistently across all Patient, Professional, and Admin screens.
*   [ ] Patient dashboard includes a visual chart for mood trends.
*   [ ] Crucial user actions trigger informative SnackBar messages.
*   [ ] All lists and data-heavy screens show refined loading states.
