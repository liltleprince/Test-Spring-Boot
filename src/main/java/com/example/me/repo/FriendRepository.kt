package com.example.me.repo

import com.example.me.model.FriendData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FriendRepository : JpaRepository<FriendData, Int> {
    @Query(nativeQuery = true, value = "SELECT * FROM friend_data WHERE sender_id = :sender_id AND receiver_id = :receiver_id")
    fun getFriendData(
            @Param("sender_id") senderId: Int,
            @Param("receiver_id") receiverId: Int
    ): FriendData?

    @Query(nativeQuery = true, value = "UPDATE friend_data SET status = 1 WHERE id = :id")
    fun acceptFriend(
            @Param("id") id: Int
    )
}