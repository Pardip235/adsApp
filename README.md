# adsApp

# Following  Android Jetpack library are implemented into this project
1. [AndroidX](https://developer.android.com/jetpack/androidx)<br/>
2. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
3. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata#:~:text=LiveData%20Overview%20Part%20of%20Android,activities%2C%20fragments%2C%20or%20services.)
4. [Room](https://developer.android.com/reference/androidx/room/package-summary#:~:text=Room%20is%20a%20Database%20Object,such%20queries%20at%20compile%20time.)
5. [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)<br/>
# Other Library used
1. [Dagger2](https://dagger.dev/dev-guide/) 
2. [Retrofit2](https://square.github.io/retrofit/)
3. [RxJava](https://github.com/ReactiveX/RxJava)

# App content details
 App contain the initial screen with list of recent advertisement fetched from network. User can makes  it favourites and also remove from favourites by clicking icon over image. When use click icon, advertisement item save in local database. And next screen shows favourite list of advertisement that fetched from local database, Also the user can make unfavourite advertisement item by click icon. 
 # Project Architecture
I followed the MVVM architecture for the project as this is the latest and so far the tending and I'm used to. I have used ViewModel class for data fetching and observe the data in the view. I have used ViewState for code readiablilty, debugging and testing easier by grouping change value by view(Activity, Fragment) "state". I have used Use-case business logic for avoiding code duplication.<br/>

# Need to improve
Local unit test and integration testing is missing in the app. It could be better for doing it in further development. Also, modular programming is latest and trending but I haven’t done it yet, I have least try to use clean code architecture, need to improve on it, willing to learn and implement it in my future project.

But the good thing I felt while developing this app is, I learn ROOM database within days, which motivated me feel energised continuous learning by doing. 
