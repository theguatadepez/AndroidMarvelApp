# AndroidMarvelApp
An Android Demo App that consumes Marvel's API to get access to their list of heroes.


The App was build following MVVM Architecture Pattern, utilizing:

- Android Navigation architecture for navigation between Fragments.
- Hilt for Dependency Injection.
- Kotlin Coroutines for suspend functions calls, using high level function for Abstraction.
- Retrofit2 with Singleton Pattern for Suspend Function API calls, with GSONConverter.
- Android ViewModel component in Ui layer.
- Android LiveData using Observer Pattern for Fragments/ViewModel communication.
- NavArgs for communication between Fragments.
- ViewBinding to access UI from Activities/Fragments.
- DataBinding to access Layouts from ViewModel. 
- Unit Test with Mockito, JUnit4, Mockk and mockWebServer.

To consider:

- The API call to get the List of Superheroes has a limit value of 100, this was on purpose.
- Hero Detail view only shows the amount of appearences in different MediaTypes and shows the first URL value for more information, this was for simplicity purpose.





