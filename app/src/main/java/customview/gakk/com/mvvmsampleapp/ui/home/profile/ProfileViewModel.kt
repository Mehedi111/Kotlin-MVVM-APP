package customview.gakk.com.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import customview.gakk.com.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(userRepository: UserRepository) : ViewModel() {

    val user = userRepository.getUser()
}
