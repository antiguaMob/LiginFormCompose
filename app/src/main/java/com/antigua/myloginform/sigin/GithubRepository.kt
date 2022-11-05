package com.antigua.myloginform.sigin

import com.antigua.myloginform.data.GithubApi
import com.antigua.myloginform.data.GithubApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubRepository {
    private var githubApi: GithubApi? =null

    fun singIn(username: String, password: String) {
        githubApi = GithubApiProvider.newGithubApi(username, password)

        CoroutineScope(Dispatchers.IO).launch {
            val repos = githubApi?.getUserRepos(username)
            println("Repository ${repos?.size}")
            repos?.map { println("repo id ${it.id} and name ${it.name}") }
        }
    }
}