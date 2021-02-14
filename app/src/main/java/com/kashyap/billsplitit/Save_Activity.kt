package com.kashyap.billsplitit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.inflate
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kashyap.billsplitit.datasave.Data_Save
import com.kashyap.billsplitit.datasave.Data_viewmodel

class Save_Activity : AppCompatActivity(),adapt.Update_Listener{
    lateinit var rv1:RecyclerView
    lateinit var ad:adapt
  lateinit var fl1:FloatingActionButton
   var itemcount:Int =0
    lateinit var viewModel: Data_viewmodel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_)
        setTitle("Save_Data")
        fl1=findViewById(R.id.fl1)
        rv1=findViewById(R.id.rv1)

        var al1=ArrayList<Data_Save>()
       var ek=intent.getIntExtra("run",-1)
        //get the size of recyclerview(because if size of recyclerview is null then we have to go main activity else adddata activity)

        if(ek>0) {
            al1 = intent.getParcelableArrayListExtra("name")
            Log.e("ek value is",ek.toString())
        }

            ad = adapt(this, al1, this)
            var linearLayout = LinearLayoutManager(this)
            rv1.layoutManager = linearLayout
            rv1.adapter = ad







//view model ka kaam
            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            ).get(Data_viewmodel::class.java)
            viewModel.getalldata.observe(this, Observer { list ->
                list?.let {
                    ad.setdata(it)
                }

                /* @Override
           fun onChanged(@Nullable  notes:ArrayList<Data_Save>){

           }*/
            })



        var m=0
        for(data_save:Data_Save in al1 ){
            var data=al1.get(m)
            var dataSave=Data_Save(data.name,data.amount)
            viewModel.insert(dataSave)
            Toast.makeText(this,"value inserted",Toast.LENGTH_LONG).show()
            m++
        }

//
        var listsize:Int=viewModel.getCount()
        if(listsize<=0   ) {
            Log.d("size of recyclerview is",listsize.toString())
            fl1.setOnClickListener(View.OnClickListener {

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })
        }
        //else we have to go adddata activity where every time only one data will added
        else {
            fl1.setOnClickListener(View.OnClickListener {
                var intent=Intent(this,AddData::class.java)
                startActivity(intent)
            })
        }

    }

    override fun onItemClick(dataSave: Data_Save) {
        Toast.makeText(this,"message",Toast.LENGTH_SHORT).show()
        var name:String=dataSave.name
        var amount:Int=dataSave.amount
        var id:Int=dataSave.id
        var intent=Intent(this,Update_activity::class.java)
        intent.putExtra(Update_activity.update_ids,id)
        intent.putExtra(Update_activity.update_names,name)
        intent.putExtra(Update_activity.update_amounts,amount)

      startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode== RESULT_OK){
               var ids:Int?= data?.getIntExtra(Update_activity.update_ids,-1)
            Log.d("ids value in save is",ids.toString() )
                 if(ids==-1){
                     Toast.makeText(this,"field cannot updated",Toast.LENGTH_SHORT).show()
                 }

                    var name= data?.getStringExtra(Update_activity.update_names)
                     var amounts= data?.getIntExtra(Update_activity.update_amounts,1)
                     var dataSave=Data_Save(name!!, amounts!!)
            if(ids!=null){
                dataSave.id=ids
            }
                     viewModel.Update(dataSave)
            Toast.makeText(this,"data updated",Toast.LENGTH_LONG).show()



        }
        else{
            Toast.makeText(this,"data not saved",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bill_menu,menu)

        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.item1){

            viewModel.DeleteallData()

            return true
        }
        else if(item.itemId==R.id.item2){
            var al2= ArrayList<Data_Save>()
            var calculatebill=ArrayList<Data_Save>()
           al2= viewModel.SelectCalcdata() as ArrayList<Data_Save>
            itemcount=ad.itemCount
            for(data_save:Data_Save in al2)
            {
                var name:String=data_save.name
                var amount:Int=data_save.amount
                var ds=Data_Save(name,amount)
                calculatebill.add(ds)


            }
            var intent=Intent(this,Calculate_Activity::class.java)
            intent.putExtra("calculatelist",calculatebill)
            intent.putExtra("itemcount",itemcount)
            Log.d("itemcount value is",itemcount.toString())
            startActivity(intent)





            return  true
        }
        else if(item.itemId==R.id.item3){

            return true
        }
        else {
            return super.onOptionsItemSelected(item)
        }
    }






}