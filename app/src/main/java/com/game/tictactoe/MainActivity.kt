package com.game.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    var play = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..8) btnClick(i)

        findViewById<LinearLayout>(R.id.refresh).setOnClickListener {
            refresh()
        }

    }

    fun refresh() {
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 0 until arrayOfbtnId.size) {
                findViewById<TextView>(arrayOfbtnId[i]).setTextSize(TypedValue.COMPLEX_UNIT_SP, 60f)
                findViewById<TextView>(arrayOfbtnId[i]).text = ""
                delay(80)
            }
        }
        value = true
    }

    fun btnClick(index: Int) {

        val btn = findViewById<TextView>(arrayOfbtnId[index])

        btn.setOnClickListener {

            if (btn.text.isEmpty() && play) {

                if (value) {
                    btn.setTextColor(Color.parseColor("#F3C623"))
                    btn.text = "X"
                    value = false
                } else {
                    btn.setTextColor(Color.parseColor("#E2F1E7"))
                    btn.text = "0"
                    value = true
                }

                checkWinner(0, 1, 2)
                checkWinner(3, 4, 5)
                checkWinner(6, 7, 8)
                checkWinner(0, 3, 6)
                checkWinner(1, 4, 7)
                checkWinner(2, 5, 8)
                checkWinner(0, 4, 8)
                checkWinner(2, 4, 6)
                checkTie()
            }
        }
    }

    @SuppressLint("CutPasteId", "SetTextI18n")
    fun checkTie() {
        CoroutineScope(Dispatchers.Main).launch {
            for (i in arrayOfbtnId.indices) {
                if (findViewById<TextView>(arrayOfbtnId[i]).text.isEmpty()) {
                    break
                }
                if (i == (arrayOfbtnId.size - 1) && play) {
                    Log.d("----t----", "checkWinner: tie")
                    val tie = findViewById<TextView>(R.id.tie).text.toString().toInt()
                    findViewById<TextView>(R.id.tie).text = "${tie + 1}"
                    delay(3000)
                    refresh()
                }
            }
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
            val animation = AnimationUtils.loadAnimation(this, R.anim.zoom)

            CoroutineScope(Dispatchers.Main).launch {

                b1.setBackgroundColor(Color.parseColor("#C62E2E"))
                delay(300)
                b2.setBackgroundColor(Color.parseColor("#C62E2E"))
                delay(300)
                b3.setBackgroundColor(Color.parseColor("#C62E2E"))

                b1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
                b2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
                b3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)

                b1.startAnimation(animation)
                b2.startAnimation(animation)
                b3.startAnimation(animation)

                delay(3000)

                b1.setBackgroundColor(Color.parseColor("#243642"))
                b2.setBackgroundColor(Color.parseColor("#243642"))
                b3.setBackgroundColor(Color.parseColor("#243642"))
                refresh()

                b1.clearAnimation()
                b2.clearAnimation()
                b3.clearAnimation()

                play = true
            }
        }
    }
}