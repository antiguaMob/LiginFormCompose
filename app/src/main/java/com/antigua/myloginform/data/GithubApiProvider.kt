package com.antigua.myloginform.data

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GithubApiProvider {
    fun newGithubApi(username: String, password: String): GithubApi? {
        // Setup authentication
        // Basic authentication
        // authToken = "Basic base64.encode(username:password)" // Base64 // non ASCII character

        val credentials = ("$username:$password").toByteArray()
        val authToken = "Basic " + Base64.encodeToString(credentials,Base64.NO_WRAP)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                    .header("Accept","application/vnd.github.v3+json")
                    .header("Authorization",authToken)

                val request = builder.build()
                chain.proceed(request)
            }.build()
        //create retrofit instance
        // https://api.github.com
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(GithubApi::class.java)
    }


}