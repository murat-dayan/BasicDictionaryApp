package com.example.basicdictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.basicdictionaryapp.databinding.ActivityWordMeanBinding

class WordMeanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWordMeanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_word_mean)

        val word= intent.getSerializableExtra("word") as Words

        binding.textViewEnglishWord.text= word.english
        binding.textViewTurkishWord.text= word.turkish



    }
}