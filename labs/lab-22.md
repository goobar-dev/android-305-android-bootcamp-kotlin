# 🖥 Lab 22: Building an Android Test Suite
Let's build a simple test suite for our app

## Objectives
1. Write unit tests for `MyNotesViewModel`
    1. Write tests to verify `ViewState` expectations
    2. Use `@Before` annotation method to simplify test setup
    3. Use `Mockito` to mock repository behavior
2. Write instrumentation tests for `MainActivity`
    1. Write a basic test to verify Android framework api
    2. Write an Espresso test to verify the AppBar title is correct in `MainActivity`
3. Use Gradle Wrapper to run unit tests from the command line
4. Use Gradle Wrapper to run instrumentation tests from the command line

# 🖥 Lab 22 Hints: Building an Android Test Suite

## 💡 Helpful Resources
- [Build Effective Android Unit Tests](https://developer.android.com/training/testing/unit-testing)
- [Build Local Unit Tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
- [Build Instrumentation Tests](https://developer.android.com/training/testing/unit-testing/instrumented-unit-tests)
- [Mock Dependencies with Mockito](https://github.com/mockito/mockito)

## 💡 What dependencies do I need to setup Android instrumentation tests?
```kotlin
androidTestImplementation('androidx.test:runner:1.1.0')
androidTestImplementation('androidx.test:rules:1.1.0')

// optional - only if you want Espresso tests
androidTestImplementation('androidx.test.espresso:espresso-core:3.1.0')
```

## 💡 What dependencies do I need to setup unit tests?
```kotlin
testImplementation("junit:junit:4.12")

// optional - only if you want to mock types with Mockito
testImplementation("org.mockito:mockito-core:1.10.19")
```

## 💡 Do I need to do anything to configure my test setup?
To simplify test setup, set your `testInstrumentationRunner` to use the androidx `AndroidJUnitRunner` class

```kotlin
android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
```

## 💡 How do I setup a basic instrumentation test?
Create a new test class, and add test methods that are annotated with `@Test`.

```kotlin
@Test
fun verifySomeBehavior() {
    // test logic
}
```