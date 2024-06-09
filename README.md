# TV Maze

A tvv application guide to display Shows, Series and Movies in one place.

<img src="assets/tvmaze.gif" alt="Tv Maze preview" width="724" />


## Getting Started

1. Clone the project to your local machine.
2. Open the project using Android Studio. (Android Studio Hedgehog | 2023.1.1 Patch 1)
3. (Optional) Run test for :data: module

## Design

Design references

* [JetStream](https://www.figma.com/community/file/1236080717929618236/jetstream-streaming-app) using color palette.
* [Material Builder](https://material-foundation.github.io/material-theme-builder/) source primary color #A8C8FF from figma.
* [TvDesign](https://developer.android.com/design/ui/tv) design for Android TV, guides, patterns and more.

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

## Architecture
* :data:
```
1. Data models as data transfer object to retrieve API request.
2. Remote logic to call API.
3. Mapping data from DTO to Domain models 
4. Local persistence by room to store the data.
5. Repositories implementation
```
* :domain:
```
1. Domain models to interact between data and presentation layer.
2. Bussines logic and validation rules such UseCase or Interactors.
3. Repository interface to define the action to implement for the :data: layer
4. Independient and agnostic layer of project and libraries configuration.
```
* :app:
```
1. User interface interaction.
2. Handle and manage the user events/interaction with the app.
3. Lifecycle management and dependencies injection.
4. Adopting the :domain: layer to manage the resources and application state.
```

## TODO List
* Implement Persistence data to store favorite movies.
* Implement ExoPlayer to reproduce short song or preview movie.
* Implement Common composable Ui on :presentation: layer.
* Implement Mobile Ui and include mobile project app module.
* Improve Ui

## Meta
Gilberto Hernández G. – [gilbertohdz.dev](https://gilbertohdz.dev/) - [@_GilbertoHdz_](https://twitter.com/_GilbertoHdz_) – ghernandez.9002@gmail.com

## License
[MIT](https://choosealicense.com/licenses/mit/)