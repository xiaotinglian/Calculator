package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null

    var  lastNum: Boolean = false
    var lastPoint: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        Toast.makeText(this, "Digit clicked", Toast.LENGTH_SHORT).show()

        tvInput?.append((view as Button).text)
        lastNum = true
        lastPoint = false



    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNum &&!lastPoint){
            tvInput?.append(".")
            lastNum = false
            lastPoint = true
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNum && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNum = false
                lastPoint = false
            }
        }
    }

    fun onEquals(view: View) {
        if(lastNum && !lastPoint){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterPoint((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterPoint((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterPoint((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterPoint((one.toDouble() * two.toDouble()).toString())
                }

            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterPoint(result: String): String {
        var value = result
        if(result.contains("0"))
            value = result.substring(0, result.length - 2)

        return value
    }


    private fun isOperatorAdded(value :String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }


}