package com.example.njeprint

import android.content.Context
import android.content.res.Configuration
import android.database.sqlite.SQLiteDatabase
import com.example.njeprint.database.InsertFirstData
import com.example.njeprint.datamodel.Data
import com.example.njeprint.datamodel.SharedDataList
import java.util.Locale

fun initializeList(dataList:ArrayList<Data>,myDatabase:SQLiteDatabase){
    val cursor = myDatabase.rawQuery("SELECT COUNT(*) FROM prices", null)

    if (cursor != null) {
        cursor.moveToFirst()
        val count = cursor.getInt(0)

        if (count == 0) {
            // Table is empty
            val data1= Data("PrintA4Black",30)
            val data2= Data("PrintA4Coloured",140)
            val data3= Data("PrintA3Black",60)
            val data4= Data("PrintA3Coloured",280)
            val data5= Data("CopyA4Black",30)
            val data6= Data("CopyA4Coloured",140)
            val data7= Data("CopyA3Black",60)
            val data8= Data("CopyA3Coloured",280)
            val data9= Data("ScanA4",20)
            val data10= Data("ScanA3",40)
            for (i in 1..10) {
                val data = when (i) {
                    1 -> data1
                    2 -> data2
                    3 -> data3
                    4 -> data4
                    5 -> data5
                    6 -> data6
                    7 -> data7
                    8 -> data8
                    9 -> data9
                    10 -> data10
                    else -> null
                }
                data?.let { dataList.add(it) }
            }
            val myInsertFirstData=InsertFirstData(myDatabase,dataList) // Add first values in Database
            myInsertFirstData.insertAllValues(dataList) // Add first values in Database
        } else {
            // Table is not empty
            for (i in 0 until 10) {
                dataList.add(Data("", 0)) // Create empty field for ArrayList
            }
            try{
                var cursor=myDatabase.rawQuery("SELECT * FROM prices",null)
                var typeIx=cursor.getColumnIndex("type")
                var priceIx=cursor.getColumnIndex("price")
                var i=0;
                while(cursor.moveToNext()){
                    SharedDataList.dataList[i].type=cursor.getString(typeIx)
                    SharedDataList.dataList[i].price=cursor.getInt(priceIx)
                    i++
                }
            }catch (e:Exception){
                System.out.println(e.message)
            }
        }

        cursor.close()
    }

}
fun setLocale(languageCode:String,context: Context){
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = Configuration()
    config.setLocale(locale)

    context.resources.updateConfiguration(config, context.resources.displayMetrics)

}