package com.gp.droid.fragmentExample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gp.droid.R

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val newFragment = UserDetailsFragment().apply {
            this.nameWithInitial = "Gilbert P."
            this.position = "Android Developer"
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment)
        transaction.commit()
    }
}