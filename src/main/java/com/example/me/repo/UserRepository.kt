package com.example.me.repo

import com.example.me.model.UserData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserData, Int> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_data WHERE username = :username LIMIT 1")
    fun findByUserName(
            @Param(value = "username") username: String
    ) : UserData?
}