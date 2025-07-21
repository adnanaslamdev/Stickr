# 🧷 Stickr

Stickr is a fully customizable **Jetpack Compose** sticker view that supports:

- ✅ Drag, Scale, and Rotate
- 🗑️ Delete
- 🔁 Flip
- 📋 Duplicate
- 🎨 Custom Borders & Shapes
- 🎛️ Toggleable Corner Controls

---

📺 **Watch the demo video**  
[![YouTube Video](https://img.shields.io/badge/Watch%20Demo%20on-YouTube-red?logo=youtube)](https://youtube.com/shorts/1Gv4oCezLd8)  
*(Replace with actual video link)*

---

## 📦 Installation (via JitPack)

1. Add JitPack to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

2. Add Stickr dependency to your module:

```kotlin
dependencies {
    implementation("com.github.adnanaslamdev:Stickr:v1.0.0")
}
```

## 🚀 Usage

## ✅ Basic Usage

```kotlin
Stickr(
    content = StickrContent.DrawableRes(R.drawable.my_sticker),
    showControls = true,
    isSelected = true,
    borderColor = Color.White,
    controls = StickrDefaults.defaultControls(),
    onSelect = { /* Called when selected */ },
    onDelete = { /* Handle deletion */ },
    onDuplicate = { /* Handle duplication */ },
    onFlip = { /* Handle flip */ }
)
```

## 🎛️ Customization Options
# 🎨 Control Icons (Customize or Replace)

```kotlin
val customControls = StickrControls(
    showDelete = true,
    showDuplicate = true,
    showFlip = true,
    showResize = true,
    deleteIcon = R.drawable.ic_my_delete,
    duplicateIcon = R.drawable.ic_my_duplicate,
    flipIcon = R.drawable.ic_my_flip,
    resizeIcon = R.drawable.ic_my_resize
)

Stickr(
    content = StickrContent.DrawableRes(R.drawable.my_sticker),
    controls = customControls
)
```

## 🧩 Toggle Control Buttons

You can show or hide each control independently:

```kotlin
StickrControls(
    showDelete = false,
    showDuplicate = true,
    showFlip = false,
    showResize = true
)
```

## 🖼️ Border Customization

```kotlin
Stickr(
    borderColor = Color.Red,
    borderWidth = 3.dp,
    borderShape = RoundedCornerShape(12.dp)
)
```

## 🔐 Available Parameters

| Parameter      | Type             | Description                                    |
| -------------- | ---------------- | ---------------------------------------------- |
| `content`      | `StickrContent`  | Sticker content (`DrawableRes`, `Bitmap`)      |
| `showControls` | `Boolean`        | Show or hide the control buttons               |
| `controls`     | `StickrControls` | Custom controls and icons                      |
| `borderColor`  | `Color`          | Color of the selection border                  |
| `borderWidth`  | `Dp`             | Width of the border                            |
| `borderShape`  | `Shape`          | Shape of the border (e.g., RoundedCornerShape) |
| `isSelected`   | `Boolean`        | Whether this sticker is selected               |
| `onSelect`     | `() -> Unit`     | Callback when sticker is selected              |
| `onDelete`     | `() -> Unit`     | Callback when delete button is pressed         |
| `onDuplicate`  | `() -> Unit`     | Callback when duplicate button is pressed      |
| `onFlip`       | `() -> Unit`     | Callback when flip button is pressed           |


## 🧪 Example

```kotlin
val stickers = remember { mutableStateListOf<StickrData>() }

stickers.forEach { data ->
    key(data.id) {
        Stickr(
            content = data.content,
            showControls = true,
            isSelected = selectedId == data.id,
            borderColor = Color.White,
            controls = StickrDefaults.defaultControls(),
            onSelect = { selectedId = data.id },
            onDelete = { stickers.remove(data) },
            onDuplicate = { stickers.add(data.copy(id = generateUniqueId())) },
            onFlip = { /* Implement flip logic */ }
        )
    }
}
```

## 🏗️ Coming Soon

<ul>
<li>Export stickers as image</li>
<li>Text support</li>
<li>GIF support</li>
<li>More gesture configurations</li>
</ul>

## 🤝 Contributing

Contributions, suggestions, and pull requests are welcome!


🙌 Made with ❤️ by Adnan Aslam
