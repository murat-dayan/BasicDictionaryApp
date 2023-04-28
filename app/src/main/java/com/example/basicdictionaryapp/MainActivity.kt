package com.example.basicdictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicdictionaryapp.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var wordList: ArrayList<Words>
    private lateinit var dicRVAdapter: DictionaryAdapter
    private lateinit var dbh: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        copyDatabase()

        binding.toolbar.title= "Dictionary"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager= LinearLayoutManager(this)

        dbh= DatabaseHelper(this)

        wordList= Wordsdao().getWordsFilterByWordName(dbh,"")

        dicRVAdapter= DictionaryAdapter(this, wordList)
        binding.rv.adapter= dicRVAdapter



    }
    fun copyDatabase(){
        val db= DatabaseCopyHelper(this)

        try {
            db.createDataBase()
            db.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)

        val item= menu?.findItem(R.id.action_search)
        val searchView= item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var filteredWordList= ArrayList<Words>()
        dbh= DatabaseHelper(this)
        filteredWordList= Wordsdao().getWordsFilterByWordName(dbh, newText.toString())
        wordList.clear()
        wordList.addAll(filteredWordList)
        dicRVAdapter.notifyDataSetChanged()
        return true
    }


}