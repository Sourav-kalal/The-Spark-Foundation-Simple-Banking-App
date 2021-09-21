package com.example.banking_app.Activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banking_app.Adapter.ContactAdapter
import com.example.banking_app.Database.Entity
import com.example.banking_app.R


class ContactList : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView;
    lateinit var adapter: RecyclerView.Adapter<ContactAdapter.contactHolder>;
    lateinit var arrayList: ArrayList<Entity>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacy_list);

        val colorDrawable = ColorDrawable(Color.parseColor("#FF7043"))

        val actionBar = getSupportActionBar()
        actionBar?.title="CONTACT DETAILS";
        actionBar?.setBackgroundDrawable(colorDrawable);

        recyclerView = findViewById(R.id.recyle_view);
        recyclerView.layoutManager = LinearLayoutManager(this@ContactList);
        arrayList = arrayListOf(Entity(1,"MAREENA","100"),Entity(2,"EMILY","100"),Entity(3,"RAMESH","100"),Entity(4,"VIKAS","100"),Entity(5,"TONY","100"),Entity(6,"LILY","100"),Entity(7,"EMMA","100"),Entity(8,"LUCY","100"),Entity(9,"AKASH","100"),Entity(10,"RAVI","100"));
        adapter = ContactAdapter(this@ContactList, arrayList);
        recyclerView.adapter = adapter;
    }
}