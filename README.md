# 📽️ Letterboxd Clone

This app is a simplified version of the movie-tracking platform Letterboxd, built with Kotlin. It allows users to browse through a list of movies, view detailed information about them, read reviews, and submit their own reviews. The project leverages Firebase for authentication and Firestore for data storage.

## 🚀 Features

### 🔒 Authentication
- **Sign Up / Log In with Firebase Auth**: Users can create an account or log in using Firebase Authentication.
- **Persistent Login State**: Once logged in, users remain authenticated even after closing the app.

### 🎥 Movie Browsing
- **Movie Grid**: Scroll through a list of movies presented in a grid layout.
- **Infinite Scrolling**: As users scroll, more movies are loaded dynamically.
- **Movie Details**: Tap on any movie to view detailed information, including title, release date, overview, and poster.

### ⭐ Reviews
- **Read Reviews**: View reviews left by other users for each movie.
- **Leave a Review**: Authenticated users can submit their own review and rate the movie.

## 🛠️ Technologies Used
- **Kotlin**: The programming language used for all development.
- **Jetpack Compose**: For building declarative UI components.
- **Retrofit**: A type-safe HTTP client used for networking.
- **Dagger + Hilt**: Dependency injection framework for managing app components.
- **Gson**: JSON library used for serialization and deserialization.
- **Firebase Authentication**: Handles user sign-up, login, and authentication.
- **Glide**: Image loading library used for fetching and displaying movie posters.
- **Firestore**: A NoSQL cloud database to store user reviews ` *IN PROGRESS*

## 🎯 Getting Started

### Prerequisites
- [Android Studio](https://developer.android.com/studio)
- A Firebase project set up with Authentication and Firestore enabled.

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/balint1026/letterboxd-hw.git
    cd letterboxd-hw
    ```
2. Open the project in Android Studio.
3. Set up Firebase:
    - Add your `google-services.json` file to the `app` directory.
    - Ensure Firebase Authentication and Firestore are enabled in your Firebase console.
4. If you do not have a TheMovieDB account, sign up [here](https://www.themoviedb.org/signup).
5. In the /main/assets/ folder, create an "env" file (not .env!), in this format: 
```API_READ_ACCESS_TOKEN="YOUR_TOKEN_HERE" ```
6. Build and run the app on an emulator or physical device.

## 📚 Usage
1. **Sign Up** or **Log In** using your email and password.
2. Browse through the list of movies on the main screen.
3. Tap on a movie to view its details, including the title, release date, overview, and poster.
4. Scroll down to see reviews left by other users.
5. If logged in, submit your own review and rate the movie. *IN PROGRESS*

