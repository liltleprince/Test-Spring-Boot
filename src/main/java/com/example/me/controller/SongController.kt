package com.example.me.controller

import com.example.me.manager.SongManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
open class SongController {
    @Autowired
    private lateinit var songManager: SongManager
    @GetMapping("/api/searchSong")
    fun searchSong(
        @RequestParam("songName", required = false) songName: String?,
        @RequestParam("currentPage", required = false, defaultValue = "1") currentPage: Int
    ): Any {
        return songManager.searchSong(songName, currentPage)
    }
}