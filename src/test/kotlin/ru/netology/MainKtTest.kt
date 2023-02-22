package ru.netology

import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun wallServiceAddCommentTrue() {
        var postId = 1
        var result: Boolean = WallService.findById(postId)
        assertEquals(true,result)
    }

// (expected = PostNotFoundException::class)
    @Test
    fun shouldThrow() {
        var postId = -1
        if (WallService.findById(postId) == false) {
            throw PostNotFoundException("No exist post with $postId")
        }
    }
}