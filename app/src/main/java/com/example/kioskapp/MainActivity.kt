package com.example.kioskapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kioskapp.Adapters.SlidingImageAdapter
import com.example.kioskapp.Models.PostListModel
import com.example.kioskapp.Requests.Posts
import com.example.kioskapp.Tools.BlockStatusBar
import com.example.kioskapp.Tools.Data
import com.viewpagerindicator.CirclePageIndicator
import org.json.JSONArray
import java.util.*


class MainActivity : AppCompatActivity() {
    private var imageModelArrayList: ArrayList<PostListModel>? = null
    private lateinit var postList:JSONArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a:Posts = Posts(this)
        a.getPosts(object:Data{
            override fun onSuccess(result: JSONArray) {
                imageModelArrayList = populateList(result)
                postList = result
                init()
            }

        },true,null)
        val menuButton: ImageButton = findViewById<ImageButton>(R.id.menuButton)
        menuButton.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity,MenuActivity::class.java)
            startActivity(i)
        })
        val leftNavButton: ImageButton = findViewById(R.id.left_nav)
        val rightNavButton: ImageButton = findViewById(R.id.right_nav)
        leftNavButton.setOnClickListener(View.OnClickListener {
            if(currentPage>0) mPager!!.currentItem = currentPage-- else mPager!!.currentItem= 9
        })
        rightNavButton.setOnClickListener(View.OnClickListener {
            if(currentPage<10) mPager!!.currentItem= currentPage++ else mPager!!.currentItem= 0
        })
    }

    private fun populateList(array:JSONArray): ArrayList<PostListModel> {

        val list = ArrayList<PostListModel>()
        for (i in 0 until array.length()) {
            val post = PostListModel()
            post.body = array.getJSONObject(i).getString("body")
            post.addDate = array.getJSONObject(i).getString("add_date")
            post.hit = array.getJSONObject(i).getInt("hit")
            post.id = array.getJSONObject(i).getInt("id")
            post.picture = array.getJSONObject(i).getString("picture")
            post.summary = array.getJSONObject(i).getString("summary")
            post.title = array.getJSONObject(i).getString("title")
            list.add(post)
        }

        return list
    }

    private fun init() {

        mPager = findViewById<ViewPager>(R.id.pager)
        mPager!!.adapter = SlidingImageAdapter(
            this@MainActivity,
            this.imageModelArrayList!!
        )

        val indicator = findViewById<CirclePageIndicator>(R.id.indicator)

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.radius = 5 * density

        NUM_PAGES = imageModelArrayList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!hasFocus) {
                val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                sendBroadcast(closeDialog)
                // Method that handles loss of window focus
                BlockStatusBar(this, false).collapseNow()
            }
        }
    }
}
