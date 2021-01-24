package com.example.me.controller

import com.example.me.manager.FriendManager
import com.example.me.manager.UserManager
import com.example.me.model.UserData
import com.example.me.request.FriendRequest
import com.example.me.request.LoginRequest
import com.example.me.request.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {
    @Autowired
    private lateinit var userManager: UserManager

    @Autowired
    private lateinit var friendManager: FriendManager

    @PostMapping("/register")
    fun register(
            @RequestBody request: RegisterRequest
    ): Any {
        return userManager.register(request)
    }

    @PostMapping("/login")
    fun login(
            @RequestBody request: LoginRequest
    ): Any {
        return userManager.login(request)
    }


    @GetMapping("/listUser")
    fun getListUser() : MutableList<UserData> {
        return userManager.getUserList()
    }

    @GetMapping("/friend/getList")
    fun getFriendList(
            @RequestParam("username", required = true) username: String
    ): Any {
        val user = userManager.findUserByUsername(username) ?: return "User not existed"
        return friendManager.getFriendList(user.id)
    }

    @PostMapping("/friend/makeFriendRequest")
    fun makeFriendRequest(
            @RequestBody request: FriendRequest
    ): Any {
        return friendManager.makeFriendRequest(request)
    }

    @PostMapping("/friend/acceptFriendRequest")
    fun acceptFriendRequest(
            @RequestBody request: FriendRequest
    ): Any {
        return friendManager.acceptFriendRequest(request)
    }
}