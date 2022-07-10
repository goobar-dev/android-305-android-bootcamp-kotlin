# 🖥 Lab 1: Deploying Your First Android App

## 📝 Objectives
1. Install the latest stable version of Android Studio
2. Install the Android 12.0 (S) SDK
3. Set a `ANDROID_HOME` environment variable to point to your Android SDK installation
    1. Add this to any shell startup script you use to ensure it's always set
4. Install Android Emulator Tooling
   1. Install Intel x86 Emulator Accelerator (HAXM installer) if using Intel cpu
5. Create a new Android Studio project
    1. Select the `Empty Activity` template
    2. Name your application `Game Counter`
    3. Set `Minimum SDK` to `API 23: Android 6.0 (Marshmallow)`
6. Create a new Android Emulator
7. Deploy your project to the emulator using Android Studio
8. Deploy your project to the emulator from the terminal using the Gradle Wrapper

## ✨ Challenges

### Create an emulator for testing other form factors
Create a second emulator for either a small screen, or large screen, device.
Having multiple emumlators makes it easier to test your app on a variety of device configurations - helping ensure a quality experience for more users.

### Test on a physical device
Enable `Developer Options` on your physical device and deploy your app to that device.
Testing on a physical device is often the best way to understand how your app will look/feel for real users.

### Install a custom Java Development Kit (JDK)
By setting up a local JDK installation, you gain greater control over which JDK you, and your team, are using.
This makes it easier to ensure local builds and CI builds are using the same JDK; making builds more reliable and consistent.

If using a local JDK install, you want to ensure Androd Studio uses the same version.  If you don't, your builds from Android Studio and the terminal may use different Gradle daemons which lead to slower builds.
   1. [Download a JDK](https://www.azul.com/downloads/?package=jdk) compatible with your development machine.
   2. Install the JDK to your machine
   3. Set a `JAVA_HOME` environment variable to point to your JDK installation
       1. Add this to any shell startup script you use to ensure it's always set
   4. Update Android Studio `Gradle JDK` setting to point to same JDK as `JAVA_HOME` 