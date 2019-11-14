package com.gp.droid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gp.droid.core.extensions.addTo
import com.gp.droid.core.services.LoginService
import com.gp.droid.core.services.NbaService
import com.gp.droid.entities.LoginBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_multithreading.*
import timber.log.Timber

class MultiThreadingActivity : AppCompatActivity() {

    private lateinit var loginService: LoginService
    private lateinit var nbaService: NbaService
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multithreading)

        (application as App).run {
            loginService = getService(LoginService::class.java)
            nbaService = getService(NbaService::class.java)
        }

        btnExecuteMultiThread.setOnClickListener {
            loginService.login(LoginBody("gilbertparreno", "test1234"))
                .flatMap {
                    Timber.d("$it")
                    Timber.d("thread #1: ${Thread.currentThread().name}")
                    nbaService.getNbaTeam("lakers").also {
                        Timber.d("thread #2: ${Thread.currentThread().name}")
                    }
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("thread #3: ${Thread.currentThread().name}")
                    Timber.d("success")
                }, {
                    Timber.d("thread #4: ${Thread.currentThread().name}")
                    Timber.d("I'm in thread: ${Thread.currentThread().name}")
                    Timber.e("error: ${it.message}")
                })
                .addTo(compositeDisposable)
        }

        btnExecuteSingleThread.setOnClickListener {
            nbaService.getNbaTeam("lakers")
                .also {
                    Timber.d("thread #1: ${Thread.currentThread().name}")
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("thread #2: ${Thread.currentThread().name}")
                    Timber.d("success")
                }, {
                    Timber.d("thread #3: ${Thread.currentThread().name}")
                    Timber.d("I'm in thread: ${Thread.currentThread().name}")
                    Timber.e("error: ${it.message}")
                })
                .addTo(compositeDisposable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}