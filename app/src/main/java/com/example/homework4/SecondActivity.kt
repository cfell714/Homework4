package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.homework4.databinding.ActivitySecondBinding
import java.sql.Date

import java.util.*

class SecondActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySecondBinding
    private lateinit var editTextDreamTitle: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextDreamDescription: EditText
    private lateinit var editTextInterpretation : EditText
    private lateinit var textView2 : TextView
    private lateinit var buttonSave: Button
    private lateinit var spinner : Spinner
    var emotions = arrayOf("", "fear", "panic", "loss of self", "grief", "freedom", "love", "joy", "vulnerability", "confused", "sad")

    private val dreamViewModel: DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextDreamTitle = binding.editTextDreamTitle
        editTextDate = binding.editTextDate
        editTextDreamDescription = binding.editTextDreamDescription
        editTextInterpretation = binding.editTextInterpretation
        textView2 = binding.textView2
        buttonSave = binding.buttonSave
        spinner = binding.spinner

        SearchSpinner()

        buttonSave.setOnClickListener{
            if(TextUtils.isEmpty(editTextDreamDescription.text) || TextUtils.isEmpty(editTextDreamTitle.text) || TextUtils.isEmpty(editTextInterpretation.text) || spinner.selectedItem == ""){
                Toast.makeText(this, "Section Missing", Toast.LENGTH_LONG).show()
            }else{
                try {
                    val dateTemp = Date.valueOf(editTextDate.text.toString())
                    val title = Dream(0, dateTemp.toString(), editTextDreamTitle.text.toString(), editTextDreamDescription.text.toString(),
                            editTextInterpretation.text.toString(), spinner.selectedItem.toString())

                    dreamViewModel.insert(title)
                    finish()
                }catch(e: Exception){
                    Toast.makeText(this, "Date incorrectly formatted", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun SearchSpinner(){
        val searchMethod = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, emotions)
        searchMethod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = searchMethod
    }
}