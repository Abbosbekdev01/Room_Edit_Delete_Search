package uz.example.myroomeditdelete.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.myroomeditdelete.databinding.RvItemBinding
import uz.example.myroomeditdelete.models.MyStudent

class StudentAdapter(var list: ArrayList<MyStudent> = ArrayList(), val rvClick: RvClick) :
    RecyclerView.Adapter<StudentAdapter.Vh>() {

    inner class Vh(private val itemRv: RvItemBinding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(myStudent: MyStudent, position: Int) {
            itemRv.tvName.text = myStudent.name
            itemRv.tvNumber.text = myStudent.number

            itemRv.btnDelete.setOnClickListener {
                rvClick.deleteStudent(myStudent, position)
            }
            itemRv.btnEdit.setOnClickListener {
                rvClick.updateStudent(myStudent, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }
}

interface RvClick {
    fun deleteStudent(myStudent: MyStudent, position: Int)
    fun updateStudent(myStudent: MyStudent, position: Int)
}