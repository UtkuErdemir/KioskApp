package com.example.kioskapp.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskapp.Models.ButtonListModel
import com.example.kioskapp.PostListActivity
import com.example.kioskapp.R


class ButtonListAdapter(context: Context,names:ArrayList<ButtonListModel>): RecyclerView.Adapter<ButtonListAdapter.ButtonListViewHolder>()
{
    var inflater: LayoutInflater = LayoutInflater.from(context)
    var nameList: ArrayList<ButtonListModel> = names
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonListViewHolder {
        var view:View = inflater.inflate(R.layout.button,parent,false)
        var buttonListViewHolder: ButtonListViewHolder =
            ButtonListViewHolder(
                view
            )
        return buttonListViewHolder
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: ButtonListViewHolder, position: Int) {
        var selectedButton: ButtonListModel = nameList.get(position)
        holder.setData(selectedButton,position)
    }

    class ButtonListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{
        var button:LinearLayout = itemView.findViewById(R.id.bMenu)
        private var pos:Int = 0
        private var selectedName: String = ""
        fun setData(b: ButtonListModel, position: Int){
            var icon = button.findViewById<TextView>(R.id.iconTxt)
            var name = button.findViewById<TextView>(R.id.nameTxt)
            icon.text = b.btnIcon
            name.text = b.btnName
            button.setOnClickListener(this)
            pos = position
            selectedName = b.btnName
        }
        override fun onClick(v: View) {
            Toast.makeText(itemView.context,selectedName,Toast.LENGTH_LONG).show()
            var i = Intent(v.context,PostListActivity::class.java)
            var a: Activity = v.context as Activity
            a.finish()
            startActivity(v.context,i,null)
        }

    }
}
