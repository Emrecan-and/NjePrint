package com.example.njeprint.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.njeprint.R
import com.example.njeprint.calculate.Quantity
import com.example.njeprint.calculate.TextViewUpdate
import com.example.njeprint.databinding.RecmainBinding
import com.example.njeprint.datamodel.SharedDataList
class MainRecAdapter(private val textViewUpdate: TextViewUpdate) : RecyclerView.Adapter<MainRecAdapter.MainRecHolder>(){
    class MainRecHolder(val binding:RecmainBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecHolder {
        val binding=RecmainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainRecHolder(binding)
    }

    override fun getItemCount(): Int {
        return Quantity.quanties.size
    }

    override fun onBindViewHolder(holder: MainRecHolder, position: Int) {
        val stringArray=holder.itemView.context.resources.getStringArray(R.array.names)
        holder.binding.text.text=stringArray.get(position)
        holder.binding.editTextNumber2.setText(Quantity.quanties[position].toString())
        holder.binding.buttonAdd.setOnClickListener {
            Quantity.quanties[position]=Quantity.quanties[position] + 1
            var a=Quantity.quanties[position].toString()
            holder.binding.editTextNumber2.setText(a)
            Quantity.toplam=Quantity.toplam + SharedDataList.dataList.get(position).price
            textViewUpdate.updateTextView(Quantity.toplam)
        }
        holder.binding.buttonMinus.setOnClickListener {
           if(Quantity.quanties[position]>0){
               Quantity.quanties[position]=Quantity.quanties[position]-1
               holder.binding.editTextNumber2.setText(Quantity.quanties[position].toString())
               Quantity.toplam=Quantity.toplam - SharedDataList.dataList.get(position).price
               textViewUpdate.updateTextView(Quantity.toplam)
           }
        }
       holder.binding.editTextNumber2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    var a=s.toString().toIntOrNull() ?: 0
                    if (a>=Quantity.quanties[adapterPosition]){
                        var b = a - Quantity.quanties[adapterPosition]
                        Quantity.quanties[adapterPosition] = a
                        Quantity.toplam += b * SharedDataList.dataList[adapterPosition].price
                        textViewUpdate.updateTextView(Quantity.toplam)
                    }
                    else{
                        var b=Quantity.quanties[adapterPosition] - a
                        Quantity.quanties[adapterPosition] = a
                        Quantity.toplam -= b * SharedDataList.dataList[adapterPosition].price
                        textViewUpdate.updateTextView(Quantity.toplam)
                    }
                }

            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }

}