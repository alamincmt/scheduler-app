package com.example.schedulerapp.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulerapp.R
import com.example.schedulerapp.listeners.ItemClickListener
import com.plcoding.mvvmtodoapp.data.AppInfo


class AppListAdapter() : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {
    private var context: Context? = null
    private var installedApps: List<AppInfo>? = null
    private var clickListener: ItemClickListener? = null

    constructor(context: Context?, installedApps: List<AppInfo>?) : this() {
        this.context = context
        this.installedApps = installedApps
    }

    fun setData(withdrawMethodsList: List<AppInfo>?) {
        installedApps = withdrawMethodsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemView: View =
            LayoutInflater.from(context).inflate(R.layout.item_app_info, parent, false)
        return AppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val data: AppInfo = installedApps!![position]
//        holder.iv_logo.setImageDrawable(data.app_icon())
        holder.tv_name.setText(data.app_name)
        holder.tv_packagename.setText(data.app_package_name)

//        holder.iv_clock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    override fun getItemCount(): Int {
        return installedApps!!.size
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        clickListener = itemClickListener
    }

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val tv_name: TextView
        val tv_packagename: TextView
        val iv_logo: ImageView
        private val iv_clock: ImageView
        override fun onClick(v: View) {
            if (clickListener != null) clickListener!!.onClick(v, adapterPosition)
        }

        init {
            iv_logo = itemView.findViewById(R.id.iv_logo)
            tv_name = itemView.findViewById(R.id.tv_name)
            tv_packagename = itemView.findViewById(R.id.tv_packagename)
            iv_clock = itemView.findViewById(R.id.iv_clock)

//            iv_clock.setOnClickListener(this);
            itemView.setOnClickListener(this)
        }
    }
}
