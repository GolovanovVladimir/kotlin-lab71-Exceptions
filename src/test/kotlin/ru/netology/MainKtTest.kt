package ru.netology

import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun wallServiceAddCommentTrue() {
        val post = Post(1,1,1,1,1,"cheking",1,1,1,"lya-lya",true,true,1,null,null)
        var postId = post.id
//      var postId = 2
        var result = false
        val comment0 = Comment(10,"678")
        WallService.Add(post)
/*       result= WallService.findById(postId)
        if (result == true)
        {
            WallService.createComment(postId, comment0)
        }
        assertEquals(true, result)
*/
        result= WallService.findById(postId)
        assertEquals(true, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post = Post(1,1,1,1,1,"cheking",1,1,1,"lya-lya",true,true,1,null,null)
        var postId = 2
        var result = false
        val comment0 = Comment(10,"678")
        WallService.Add(post)
/*        if (WallService.findById(postId) == true){
            WallService.createComment(postId, comment0)
        }
        else
        {
            throw PostNotFoundException("No exist post with ${post.id}")
        }
*/
        WallService.createComment(postId, comment0)
    }
}