package com.example.me.manager

import com.example.me.model.UserData
import com.example.me.repo.UserRepository
import com.example.me.request.LoginRequest
import com.example.me.request.RegisterRequest
import com.example.me.response.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserManager {
    @Autowired
    private lateinit var userRepo: UserRepository
    fun register(request: RegisterRequest): CommonResponse {
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
        userData.sex = request.sex

        val encoder = BCryptPasswordEncoder()
        userData.password = encoder.encode(request.password)


        userRepo.save(userData)
        return CommonResponse(userData)
    }

    fun getUserList(): MutableList<UserData> {
        return userRepo.findAll()
    }

    fun findUserByUsername(username: String): UserData? {
        return userRepo.findByUserName(username)
    }

    fun login(request: LoginRequest): CommonResponse {
        val user = userRepo.findByUserName(request.username)
                ?: return CommonResponse(2, "Username not found or Password is wrong")
        val encoder = BCryptPasswordEncoder()
        if (!encoder.matches(request.password, user.password)) {
            return CommonResponse(2, "Username not found or Password is wrong")
        }
        return CommonResponse(user)
    }
}