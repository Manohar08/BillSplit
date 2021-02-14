package com.kashyap.billsplitit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var et1:EditText
    lateinit var et2:EditText
    lateinit var tv1:TextView
    lateinit var tv2:TextView
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et1=findViewById(R.id.a1ll2et1)
        tv1=findViewById(R.id.a1ll2tv1)
        et2=findViewById(R.id.a1ll3et1)
        tv2=findViewById(R.id.a1ll2tv1)
        btn1=findViewById(R.id.a1ll1btn1)
        btn1.setOnClickListener(View.OnClickListener {

            var persons: String = et2.text.toString()

            var person:Int=persons.toInt()
            Log.d("output is",person.toString())
            var intent:Intent=Intent(this,AddData::class.java)
            var bundle=Bundle()
            bundle.putInt("no_of_persons",person)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        })

    }

}