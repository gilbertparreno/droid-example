package com.gp.droid

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.DialogCompat
import com.google.gson.Gson
import com.gp.droid.core.extensions.addTo
import com.gp.droid.core.services.LoginService
import com.gp.droid.entities.LoginBody
import com.gp.droid.entities.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {

    private lateinit var loginService: LoginService
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginService = (application as App).getService(LoginService::class.java)

        btnLogin.setOnClickListener {
            loginService.login(
                LoginBody(
                    username.text.toString(),
                    password.text.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showDialog(it.message)
                }, {
                    showDialog("Can't proceed with login.")
                })
                .addTo(compositeDisposable)
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this).apply {
            setMessage(message)
        }.create().show()
    }
}