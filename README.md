# 📱 ThoughtPad – Notes App with Firebase (Kotlin)

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![Language](https://img.shields.io/badge/language-Kotlin-orange.svg)

ThoughtPad is a modern Android note-taking application built in **Kotlin**.  
It uses **Firebase Firestore** to store notes and **Firebase Realtime Database** to trigger real-time notifications when new notes are added.

> 🚀 **[Download APK](https://github.com/abhichiku18/ThoughtPad/blob/master/app-release.apk)**

---

## 📌 Table of Contents
- [About the Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Download APK](#download-apk)
- [Author](#author)
- [License](#license)

---

## ✏️ About the Project

**ThoughtPad** lets users:
- Create, update, and delete notes
- Sync notes in real time across devices
- Get live notifications when a new note is added (using Firebase Realtime Database)

The project demonstrates:
- Using **Firestore** for structured data storage
- Using **Realtime Database** to trigger and listen for live updates

---

##  Features
- Add, update, delete notes
- Real-time sync across devices
- Live notifications on adding notes
- Login & Logout with Firebase Authentication
- Modern UI built with Jetpack Compose
- Clean, maintainable codebase

---

##  Tech Stack
| Layer              | Technology                                    |
| ------------------ | ---------------------------------------------- |
| Language           | Kotlin                                         |
| UI Framework       | Jetpack Compose                               |
| Backend / Database | Firebase Firestore & Realtime Database        |
| Auth               | Firebase Authentication                       |
| Build Tool         | Gradle                                        |

---

## Project Structure
com.abhichiku.thoughtpad
├── models
│   └── Notes.kt
├── navigation
│   └── NotesNavigation.kt
├── screens
│   ├── LoginScreen.kt
│   ├── SignupScreen.kt
│   ├── SplashScreen.kt
│   ├── NotesScreen.kt
│   └── InsertNoteScreen.kt
└── MainActivity.kt

 ## Screenshots
 ![WhatsApp Image 2025-07-09 at 2 09 39 PM](https://github.com/user-attachments/assets/e1649b7b-341a-4987-9637-80cd7638fe22)
![WhatsApp Image 2025-07-09 at 2 09 43 PM](https://github.com/user-attachments/assets/295dea38-2539-46bf-87ec-56933846b94e) 
![WhatsApp Image 2025-07-09 at 2 09 45 PM](https://github.com/user-attachments/assets/b067701d-5904-459f-a436-ed261534e296)
![WhatsApp Image 2025-07-09 at 2 09 44 PM](https://github.com/user-attachments/assets/88b52d70-160e-44af-b08a-c0d3a6a68179)

## Getting Started
Clone the repository:
bash
Copy
Edit
git clone https://github.com/abhichiku18/ThoughtPad
cd thoughtpad
Open in Android Studio
Connect your Firebase project:
Enable Firestore and Realtime Database
Download google-services.json and add it to the app/ directory
Run the project on emulator or device

## Usage
Firestore: Store and fetch notes.
Realtime Database: Send live notifications when new notes are added.
Authentication: Manage login/logout.
Jetpack Compose: Build reactive and modern UI.

## Download APK
You can directly download and try the app here:
https://github.com/abhichiku18/ThoughtPad/blob/master/app-release.apk


## Author
Abhinav Kumar Chaudhary
Android Developer | Kotlin | Firebase | ML Enthusiast


## License
This project is licensed under the MIT License.


