package com.example.homework4

import android.R
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.homework4.databinding.ActivitySecondBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var editTextDreamTitle: EditText
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
        editTextDreamDescription = binding.editTextDreamDescription
        editTextInterpretation = binding.editTextInterpretation
        textView2 = binding.textView2
        buttonSave = binding.buttonSave
        spinner = binding.spinner

        val temp_id = intent.getStringExtra("id")
        val new_id = Integer.parseInt(temp_id)

        val temp_title = intent.getStringExtra("title")
        val temp_description = intent.getStringExtra("description")
        val temp_interpretation = intent.getStringExtra("interpretation")
        val temp_emotion = intent.getStringExtra("emotion")


        editTextDreamTitle.setText(temp_title)
        println("this is me " + temp_title.toString())
        editTextDreamDescription.setText(temp_description)
        editTextInterpretation.setText(temp_interpretation)

        SearchSpinner()

        val emotion_temp = temp_emotion
        if(emotion_temp == "fear"){ spinner.setSelection(1) }
        if(emotion_temp == "panic"){ spinner.setSelection(2) }
        if(emotion_temp == "loss of self"){ spinner.setSelection(3) }
        if(emotion_temp == "grief"){ spinner.setSelection(4) }
        if(emotion_temp == "freedom"){ spinner.setSelection(5) }
        if(emotion_temp == "love"){ spinner.setSelection(6) }
        if(emotion_temp == "joy"){ spinner.setSelection(7) }
        if(emotion_temp == "vulnerability"){ spinner.setSelection(8) }
        if(emotion_temp == "confused"){ spinner.setSelection(9) }
        if(emotion_temp == "sad"){ spinner.setSelection(10) }


        buttonSave.setOnClickListener{
            if(TextUtils.isEmpty(editTextDreamDescription.text) || TextUtils.isEmpty(editTextDreamTitle.text) || TextUtils.isEmpty(editTextInterpretation.text) || spinner.selectedItem == ""){
                Toast.makeText(this, "Section Missing", Toast.LENGTH_LONG).show()
            }else{
                dreamViewModel.update(new_id, editTextDreamTitle.text.toString(), editTextDreamDescription.text.toString(),
                        editTextInterpretation.text.toString(), spinner.selectedItem.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun SearchSpinner(){
        val searchMethod = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, emotions)
        searchMethod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = searchMethod
    }
}