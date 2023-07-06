package com.example.temperatureconverter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri.Builder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    //Declaring Views
    private lateinit var selectedUnitLayout:LinearLayout
    private lateinit var selectedUnitText:TextView
    private lateinit var editInput: EditText
    private lateinit var textResult: TextView
    private lateinit var resultTypeText: TextView

    //Input type
    private lateinit var selectedUnit:String


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing views
        selectedUnitLayout = findViewById(R.id.selactType)
        selectedUnitText = findViewById(R.id.textSelect)
        editInput = findViewById(R.id.editInput)
        textResult = findViewById(R.id.textRe)
        resultTypeText = findViewById(R.id.textResultType)

        //By default Fahrenheit is Input unit
        selectedUnit = "Fahrenheit"


        //Setting alert Dialogue to appear for selection of input unit
        selectedUnitLayout.setOnClickListener(){
            showAlertDialog()
        }

        editInput.addTextChangedListener(){
            var resultText:String = ""
            var inputVal = editInput.text.toString()

            val df = DecimalFormat("#.##")

            if(inputVal.isNotEmpty()){
                val doubleInput = inputVal.toDouble() // to convert string to Double for calculations
                //Taking decision as per current input type
                if(selectedUnit == "Fahrenheit"){
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    resultTypeText.text = "Celsius"
                }
                else{
                    resultText = df.format((doubleInput * 9 / 5 + 32))
                    resultTypeText.text = "Fahrenheit"
                }

                textResult.text = resultText
            }
        }
    }



    //code to show alert dialog
    private fun showAlertDialog() {
        var alertDialog:AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Select Input Unit") //Setting title of alert dialog
        val items = arrayOf("Fahrenheit","Celsius") //Options in alert dialog
        val checkedItem = -1 //No item by default selected

        alertDialog.setSingleChoiceItems(items,checkedItem,
            DialogInterface.OnClickListener() { dialog, which ->
                selectedUnit = items[which] //which user has selected
                selectedUnitText.setText(selectedUnit)
            })
        alertDialog.setPositiveButton(android.R.string.ok,
        DialogInterface.OnClickListener() { dialog, which ->
            dialog.dismiss()
        })
        val alert:AlertDialog = alertDialog.create()
        alertDialog.show()
    }
}