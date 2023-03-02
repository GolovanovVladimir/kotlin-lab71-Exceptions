package ru.netology

import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun wallServiceAddCommentTrue() {
        val post = Post(1,1,1,1,1,"cheking",1,1,1,"lya-lya",true,true,1,null,null)
        var postId = 1
        val comment0 = Comment(10,"678")
        WallService.Add(post)
        WallService.createComment(postId, comment0)
        assertTrue(true)

    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post = Post(1,1,1,1,1,"cheking",1,1,1,"lya-lya",true,true,1,null,null)
        var postId = 2
        var result = false
        val comment0 = Comment(10,"678")
        WallService.Add(post)
        WallService.createComment(postId, comment0)
    }
}