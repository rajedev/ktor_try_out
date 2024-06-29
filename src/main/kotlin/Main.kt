package org.example

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Post
import repo.FeedRepository

fun main(): Unit = runBlocking {
    launch {
        val post = Post(
            body = "this is body",
            id = 123,
            title = "this is title",
            userId = 1
        )

        val map = mutableMapOf<String, String>()
        map["body"] = "this is new body"

        with(FeedRepository()) {
            println("\ngetPosts: ${getPosts()[0].title} \n")
            println("get Comments: ${getComments("1")[0].email} \n")
            println("Create Post: ${postPost(post)} \n")
            println("Put Post: ${putPost(12, post)} \n")
            println("Patch Post: ${patchPost(12, map)} \n")
            println("Delete Post: ${deletePost(13).status} \n")
        }
    }
}




