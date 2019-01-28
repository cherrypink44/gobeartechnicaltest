package com.thao.gobear.login.model

public interface UserInterface {
    val name: String
    val pwd: String
    fun checkUserValidity(name: String, pwd: String): Int
}