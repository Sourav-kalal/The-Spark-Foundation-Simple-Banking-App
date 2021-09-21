package com.example.banking_app.Adapter

import android.content.Context
import android.content.res.Resources
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banking_app.Database.Entity
import com.example.banking_app.R


class TransAdapter(val context: Context,val arrayList: ArrayList<Entity>) :RecyclerView.Adapter<TransAdapter.Trans_vh>() {
    class Trans_vh(val view: View): RecyclerView.ViewHolder(view){
        var tv_user: TextView = view.findViewById(R.id.tv_Trans_cus_name);
        var tv_amt_sent : TextView = view.findViewById(R.id.tv_amt_sent);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Trans_vh {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trans_list,parent,false);
        return (Trans_vh(view));
    }

    override fun getItemCount(): Int {
        return arrayList.size;
    }

    override fun onBindViewHolder(holder: Trans_vh, position: Int) {
        val E = arrayList[position];
        val bal = String.format(context.getString(R.string.amt), E.balance);
        holder.tv_amt_sent.text=bal;
        holder.tv_user.text=E.firstName;
    }
}