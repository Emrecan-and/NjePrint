package com.example.njeprint.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.njeprint.database.DatabasePrice
import com.example.njeprint.database.InsertFirstData
import com.example.njeprint.R
import com.example.njeprint.calculate.TextViewUpdate
import com.example.njeprint.adapter.MainRecAdapter
import com.example.njeprint.calculate.Quantity
import com.example.njeprint.datamodel.SharedDataList
import com.example.njeprint.databinding.ActivityMainBinding
import com.example.njeprint.initializeList
import com.example.njeprint.setLocale
import java.util.Locale

class MainActivity : AppCompatActivity(), TextViewUpdate {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myDatabas:SQLiteDatabase
    private lateinit var myDatabase: DatabasePrice
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        try {
            myDatabas=this.openOrCreateDatabase("NjePrint", MODE_PRIVATE,null)
        }catch (e:Exception){
            e.printStackTrace()
            binding.textView.text=e.message
        }
        myDatabase= DatabasePrice(myDatabas)
        val myInsertFirstData= InsertFirstData(myDatabas, SharedDataList.dataList)
        myDatabase.createDatabase()
        initializeList(SharedDataList.dataList,myDatabas)//Put values in arrayList according to status of Database
        binding.recycle.layoutManager=LinearLayoutManager(this)
        binding.recycle.adapter=MainRecAdapter(this)

    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.update_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.updateItem){
            val intent=Intent(this@MainActivity, UpdatePrices::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateTextView(newValue: Int) {
        binding.sumText.text=newValue.toString()
    }

  fun clear(view: View){
     Quantity.toplam=0
     for(i in 0..9){
         Quantity.quanties[i]=0
     }
      //Update content of RecyclerView
      binding.recycle.adapter?.notifyDataSetChanged()
  }

}