package com.antigua.myloginform.data

import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
   @GET("users/{username}/repos?per_page=100")
    suspend fun getUserRepos(
       @Path("username") username: String
    ): List<Repo>
}

@JsonClass(generateAdapter = true)
data class Repo(
    val id: Long,
    val name: String
)