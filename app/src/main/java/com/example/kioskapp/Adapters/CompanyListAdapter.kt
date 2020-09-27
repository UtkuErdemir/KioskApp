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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kioskapp.CompanyDetailsActivity
import com.example.kioskapp.Models.CompanyListModel
import com.example.kioskapp.R

class CompanyListAdapter(context: Context, names:ArrayList<CompanyListModel>): RecyclerView.Adapter<CompanyListAdapter.CompanyListViewHolder>()
{
    var inflater: LayoutInflater = LayoutInflater.from(context)
    var nameList: ArrayList<CompanyListModel> = names
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyListViewHolder {
        var view: View = inflater.inflate(R.layout.post_item,parent,false)
        return CompanyListViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: CompanyListViewHolder, position: Int) {
        var selectedCompany: CompanyListModel = nameList[position]
        holder.setData(selectedCompany,position)
    }

    class CompanyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var company: CardView = itemView.findViewById(R.id.companyItemCardView)
        lateinit var selectedCompany : CompanyListModel
        fun setData(c: CompanyListModel, position: Int){
            var companyItemImageView: ImageView = itemView.findViewById(R.id.companyItemImageView)
            Glide.with(itemView.context)
                .load(c.logo).apply(RequestOptions().override(900).fitCenter())
                .into(companyItemImageView)
            company.setOnClickListener(View.OnClickListener {
                selectedCompany = c
                val intent = Intent(itemView.context, CompanyDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("selectedCompany",selectedCompany)
                intent.putExtras(bundle)
                startActivity(itemView.context,intent,null)
            })
            var companyItemTextView: TextView = itemView.findViewById(R.id.companyItemTitle)
            var companyDetail1: TextView = itemView.findViewById(R.id.companyDetail1)
            var companyDetail2: TextView = itemView.findViewById(R.id.companyDetail2)
            var companyDetail3: TextView = itemView.findViewById(R.id.companyDetail3)
            companyItemTextView.text = c.name
            companyDetail1.text = c.line
            companyDetail2.text = c.floor
            companyDetail3.text = c.doorNo.toString()
        }
    }
}
