package com.android.sealedclassdemo

import java.io.Serializable
import java.util.Date

sealed class ChatType : Serializable {
    data class Personal(val personName: String,val status: String) : ChatType()
    data class Group(val groupName: String, val description: String, val creationDate: Date) : ChatType()
}
