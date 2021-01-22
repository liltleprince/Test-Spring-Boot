package com.example.me.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class FriendData {
    companion object {
        const val PENDING = 0
        const val ACCEPTED = 1
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var senderId: Int = 0
    var receiverId: Int = 0
    var status = PENDING
}