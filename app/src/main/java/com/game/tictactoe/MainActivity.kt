package com.game.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var arrayOfbtnId = arrayOf(
        R.id.btn_0,
        R.id.btn_1,
        R.id.btn_2,
        R.id.btn_3,
        R.id.btn_4,
        R.id.btn_5,
        R.id.btn_6,
        R.id.btn_7,
        R.id.btn_8
    )
    private val tie = 0;
    private var value = true
    var play = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..8) btnClick(i)

    }

    fun btnClick(index: Int) {

        val btn = findViewById<TextView>(arrayOfbtnId[index])
        btn.setOnClickListener {

            if (btn.text.isEmpty() && play) {
                if (value) {
                    btn.text = "X"
                    value = false
                } else {
                    btn.setTextColor(Color.parseColor("#E2F1E7"))
                    btn.text = "0"
                    value = true
                }
            }

            checkWinner(0, 1, 2)
            checkWinner(3, 4, 5)
            checkWinner(6, 7, 8)
            checkWinner(0, 3, 6)
            checkWinner(1, 4, 7)
            checkWinner(2, 5, 8)
            checkWinner(0, 4, 8)
            checkWinner(2, 4, 6)


        }
    }

    @SuppressLint("CutPasteId", "SetTextI18n")
    fun checkWinner(i1: Int, i2: Int, i3: Int) {

        val b1 = findViewById<TextView>(arrayOfbtnId[i1])
        val b2 = findViewById<TextView>(arrayOfbtnId[i2])
        val b3 = findViewById<TextView>(arrayOfbtnId[i3])

        if (b1.text.equals(b2.text) && b1.text.equals(b3.text) && !b1.text.equals("") && play) {

            play = false
            if (b1.text.equals("X")) {
                val player_x = findViewById<TextView>(R.id.player_x).text.toString().toInt()
                findViewById<TextView>(R.id.player_x).text = "${player_x + 1}"
            } else {
                val player_0 = findViewById<TextView>(R.id.player_0).text.toString().toInt()
                findViewById<TextView>(R.id.player_0).text = "${player_0 + 1}"
            }
            b1.setBackgroundColor(Color.parseColor("#E2F1E7"))
        }

    }

}