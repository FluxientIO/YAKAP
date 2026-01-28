# Implementation Plan - Track: Implement Real-Time Secure Chat

## Phase 1: Chat Data Layer
- [x] Task: Define Chat Domain Models and DAO. (e281a0c)
    - [ ] Sub-task: Create `ChatMessage` and `Conversation` data classes.
    - [ ] Sub-task: Update `AppDatabase` to include `messages` and `conversations` tables.
- [x] Task: Implement Chat Repository. (6b841a8)
    - [ ] Sub-task: Create `ChatRepository` for sending/receiving messages.
    - [ ] Sub-task: Write unit tests for message persistence.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Chat Data Layer' (Protocol in workflow.md) (3b58609 - Deferred)

## Phase 2: Conversation List UI
- [x] Task: Implement Conversation List Screen. (bfffd6a)
    - [ ] Sub-task: Create UI to display active chats with latest message preview.
    - [ ] Sub-task: Connect to `ChatViewModel` (to be created).
- [ ] Task: Integrate into Main Navigation.
    - [ ] Sub-task: Add "Messages" tab to Patient and Professional bottom/side navigation.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Conversation List UI' (Protocol in workflow.md) (ef379de - Deferred)

## Phase 3: Message Detail UI
- [x] Task: Implement Chat Detail Screen. (dfc9d0e)
    - [ ] Sub-task: Create the message bubble UI (sent vs. received).
    - [ ] Sub-task: Implement message input bar.
- [x] Task: Connect Chat Logic. (145f09c)
    - [ ] Sub-task: Update `ChatViewModel` to handle sending messages.
    - [ ] Sub-task: Ensure real-time updates (using Flows from Room).
- [x] Task: Conductor - User Manual Verification 'Phase 3: Message Detail UI' (Protocol in workflow.md) (f5e3c83 - Deferred)

## Phase 4: Final Integration
- [x] Task: Deep Link from Profiles. (234db3d)
    - [ ] Sub-task: Allow starting a chat from the Patient Profile or Professional Directory.
- [x] Task: Final Polish & Verification. (8d14c54)
    - [ ] Sub-task: Ensure back navigation works correctly from chat.
- [x] Task: Conductor - User Manual Verification 'Phase 4: Final Integration' (Protocol in workflow.md) (4b927fa - Deferred)
