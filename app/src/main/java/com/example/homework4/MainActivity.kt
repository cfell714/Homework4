package com.example.homework4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var buttonDream : Button
    private lateinit var binding : ActivityMainBinding
    private lateinit var recyclerView:RecyclerView

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonDream = binding.buttonDream
        recyclerView = findViewById(R.id.recyclerview)

        val adapter = DreamListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        dreamViewModel.allDreams.observe(this, androidx.lifecycle.Observer {
            dreams -> dreams?.let {
                adapter.submitList(it)
            }
        })

        buttonDream.setOnClickListener{
            launchSecondActivity()
        }
    }

    private fun launchSecondActivity(){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

}