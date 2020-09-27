package com.example.kioskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kioskapp.Models.PostListModel
import uk.co.deanwild.flowtextview.FlowTextView

class PostDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        var t: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(t)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val intent = this.intent
        val bundle = intent.extras
        var post= bundle["selectedPost"] as PostListModel
        init(post)
    }
    fun init(post:PostListModel){
        val img: ImageView = findViewById(R.id.detailImageView)
        val txt: FlowTextView = findViewById(R.id.ftv)
        supportActionBar!!.title=post.title
        txt.setTextSize(txt.textsize - 20)
        val spanned: Spanned = HtmlCompat.fromHtml(post.body,HtmlCompat.FROM_HTML_MODE_LEGACY)
        txt.text = spanned
        Glide.with(this)
            .load(post.picture).apply(RequestOptions().fitCenter().transform(RoundedCorners(25)))
            .into(img)
    }
}
