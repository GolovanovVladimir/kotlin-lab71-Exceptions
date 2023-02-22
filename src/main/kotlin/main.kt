package ru.netology

import java.lang.NullPointerException
import java.util.MissingFormatWidthException
import java.util.Objects
import javax.swing.tree.FixedHeightLayoutCache

fun main() {
   val post = Post(1,1,1,1,1,"cheking",1,1,1,"lya-lya",true,true,1,null,null)
   val post2 = Post(2,2,2,2,2,"cheking",2,2,2,"lya-lya",true,true,2,null,null)
   val post3 = Post(3,3,3,2,3,"cheking",3,3,3,"lya-lya",true,true,3,null,null)

   val liked = post.copy(likes = post.likes + 1)
   println(liked)
   val (id, owner_id,_,_,_,text) = post
   println("$id, $owner_id, $text")
   WallService.Add(post)
   WallService.Add(post2)
   WallService.update(post2)
   WallService.update(post3)
   WallService.printPosts()
   val comment0 = Comment(10,"678")
   try {
      val comment2 = WallService.createComment(1, comment0)
   } catch (e: NotImplementedError) {
      println("I'm catching NotImplementedError")
      }
   val comment3 = Comment(666,"12345")
   try {
      val comment4 = WallService.createComment(2, comment3) /* исправить 2 на 3 для проверки exception  c*/
   } catch (e: NotImplementedError) {
      println("I'm catching NotImplementedError")
   }

   WallService.printComments()
}

class PostNotFoundException(message: String): RuntimeException(message)

// part 6.2
interface Attachment {
   val type: String
}
data class AudioAttachment(val audio: Audio): Attachment {
   override val type: String = "Audio"
}
data class Audio(
   val id: Int,
   val duration: Int,
   val artist: String
)
data class VideoAttachment(val video: Video): Attachment {
   override val type: String = "Video"
}
data class Video(
   val id: Int,
   val platform: Int,
   val title: String
)
data class PhotoAttachment(val photo: Photo): Attachment {
   override val type: String = "Photo"
}
data class Photo(
   val id: Int,
   val width: Int,
   val height: Int
)
data class FileAttachment(val file: File): Attachment {
   override val type: String = "File"
}
data class File(
   val id: Int,
   val size: Int,
   val url: String
)
data class HistoryAttachment(val history: History): Attachment {
   override val type: String = "History"
}
data class History(
   val id: Int,
   val can_see: Int,
   val seen: Int
)
// __end of part 6.2__

data class Post(
   val id: Int,
   val owner_id: Int,
   val from_id: Int,
   val created_by: Int,
   val date: Int,
   val text: String,
   val reply_owner_id: Int,
   val reply_post_id: Int,
   val friends_only: Int,
   var comment: String,
   val marked_as_ads: Boolean,
   val is_favorite: Boolean,
   val likes: Int = 0,
   val original: Post?,
   val files: Attachment?
)


data class Comment(
   val id: Int,
   val text: String
/* val from_id: Int,
   val date: Int,
   val reply_to_user: Int,
   val reply_to_comment: Int,
   val attachments: Objects,
   val parents_stack = emptyArray<Int>() ,
   val thread: Objects
*/
   )
   {
   }


object WallService{
   private var posts = emptyArray<Post>()
   private var comments = emptyArray<Comment>()

   fun createComment(postId: Int, comments: Comment): Comment {
      if (findById(postId) == true) {
      val commentReturn = addComment(comments)
      return commentReturn
      }
      else {
        throw PostNotFoundException("No exist post with $postId")
      }
   }

   fun addComment(comment:Comment):Comment{
      println("New comment added")
      comments += comment
      val commentAdded = comment.copy(id = (WallService.uniqueNumberIdComment(comment.id)),"New comment added")
      return commentAdded
   }

   fun Add(post: Post): Post {
      println("Post added")
      posts += post
      val postAdded = post.copy(id = (WallService.uniqueNumberId(post.id) ))
      return postAdded
   //   добавлять запись в массив, но при этом назначать посту уникальный среди всех постов идентификатор;
   //   возвращать пост с уже выставленным идентификатором.
   }

   fun findById(id: Int) : Boolean {
      var flagExist : Boolean = false
      for ((index,post) in posts.withIndex()){
         if (post.id == id) flagExist = true
      }
      return flagExist
   }

   fun uniqueNumberId(id: Int) : Int {
      var maxId : Int = -1
      for ((index,post) in posts.withIndex()){
         if (post.id > maxId) maxId = post.id
      }
      return maxId + 1
   }

   fun uniqueNumberIdComment(id: Int) : Int {
      var maxId : Int = -1
      for ((index,comment) in comments.withIndex()){
         if (comment.id > maxId) maxId = comment.id
      }
      return maxId + 1
   }

   fun update(post: Post): Boolean {
      println("Post updated")
      if (findById(post.id)==true) {
         post.comment= "Updated"
         return true
      }
      else {
         return false
      }
   //находить среди всех постов запись с тем же id, что и у post и обновлять все свойства, кроме id владельца и даты создания;
   // если пост с таким id не найден, то ничего не происходит и возвращается false, в противном случае – возвращается true.
   }
   fun printPosts(){
      for (post in posts){
         println(post)
      }
      println()
   }

   fun printComments(){
      for (comment in WallService.comments){
         println(comment)
      }
      println()
   }

}