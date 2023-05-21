package uz.example.myroomeditdelete

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import uz.example.myroomeditdelete.adapters.RvClick
import uz.example.myroomeditdelete.adapters.StudentAdapter
import uz.example.myroomeditdelete.databinding.ActivityMainBinding
import uz.example.myroomeditdelete.databinding.ItemDialogBinding
import uz.example.myroomeditdelete.databse.MyDbHelper
import uz.example.myroomeditdelete.models.MyStudent

class MainActivity : AppCompatActivity(),RvClick {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
     lateinit var myDbHelper: MyDbHelper
     lateinit var studentAdapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper.newInstance(this)
        studentAdapter = StudentAdapter(myDbHelper.studentDao().getAllStudents() as ArrayList<MyStudent>, this)

        binding.apply {
            myRv.adapter = studentAdapter
            binding.addBtn.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity).create()
                val itemDialog = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialog.root)

                itemDialog.btnSave.setOnClickListener {
                    val myStudent = MyStudent(
                        itemDialog.name.text.toString(),
                        itemDialog.number.text.toString()
                    )
                    myDbHelper.studentDao().addStudents(myStudent)
                    studentAdapter.list.add(myStudent)
                    studentAdapter.notifyItemChanged(studentAdapter.list.size - 1)
                    Toast.makeText(this@MainActivity, "Saved", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }
                dialog.show()
            }

            search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // todo:Qidiruv tugati
                    return true
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {
                    //todo: Qidiruv so'rovi o'zgarganda amalga oshiriladigan kodni yozing
                    val searchQuery = newText?: ""

                    //todo: Databasega so'rovni yuborish yoki qidirishni boshlash
                    val list = myDbHelper.studentDao().searchStudents(searchQuery)
                    studentAdapter.list.clear()
                    studentAdapter.list.addAll(list)
                    studentAdapter.notifyDataSetChanged()
                    return true
                }

            })
        }
    }

    override fun deleteStudent(myStudent: MyStudent, position: Int) {
        myDbHelper.studentDao().deleteStudent(myStudent)
        studentAdapter.list.remove(myStudent)
        studentAdapter.notifyItemRemoved(position)
        studentAdapter.notifyItemRangeChanged(position,studentAdapter.list.size )
    }

    override fun updateStudent(myStudent: MyStudent, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val itemDialog = ItemDialogBinding.inflate(layoutInflater)
        dialog.setView(itemDialog.root)
        itemDialog.name.setText(myStudent.name)
        itemDialog.number.setText(myStudent.number)

        itemDialog.btnSave.setOnClickListener {
            val newName = itemDialog.name.text.toString()
            val newNumber = itemDialog.number.text.toString()

            myStudent.name = newName
            myStudent.number = newNumber

            myDbHelper.studentDao().updateStudent(myStudent)
            studentAdapter.notifyItemChanged(position)
            dialog.cancel()
        }
        dialog.show()
    }
}