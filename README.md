# Organ Donation Platform

This project aims to provide a common platform for hospitals and potential organ donors, streamlining the organ donation process and facilitating efficient communication between hospitals in need of donors and willing donors.

## Features

- **User Registration and Login:** Hospitals and donors can register and log in to access the platform's functionalities.
- **Hospital Profiles and Verification:** Hospitals can create and manage their profiles, including necessary document uploads for verification purposes.
- **Donor Registration and QR Code Generation:** Donors can register by providing relevant details and uploading required documents. Upon successful registration, a unique QR code is generated for each donor.
- **Donor Verification:** Hospitals can verify the authenticity of donors by scanning their QR codes.
- **Donor Requests:** Hospitals can post requests for donors in the "Add Post" section, specifying the details of patients in need. Other hospitals receive notifications about these requests.
- **Adding and Viewing Donors:** Hospitals can add new donors by scanning their QR codes or view the existing list of donors.
- **Patient Details:** Hospitals can store and manage patient details within the platform.

## Technologies Used

- Java: Backend and frontend development.
- XML: User interface design.
- Android Studio: Integrated development environment (IDE) for app development.
- Firebase Authentication: User authentication and registration.
- Firebase Realtime Database: Storage for profile details of hospitals and donors, donor details, patient details, and donor requests.
- Firebase Storage: Storage for necessary documents uploaded by hospitals and donors.

## Getting Started

1. Clone the repository to your local machine.
2. Install Java and Android Studio if not already installed.
3. Set up a Firebase project and configure the necessary authentication and database services.
4. Open the project in Android Studio.
5. Connect the project to your Firebase project by following the Firebase integration guide.
6. Build and run the project on an Android device or emulator.

## Contributing

Contributions to this project are welcome. If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute this project for personal or commercial purposes.
