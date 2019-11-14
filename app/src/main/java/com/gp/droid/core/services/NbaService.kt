package com.gp.droid.core.services

import com.gp.droid.entities.Players
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NbaService {

    @GET("api/{team}")
    fun getNbaTeam(@Path("team") team: String): Single<List<Players>>
}