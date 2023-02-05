package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var calScreen: TextView? = null
    var lastNumberic: Boolean = false
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calScreen = findViewById(R.id.cal_screen)
    }

    fun onDigit(view: View){
        calScreen?.append((view as Button).text)
        lastNumberic = true
        lastDot = false
    }
    fun onDecimalPoint(view: View){
        if (lastNumberic && !lastDot){
            calScreen?.append(".")
            lastNumberic=false
            lastDot=true
        }
    }
    fun onClear(view: View){
        calScreen?.text = "0"

    }
    fun onOperator(view: View){
        calScreen?.text.let {
            if (lastNumberic && !isOperatorAdded(it.toString())){
                calScreen?.append((view as Button).text)
                lastNumberic=false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*")  || value.contains("+") || value.contains("-")
        }
    }

    fun onEqual(view: View){
        if(lastNumberic){
            var srcVal = calScreen?.text.toString()
            var prefix = ""
            try {
                if(srcVal.startsWith("-")){
                    prefix="-"
                    srcVal= srcVal.substring(1)
                }
                if(srcVal.contains("-")){
                    var spitValue = srcVal.split('-')
                    var one = spitValue[0]
                    var two = spitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    calScreen?.text = (one.toDouble()-two.toDouble()).toString()
                }
                else if(srcVal.contains("+")){
                    var spitValue = srcVal.split('+')
                    var one = spitValue[0]
                    var two = spitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    calScreen?.text = (one.toDouble()+two.toDouble()).toString()
                }
                else if(srcVal.contains("*")){
                    var spitValue = srcVal.split('*')
                    var one = spitValue[0]
                    var two = spitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    calScreen?.text = (one.toDouble()*two.toDouble()).toString()
                }
                else if(srcVal.contains("/")){
                    var spitValue = srcVal.split('/')
                    var one = spitValue[0]
                    var two = spitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    calScreen?.text = (one.toDouble()/two.toDouble()).toString()
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}