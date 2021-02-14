package com.kashyap.billsplitit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.kashyap.billsplitit.datasave.Data_Save

class adapt(var context: Context,var al1:List<Data_Save>,var listener: Update_Listener) : RecyclerView.Adapter<adapt.viewh>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewh {
            var view=LayoutInflater.from(context).inflate(R.layout.recycle_custom,parent,false)

        return viewh(view)
    }
    fun setdata( al1:List<Data_Save>){
        this.al1= al1
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: viewh, position: Int) {
          var dataSave=al1[position]
              holder.catv1.text=dataSave.name
              holder.catv2.text= dataSave.amount.toString()


    }


    override fun getItemCount(): Int {
        return al1.size
    }
   inner class viewh(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var catv1=itemView.findViewById<TextView>(R.id.rctv1)
        var catv2=itemView.findViewById<TextView>(R.id.rctv2)
       init {
           itemView.setOnClickListener(this)
       }
       override fun onClick(p0: View?) {
      var position=adapterPosition
           if(position!=RecyclerView.NO_POSITION){
               listener.onItemClick(al1[position])
           }
       }

   }
    interface Update_Listener{
        fun onItemClick( dataSave: Data_Save)
    }

}
