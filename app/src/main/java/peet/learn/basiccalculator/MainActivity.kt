package peet.learn.basiccalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    private var firstOperand: Double? = null
    private var secondOperand: Double = 0.0
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        // inputs
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        // operations
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)

        val inputListener = View.OnClickListener { value ->
            val button = value as Button
            newNumber.append(button.text)
        }

        val operationListener = View.OnClickListener { value ->
            val operation = (value as Button).text.toString()
            val operand = newNumber.text.toString()
            if (operand.isNotEmpty()) {
                performOperation(operand, operation)
            }

            pendingOperation = operation
            displayOperation.text = pendingOperation
        }

        button0.setOnClickListener(inputListener)
        button1.setOnClickListener(inputListener)
        button2.setOnClickListener(inputListener)
        button3.setOnClickListener(inputListener)
        button4.setOnClickListener(inputListener)
        button5.setOnClickListener(inputListener)
        button6.setOnClickListener(inputListener)
        button7.setOnClickListener(inputListener)
        button8.setOnClickListener(inputListener)
        button9.setOnClickListener(inputListener)
        buttonDot.setOnClickListener(inputListener)

        buttonEquals.setOnClickListener(operationListener)
        buttonDivide.setOnClickListener(operationListener)
        buttonMultiply.setOnClickListener(operationListener)
        buttonMinus.setOnClickListener(operationListener)
        buttonPlus.setOnClickListener(operationListener)

    }

    private fun performOperation(value: String, operation: String) {
        displayOperation.text = operation
    }
}
