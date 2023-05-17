package uz.example.myroomeditdelete.databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.example.myroomeditdelete.models.MyStudent

@Database(entities = [MyStudent::class], version = 1)
abstract class MyDbHelper : RoomDatabase() {

    abstract fun userDao(): StudentDao

    companion object{
        private var instance:MyDbHelper? = null
        fun newInstance(context: Context):MyDbHelper{
            if (instance == null){
                instance = Room.databaseBuilder(context, MyDbHelper::class.java, "database_name")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}