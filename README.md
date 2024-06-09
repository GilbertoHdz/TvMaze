# TV Maze

A tvv application guide to display Shows, Series and Movies in one place.

## Getting Started

1. Clone the project to your local machine.
2. Open the project using Android Studio. (Android Studio Hedgehog | 2023.1.1 Patch 1)
3. (Optional) Run test for :data: module

## Built With

Core architecture 

* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Manage dependency injection.
* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service.
* [OkHttp](https://square.github.io/okhttp/) to handle client request.
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects.
* [Coil](https://coil-kt.github.io/coil/compose/) to load and cache images by URL.
* [Room](https://developer.android.com/training/data-storage/room) for local database storage.
* [Coroutines](https://developer.android.com/kotlin/coroutines) for asynchronously tasks. 

It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Flow](https://developer.android.com/kotlin/flow)
* [ComposeTv](https://developer.android.com/training/tv/playback/compose)
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/)


## TODO List
* Implement Persistence data to store favorite movies.
* Implement ExoPlayer to reproduce short song or preview movie.
* Implement Common composable Ui on :presentation: layer.
* Implement Mobile project and Ui.

## Meta
Gilberto Hernández G. – [gilbertohdz.dev](https://gilbertohdz.dev/) - [@_GilbertoHdz_](https://twitter.com/_GilbertoHdz_) – ghernandez.9002@gmail.com

## License
[MIT](https://choosealicense.com/licenses/mit/)