# 🖥 Lab 3: Exploring Kotlin Fundamentals

## Objectives
1. Open the lab-3 project in Android Studio
2. Open `Functions.kt` and update/implement the functions there to make the tests in `FunctionTests.kt` pass
3. Open `Loggers.kt` and implement the interfaces, classes, and functions required to make the tests in `OOPTests.kt` pass
    1. You'll need to implement a `Logger` interface
    2. You'll need a `BasicLogger` Object class that implements `Logger`
    3. You'll need a `FancyLogger` class that implements `Logger`
    4. You'll need an extension function on the `BasicLogger` class to log multiple messages at the same time
4. Open `SupportedLanguages.kt` and update/implement the code to make the tests in `SealedClassTests.kt` pass
    1. You'll need to add a `InvalidNameException` and `EmptyNameException` to the `SupportedAndroidLanguageException` sealed class
5. Open `CollectionsProcessing.kt` and update/implement the function to make the tests in `CollectionsTests.kt` pass


## Challenges
### Create an "Extra Fancy" Logger
1. Create an `ExtraFancyLogger` class that extends `FancyLogger`.  
2. It should specify both a `logTag` and a `separator`.
3. It should override the `log()` implementation to use both properties when logging the message.

### Create an extension function
Create an extension function on `BasicLogger` to print all elements of a `List`.
1. It should take in a `List<String>`
2. It should print each message using `BasicLogger.log()`

# 🖥 Lab 3 Hints: Exploring Kotlin Fundamentals

## 💡 Filtering values from a Collection
The Kotlin Standard Library has several helpful functions for filtering values from a collection:
1. `filter{}` The generic filter function takes a lambda allowing you to specify your own filtering criteria
2. `filterNotNull()` Will filter out any null values

## 💡 Extending a class
Class in Kotlin are closed for extension by default.  To extend a class you must do 1 of 2 things
1. Make the base class abstract
2. Mark the base class as open for extension by adding the `open` modifier to the class declaration

## 💡 Extending a method
Like classes, methods in Kotlin are closed for extension be default.

To mark a method as being open for extension in child classes, add the `open` modifier to the method declaration