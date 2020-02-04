# Task: Java android is your friend - 2h task
Please install on your computer: Android studio and an Android emulator
Please write Java code (no Kotlin). Please do not use any third-party libraries. The app should be able to run on every device from Android 5.0 on.
## Part 1. Native android
* 1.1 Create an activity which loads (and processes) data from this URL https://jsonplaceholder.typicode.com/albums
* 1.2 Find a way to render the album titles (sorted by ID) from the JSON into a view, which is scrollable.
## Part 2. Services
* 2.1 Build a BroadcastReceiver that listens to the Intent with the action
Please note: there is no need to create an Intent with action , please use the existing one.
* 2.2 Implement a background task (for example: JobScheduler or background service) that runs periodically
* 2.3 Everytime the background task runs, create a push notification that shows the user how long the phone was active since the Intent was triggered (the time should be shown every few seconds)
Have fun and good luck ​☺