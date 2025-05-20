package com.sammyg.assessmate.models.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assignment(
    val teacher: String = "",
    val className: String = "",
    val assigntitle: String = "",
    val assigndescription: String = "",
    val dueDate: String = "",
    val fileURL: String = "",
    val createdTime: String = "",
    val assignId: String = ""
) : Parcelable
