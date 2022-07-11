# 🖥 Lab 2: Introducing Kotlin for Android

## Objectives
1. Create a new Kotlin file named `hello-world.kt` within your app's primary src directory
2. Write a Kotlin function named `helloWorld()` that prints `"Hello World!"` to the console when invoked
3. Call this new function from your `MainActivity.onCreate()` method
4. Deploy your app to a device/emulator and observe the `"Hello World!"` output in the `Run` tool window
5. Within `hello-world.kt`, create `main()` function that invokes `helloWorld()`
6. Run the `main()` function and observe the output in the `Run` tool window
7. Update your `helloWorld()` function to use Android's `Log.d(tag, message)` method rather than Kotlin's `println()`
    1. For the `tag` parameter, you can use any String you wish.  A common convention is to use the name of the class that is calling the method
    2. For the `msg` parameter, pass `"Hello World!"`
8. Deploy your app again, and this time observe the output from the `Logcat` tool window
    1. Use the search box in the `Logcat` window to search for the tag you used when invoking `Log.d()`
9. Within your primary src directory, create a new Java class named `SampleClass`
    1. Right-click on the class name declaration, and click the `Generate` option
    2. Generate implementations for `toString()`, `equals()`, and `hashCode()`
10. In the `Project` tool window, right-click the `SampleClass.java` file name and select `Convert Java File to Kotlin File`
    1. Once the conversion is complete, review the changes
    2. You can then delete the file if you wish
11. Convert `settings.gradle` to `settings.gradle.kts`
12. Convert `build.gradle` to `build.gradle.kts`

## Challenges
### Refactor `helloWorld()`
1. Modify your `helloWorld()` function to take a custom greeting as a parameter
2. Modify your `helloWorld()` function to use a String Template to format the output message

## Use a Kotlin scratch file
Create a new Kotlin Scratch File, implement `helloWorld()`, and invoke the function to observe the interactive output

## Migrate your app's Gradle build logic to Kotlin
Convert `app/build.gradle` to `app/build.gradle.kts`.

# 🖥 Lab 2 Hints: Introducing Kotlin for Android

## 💡 Converting `settings.gradle` to `settings.gradle.kts`
After you convert the file extension, you'll need to update the invocation of the `include` function.

In Groovy, there is some syntactic sugar that allows calling a function with a single paramter without using any parentheses.
So, when we see `include ':app'` in the Groovy version, this is actually invoking the `include` function with the parameter `:app`.

When converting to Kotlin then, we need to update this invocation to use the parentheses `include(":app")`

## 💡 Converting `build.gradle` to `build.gradle.kts`
In the generated `build.gradle` file, there is an extension defined to specify the Kotlin version
`ext.kotlin_version = "1.6.20"`

This extension is then used in both `build.gradle` and `app/build.gradle`

When converting to `.kts` this extension pattern doesn't work quite the same way.  This leaves us with 2 options:
1. Update the extension to work in the .kts environment
2. In our case, we can simply remove it and update the two references accordingly
    1. In `build.gradle.kts` we can reference the Kotlin version directly as `"1.6.20"`
    2. In `app/build.gradle.kts` we can remove the explicit declaration of the Kotlin StdLib which is no longer required

## 💡 String literals in .kts files
In Groovy-based `.gradle` files, we cause use single, or double, quotes for String literals

However, in Kotlin-based buildscript files, we must use double quotes.  So when converting from `.gradle` to `.gradle.kts` it's often necessary to first update any strings to use double quotes

## 💡 Converting Java files to Kotlin files
This can be done several ways:
1. By right-clicking a Java file in the `Project` tool window and selecting the conversion option
2. Navigating to `Menu` -> `Code` -> `Convert Java File to Kotlin File`
3. Using the hotkey assigned to the `Convert Java File to Kotlin File` action
4. Use the hotkey for the `Action Lookup` action, start typing `Convert Java File...` and selecting the desired action

## 💡 Using Kotlin Scratch Files
Scratch files can be a great way to quickly prototype some code without building/running your whole project.

After you implement a function in the scratch file, don't forget to invoke the function.  If you don't explicitly invoke the function, you will not see the desired output.