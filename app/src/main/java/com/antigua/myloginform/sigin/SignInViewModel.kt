package com.antigua.myloginform.sigin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignInViewModel(
    private val githubRepository: GithubRepository
): ViewModel() {
    fun signIn(username: String, password: String){
        githubRepository.singIn(username,password)
    }
}

@Suppress("UNCHECKED_CAST")
class SignInViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(SignInViewModel::class.java)){
           return SignInViewModel(GithubRepository()) as T
       }
        throw IllegalArgumentException("Unknown  view model class")
    }

}