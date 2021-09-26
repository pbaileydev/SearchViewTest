package com.pbaileyapps.searchviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener{
    lateinit var WordView: ListView
    lateinit var WordAdapter: ArrayAdapter<String>
    lateinit var wordList : ArrayList<String>
    var searchView:SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        WordView = findViewById(R.id.list_view)
        wordList = ArrayList()
        wordList.add("dog")
        wordList.add("cat")
        wordList.add("carrot")
        wordList.add("rabbit")
        wordList.add("ball")
        WordAdapter = ArrayAdapter(this,R.layout.list_item,wordList)
        WordView.adapter = WordAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        super.onCreateOptionsMenu(menu)
        val search = menu?.findItem(R.id.search)
        searchView = search?.actionView as? androidx.appcompat.widget.SearchView
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
            if(wordList.contains(query)){
                WordAdapter.filter.filter(query)
                Log.d("WORD_POSITION","Word ${wordList.indexOf(query)}")
            }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        WordAdapter.filter.filter(newText)
        return true
    }
}