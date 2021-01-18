package com.example.me.controller

import com.example.me.manager.UserManager
import com.example.me.model.UserData
import com.example.me.request.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired
    private lateinit var userManager: UserManager

    @PostMapping("/register")
    fun register(
            @RequestBody request: RegisterRequest
    ): Any {
        return userManager.register(request)
    }

    @GetMapping("/listUser")
    fun getListUser() : MutableList<UserData> {
        return userManager.getUserList()
    }
}