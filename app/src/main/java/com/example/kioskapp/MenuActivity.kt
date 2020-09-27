package com.example.kioskapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskapp.Adapters.ButtonListAdapter
import com.example.kioskapp.Models.ButtonListModel
import com.example.kioskapp.Tools.GridSpacingItemDecoration
import java.util.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        var buttonList = ArrayList<ButtonListModel>()
        for (i in 0..12){
            var model = ButtonListModel()
            model.btnName = "A"
            model.btnIcon = resources.getString(R.string.fa_search_solid)
            buttonList.add(model)
        }
        var buttonListAdapter = ButtonListAdapter(this, buttonList)
        recyclerView.adapter = buttonListAdapter
        recyclerView.addItemDecoration(GridSpacingItemDecoration(5,0,false))
        recyclerView.layoutManager = GridLayoutManager(this,5)
        var t: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(t)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //supportActionBar!!.setHomeAsUpIndicator(R.drawable.vpi__tab_selected_focused_holo)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
