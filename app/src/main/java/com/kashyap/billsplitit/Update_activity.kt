package com.kashyap.billsplitit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Update_activity : AppCompatActivity() {
    lateinit var et1:EditText
    lateinit var et2:EditText
lateinit var btn1:Button
companion object{
    val  update_names:String="name"
    val  update_amounts:String="amount"
    val update_ids:String="id"

}
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_activity)
        et1=findViewById(R.id.ua2ll1et1)
        et2=findViewById(R.id.ua2ll2et1)
        btn1=findViewById(R.id.ua2btn1)
   setTitle("Update_Activity")
        var intent=intent
        if(intent.hasExtra(update_ids)){
            et1.text=Editable.Factory.getInstance().newEditable(intent.getStringExtra(update_names))

             var amounts:Int=intent.getIntExtra(update_amounts,1)
          et2.text=Editable.Factory.getInstance().newEditable(amounts.toString())

        }
        btn1.setOnClickListener(View.OnClickListener {
        var intent:Intent=Intent()
             var name:String=et1.text.toString()
            var amount:Int=et2.text.toString().toInt()
            intent.putExtra(update_names,name)
            intent.putExtra(update_amounts,amount)
            var id1=getIntent().getIntExtra(update_ids,-1)
            Log.d("id value  in update is",id1.toString())
            if(id1!=-1){
                intent.putExtra(update_ids,id1)
            }
            setResult(RESULT_OK,intent)
         finish()
        })
    }
}