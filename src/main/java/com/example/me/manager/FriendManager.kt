package com.example.me.manager

import com.example.me.model.FriendData
import com.example.me.repo.FriendRepository
import com.example.me.repo.FriendResponseRepo
import com.example.me.request.FriendRequest
import com.example.me.response.CommonResponse
import com.example.me.response.FriendResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FriendManager {
    @Autowired
    private lateinit var friendRepo: FriendRepository

    @Autowired
    private lateinit var friendResponseRepo: FriendResponseRepo
    fun getFriendList(id: Int): Any {
        return CommonResponse(friendResponseRepo.getFriendList(id))
    }

    fun makeFriendRequest(request: FriendRequest): CommonResponse {
        val friend = friendRepo.getFriendData(request.senderId, request.receiverId)
        if (friend != null) {
            return CommonResponse(2, "Friend request existed")
        }
        val friendData = FriendData()
        friendData.senderId = request.senderId
        friendData.receiverId = request.receiverId
        friendData.status = FriendData.PENDING
        friendRepo.save(friendData)
        return CommonResponse(friendData)
    }

    fun acceptFriendRequest(request: FriendRequest): CommonResponse {
        val friend = friendRepo.getFriendData(request.receiverId, request.senderId)
        friend!!.status = FriendData.ACCEPTED
        friendRepo.save(friend)
        return CommonResponse(friend)
    }
}