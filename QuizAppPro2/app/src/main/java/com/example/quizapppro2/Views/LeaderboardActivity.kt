package com.example.quizapppro2.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import android.widget.TextView
import androidx.core.view.isVisible
import com.example.quizapppro2.Class.Player
import com.example.quizapppro2.R


class LeaderboardActivity : AppCompatActivity() {

    private lateinit var tx1: TextView
    private lateinit var tx2: TextView
    private lateinit var tx3: TextView
    private lateinit var tx4: TextView
    private lateinit var tx5: TextView
    private lateinit var tx6: TextView

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView
    private lateinit var img5: ImageView
    private lateinit var img6: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        //val valores = Score.score.scoreboardName.toMutableList()
        //val valoresPlayer = Score.score.scoreboardPlayer.toMutableList()
        tx1 = findViewById(R.id.player1_textview)
        tx2 = findViewById(R.id.player2_textview)
        tx3 = findViewById(R.id.player3_textview)
        tx4 = findViewById(R.id.player4_textview)
        tx5 = findViewById(R.id.player5_textview)
        tx6 = findViewById(R.id.player6_textview)
        img1 = findViewById(R.id.imageView1)
        img2 = findViewById(R.id.imageView2)
        img3 = findViewById(R.id.imageView3)
        img4 = findViewById(R.id.imageView4)
        img5 = findViewById(R.id.imageView5)
        img6 = findViewById(R.id.imageView6)
        /*var imgs = mutableListOf(img1, img2, img3, img4, img5, img6)
        var tx = mutableListOf(tx1, tx2, tx3, tx4, tx5, tx6)
        var pos = 0

        while (pos < 6) {
            var aux = Player()
            if(valoresPlayer.size == pos)
            {
                valoresPlayer.add(aux)
            }
            if (valoresPlayer[pos].initials !="---") {
                tx[pos].setText("${valoresPlayer[pos].initials} ${valoresPlayer[pos].score}")
                if (valoresPlayer[pos].cheater) {
                    imgs[pos].isVisible = true
                } else {
                    if (pos == 0) {
                        imgs[pos].setImageResource(R.drawable.ic_trophy)
                        imgs[pos].isVisible=true
                    }
                }

            } else {
                tx[pos].setText("---")
            }
            pos++
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        setResult(RESULT_OK, intent)*/
    }
}
