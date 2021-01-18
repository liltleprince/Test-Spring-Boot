package com.example.me.manager

import com.example.me.model.UserData
import com.example.me.repo.UserRepository
import com.example.me.request.RegisterRequest
import com.example.me.response.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserManager {
    @Autowired
    private lateinit var userRepo: UserRepository
    fun register(request: RegisterRequest) : CommonResponse {
        val user = userRepo.findByUserName(request.username)
        println(user)
        if (user != null) {
            return CommonResponse(1, "Username existed")
        }
        val userData = UserData()
        userData.avatar = request.avatar
        userData.firstName = request.firstName
        userData.lastName = request.lastName
        userData.username = request.username
        userData.password = request.password
        userData.sex = request.sex
        userRepo.save(userData)
        return CommonResponse(userData)
    }

    fun getUserList() : MutableList<UserData>{
        return userRepo.findAll()
    }
}