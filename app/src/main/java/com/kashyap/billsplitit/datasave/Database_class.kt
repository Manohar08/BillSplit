package com.kashyap.billsplitit.datasave
import android.content.Context
import androidx.room.*


@Database(entities = (arrayOf(Data_Save::class)),version = 2,exportSchema = false)
abstract class Database_class:RoomDatabase() {
    abstract fun dao():Daos


    companion object {
        @Volatile
        var Instance:Database_class?=null
        fun getInstance(context: Context): Database_class {

            return Instance?: synchronized(this){
                var instance=Room.databaseBuilder(
                    context.applicationContext,Database_class::class.java,"database_class"
                ).build()
                Instance=instance
                 instance
            }
        }

    }

}