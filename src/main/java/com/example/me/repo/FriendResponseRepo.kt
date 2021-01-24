package com.example.me.repo

import com.example.me.response.FriendResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FriendResponseRepo : JpaRepository<FriendResponse, Int> {
    @Query(nativeQuery = true, value = "SELECT user_data.id as friend_id, username, first_name, last_name, avatar FROM user_data JOIN friend_data ON" +
            " (user_data.id = friend_data.sender_id AND friend_data.receiver_id = :userId) OR (user_data.id = friend_data.receiver_id AND friend_data.sender_id = :userId) WHERE friend_data.status = 1")
    fun getFriendList(
            @Param("userId") userId: Int
    ): MutableList<FriendResponse>

}