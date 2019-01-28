package com.thao.gobear.login.model

class UserModel(override var name: String, override var pwd: String) : UserInterface {
    override fun checkUserValidity(name: String, pwd: String): Int {
        if (name != this.name || pwd != this.pwd) {
            return -1;
        }
        return 0;
    }

}