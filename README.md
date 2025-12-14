# LintingGuild 🛡️

A demonstration project showcasing how to create **custom Android Lint rules** for Jetpack Compose applications. This project specifically implements a lint rule that detects and flags hardcoded colors in Compose code, encouraging developers to use centralized color definitions instead.

## 📸 Screenshot
<img width="1440" height="900" alt="Screenshot 2025-12-14 at 8 10 44 PM" src="https://github.com/user-attachments/assets/fae02bc5-db4d-4a11-a587-3dea2f73d481" />


*The custom lint rule in action - detecting hardcoded colors in Jetpack Compose and providing helpful guidance.*

## 🎯 Purpose

Hardcoded colors scattered throughout a codebase can lead to:
- Inconsistent UI theming
- Difficulty implementing dark mode
- Maintenance nightmares when rebranding
- Accessibility issues

This project provides a custom lint rule that catches these issues at compile time, enforcing best practices for color management in Compose applications.

## 🏗️ Project Structure

```
LintingGuild/
├── app/                          # Sample Android app with Compose
│   ├── src/main/java/com/qamar/lintingwtm/
│   │   └── MainActivity.kt       # Contains example hardcoded color
│   └── build.gradle.kts          # Includes lint configuration
│
├── guild-lint/                   # Custom lint rules module
│   ├── src/main/java/com/qamar/guildlint/
│   │   ├── detector/
│   │   │   └── HardcodedColorDetector.kt   # Detection logic
│   │   └── issue/
│   │       └── HardcodedColorIssue.kt      # Issue definition & registry
│   └── build.gradle.kts
│
└── gradle/
    └── libs.versions.toml        # Dependency versions catalog
```

## 🔍 How It Works

### The Detector

The `HardcodedColorDetector` uses UAST (Unified Abstract Syntax Tree) to scan Kotlin/Java code for specific method calls:

```kotlin
// Methods that are checked for hardcoded colors
"background", "border", "color", "tint"
```

It identifies hardcoded colors by matching this pattern:
```kotlin
Color(0xFFxxxxxx)  // Hexadecimal color literals
```

### The Issue

When a hardcoded color is detected, the lint rule reports an **ERROR** severity issue with:
- **ID**: `HardcodedColor`
- **Category**: Correctness
- **Message**: "Avoid hardcoded colors. Use them from Colors file only"

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- JDK 17+
- Gradle 8.x

### Building the Project

```bash
# Clone the repository
git clone https://github.com/yourusername/LintingGuild.git
cd LintingGuild

# Build the lint rules
./gradlew :guild-lint:build

# Run lint checks on the app
./gradlew :app:lint
```

### Integrating into Your Project

1. **Copy the `guild-lint` module** to your project

2. **Add it to `settings.gradle.kts`**:
   ```kotlin
   include(":guild-lint")
   ```

3. **Add the lint dependency** in your app's `build.gradle.kts`:
   ```kotlin
   dependencies {
       lintChecks(project(":guild-lint"))
   }
   ```

4. **Configure lint severity** (optional):
   ```kotlin
   android {
       lint {
           abortOnError = true
           fatal += setOf("HardcodedColor")
       }
   }
   ```

## 📋 Example

### ❌ This will trigger the lint error:

```kotlin
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.background(Color(0xFFEFB8C8))  // ⚠️ Hardcoded color!
    )
}
```

### ✅ Use colors from a centralized file instead:

```kotlin
// In your Color.kt or Theme.kt
val PrimaryPink = Color(0xFFEFB8C8)

// In your Composable
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.background(PrimaryPink)  // ✅ Centralized color
    )
}
```

## 🛠️ Tech Stack

| Component | Version |
|-----------|---------|
| Android Gradle Plugin | 8.8.0 |
| Kotlin | 2.0.0 |
| Lint API | 31.9.0 |
| Compose BOM | 2024.04.01 |
| Min SDK | 24 |
| Target SDK | 35 |

## 📚 Resources

- [Android Lint API Guide](https://googlesamples.github.io/android-custom-lint-rules/)
- [Writing Custom Lint Rules](https://developer.android.com/studio/write/lint)
- [UAST Documentation](https://plugins.jetbrains.com/docs/intellij/uast.html)

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Add new lint rules for other common issues
- Improve detection patterns
- Add unit tests for the detector

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

*Built with ❤️ to help Android developers write cleaner, more maintainable code.*

