package com.example.quizapppro2.Class

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.quizapppro2.R

class MyListAdapter(private val context: Activity, private var playerLists: Array<Player_list>) :
    ArrayAdapter<Player_list>(context, R.layout.custom_listview, playerLists) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_listview, null, true)

        var playerPosition = rowView.findViewById(R.id.tvPos) as TextView
        val playerName = rowView.findViewById(R.id.tvName) as TextView
        val playerScore = rowView.findViewById(R.id.tvScore) as TextView
        var playerAvatar = rowView.findViewById(R.id.ivAvatar) as ImageView
        val playerIcon = rowView.findViewById(R.id.ivIcon) as ImageView

        playerName.text = playerLists[position].Name
        playerScore.text = playerLists[position].Score
        playerPosition.text =
            if (position == 1) "${position}ST." else if (position == 2) "${position}ND." else if (position == 3) "${position}RD." else "${position}TH."
        playerAvatar.setImageResource(playerLists[position].Avatar)

        if (playerLists[position].Icon != null) {
            playerIcon.isVisible = true
            playerIcon.setImageResource(playerLists[position].Icon!!)
        }
        return rowView
    }
}