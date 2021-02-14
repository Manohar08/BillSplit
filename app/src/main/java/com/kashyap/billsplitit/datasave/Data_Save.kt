package com.kashyap.billsplitit.datasave

import android.content.Context
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Data_Save(@ColumnInfo(name="User_Name") var name:String, @ColumnInfo(name="User_amount") var amount:Int,):Parcelable{

     @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int=0



}
