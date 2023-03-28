package org.hyperskill.calculator.tip

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val totalAmountView = findViewById<EditText>(R.id.edit_text)
        val result: TextView = findViewById<TextView>(R.id.text_view)
        var bill : Double? = null
        var percent = 0

        fun make(bill: Double?, percent: Int){
            val df = DecimalFormat("0.00")
            val tip = df.format(bill!! * percent/ 100)
            result.text = "Tip amount: $tip$"
        }

        totalAmountView.doAfterTextChanged {
            if (it != null && it.toString() != "") {
                    bill  = it.toString().toDouble()
                    make(bill, percent)
                } else result.text = ""
            }



       val sliderValue: Slider = findViewById<com.google.android.material.slider.Slider>(R.id.slider)
        sliderValue.addOnChangeListener { _, value, _ ->
            percent = value.toInt()
            if (bill != null) make(bill, percent)
        }
    }
}
