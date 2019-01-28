package com.thao.gobear.login.presenter

interface LoginPresenterInterface {
    fun doLogin(name: String, pwd: String)
    fun setProgressBarVisibility(visibility: Int)
}