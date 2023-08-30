package com.example.njeprint.database

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.example.njeprint.datamodel.SharedDataList
import java.io.Serializable

open class DatabasePrice(var myDatabasePrice:SQLiteDatabase){
    fun createDatabase(){
        try {
            myDatabasePrice.execSQL("CREATE TABLE IF NOT EXISTS prices(type VARCHAR PRIMARY KEY,price INT)")
        }
        catch (e:Exception){
            System.out.println(e.message)
            e.printStackTrace()
        }
    }
    open fun insertValues(a:String, b:Int){
        try {
            myDatabasePrice.execSQL("INSERT INTO prices(type,price) VALUES ('$a','$b')")
        }catch (e:Exception){
            System.out.println(e.message)
            e.printStackTrace()
        }
    }
    fun select(){
        try{
        var cursor=myDatabasePrice.rawQuery("SELECT * FROM prices",null)
        var typeIx=cursor.getColumnIndex("type")
        var priceIx=cursor.getColumnIndex("price")
            while(cursor.moveToNext()){
                System.out.println(cursor.getString(typeIx))
                System.out.println(cursor.getInt(priceIx))
        }
    }catch (e:Exception){
        System.out.println(e.message)
    }
}
   fun updateDatabase(){
       for(data in SharedDataList.dataList){
           try {
               myDatabasePrice.execSQL("UPDATE prices SET price='${data.price}' WHERE type='${data.type}'")
           }catch (e:Exception){
               System.out.println(e.message)
               e.printStackTrace()
           }
       }
   }
}