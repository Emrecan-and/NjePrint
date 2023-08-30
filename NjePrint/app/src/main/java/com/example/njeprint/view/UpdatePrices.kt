package com.example.njeprint.view

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.njeprint.adapter.RecAdapter
import com.example.njeprint.database.DatabasePrice
import com.example.njeprint.databinding.ActivityUpdatePricesBinding
import com.example.njeprint.datamodel.SharedDataList

class UpdatePrices : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePricesBinding
    private lateinit var myDatabas: SQLiteDatabase
    private lateinit var myDatabase: DatabasePrice
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePricesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        try {
            myDatabas=this.openOrCreateDatabase("NjePrint", MODE_PRIVATE,null)
        }catch (e:Exception){
            e.printStackTrace()
        }
       myDatabase= DatabasePrice(myDatabas)
       binding.recyclerView.layoutManager=LinearLayoutManager(this)
       binding.recyclerView.adapter=RecAdapter(SharedDataList.dataList)
    }
    fun onUpdateClick(view: View) {
        myDatabase.updateDatabase()
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}