# SealedClassDemo
 Repository created to demonstrate Kotlin sealed class in a Android project

<img src="/media/app.gif" height="700" />

## About the example

The app simulates a chat whit two chat types with independent parameters, **personal chat** and **group chat**, implemented in the following [sealed class](https://kotlinlang.org/docs/sealed-classes.html):

``` kotlin
sealed class ChatType : Serializable {
    data class Personal(val personName: String, val status: String) : ChatType()
    data class Group(val groupName: String, val description: String, val creationDate: Date) : ChatType()
}
```

Furthermore, it contains a `ChatActivity` that receives an `ChatType` by [Activity Intent](https://developer.android.com/training/basics/firstapp/starting-activity) and contains different layout informations for each `ChatType`. At this point the `sealed class` provides an advantage compared to `enum class`, which does not provides parameter constructors, or `abstract class`, which does not have compile time inheritance knowledge and requires an `else` statement in `when` conditions.

An example of `when` condidition with `sealed class` is shown below:

``` kotlin
private fun getChatName(): String {
    return when (chatType) {
        is ChatType.Personal -> {
            getString(R.string.chat_personal_name, (chatType as ChatType.Personal).personName)
        }
        is ChatType.Group -> {
            getString(R.string.chat_group_name, (chatType as ChatType.Group).groupName)
        }
    }
}
```

Thank you for reading and feel free to add or suggest any point at this repo, please! :D
