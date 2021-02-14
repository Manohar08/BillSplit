package com.kashyap.billsplitit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kashyap.billsplitit.datasave.Data_Save
import kotlinx.android.synthetic.main.activity_add_data.*
import java.io.Serializable

class Calculate_Activity : AppCompatActivity() {

    lateinit var calcll1:LinearLayout
    val s1:String="he receive"
    val s2:String="he pay"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_)

setTitle("Calculate_bill")
       calcll1=findViewById(R.id.calcll1)
        var al1=ArrayList<Data_Save>()
        al1=intent.getParcelableArrayListExtra("calculatelist")
       var itemc:Int=intent.getIntExtra("itemcount",0)

           var arraylistTextview1=ArrayList<TextView>()
        var arraylistTextView2=ArrayList<TextView>()
          for(i:Int in 0..itemc-1){
                var view:View=layoutInflater.inflate(R.layout.calculate_child,null,false)
           var   catv1:TextView=view.findViewById(R.id.calc_childtv1)
             var catv2:TextView=view.findViewById(R.id.calc_childtv2)

              arraylistTextview1.add(catv1)
              arraylistTextView2.add(catv2)
              calcll1.addView(view)
          }



    var size:Int=al1.size
        var arr= IntArray(size)
        Log.d("size of array is",size.toString())
        var totalAmount:Int=0
         for (data_save:Data_Save in al1){
             var name:String=data_save.name
             var amount:Int=data_save.amount
             Log.d("name is",amount.toString())
             totalAmount=totalAmount+amount

         }
          var mainbalance:Int=totalAmount/itemc
      //    catv2.text="Rs "+mainbalance.toString()+" each"

        var i:Int=0
        for(data_save:Data_Save in al1){
            var name:String=data_save.name
            var amounts:Int=data_save.amount
            var catv1:TextView=arraylistTextview1.get(i)
            var catv2:TextView=arraylistTextView2.get(i)

            if(amounts>mainbalance){
                catv1.text=name+" "+ "has to Receive"
                arr[i]=amounts-mainbalance
                  catv2.text=arr[i].toString()
                Log.d(s1,arr[i].toString())


            }
            else{
                catv1.text=name+ " "+"has to Pay"
                arr[i]=mainbalance-amounts
                Log.d(s2,arr[i].toString())
                 catv2.text=arr[i].toString()
            }
            i++
        }
    }
}