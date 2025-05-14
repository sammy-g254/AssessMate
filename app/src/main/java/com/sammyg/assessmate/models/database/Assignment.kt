package com.sammyg.assessmate.models.database

class Assignment{
    var teacher: String = ""
    var className: String = ""
    var assigntitle: String = ""
    var assigndescription: String = ""
    var dueDate: String = ""
    var fileURL: String = ""
    var createdTime: String = ""
    var assignId: String = ""

    // Primary constructor with all properties
    constructor(
        teacher: String,
        className: String,
        assigntitle: String,
        assigndescription: String,
        dueDate: String,
        fileURL: String,
        createdTime: String,
        assignId: String
    ) {
        this.teacher = teacher
        this.className = className
        this.assigntitle = assigntitle
        this.assigndescription = assigndescription
        this.dueDate = dueDate
        this.fileURL = fileURL
        this.createdTime = createdTime
        this.assignId = assignId
    }

    // Secondary constructor with no parameters
    constructor()
}