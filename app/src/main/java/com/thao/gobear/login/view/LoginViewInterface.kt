package com.thao.gobear.login.view

public interface LoginViewInterface {
    fun onLoginResult(result: Boolean, code: Int)
    fun onSetProgressBarVisibility(visibility: Int)
}