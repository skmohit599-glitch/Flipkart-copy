# Flipkart Android Application 🛒

[![Build Debug APK & Release](https://github.com/skmohit599-glitch/Flipkart-copy/actions/workflows/build-apk.yml/badge.svg)](https://github.com/skmohit599-glitch/Flipkart-copy/actions/workflows/build-apk.yml)
[![GitHub Release](https://img.shields.io/github/v/release/skmohit599-glitch/Flipkart-copy?label=Latest%20Release&color=2874F0)](https://github.com/skmohit599-glitch/Flipkart-copy/releases)
[![Download APK](https://img.shields.io/badge/Direct%20Download-flipkart--debug.apk-brightgreen?logo=android)](https://github.com/skmohit599-glitch/Flipkart-copy/releases/latest/download/flipkart-debug.apk)

An e-commerce shopping Android application inspired by Flipkart, crafted with modern Android architecture, Jetpack Compose, Material 3, and Kotlin.

---

## 📥 Direct APK Download

- 🚀 **Latest Debug APK**: [**Download `flipkart-debug.apk`**](https://github.com/skmohit599-glitch/Flipkart-copy/releases/latest/download/flipkart-debug.apk)
- 📦 **All Releases**: [GitHub Releases page](https://github.com/skmohit599-glitch/Flipkart-copy/releases)
- ⚡ **GitHub Actions Runs**: [Actions Workflow Runs](https://github.com/skmohit599-glitch/Flipkart-copy/actions/workflows/build-apk.yml)

---

## 🚀 Features

- **🏠 Home & Feed:** Dynamic hero promo banner, category quick-access bar, SuperCoin status, Flash Deals with countdown timer, and grid of recommended products.
- **🏷️ Product Details:** Rich bottom-sheet product preview with images, high discount tags, ratings, and instant "Add to Cart" or "Buy Now".
- **🛍️ Cart Management:** Real-time quantity adjustment, item removal, coupon discounts, delivery fee calculations, and one-tap checkout with celebration dialog.
- **📁 Categories & Filters:** Department navigation across Electronics, Fashion, Grocery, Beauty, Home & Appliances with search filtering.
- **❤️ Wishlist & Saved Items:** Bookmark favorite items with instant move-to-cart functionality.
- **👤 Account & Orders:** Order history, SuperCoin wallet balance, saved addresses, Flipkart Plus badge, and help center shortcuts.

---

## 📦 Automatic Debug APK Building & Direct Downloads

This repository includes a pre-configured **GitHub Actions CI/CD workflow** (`.github/workflows/build-apk.yml`) to automatically compile the debug APK and publish it for 1-click downloading.

### 📥 2 Ways to Download the APK

#### Option 1: Direct Download from GitHub Releases (Recommended for Mobile)
1. Go to [**Releases**](https://github.com/skmohit599-glitch/Flipkart-copy/releases) on `skmohit599-glitch/Flipkart-copy`.
2. Click the latest release (`Flipkart App - Latest Debug APK` or tag release like `v1.0.0`).
3. Under **Assets**, click [**`flipkart-debug.apk`**](https://github.com/skmohit599-glitch/Flipkart-copy/releases/latest/download/flipkart-debug.apk) to download it directly onto your Android device.

#### Option 2: Download from GitHub Actions Artifacts
1. Go to the [**Actions**](https://github.com/skmohit599-glitch/Flipkart-copy/actions/workflows/build-apk.yml) tab.
2. Click on the latest workflow run: **Build Debug APK & Release**.
3. Scroll down to the **Artifacts** section at the bottom.
4. Click **`flipkart-debug-apk`** to download the zipped APK package.

---

## ⚙️ How the Workflow is Triggered

The workflow automatically runs in any of these scenarios:

1. **On Push to `main` or `master`**:
   - Automatically builds the debug APK and updates the `latest-debug` release.
2. **On Git Tag Push (`v*`)**:
   - Creating and pushing a release tag (e.g., `git tag v1.0.0 && git push origin v1.0.0`) automatically compiles the APK and creates a permanent **GitHub Release `v1.0.0`** with the attached APK.
3. **Manual Trigger (Workflow Dispatch)**:
   - Go to **Actions** → **Build Debug APK & Release** → **Run workflow**.
   - You can specify a custom release tag or let it default to `latest-debug`.

---

## 📲 How to Install the APK on Your Android Device

1. Download **`flipkart-debug.apk`** to your phone or tablet.
2. Tap the notification or find the file in your **Files / Downloads** app.
3. If Android shows a prompt stating *"For your security, your phone is not allowed to install unknown apps from this source"*:
   - Tap **Settings**.
   - Switch on **Allow from this source**.
4. Tap **Install**, then **Open** to launch the app!

---

## 🚀 How to Push this Code to `skmohit599-glitch/Flipkart-copy`

If you are setting up or updating your GitHub repository from your local machine:

```bash
# In your project folder
git init
git branch -M main
git remote add origin https://github.com/skmohit599-glitch/Flipkart-copy.git

# Stage and commit all files
git add .
git commit -m "Initialize Flipkart Android App with automated APK build workflow"

# Push to GitHub (requires GitHub Personal Access Token or SSH key)
git push -u origin main
```

Once pushed, GitHub Actions will automatically start compiling your APK under the **Actions** tab and publish the downloadable **`flipkart-debug.apk`** under **Releases**!

---

## 🛠️ Local Build Instructions

If building locally using Android Studio or the command line:

```bash
# Clone the repository
git clone https://github.com/skmohit599-glitch/Flipkart-copy.git
cd Flipkart-copy

# Prepare debug keystore (if debug.keystore is missing)
base64 -d debug.keystore.base64 > debug.keystore

# Build Debug APK
./gradlew assembleDebug

# Output APK will be located at:
# app/build/outputs/apk/debug/app-debug.apk
```
