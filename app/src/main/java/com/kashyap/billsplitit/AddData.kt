package com.kashyap.billsplitit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.children
import androidx.core.view.get
import com.kashyap.billsplitit.datasave.Data_Save
import kotlinx.android.synthetic.main.activity_add_data.*

class AddData : AppCompatActivity() {

     lateinit var tv1:TextView
     lateinit var tv2:TextView
     lateinit var savebtn:Button
     lateinit var calculatebtn:Button
     lateinit var sv1:ScrollView
     lateinit var ll1:LinearLayout
     lateinit var model:Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_add_data)
        setTitle("Add_Data")

        var bundle:Bundle?= intent.extras
        var ek:Int=1

       var person:Int?= bundle?.getInt("no_of_persons")
        Log.e("aise hi",person.toString())
       Toast.makeText(this,"person",Toast.LENGTH_SHORT).show()

       sv1=findViewById(R.id.sv1)
       var ll1:LinearLayout=findViewById(R.id.ll1)
        savebtn=findViewById(R.id.savebtn)

    //    setLayout()
        var a:Int=1
        var b:Int=10
        var al1=ArrayList<EditText>()
        var al2=ArrayList<EditText>()
        if (person != null) {


            for (i in 1..person!!) {
                var v: View = layoutInflater.inflate(R.layout.custom, null, false)


                var et1: EditText = v.findViewById(R.id.a2ll1et1)

                var et2: EditText = v.findViewById(R.id.a2ll2et1)
                al1.add(et1)
                al2.add(et2)

                Log.d("id of et1 is", et1.toString())
                Log.d("id of et2 is", et2.toString())


                ll1.addView(v)
            }

        }
        //else we have to add only one activity
        else{
            var v:View=layoutInflater.inflate(R.layout.custom,null,false)
            var et1:EditText=v.findViewById(R.id.a2ll1et1)
            var et2: EditText = v.findViewById(R.id.a2ll2et1)
            al1.add(et1)
            al2.add(et2)

            Log.d("id of et1 is", et1.toString())
            Log.d("id of et2 is", et2.toString())


            ll1.addView(v)
        }
        //layout function

         //fetch value of name and amout



        //savebtn clicck listener
        var nameal1=ArrayList<Data_Save>()
        var amountal1=ArrayList<Int>()
        var list=ArrayList<Model>()

        savebtn.setOnClickListener(View.OnClickListener {
        var j=0
         for(i:EditText in al1)
         {
             var et1:EditText=al1.get(j)
             var et2:EditText=al2.get(j)
             var name:String=et1.text.toString()
             var amounts:String=et2.text.toString()
             var amount:Int=amounts.toInt()

             nameal1.add(Data_Save(name,amount))
         //  amountal1.add(amount)


             j++
         }




            var i:Intent=Intent(this,Save_Activity::class.java)
          //  var bundle:Bundle=Bundle()
       //     bundle.putParcelableArrayList("name",nameal1)
          i.putExtra("name",nameal1)
            i.putExtra("run",ek)
            startActivity(i)
        })

       

    }





   fun setLayout(){

        tv1=findViewById(R.id.a2ll1tv1)

        tv2=findViewById(R.id.a2ll2tv1)

    }
}