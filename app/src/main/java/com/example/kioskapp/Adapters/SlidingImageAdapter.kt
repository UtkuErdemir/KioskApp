package com.example.kioskapp.Adapters

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kioskapp.Models.PostListModel
import com.example.kioskapp.R
import java.util.ArrayList


class SlidingImageAdapter(private val context: Context, private val postList: ArrayList<PostListModel>) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return postList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false)!!

        val imgSlider: ImageView = imageLayout
            .findViewById(R.id.imgSlider)
        val txtSlider: TextView = imageLayout.findViewById(R.id.txtSlider)
        txtSlider.text = postList[position].title
        Glide.with(context)
            .load(postList[position].picture).apply(RequestOptions().override(1500).fitCenter())
            .into(imgSlider)
        imgSlider.setOnClickListener(View.OnClickListener {
            Toast.makeText(view.context,postList[position].id.toString(),Toast.LENGTH_SHORT).show()
        })
        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}
