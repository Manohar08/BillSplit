package com.kashyap.billsplitit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(var name:String,var amount:Int):Parcelable {
}