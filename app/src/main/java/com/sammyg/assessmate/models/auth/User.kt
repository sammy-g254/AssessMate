package com.sammyg.assessmate.models.auth

class User {
    var name:String = ""
    var email:String = ""
    var schoolCode:String = ""
    var password:String = ""
    var studentorteacher:String = ""
    var id:String = ""

    constructor(name: String, email: String, schoolCode: String, password: String, studentorteacher: String, id: String) {
        this.name = name
        this.email = email
        this.schoolCode = schoolCode
        this.password = password
        this.studentorteacher = studentorteacher
        this.id = id
    }

    constructor()
}