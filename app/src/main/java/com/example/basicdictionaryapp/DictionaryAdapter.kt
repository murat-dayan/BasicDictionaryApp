package com.example.basicdictionaryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class DictionaryAdapter(private val mContext:Context, private val wordsList: List<Words>) :
    RecyclerView.Adapter<DictionaryAdapter.WordHolder>() {

    var filteredList= wordsList

    inner class WordHolder(view: View): RecyclerView.ViewHolder(view){
        var rowCardView:CardView
        var rowEnWord:TextView
        var rowTrWord: TextView

        init {
            rowCardView= view.findViewById(R.id.cardViewId)
            rowEnWord= view.findViewById(R.id.cardViewTextEn)
            rowTrWord= view.findViewById(R.id.cardViewTextTr)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val design= LayoutInflater.from(mContext).inflate(R.layout.word_card_view, parent, false)
        return WordHolder(design)
    }

    override fun getItemCount(): Int {
        return wordsList.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word= wordsList[position]

        holder.rowEnWord.text= word.english
        holder.rowTrWord.text= word.turkish

        holder.rowCardView.setOnClickListener {
            val intent= Intent(mContext, WordMeanActivity::class.java)
            intent.putExtra("word", word)
            mContext.startActivity(intent)
        }

    }



}