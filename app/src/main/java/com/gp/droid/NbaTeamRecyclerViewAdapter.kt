package com.gp.droid

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gp.droid.core.extensions.inflate
import com.gp.droid.entities.Players
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_nba_player.view.*

class NbaTeamRecyclerViewAdapter : RecyclerView.Adapter<NbaTeamRecyclerViewAdapter.ViewHolder>() {

    var players: List<Players> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_nba_player))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.playerName.text = players[position].name
        holder.itemView.playerPosition.text = players[position].position
        Picasso.get().load(players[position].imageUrl).into(holder.itemView.profileImage)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}