package com.gp.droid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gp.droid.core.extensions.addTo
import com.gp.droid.core.services.NbaService
import com.gp.droid.fragmentExample.ContainerActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFragment.setOnClickListener {
            startActivity(Intent(this, ContainerActivity::class.java))
        }

        btnNetWorkCall.setOnClickListener {
            startActivity(Intent(this, NetworkCallActivity::class.java))
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnMultiThreading.setOnClickListener {
            startActivity(Intent(this, MultiThreadingActivity::class.java))
        }
    }
}
