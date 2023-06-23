package com.kevin.taskmanagement.Fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.PopupMenu
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.taskmanagement.Adapter.TaskAdapter
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.FragmentHomeBinding
import java.util.Locale
import com.kevin.taskmanagement.databinding.UpdatedialogBinding
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.time.Duration.Companion.days

//
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: TaskAdapter
    var Tasklist = ArrayList<TaskEnitiy>()
    lateinit var tempadapter: ArrayList<TaskEnitiy>
    lateinit var db: RoomDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        db = RoomDB.init(context)
        tempadapter = ArrayList<TaskEnitiy>()

        initview()

        return binding.root
    }

    private fun initview() {
        var list = db.task().GetTask()
        adapter = TaskAdapter(
            list as ArrayList<TaskEnitiy>, {
                Update(it)
            }, {
                Delete(it)
            })

        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
        binding.rcvtasklist.adapter = adapter

        binding.sortby.setOnClickListener(fun (v: View?) {
            var popupMenu = PopupMenu(context,view)
            popupMenu.menuInflater.inflate(R.menu.sortby, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {

                    if (p0?.itemId == R.id.oltola) {
                        tempadapter.sortBy {
                            it.date
                        }
                    }

                    if (p0?.itemId == R.id.latool) {
                        tempadapter.sortByDescending {
                            it.date
                        }
                    }
                    adapter.update(db.task().GetTask())
                    return true
                }
            })
            popupMenu.show()
        })

//        binding.searchData.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchView.clearFocus()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                seachList.clear()
//                var searchtext = newText!!.toLowerCase(Locale.getDefault())
//                if (searchtext.isNotEmpty()){
//                    seachList.forEach {
//                        if (it.title.toLowerCase(Locale.getDefault()).contains(searchtext)) {
//                            seachList.add(it)
//                        }
//                    }
//                    binding.rcvtasklist.adapter!!.notifyDataSetChanged()
//                } else {
//                    seachList.clear()
//                    seachList.addAll(seachList)
//                    binding.rcvtasklist.adapter!!.notifyDataSetChanged()
//                }
//                return false
//            }
//        })
    }

    private fun Update(it: TaskEnitiy) {

        var dialog = Dialog(requireContext())
        var b = UpdatedialogBinding.inflate(layoutInflater)
        dialog.setContentView(b.root)

        b.edtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            b.edtdate.text = currentDate

            var dialog =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                        var Year = p1
                        var Month = p2 + 1
                        var Date = p3

                        var selectedDate = "$p3-${(p2 + 1)}-$p1"
                        b.edtdate.text = selectedDate
                    }
                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()
        }

        b.edttime.setOnClickListener {
            var date = Date()

            var format2 = SimpleDateFormat("hh:mm")
            var currentTime = format2.format(date)

            b.edttime.text = currentTime
            var seleTime = currentTime
            var dialog1 = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                    var hour = p1
                    var minute = p2
                    var sdf = SimpleDateFormat("hh:mm", Locale.US)
                    var tme = "$hour:$minute "
                    b.edttime.setText(tme)
                }
            }, 10, 0, true)
            dialog1.show()
        }

        b.btnsubmit.setOnClickListener {
            var title = b.edtTask.text.toString()
            var text = b.edtdescription.text.toString()
            var Date = b.edtdate.text.toString()
            var Month = b.edtdate.text.toString()
            var Year = b.edtdate.text.toString()
            var hour = b.edttime.text.toString()
            var minute = b.edttime.text.toString()
            var format = SimpleDateFormat("dd-MM-YY hh:mm")
            var current = format.format(Date())
            var data = TaskEnitiy(title, text, Date, Month, Year, hour, minute)
            var tasks = db.task().GetTask()

            for (task in tasks) {
                task.title = title
                task.discription = text
                task.date = Date
                task.month = Month
                task.year = Year
                task.hour = hour
                task.minute = minute
                task.id

                if (title.isEmpty() || text.isEmpty() || Date.isEmpty() || Month.isEmpty() || Year.isEmpty() || hour.isEmpty() || minute.isEmpty()) {
                    Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show()
                } else {
                    b.edtTask.setText("")
                    b.edtdescription.setText("")
                    b.edtdate.setText("")
                    b.edttime.setText("")
                    var data = TaskEnitiy(title, text, Date, Month, Year, hour, minute)
                    db.task().UpdateTask(task)
                }
            }
            adapter.update(db.task().GetTask())
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun Delete(it: Int) {
        db.task().DeleteTask(it)
        adapter.update(db.task().GetTask())
    }
}