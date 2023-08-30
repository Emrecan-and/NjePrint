package com.example.njeprint.database

import android.database.sqlite.SQLiteDatabase
import com.example.njeprint.database.DatabasePrice
import com.example.njeprint.datamodel.Data

class InsertFirstData(myDatabase:SQLiteDatabase,dataList:ArrayList<Data>) : DatabasePrice(myDatabase) {
   fun insertAllValues(dataList: ArrayList<Data>){
       for(data in dataList){
       insertValues(data.type,data.price)
   }
   }
}