package com.kashyap.billsplitit.datasave

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Data_viewmodel(application: Application):AndroidViewModel(application) {

    var getalldata:LiveData<List<Data_Save>>

    lateinit var repositoryClass:Repository_class

    init {
        var databaseClass=Database_class.getInstance(application)
        var dao=databaseClass.dao()
         repositoryClass=Repository_class(dao)
        getalldata=repositoryClass.getdata

    }
    fun insert(dataSave: Data_Save)=viewModelScope.launch {
        repositoryClass.Insert(dataSave)
    }

    fun Update(dataSave: Data_Save)=viewModelScope.launch {
        repositoryClass.Update(dataSave)
    }

    fun DeleteallData()=viewModelScope.launch {
        repositoryClass.Deleteall()
    }

     fun SelectCalcdata():List<Data_Save> = runBlocking {
         repositoryClass.SelectCalc()
     }

      fun getCount():Int= runBlocking {
          repositoryClass.getidCount()
      }


}