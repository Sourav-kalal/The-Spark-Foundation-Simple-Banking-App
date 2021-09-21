package com.example.banking_app.Activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.WindowDecorActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.banking_app.Database.Database
import com.example.banking_app.Database.Entity
import com.example.banking_app.Adapter.TransAdapter
import com.example.banking_app.R

class Transaction : AppCompatActivity() {

    lateinit var ls : List<Entity>;
    lateinit var recyclerView: RecyclerView;
    lateinit var adapter: RecyclerView.Adapter<TransAdapter.Trans_vh>;
    lateinit var arrayList: ArrayList<Entity>;
    lateinit var actionBar: WindowDecorActionBar;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val colorDrawable = ColorDrawable(Color.parseColor("#FF7043"))

        val actionBar = getSupportActionBar();
        actionBar?.title = "TRANSCATIONS";
        actionBar?.setBackgroundDrawable(colorDrawable);


        recyclerView = findViewById(R.id.recyle_view1);
        recyclerView.layoutManager = LinearLayoutManager(this@Transaction);
        ls= asynTask(this@Transaction).execute().get();
        arrayList= arrayListOf();
        arrayList.addAll(ls);
        System.out.println("Hello"+arrayList);
        adapter = TransAdapter(this@Transaction, arrayList);
        recyclerView.adapter = adapter;
    }

    class asynTask(val context: Context) : AsyncTask<Void, Void, List<Entity>>() {
        override fun doInBackground(vararg params: Void?): List<Entity> {
            val db =
                    Room.databaseBuilder(context, Database::class.java, "User").build();
            val ss = db.userDao().getAll();
            db.close()
            return ss;
        }
    }

}