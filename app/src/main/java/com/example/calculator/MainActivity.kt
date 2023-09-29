package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private lateinit var expression: TextView
    private lateinit var result: TextView
    private lateinit var clear: Button
    private lateinit var backSpace: Button
    private lateinit var percent: Button
    private lateinit var divide: Button
    private lateinit var multiply: Button
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var equal: Button
    private lateinit var dot: Button
    private lateinit var zero: Button
    private lateinit var doubleZero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        clear = findViewById(R.id.clear)
        backSpace = findViewById(R.id.backSpace)
        percent = findViewById(R.id.percent)
        divide = findViewById(R.id.divide)
        multiply = findViewById(R.id.multiply)
        add = findViewById(R.id.add)
        subtract = findViewById(R.id.subtract)
        equal = findViewById(R.id.equal)
        dot = findViewById(R.id.dot)
        zero = findViewById(R.id.zero)
        doubleZero = findViewById(R.id.doubleZero)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)

        expression.movementMethod = ScrollingMovementMethod()
        expression.isActivated = true
        expression.isPressed = true

        var str: String
        val listOfActions = listOf<String>("+", "-", "%", "*", "/", ".")

        clear.setOnClickListener {
            expressionText("0")
            expression.textSize = 60F
            result.textSize = 30F
            resultText()
        }

        backSpace.setOnClickListener {
            if (expression.text.toString().isNotEmpty()) {
                val lastIndex = expression.text.toString().lastIndex
                str = expression.text.toString().substring(0, lastIndex)
                expressionText(str)
                resultText()
            }
        }

        percent.setOnClickListener {
            setExpression("%", listOfActions, expression.text.toString())
        }

        divide.setOnClickListener {
            setExpression("/", listOfActions, expression.text.toString())
        }

        add.setOnClickListener {
            setExpression("+", listOfActions, expression.text.toString())
        }

        subtract.setOnClickListener {
            setExpression("-", listOfActions, expression.text.toString())
        }

        equal.setOnClickListener {
            expression.textSize = 30F
            result.textSize = 60F
        }

        dot.setOnClickListener {
            setExpression(".", listOfActions, expression.text.toString())
        }

        zero.setOnClickListener {
            setNumberExpression(expression.text.toString(),"0")
        }

        doubleZero.setOnClickListener {
            setNumberExpression(expression.text.toString(), "00")
        }

        one.setOnClickListener{
            setNumberExpression(expression.text.toString(), "1")
        }
        two.setOnClickListener{
            setNumberExpression(expression.text.toString(), "2")
        }
        three.setOnClickListener{
            setNumberExpression(expression.text.toString(), "3")
        }
        four.setOnClickListener{
            setNumberExpression(expression.text.toString(), "4")
        }
        five.setOnClickListener{
            setNumberExpression(expression.text.toString(), "5")
        }
        six.setOnClickListener{
            setNumberExpression(expression.text.toString(), "6")
        }
        seven.setOnClickListener{
            setNumberExpression(expression.text.toString(), "7")
        }
        eight.setOnClickListener{
            setNumberExpression(expression.text.toString(), "8")
        }
        nine.setOnClickListener{
            setNumberExpression(expression.text.toString(), "9")
        }
    }

    private fun setNumberExpression (text: String, char: String) {
        if (text.startsWith("0")) {
            expressionText(text.replace("0", "") + char)
            resultText()
        } else {
            expressionText(text + char)
            resultText()
        }
    }
    private fun setExpression(expression: String, listOfActions: List<String>, text: String ) {
        val endsWithExpression = listOfActions.any { text.endsWith(it) }
        if (endsWithExpression) {
            expressionText(text)
        } else {
            expressionText(text + expression)
        }
    }
    private fun expressionText(srt: String) {
        expression.text = srt
    }

    private fun resultText(){
        val exp = expression.text.toString()
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

        try {
            val res = engine.eval(exp)
            if (res.toString().endsWith(".0")) {
                result.text = "=" + res.toString().replace(".0", "")
            } else {
                result.text = "$res"
            }
        } catch (e: Exception) {
            expression.text = expression.text.toString()
            result.text = expression.text.toString()
        }
    }
}