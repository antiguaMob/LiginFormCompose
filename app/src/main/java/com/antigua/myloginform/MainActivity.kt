package com.antigua.myloginform

// Jetpack Compose - Login Form (Build a Login Screen Part 1)
// https://www.youtube.com/watch?v=okX4Is32Lyw&list=PLIrQ_OvOXNvs-T5pmvc8lxPaSk7AzEc-o
//      Part 1 of a series for Building a login screen with Jetpack compose.
//      In this part we will be the UI of the screen.

// Jetpack Compose - Login Form Validation (Build a Login Screen Part 2)
// https://www.youtube.com/watch?v=NvIisC97_9Q&list=PLIrQ_OvOXNvs-T5pmvc8lxPaSk7AzEc-o&index=2
//      Part 2 of a series for Building a login screen with Jetpack compose.
//      In this part 2 we will add validation and enabling sign in only if other fields are valid.

// Jetpack Compose - Login Keyboard Actions (Build a Login Screen Part 3)
// https://www.youtube.com/watch?v=u9DjwUwI-g4&list=PLIrQ_OvOXNvs-T5pmvc8lxPaSk7AzEc-o&index=4
//      Part 3 of a series for Building a login screen with Jetpack compose.
//      In this part 3 we will add keyboard actions to make the UI easy to use with a phone keyboard.

// Authentication implementation: ViewModel and Github api using Retrofit (Build a Login Screen Part 4)
// https://www.youtube.com/watch?v=qpSmjgIcfg4&list=PLIrQ_OvOXNvs-T5pmvc8lxPaSk7AzEc-o&index=4
//      Part 4 of a series for Building a login screen with Jetpack compose.
//      In this part 4 we will add authentication implementation using retrofit and okhttp to login to github api.

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.antigua.myloginform.sigin.SignInScreen
import com.antigua.myloginform.sigin.SignInViewModel
import com.antigua.myloginform.sigin.SignInViewModelFactory
import com.antigua.myloginform.theme.MyLoginFormTheme

class MainActivity : ComponentActivity() {

    private val viewModel: SignInViewModel by viewModels{ SignInViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLoginFormTheme {
                SignInScreen{ username: String ,password: String ->
                    viewModel.signIn(username,password)
                }
            }
        }
    }
}

