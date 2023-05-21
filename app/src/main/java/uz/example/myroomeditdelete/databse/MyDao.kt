package uz.example.myroomeditdelete.databse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.example.myroomeditdelete.models.MyStudent

@Dao
interface MyDaoInterface {

    @Insert
    fun addStudents(myStudent: MyStudent)

    @Query("select * from MyStudent")
    fun getAllStudents(): List<MyStudent>

    @Query("select * from MyStudent where name like '%' || :searchQuery || '%' or number like '%' || :searchQuery || '%'")
    fun searchStudents(searchQuery: String): List<MyStudent>

    @Delete
    fun deleteStudent(myStudent: MyStudent)

    @Update
    fun updateStudent(myStudent: MyStudent)
}