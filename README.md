# mvvm_demo

> 🗄️ **2019 learning project, no longer maintained.** An Android project skeleton from early in my career. Kept for history.

The base I used in 2019 to start Android apps in Java with an MVVM-style layout and a reactive networking stack.

## What's inside

- **Base classes:** `BaseActivity` / `BaseFragment` with shared fragment add/replace helpers
- **Utilities:** keyboard helpers, a reusable error dialog, app-wide constants
- **UI resources:** custom input states (normal / error), button and dialog shapes, `sdp` for screen-size-aware dimensions

| Layer | Library |
|---|---|
| Networking | Retrofit 2, OkHttp 3 + logging interceptor, Gson |
| Async | RxJava 2, RxAndroid |
| View binding | ButterKnife |
| UI | AndroidX AppCompat, ConstraintLayout, RecyclerView |

**Target:** `compileSdk 28`, `minSdk 23`, Java

## Run

Open in Android Studio and run the `app` module. Because it targets 2019 tooling, a current Android Studio will ask to upgrade Gradle and the Android Gradle Plugin.

## Where this led

ButterKnife and RxJava were later replaced in my work by Kotlin, Coroutines/Flow, Jetpack ViewModel and Dagger 2 / Hilt. See my [portfolio](https://quyenchau.pages.dev) for current projects.

---

**Quyen Chau** · [Portfolio & CV](https://quyenchau.pages.dev) · [GitHub](https://github.com/Tuan-Quyen)
