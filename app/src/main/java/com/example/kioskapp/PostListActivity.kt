package com.example.kioskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskapp.Adapters.PostListAdapter
import com.example.kioskapp.Models.PostListModel
import com.example.kioskapp.Requests.Posts
import com.example.kioskapp.Tools.Data
import com.example.kioskapp.Tools.GridSpacingItemDecoration
import org.json.JSONArray
import java.util.ArrayList

class PostListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        var recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        var postList = ArrayList<PostListModel>()
        var a: Posts = Posts(this)
        var t: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(t)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        a.getPosts(object: Data {
            override fun onSuccess(result: JSONArray) {
                for (i in 0 until result.length())
                {
                    val post = PostListModel()
                    post.body = result.getJSONObject(i).getString("body")
                    post.addDate = result.getJSONObject(i).getString("add_date")
                    post.hit = result.getJSONObject(i).getInt("hit")
                    post.id = result.getJSONObject(i).getInt("id")
                    post.picture = result.getJSONObject(i).getString("picture")
                    post.summary = result.getJSONObject(i).getString("summary")
                    post.title = result.getJSONObject(i).getString("title")
                    postList.add(post)
                }
                var postListAdapter = PostListAdapter(baseContext, postList)
                recyclerView.adapter = postListAdapter
                var layoutManager = LinearLayoutManager(baseContext)
                layoutManager.orientation=RecyclerView.VERTICAL
                recyclerView.layoutManager = layoutManager
            }

        },false ,5)
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
