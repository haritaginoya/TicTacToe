package com.game.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


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

    private var value = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..8) btnClick(i)

    }

    fun btnClick(index: Int) {

        val btn = findViewById<TextView>(arrayOfbtnId[index])
        btn.setOnClickListener {

            if (btn.text.isEmpty()) {
                if (value) {
                    btn.text = "X"
                    value = false
                } else {
                    btn.setTextColor(Color.parseColor("#E2F1E7"))
                    btn.text = "0"
                    value = true
                }
            }

            checkWinner(arrayOfbtnId[0], arrayOfbtnId[1], arrayOfbtnId[2])
            checkWinner(arrayOfbtnId[3], arrayOfbtnId[4], arrayOfbtnId[5])
            checkWinner(arrayOfbtnId[6], arrayOfbtnId[7], arrayOfbtnId[8])
            checkWinner(arrayOfbtnId[0], arrayOfbtnId[3], arrayOfbtnId[6])
            checkWinner(arrayOfbtnId[1], arrayOfbtnId[4], arrayOfbtnId[7])
            checkWinner(arrayOfbtnId[2], arrayOfbtnId[5], arrayOfbtnId[8])
            checkWinner(arrayOfbtnId[0], arrayOfbtnId[4], arrayOfbtnId[8])
            checkWinner(arrayOfbtnId[2], arrayOfbtnId[4], arrayOfbtnId[6])


        }
    }

    fun checkWinner(i1: Int, i2: Int, i3: Int) {

        val b1 = findViewById<TextView>(arrayOfbtnId[i1]).text
        val b2 = findViewById<TextView>(arrayOfbtnId[i2]).text
        val b3 = findViewById<TextView>(arrayOfbtnId[i3]).text


    }

}