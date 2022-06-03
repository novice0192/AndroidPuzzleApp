package com.example.mathappdelta

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val oprtrlst = listOf("+", "-", "*", "/")
    private val oprtrids: List<Int> = listOf(R.id.opView1, R.id.opView2, R.id.opView3, R.id.opView4, R.id.opView5)
    private val numids: List<Int> = listOf(R.id.numView11, R.id.numView12, R.id.numView13, R.id.numView14, R.id.numView15, R.id.numView21, R.id.numView22, R.id.numView23, R.id.numView24, R.id.numView25)
    private val resids: List<Int> = listOf(R.id.resView1, R.id.resView2, R.id.resView3, R.id.resView4, R.id.resView5)
    private val textids: List<Int> = listOf(R.id.textView11, R.id.textView12, R.id.textView21, R.id.textView22, R.id.textView31, R.id.textView32, R.id.textView41, R.id.textView42, R.id.textView51, R.id.textView52)
    private val numlst = mutableListOf<Int>()

    private var score = 0
    private var life = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val randoprtr = oprtrgen()
        opassgn(randoprtr)
        numlst.addAll(numgen(randoprtr))
        val dump: TextView = findViewById(R.id.textnumView11)
        dump.text = numlst.toString()
        numassgn(numlst)
        resassgn(randoprtr, numlst)
        textclear()

        var sel_num = ""
        var sel_num_id = 0

        // Selecting number tile

        fun sel_box(sel_field: TextView) {
            if (sel_field.getText().toString() != "") {
                dehglgt(sel_num_id)
                sel_field.background = getDrawable(R.drawable.round_corner_hg)
                sel_num = sel_field.text.toString()
                sel_num_id = sel_field.id
            }
        }

        val sel_num_field1: TextView? = findViewById(R.id.numView11)
        sel_num_field1!!.setOnClickListener {
            sel_box(sel_num_field1)
        }

        val sel_num_field2: TextView? = findViewById(R.id.numView12)
        sel_num_field2!!.setOnClickListener {
            sel_box(sel_num_field2)
        }

        val sel_num_field3: TextView? = findViewById(R.id.numView13)
        sel_num_field3!!.setOnClickListener {
            sel_box(sel_num_field3)
        }

        val sel_num_field4: TextView? = findViewById(R.id.numView14)
        sel_num_field4!!.setOnClickListener {
            sel_box(sel_num_field4)
        }

        val sel_num_field5: TextView? = findViewById(R.id.numView15)
        sel_num_field5!!.setOnClickListener {
            sel_box(sel_num_field5)
        }

        val sel_num_field6: TextView? = findViewById(R.id.numView21)
        sel_num_field6!!.setOnClickListener {
            sel_box(sel_num_field6)
        }

        val sel_num_field7: TextView? = findViewById(R.id.numView22)
        sel_num_field7!!.setOnClickListener {
            sel_box(sel_num_field7)
        }

        val sel_num_field8: TextView? = findViewById(R.id.numView23)
        sel_num_field8!!.setOnClickListener {
            sel_box(sel_num_field8)
        }

        val sel_num_field9: TextView? = findViewById(R.id.numView24)
        sel_num_field9!!.setOnClickListener {
            sel_box(sel_num_field9)
        }

        val sel_num_field10: TextView? = findViewById(R.id.numView25)
        sel_num_field10!!.setOnClickListener {
            sel_box(sel_num_field10)
        }

        // Placing number in equation

        fun eqn_box(eqn_field: TextView) {
            if ((sel_num_id > 0) and ("" == eqn_field.text.toString())) {
                eqn_field.text = sel_num
                val sel_num_field: TextView = findViewById(sel_num_id)
                sel_num_field.text = ""
                sel_num = ""
                dehglgt(sel_num_id)
            }
        }

        val eqn_num_field1: TextView? = findViewById(R.id.textView11)
        eqn_num_field1!!.setOnClickListener {
            eqn_box(eqn_num_field1)
        }

        val eqn_num_field2: TextView? = findViewById(R.id.textView12)
        eqn_num_field2!!.setOnClickListener {
            eqn_box(eqn_num_field2)
        }

        val eqn_num_field3: TextView? = findViewById(R.id.textView21)
        eqn_num_field3!!.setOnClickListener {
            eqn_box(eqn_num_field3)
        }

        val eqn_num_field4: TextView? = findViewById(R.id.textView22)
        eqn_num_field4!!.setOnClickListener {
            eqn_box(eqn_num_field4)
        }

        val eqn_num_field5: TextView? = findViewById(R.id.textView31)
        eqn_num_field5!!.setOnClickListener {
            eqn_box(eqn_num_field5)
        }

        val eqn_num_field6: TextView? = findViewById(R.id.textView32)
        eqn_num_field6!!.setOnClickListener {
            eqn_box(eqn_num_field6)
        }

        val eqn_num_field7: TextView? = findViewById(R.id.textView41)
        eqn_num_field7!!.setOnClickListener {
            eqn_box(eqn_num_field7)
        }

        val eqn_num_field8: TextView? = findViewById(R.id.textView42)
        eqn_num_field8!!.setOnClickListener {
            eqn_box(eqn_num_field8)
        }

        val eqn_num_field9: TextView? = findViewById(R.id.textView51)
        eqn_num_field9!!.setOnClickListener {
            eqn_box(eqn_num_field9)
        }

        val eqn_num_field10: TextView? = findViewById(R.id.textView52)
        eqn_num_field10!!.setOnClickListener {
            eqn_box(eqn_num_field10)
        }

        val reset_puzzle: Button = findViewById(R.id.reset)
        reset_puzzle.setOnClickListener {
            dehglgt(sel_num_id)
            sel_num = ""
            sel_num_id = 0
            textclear()
            numassgn(numlst)
        }

        val submit: Button = findViewById(R.id.submit)
        submit.setOnClickListener {
            val wrong = R.id.imageView1
            if (submit.text.toString() != getString(R.string.submit_button)) {
                if (life==0) { scorePage(score, life) }
                else {
                    newpuzzle()
                    reset_puzzle.visibility = View.VISIBLE
                    for (i in wrong until wrong + 5) {
                        findViewById<ImageView>(i).visibility = View.INVISIBLE
                    }
                    submit.text = getString(R.string.submit_button)
                }
            }
            else {
                var flag = 0
                for (i in textids) {
                    if (findViewById<TextView>(i).text == "") {
                        flag = 1
                        break
                    }
                }
                if (flag == 1) {
                    Toast.makeText(this, "Fill all values to submit", Toast.LENGTH_LONG).show()
                }
                else {
                    val ans_check = checkeqn(wrong)

                    if (1 !in ans_check) {
                        Toast.makeText(this, "Nailed it!!", Toast.LENGTH_LONG).show()
                        score += 300/(4-life)
                        newpuzzle()
                        findViewById<TextView>(R.id.scoreView).text = "Score: " + score.toString()
                    } else {
                        reset_puzzle.visibility = View.INVISIBLE
                        score += 60 * (ans_check.count { it == 0 }) / (4-life)
                        life--
                        findViewById<TextView>(R.id.scoreView).text = "Score: " + score.toString()
                        findViewById<TextView>(R.id.lifeView).text = "Lives: " + life.toString()
                        if (life==0) { submit.text = "Score Page" }
                        else { submit.text = "Play again" }
                    }
                }
            }
        }
    }

    private fun dehglgt(sel_num_id: Int) {
        if (sel_num_id != 0) {
            val oldtxt: TextView = findViewById(sel_num_id)
            oldtxt.background = getDrawable(R.drawable.round_corner)
            //val dump: TextView = findViewById(R.id.textnumView11)
            //dump.text = oldtxt.id.toString()
        }
    }

    private fun oprtrgen(): List<String> {
        val randoprtr = mutableListOf<String>()
        for (i in (1..5)) {
            randoprtr.add(oprtrlst.shuffled().first())
        }
        return randoprtr
    }

    private fun opassgn(lst: List<String>) {
        for (i in 0 until lst.size) {
            val temp_obj: TextView = findViewById(oprtrids[i])
            temp_obj.text = lst[i]
        }
    }

    private fun numgen(oprtr: List<String>): List<Int> {
        val numlst = mutableListOf<Int>()

        // Generate numbers
        for (i in oprtr) {
            numlst.addAll((1..32).shuffled().slice(0..1))
        }
        //numlst.add(-1)

        for (i in 0..4) {
            if (oprtr[i] == "/") {
                numlst[i * 2] = numlst[i * 2] * numlst[1 + i * 2]
            }
        }
        return numlst
    }

    private fun numassgn(numlst: List<Int>) {
        // Assign numbers
        val dump: TextView = findViewById(R.id.textnumView11)
        dump.text = dump.text.toString() + numlst.toString()
        var ind = 0
        for (i in numids.shuffled()) {
            findViewById<TextView>(i).text = numlst[ind].toString()
            ind++
        }

    }

    private fun resassgn(oprtr: List<String>, numlst: List<Int>) {
        //Assign result
        var res = 0
        for (i in 0..9 step 2) {
            when (oprtr[i/2]) {
                "+" -> res = numlst[i] + numlst[i+1]
                "-" -> res = abs(numlst[i] - numlst[i+1])
                "*" -> res = numlst[i]  * numlst[i+1]
                "/" -> res = numlst[i] / numlst[i+1]
            }
            findViewById<TextView>(resids[i/2]).text = res.toString()
        }
        val dump: TextView = findViewById(R.id.textnumView11)
        dump.text = dump.text.toString() + numlst.toString()
    }

    private fun textclear() {
        for (i in textids) {
            val text_box: TextView = findViewById(i)
            text_box.text = ""
        }
    }

    private fun checkeqn(wrong: Int): List<Int> {
        val ans_check = mutableListOf<Int>(0, 0, 0, 0, 0)
        var res = 0
        for (i in 0..4) {
            when (findViewById<TextView>(oprtrids[i]).text.toString()) {
                "+" -> res = findViewById<TextView>(textids[i*2]).text.toString()
                    .toInt() + findViewById<TextView>(textids[1 + i*2]).text.toString()
                    .toInt()
                "-" -> res = findViewById<TextView>(textids[i*2]).text.toString()
                    .toInt() - findViewById<TextView>(textids[1 + i*2]).text.toString()
                    .toInt()
                "*" -> res = findViewById<TextView>(textids[i*2]).text.toString()
                    .toInt() * findViewById<TextView>(textids[1 + i*2]).text.toString()
                    .toInt()
                "/" -> res = findViewById<TextView>(textids[i*2]).text.toString()
                    .toInt() / findViewById<TextView>(textids[1 + i*2]).text.toString()
                    .toInt()
            }
            if (res != findViewById<TextView>(resids[i]).text.toString().toInt()) {
                ans_check[i] = 1
            }
        }
        for (i in wrong until wrong + 5) {
            if (ans_check[i - wrong] == 1) {
                findViewById<ImageView>(i).visibility = View.VISIBLE
            }
        }
        return ans_check
    }

    private fun newpuzzle() {
        textclear()
        val randoprtr = oprtrgen()
        opassgn(randoprtr)
        numlst.clear()
        numlst.addAll(numgen(randoprtr))
        val dump: TextView = findViewById(R.id.textnumView11)
        dump.text = numlst.toString()
        numassgn(numlst)
        resassgn(randoprtr, numlst)
    }

    private fun scorePage(score: Int, life: Int) {
        startActivity(Intent(this, MenuActivity::class.java).apply {
            putExtra("score", score)
        })
    }
}
