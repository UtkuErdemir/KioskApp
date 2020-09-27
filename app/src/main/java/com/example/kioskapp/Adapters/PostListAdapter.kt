package com.example.kioskapp.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kioskapp.Models.PostListModel
import com.example.kioskapp.PostDetailsActivity
import com.example.kioskapp.R

class PostListAdapter(context: Context, names:ArrayList<PostListModel>): RecyclerView.Adapter<PostListAdapter.PostListViewHolder>()
{
    var inflater: LayoutInflater = LayoutInflater.from(context)
    var nameList: ArrayList<PostListModel> = names
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        var view: View = inflater.inflate(R.layout.post_item,parent,false)
        return PostListViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        var selectedPost: PostListModel = nameList[position]
        holder.setData(selectedPost,position)
    }

    class PostListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var post: CardView = itemView.findViewById(R.id.postItemCardView)
        lateinit var selectedPost : PostListModel
        fun setData(p: PostListModel, position: Int){
            var postItemImageView: ImageView = itemView.findViewById(R.id.postItemImageView)
            Glide.with(itemView.context)
                .load(p.picture).apply(RequestOptions().override(900).fitCenter())
                .into(postItemImageView)
            post.setOnClickListener(View.OnClickListener {
                selectedPost = p
                val intent = Intent(itemView.context, PostDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("selectedPost",selectedPost)
                intent.putExtras(bundle)
                startActivity(itemView.context,intent,null)
            })
            var postItemTextView: TextView = itemView.findViewById(R.id.postItemTitle)
            var postItemTextSumary: TextView = itemView.findViewById(R.id.postItemSummary)
            postItemTextView.text = p.title
            postItemTextSumary.text = HtmlCompat.fromHtml(p.body.substring(0,p.body.indexOf('.')-1)+"...",HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}
