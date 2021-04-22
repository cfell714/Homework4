package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.homework4.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var textViewTitleAct3 : TextView
    private lateinit var textViewDescriptionAct3 : TextView
    private lateinit var textViewInterpretationAct3 : TextView
    private lateinit var textViewScrollAct3 : TextView
    private lateinit var buttonDelete : Button
    private lateinit var buttonUpdate : Button

    private lateinit var textViewSpinnerText : TextView
    private lateinit var textViewDescriptionText : TextView
    private lateinit var textViewInterpretationText : TextView

    private lateinit var tempId : String
    private lateinit var tempTitle : String
    private lateinit var tempDescription : String
    private lateinit var tempInterpretation : String
    private lateinit var tempEmotion : String

    private val dreamViewModel: DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewTitleAct3 = binding.textViewTitleAct3
        textViewDescriptionAct3 = binding.textViewDescriptionAct3
        textViewInterpretationAct3 = binding.textViewInterpretationAct3
        textViewScrollAct3 = binding.textViewScrollAct3
        buttonDelete = binding.buttonDelete
        buttonUpdate = binding.buttonUpdate

        textViewSpinnerText = binding.textViewSpinnerText
        textViewDescriptionText = binding.textViewDescriptionText
        textViewInterpretationText = binding.textViewInterpretationText

        val getId = intent.getStringExtra("id")
        val newId = Integer.parseInt(getId)

            dreamViewModel.select(newId).observe(this, Observer {
                if(it!=null) {
                    textViewTitleAct3.text = it.title
                    textViewDescriptionAct3.text = it.description
                    textViewInterpretationAct3.text = it.interpretation
                    textViewScrollAct3.text = it.emotion

                    tempId = it.id.toString()
                    tempTitle = it.title
                    tempDescription = it.description
                    tempInterpretation = it.interpretation
                    tempEmotion = it.emotion
                }
            })

        buttonDelete.setOnClickListener{
            dreamViewModel.delete(newId)
            finish()
        }

        buttonUpdate.setOnClickListener{
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("id", tempId)
            intent.putExtra("title", tempTitle)
            intent.putExtra("description", tempDescription)
            intent.putExtra("interpretation", tempInterpretation)
            intent.putExtra("emotion", tempEmotion)
            startActivity(intent)
        }
    }
}