package com.example.me.model

import javax.persistence.*

@Entity
class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var username: String = ""
    var password: String = ""
    var avatar: String? = null
    var sex: String = "male"
    var firstName: String = ""
    var lastName: String = ""
}