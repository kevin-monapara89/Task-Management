package com.kevin.taskmanagement.Adapter

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.getDefaultSize
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.TodolistviewBinding
import java.nio.file.attribute.AclEntry.Builder

class TaskAdapter(getTask: ArrayList<TaskEnitiy>, Update: (TaskEnitiy) -> Unit, delete: (Int) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    var getTask = getTask
    var update = Update
    var delete = delete
    lateinit var context: Context
    var isChecked = 0
  private val CHANNEL_ID = "channelId"
    private val notificationId = 101



    class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        context = parent.context
        var binding = TodolistviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return getTask.size
    }

    override fun onBindViewHolder(holder: TaskHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.apply {
            getTask.get(position).apply {
                txttitle.text = title
                txtdiscription.text = discription
                txtdate.text = date
                txtdate.text = month
                txtdate.text = year
                txttime.text = hour
                txttime.text = minute
            }
        }

        holder.binding.menuitem.setOnClickListener(fun (v: View?) {
            var popupMenu = PopupMenu(context, holder.itemView)
            popupMenu.menuInflater.inflate(R.menu.delete_ubdate, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {

                    if (p0?.itemId == R.id.update) {
                        update.invoke(getTask.get(position))
                    }

                    if (p0?.itemId == R.id.delete) {
                        delete.invoke(getTask.get(position).id)
                    }
                    return true
                }
            })
            popupMenu.show()
        })

////
//holder.binding.apply {
//    getTask.get(position).apply {
//
//    imgCheck.setOnClickListener {
//
//
//        if (isChecked == false) {
//            logobg.setCardBackgroundColor(Color.parseColor("#7CB342"))
//
//
//        } else {
//            logobg.setCardBackgroundColor(Color.parseColor("#EEF6E54F"))
//
//        }
//    }
//    }
//
//}
       holder.binding.apply {

           createNotifictionChannel()
           notify.setOnClickListener {

               var builder = NotificationCompat.Builder(context,CHANNEL_ID)
               builder.setSmallIcon(R.drawable.baseline_notifications_none_24)
                   .setContentTitle("Text Title")
                   .setContentText("This is Task Notify")
                   .setPriority(NotificationCompat.PRIORITY_DEFAULT)



               with(NotificationManagerCompat.from(context)){
                   if (ActivityCompat.checkSelfPermission(
                           context,
                           Manifest.permission.POST_NOTIFICATIONS
                       ) != PackageManager.PERMISSION_GRANTED
                   ) {

                       return@with
                   }
                   notify(1,builder.build())
               }




           }



       }

    }

    @SuppressLint("NewApi")
    private fun createNotifictionChannel() {

         fun getSystemService(notificationService: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "first channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = "Text Description"

                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)

            }

        }

    }

    fun update(l: List<TaskEnitiy>) {
        this.getTask = l as ArrayList<TaskEnitiy>
        notifyDataSetChanged()

    }

    }





