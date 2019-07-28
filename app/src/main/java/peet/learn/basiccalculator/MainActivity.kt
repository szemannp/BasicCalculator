package peet.learn.basiccalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_PENDING_OPERATION = "PendingOperation"
private const val STATE_FIRST_OPERAND = "FirstOperand"
private const val STATE_OPERAND_STORED = "FirstOperand Stored"

class MainActivity : AppCompatActivity() {

    private var firstOperand: Double? = null
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputListener = View.OnClickListener { value ->
            val button = value as Button
            newNumber.append(button.text)
        }

        val operationListener = View.OnClickListener { value ->
            val op = (value as Button).text.toString()

            try {
                val operand = newNumber.text.toString().toDouble()
                performOperation(operand, op)
            } catch (error: NumberFormatException) {
                newNumber.setText("")
            }

            pendingOperation = op
            operation.text = pendingOperation
        }

        val inputSignListener = View.OnClickListener { view ->
            val value = newNumber.text.toString()
            if (value.isEmpty()) {
                newNumber.setText("-")
            } else {
                try {
                  var doubleValue = value.toDouble()
                  doubleValue *= -1
                  newNumber.setText(doubleValue.toString())
                } catch (e: NumberFormatException) {
                    newNumber.setText("")

                }
            }
        }

        val clearCalcListener = View.OnClickListener { view ->
            firstOperand = null
            pendingOperation = "="
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
        buttonNeg.setOnClickListener(inputSignListener)
        buttonClear.setOnClickListener(clearCalcListener)

    }

    private fun performOperation(value: Double, operation: String) {
        if (firstOperand == null) {
            firstOperand = value
        } else {

            if (pendingOperation == "=") {
                pendingOperation = operation
            }

            when (pendingOperation) {
                "=" -> firstOperand = value
                "/" -> firstOperand = if (value == 0.0) {
                    Double.NaN // handle division by 0
                } else {
                    firstOperand!! / value
                }
                "*" -> firstOperand = firstOperand!! * value
                "-" -> firstOperand = firstOperand!! - value
                "+" -> firstOperand = firstOperand!! + value
            }
        }


        result.setText(firstOperand.toString())
        newNumber.setText("")
        println("\nthe value is $value")
        println("\nthe first operand is $firstOperand")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (firstOperand != null) {
            outState.putDouble(STATE_FIRST_OPERAND, firstOperand!!)
            outState.putBoolean(STATE_OPERAND_STORED, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState.getBoolean(STATE_OPERAND_STORED, false)) {
            firstOperand = savedInstanceState.getDouble(STATE_FIRST_OPERAND)
        } else {
            firstOperand = null
        }

        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION)
        operation.text = pendingOperation
    }
}
