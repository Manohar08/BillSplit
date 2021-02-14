package com.kashyap.billsplitit.datasave
import androidx.room.Dao
import androidx.room.*
import androidx.lifecycle.LiveData
@Dao
interface Daos {

    @Insert
  suspend  fun InsertData(dataSave: Data_Save)

    @Update
  suspend  fun UpdateData(dataSave: Data_Save)

  @Query("delete from Data_Save")
  suspend fun Deletealldata()

    @Query("select * from Data_Save")
    fun SelectData():LiveData<List<Data_Save>>

    @Query("select * from Data_Save")
   suspend fun SelectCalculatedata():List<Data_Save>

   @Query("select count(id) from Data_Save")
   suspend fun   getCount():Int
}