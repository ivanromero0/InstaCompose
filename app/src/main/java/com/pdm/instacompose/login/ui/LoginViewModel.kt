package com.pdm.instacompose.login.ui

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.pdm.instacompose.login.data.OfflineUsersRepository
import com.pdm.instacompose.login.data.User

class LoginViewModel(private val usersRepository: OfflineUsersRepository) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _chkState = MutableLiveData<Boolean>()
    val chkState: LiveData<Boolean> = _chkState

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    var userInfo by mutableStateOf(UserInfo())

    fun toggleCheck() {
        _chkState.value = !_chkState.value
    }
    private fun validCredentials() {
        _isLoginEnabled.value = _password.value.length > 7 &&
            Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
    }
    fun onLoginChange(newMail:String, newPass:String) {
        _email.value = newMail
        _password.value = newPass
        userInfo = UserInfo(email = newMail, password = newPass)
        validCredentials()
    }


    suspend fun insertUser() {
        usersRepository.insertUser(userInfo.toUser())
    }

    suspend fun validateUser(email: String, password: String):Boolean {
        val user = usersRepository.getUserByEmailStream(email)?.toUserInfo()
        if (user != null) {
            if (user.password == password) {
                return true
            }
        }
        return false
    }
}

data class UserInfo (
    val id: Int =0,
     val firstName: String ="",
    val lastName: String?="",
    val email: String="",
     val password: String=""
    )

fun UserInfo.toUser(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)

fun User.toUserInfo(): UserInfo = UserInfo(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)