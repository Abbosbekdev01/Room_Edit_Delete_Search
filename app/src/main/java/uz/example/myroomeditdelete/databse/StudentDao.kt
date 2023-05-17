package uz.example.myroomeditdelete.databse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.example.myroomeditdelete.models.MyStudent


@Dao
interface StudentDao {

    @Insert
    fun addStudent(student: MyStudent)

    @Query("select * from MyStudent")
    fun getAllStudents(): ArrayList<MyStudent>

    @Query("select * from MyStudent WHERE name LIKE '%' || :searchQuery || '%' OR number LIKE '%' || :searchQuery || '%'")
    fun searchQuery(searchQuery: String): ArrayList<MyStudent>

    @Delete
    fun deleteStudent(student: MyStudent)

    @Update
    fun updateStudent(student: MyStudent)
}