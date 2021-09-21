package com.example.banking_app.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.banking_app.Database.Database
import com.example.banking_app.Database.Entity
import com.example.banking_app.R

class ContactAdapter(val context: Context, val Arr: ArrayList<Entity>) : RecyclerView.Adapter<ContactAdapter.contactHolder>() {

    lateinit var input: EditText;
    lateinit var entity: Entity;

    class contactHolder(view: View) : RecyclerView.ViewHolder(view) {
        val Username = view.findViewById<TextView>(R.id.tv_name);
        val send = view.findViewById<Button>(R.id.btn_send);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false);
        return contactHolder(view);
    }

    override fun getItemCount(): Int {
        return Arr.size;
    }

    override fun onBindViewHolder(holder: contactHolder, position: Int) {
        val ContactDdetails = Arr[position];
        holder.Username.text = ContactDdetails.firstName.toString();
        holder.send.setOnClickListener {
            val builder = AlertDialog.Builder(context);
            builder.setTitle("Enter The Amount that you would like to send");
            input = EditText(context);
            input.inputType = InputType.TYPE_CLASS_TEXT;
            builder.setView(input);

            builder.setPositiveButton("SEND NOW ", DialogInterface.OnClickListener { _, which ->
                val id = ContactDdetails.uid;
                val bal = input.text.toString();
                val user = holder.Username.text.toString();
                entity = Entity(id,user, bal);
                System.out.println("name " + user + " bal " + bal);
                if (bal != "") {
                    send();
                } else {
                    Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            })
            builder.show();
        }
    }

    class asynTask(val context: Context, val entity: Entity) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean? {
            val db =
                    Room.databaseBuilder(context, Database::class.java, "User").build();
            val s = db.userDao().insert(entity);
            return s!=null;
        }
    }

    fun send() {
        val k = asynTask(context, entity).execute().get();
        if(k){
            Toast.makeText(context,"Money Sent to "+entity.firstName,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Error ocuured ..!! ",Toast.LENGTH_SHORT).show();
        }
    }
}