package com.uma.tictaktoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.uma.tictaktoe.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class TicTakToeActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    var player = 0
    var cellId = 0
    lateinit var player1List: ArrayList<Int>
    lateinit var player2List: ArrayList<Int>
    var count = 0
    var isMatchCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.ticTakToeBinding = TicTakToeVM()

        player1List = ArrayList()
        player2List = ArrayList()

//        recyclerView.layoutManager = GridLayoutManager(this,3)
//        recyclerView.adapter = TicTakToeAdapter()
    }

    fun btnClick(v: View) {
        count++
        val tv = v as TextView
        when (tv) {
            tv1 -> cellId = 1
            tv2 -> cellId = 2
            tv3 -> cellId = 3
            tv4 -> cellId = 4
            tv5 -> cellId = 5
            tv6 -> cellId = 6
            tv7 -> cellId = 7
            tv8 -> cellId = 8
            tv9 -> cellId = 9
            else -> cellId = 0
        }
        player = when (player) {
            1 -> {
                2
            }
            2 -> {
                1
            }
            else -> 1
        }
        playGame(tv)
    }

    private fun playGame(view: TextView) {
        if (player == 1) {
            player1List.add(cellId)
            for (i in 1 until 10) {
                if (i == cellId) {
                    view.setBackgroundColor(resources.getColor(R.color.blue))
                    val p1 = "P1"
                    view.text = p1
                    view.isClickable = false
                }
            }
        } else {
            player2List.add(cellId)
            for (i in 1 until 10) {
                if (i == cellId) {
                    view.setBackgroundColor(resources.getColor(R.color.red))
                    val p2 = "P2"
                    view.text = p2
                    view.isClickable = false
                }
            }
        }
        if (count == 9 && findWinner()) {
            "Do you want to Shuffle the game?".alertDialog("No more moves:(")
        } else
            findWinner()

    }

    private fun findWinner(): Boolean {
        if (player == 1) {
            if (player1List.containsAll(listOf(1, 2, 3)) ||
                player1List.containsAll(listOf(4, 5, 6)) ||
                player1List.containsAll(listOf(7, 8, 9)) ||
                player1List.containsAll(listOf(1, 4, 7)) ||
                player1List.containsAll(listOf(2, 5, 8)) ||
                player1List.containsAll(listOf(3, 6, 9)) ||
                player1List.containsAll(listOf(1, 5, 9)) ||
                player1List.containsAll(listOf(3, 5, 7))
            ) {
                isMatchCompleted = true
                "Do you want to play Again?".alertDialog("Player1 is winner")
                Toast.makeText(this, "player1 is winner", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        if (player == 2) {
            if (player2List.containsAll(listOf(1, 2, 3)) ||
                player2List.containsAll(listOf(4, 5, 6)) ||
                player2List.containsAll(listOf(7, 8, 9)) ||
                player2List.containsAll(listOf(1, 4, 7)) ||
                player2List.containsAll(listOf(2, 5, 8)) ||
                player2List.containsAll(listOf(3, 6, 9)) ||
                player2List.containsAll(listOf(1, 5, 9)) ||
                player2List.containsAll(listOf(3, 5, 7))
            ) {
                isMatchCompleted = true
                "Do you want to play Again?".alertDialog("Player2 is winner")
                Toast.makeText(this, "player2 is winner", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun String.alertDialog(title: String) {
        val dialog = AlertDialog.Builder(this@TicTakToeActivity)
        dialog.setTitle(title)
        dialog.setMessage(this)
        dialog.setPositiveButton("Yes") { _, _ ->
            recreate()
        }
        dialog.setNegativeButton("No") { _, _ ->
            tv1.isClickable = false
            tv2.isClickable = false
            tv3.isClickable = false
            tv4.isClickable = false
            tv5.isClickable = false
            tv6.isClickable = false
            tv7.isClickable = false
            tv8.isClickable = false
            tv9.isClickable = false
        }

        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.playAgain -> {
                if (count == 9 || isMatchCompleted) recreate()
                else Toast.makeText(
                    this,
                    "Game in progress please wait...",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
            }
        }
        return true
    }

}
