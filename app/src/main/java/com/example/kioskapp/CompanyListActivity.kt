package com.example.kioskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskapp.Adapters.CompanyListAdapter
import com.example.kioskapp.Models.CompanyListModel
import com.example.kioskapp.Requests.Companies
import com.example.kioskapp.Tools.Data
import org.json.JSONArray
import java.util.ArrayList

class CompanyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_list)
        var t: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(t)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        getCompany(true,"",-1)
        val searchButton: Button = findViewById(R.id.companySearchButton)
        val searchEditText: EditText = findViewById(R.id.companySearchEditText)
        searchButton.setOnClickListener(View.OnClickListener {
            getCompany(false, searchEditText.text.toString(), -1)
        })
    }
    fun getCompany(b: Boolean, s: String, i: Int) {
        var recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        var companyList = ArrayList<CompanyListModel>()
        var a: Companies = Companies(this)
        a.getCompanyList(object: Data {
            override fun onSuccess(result: JSONArray) {
                for (i in 0 until result.length())
                {
                    val company = CompanyListModel()
                    company.body = result.getJSONObject(i).getString("body")
                    company.addDate = result.getJSONObject(i).getString("add_date")
                    company.hit = result.getJSONObject(i).getInt("hit")
                    company.id = result.getJSONObject(i).getInt("id")
                    company.logo = result.getJSONObject(i).getString("logo")
                    company.fax = result.getJSONObject(i).getString("fax")
                    company.eMail = result.getJSONObject(i).getString("email")
                    company.line = result.getJSONObject(i).getString("line")
                    company.mapId = result.getJSONObject(i).getInt("map_id")
                    company.sectorId = result.getJSONObject(i).getInt("sector_id")
                    company.web = result.getJSONObject(i).getString("web")
                    company.telephone = result.getJSONObject(i).getString("telephone")
                    company.urlFacebook = result.getJSONObject(i).getString("url_facebook")
                    company.urlTwitter = result.getJSONObject(i).getString("url_twitter")
                    company.urlOther= result.getJSONObject(i).getString("url_other")
                    company.youtube= result.getJSONObject(i).getString("youtube")
                    company.block = result.getJSONObject(i).getString("block") as Char
                    companyList.add(company)
                }
                var companyListAdapter = CompanyListAdapter(baseContext, companyList)
                recyclerView.adapter = companyListAdapter
                var layoutManager = LinearLayoutManager(baseContext)
                layoutManager.orientation=RecyclerView.VERTICAL
                recyclerView.layoutManager = layoutManager
            }

        },b ,s,i)
    }
}
