package com.thao.gobear

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thao.gobear.login.presenter.LoginPresenter
import com.thao.gobear.login.view.LoginViewInterface
import com.thao.gobear.util.SharePrefUtils
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), LoginViewInterface {
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter(this);
        loginPresenter.setProgressBarVisibility(View.INVISIBLE);


        btnLogin.setOnClickListener {
            val isRemember = swtRemember.isChecked
            loginPresenter.setProgressBarVisibility(View.VISIBLE);
            SharePrefUtils.setBoolean(this, SharePrefUtils.Key.REMEMBER, isRemember)
            btnLogin.isEnabled = true;
            loginPresenter.doLogin(edtUserName.text.toString(), edtPwd.text.toString());
        }
    }

    override fun onSetProgressBarVisibility(visibility: Int) {
        progress_login.visibility = visibility;
    }

    override fun onLoginResult(result: Boolean, code: Int) {
        loginPresenter.setProgressBarVisibility(View.INVISIBLE);
        btnLogin.isEnabled = true;
        if (result) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            val intent = Intent(this, ArticleListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else
            Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
    }

}


