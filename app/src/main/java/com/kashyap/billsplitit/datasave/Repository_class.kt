package com.kashyap.billsplitit.datasave

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class Repository_class(private val dao: Daos) {
 var getdata:LiveData<List<Data_Save>>


init {
    getdata=dao.SelectData()

}







    @Suppress("ReduntantSupressModifier")
    @WorkerThread
    suspend fun  Insert(dataSave: Data_Save){
       dao.InsertData(dataSave)

    }
    @WorkerThread
    suspend fun Update(dataSave: Data_Save){
        dao.UpdateData(dataSave)
    }
    @WorkerThread
    suspend fun Deleteall(){
        dao.Deletealldata()
    }

    @WorkerThread
    suspend fun SelectCalc():List<Data_Save>{
      var calcdata=  dao.SelectCalculatedata()
        return calcdata
    }
    @WorkerThread
    suspend fun getidCount():Int{
         var getidc=dao.getCount()
        return  getidc
    }
}