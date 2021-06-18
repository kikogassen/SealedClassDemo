# SealedClassDemo
 Repository created to demonstrate Kotlin sealed class in a Android project

<img src="/media/app.gif" height="700" />

## About the sealed class

The app simulates a chat whit two chat types with independent parameters, **personal chat** and **group chat**, implemented in the following [sealed class](https://kotlinlang.org/docs/sealed-classes.html):

``` kotlin
sealed class ChatType : Serializable {
    data class Personal(val personName: String, val status: String) : ChatType()
    data class Group(val groupName: String, val description: String, val creationDate: Date) : ChatType()
}
```

Furthermore, it contains a `ChatActivity` that receives an `ChatType` by [Activity Intent](https://developer.android.com/training/basics/firstapp/starting-activity) and contains different layout informations for each `ChatType`. 

### Advantage 1

At this point the `sealed class` provides an advantage compared to `enum class`, which does not provides parameter constructors, or `abstract class`, which does not have compile time inheritance knowledge and requires an `else` statement in `when` conditions.

### Advantage 2

There are so many scenarios where we must pass so many parameters from one activity to another. Using this `sealed class` implementation, we always need to parametrize only the `ChatType` instance and retrieve other data inside it.

### Advantage 3

Using this feature, the project scaling becomes easy: if you want to add some other `ChatType`, just add the `ChatType.OtherType` class and the respective cases in `when` statements along the code. You will not forget it, the code will show a compile error if you do not cover that case ;)

## Using the sealed class

An example of `when` condidition with `sealed class` is shown below:

``` kotlin
private fun getChatName(): String {
    return when (chatType) {
        is ChatType.Personal -> {
            (chatType as ChatType.Personal).personName
        }
        is ChatType.Group -> {
            (chatType as ChatType.Group).groupName
        }
    }
}
```

Thank you for reading and feel free to add or suggest any point at this repo, please! :D
