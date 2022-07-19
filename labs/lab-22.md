# 🖥 Lab 22: Building an Android Test Suite
Let's build a simple test suite for our app

## Objectives
1. Write unit tests for `MyNotesViewModel`
    1. Write tests to verify `ViewState` expectations
    2. Use `@Before` annotation method to simplify test setup
    3. Use `Mockito` to mock `NoteDao` behavior
    4. Use Turbine library to make assertions about multiple `Flow` emissions

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
- [Testing Flows using Turbine](https://github.com/cashapp/turbine)

## 💡 How do I setup a basic instrumentation test?
Create a new test class, and add test methods that are annotated with `@Test`.

```kotlin
@Test
fun verifySomeBehavior() {
    // test logic
}
```
