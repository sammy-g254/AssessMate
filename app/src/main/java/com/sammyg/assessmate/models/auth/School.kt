package com.sammyg.assessmate.models.auth

class School {
    var schoolname:String = ""
    var schoolemail:String = ""
    var schoolCode:String = ""
    var schoolpassword:String = ""
    var id:String = ""

    constructor(schoolname: String, schoolemail: String, schoolCode: String, schoolpassword: String, id: String) {
        this.schoolname = schoolname
        this.schoolemail = schoolemail
        this.schoolCode = schoolCode
        this.schoolpassword = schoolpassword
        this.id = id
    }

    constructor()
}