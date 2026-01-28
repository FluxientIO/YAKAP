# Specification: Real-Time Secure Chat

## Context
Effective therapy requires clear communication. This track implements a secure, direct messaging system within YAKAP to facilitate conversations between Patients and their assigned Professionals.

## Requirements

### 1. Conversation Management
*   **Conversation List:** Users can see a list of active conversations (e.g., Patient sees their Professional; Professional sees multiple Patients).
*   **Context:** Each conversation item displays the other participant's name, the last message snippet, and the timestamp.

### 2. Chat Interface
*   **Message Stream:** A scrollable list of messages in a conversation, ordered chronologically.
*   **Input Area:** A text field and send button to compose new messages.
*   **Visual Distinction:** Sent messages appear on the right; received messages appear on the left.

### 3. Data Model
*   `ChatMessage`:
    *   `id`: Unique ID.
    *   `conversationId`: ID linking participants.
    *   `senderId`: ID of the user sending the message.
    *   `content`: The text body.
    *   `timestamp`: Long (milliseconds).
*   `Conversation`:
    *   `id`: Unique ID.
    *   `participantIds`: List of user IDs (Patient + Professional).
    *   `lastMessage`: String preview.
    *   `lastTimestamp`: Long.

## Design
*   **Style:** Clean, chat-app aesthetic (bubbles, avatars).
*   **Privacy:** Emphasize security visually (e.g., lock icon).
*   **Navigation:** Accessible from the main dashboard or specific profile screens.

## Acceptance Criteria
*   [ ] Users can view a list of their conversations.
*   [ ] Users can open a conversation and see message history.
*   [ ] Users can send a text message.
*   [ ] Sent messages appear immediately in the UI.
*   [ ] Messages are persisted locally.
*   [ ] UI clearly distinguishes between "Me" and "Them".
