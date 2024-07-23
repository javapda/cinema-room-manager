# [cinema-room-manager](https://hyperskill.org/projects/138)
* based on the Hyperskill Cinema Room Manager (Kotlin) project
* project [cinema-room-manager](https://github.com/javapda/cinema-room-manager.git on github

## setup folders
### Windows
```
mkdir src/main/kotlin
mkdir src/main/resources
mkdir src/test/kotlin
mkdir src/test/resources
```
## setup build.gradle.kts (Kotlin form of gradle build script)
```
plugins {
    // other plugins ...
    application
}
val mainKotlinClass = "com.javapda.cinemaroommanager.CinemaRoomManagerKt"
application {
    mainClass = mainKotlinClass
}
```
## link project to gradle
* create basic files for gradle:
  * settings.gradle.kts
  * build.gradle.kts
  * gradle.properties
* in IntelliJ IDEA, right click on settings.gradle.kts or build.gradle.kts and select **Link Gradle Project**
## build and run
```
.\gradlew.bat clean build 
java -jar .\build\libs\minesweeper-kotlin-0.0.1-SNAPSHOT-all.jar
# - or - 
#  java -cp  .\build\libs\minesweeper-kotlin-0.0.1-SNAPSHOT-all.jar  com.javapda.cinemaroommanager.CinemaRoomManagerKt
# - or - 
#  .\gradlew.bat clean build :run
# - or - 
#  .\gradlew.bat :run
```
## Hyperskill
* [my profile](https://hyperskill.org/profile/615178637)
* [Troubleshooting: no tests have run](https://plugins.jetbrains.com/plugin/10081-jetbrains-academy/docs/troubleshooting-guide.html#no_tests_have_run)
* [discord markdown live previewer](https://discord-markdown-live-previewer.vercel.app/)

## Stage 5/5 : 
## Stage 4/5 : 
## Stage 3/5 : 
## Stage 2/5 : 
## Stage 1/5 : [Arrangement](https://hyperskill.org/projects/138/stages/735/implement)
### Description
There are many enjoyable activities on this funny little planet Earth, and still, the happiest, simplest, and most fulfilling one is probably going to the movies. Going with friends, going with loved ones, experiencing a whole new adventure from the safety of a cinema seat, surrounded by like-minded fellow travelers.

As a beginner developer, you can contribute to creating this wonderful cinema experience. Your good friends asked you to help them create an application for a cinema theatre where people can get tickets, reserve seats, and enjoy their movie night.

### Objectives
There is not a lot of space in our new cinema theatre, so you need to take into account the restrictions on the number of seats. Your friends said that the room would fit 7 rows of 8 seats. Your task is to help them visualize the seating arrangement by printing the scheme to the console.

Your output should be like in the example below and should contain 9 lines, title Cinema: - 1 line and room size - 8 lines.

### Example
```
Cinema:
1 2 3 4 5 6 7 8
1 S S S S S S S S
2 S S S S S S S S
3 S S S S S S S S
4 S S S S S S S S
5 S S S S S S S S
6 S S S S S S S S
7 S S S S S S S S
```
