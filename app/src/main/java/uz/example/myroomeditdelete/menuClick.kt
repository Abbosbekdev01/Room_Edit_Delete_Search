package uz.example.myroomeditdelete

import android.widget.ImageView
import uz.example.myroomeditdelete.models.MyStudent

interface RvEvent {
    fun menuClick(My_studenyt: MyStudent, view: ImageView)
    fun callNow(myStudent: MyStudent)
}