package com.example.me.controller

import com.example.me.manager.FriendManager
import com.example.me.manager.UserManager
import com.example.me.request.FriendRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class FriendController {
    @Autowired
    private lateinit var friendManager: FriendManager

    @Autowired
    private lateinit var userManager: UserManager

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