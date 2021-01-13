package com.example.me.manager

import com.example.me.model.MusicOnline
import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
open class SongManager {
    fun searchSong(songName: String?, currentPage: Int): MutableList<MusicOnline> {
        return if (songName == null) {
            searchMusic("", currentPage,
                    "https://chiasenhac.vn/tim-kiem?q={0}&page_music={1}&filter=")
        } else {
            searchMusic(songName, currentPage,
                    "https://chiasenhac.vn/tim-kiem?q={0}&page_music={1}&filter=")
        }

    }

    private fun searchMusic(songName: String, page: Int = 1, linkOrigin: String): MutableList<MusicOnline> {
        var newName = songName
        while (newName.contains("  ")) {
            newName = newName.replace("  ", " ")
        }
        newName = newName.trim()
        val link = linkOrigin
                .replace("{0}", newName)
                .replace("{1}", page.toString())
                .replace(" ", "%20")
        val listMusic = mutableListOf<MusicOnline>()
        val doc = Jsoup.connect(link).get()
        for (element in doc.selectFirst("div.tab-content").select("li.media")) {
            try {
                var linkHtml = element.selectFirst("div.media-left").selectFirst("a").attr("href")
                if (!linkHtml.startsWith("http")) {
                    linkHtml = "https://vi.chiasenhac.vn$linkHtml"
                }
                val title = element.selectFirst("div.media-left").selectFirst("a").attr("title")
                val linkImage =
                        element.selectFirst("div.media-left").selectFirst("a").selectFirst("img")
                                .attr("src")
                val artist = element.selectFirst("div.author").text()
                listMusic.add(
                        MusicOnline(
                                title, artist, linkHtml, linkImage, id = linkHtml
                        )
                )
            } catch (e: Exception) {

            }
        }

        return listMusic
    }
}