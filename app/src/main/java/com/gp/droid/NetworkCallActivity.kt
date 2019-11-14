package com.gp.droid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.gp.droid.core.extensions.addTo
import com.gp.droid.core.services.NbaService
import com.gp.droid.entities.Players
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_network_call.*

class NetworkCallActivity : AppCompatActivity() {

    private lateinit var nbaService: NbaService
    private val compositeDisposable = CompositeDisposable()
    private val adapter = NbaTeamRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_call)

        nbaService = (application as App).getService(NbaService::class.java)
        nbaService.getNbaTeam("lakers")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ playersFromNetwork ->
                adapter.players = playersFromNetwork
            }, {
                System.err.println(it)
            })
            .addTo(compositeDisposable)

        with(teamRosterList) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@NetworkCallActivity.adapter
        }
    }
}