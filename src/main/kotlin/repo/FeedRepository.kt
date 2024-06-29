package repo

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import model.Comment
import model.Post
import org.example.network.APIClient
import org.example.network.COMMENT_PATH
import org.example.network.ENDPOINTS
import org.example.network.POST_PATH

/**
 * Wish
 * Created by Rajendhiran Easu on 27/05/24.
 * Feed Repository to handle - Post and Comments - CRUD
 */

class FeedRepository {

    suspend fun getPosts(): List<Post> = APIClient.httpClient.get {
        url("$ENDPOINTS$POST_PATH")
    }.body()

    suspend fun getComments(id: String): List<Comment> = APIClient.httpClient.get {
        url("$ENDPOINTS$COMMENT_PATH")
        parameter("postId", id)
    }.body()

    suspend fun postPost(post: Post): Post = APIClient.httpClient.post {
        url("$ENDPOINTS$POST_PATH")
        setBody(post)
    }.body()

    suspend fun putPost(id: Int, post: Post): Post = APIClient.httpClient.put {
        url("$ENDPOINTS$POST_PATH/$id")
        setBody(post)
    }.body()

    suspend fun patchPost(id: Int, map: MutableMap<String, String>) = APIClient.httpClient.patch {
        url("$ENDPOINTS$POST_PATH/$id")
        setBody(map)
    }.body<Post>()

    suspend fun deletePost(id: Int): HttpResponse = APIClient.httpClient.delete {
        url("$ENDPOINTS$POST_PATH/$id")
    }
}