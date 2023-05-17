package uz.example.myroomeditdelete

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.myroomeditdelete.databinding.RvItemBinding
import uz.example.myroomeditdelete.models.MyStudent

class Rv_Adabter(var list:List<MyStudent>, val rvEvent: MainActivity): RecyclerView.Adapter<Rv_Adabter.Vh>() {

    inner class Vh(val rvItem: RvItemBinding): RecyclerView.ViewHolder(rvItem.root){
        fun onBind(my_Student: MyStudent){
            rvItem.edtName.text=my_Student.name
            rvItem.edtNumber.text=my_Student.number
            rvItem.icMore.setOnClickListener {
                rvEvent.menuClick(my_Student, rvItem.icMore)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}