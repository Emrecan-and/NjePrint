package com.example.njeprint.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.njeprint.R
import com.example.njeprint.databinding.RecycleBinding
import com.example.njeprint.datamodel.Data
import com.example.njeprint.datamodel.SharedDataList

class RecAdapter(val dataList:ArrayList<Data>) :RecyclerView.Adapter<RecAdapter.RecHolder>(){
class RecHolder(val binding:RecycleBinding):RecyclerView.ViewHolder(binding.root){

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val binding=RecycleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val stringArray=holder.itemView.context.resources.getStringArray(R.array.names)//Take from string.xml
        holder.binding.typeText.text=stringArray[position]
        holder.binding.editTextNumber.hint=dataList.get(position).price.toString()
        holder.binding.editTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    dataList[adapterPosition].price = s.toString().toInt()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }


}