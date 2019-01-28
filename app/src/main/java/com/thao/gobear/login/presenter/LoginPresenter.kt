package com.thao.gobear.login.presenter

import android.os.Handler
import android.os.Looper
import com.thao.gobear.login.model.UserInterface
import com.thao.gobear.login.model.UserModel
import com.thao.gobear.login.view.LoginViewInterface


class LoginPresenter(iLoginView: LoginViewInterface) : LoginPresenterInterface {
    var loginViewInterface: LoginViewInterface? = iLoginView;
    var user: UserInterface? = null
    var handler: Handler? = null

    init {
        initUser()
        handler = Handler(Looper.getMainLooper())
    }

    override fun doLogin(name: String, pwd: String) {
        var isLoginSuccess: Boolean? = true
        val code = user?.checkUserValidity(name, pwd)
        if (code != 0) isLoginSuccess = false
        val result = isLoginSuccess
        handler?.postDelayed({ code?.let { result?.let { it1 -> loginViewInterface?.onLoginResult(it1, it) } } }, 1000)
    }

    override fun setProgressBarVisibility(visibility: Int) {
        loginViewInterface?.onSetProgressBarVisibility(visibility)
    }

    private fun initUser() {
        user = UserModel("GoBear", "GoBearDemo")
    }
}