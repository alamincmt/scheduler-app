package com.example.schedulerapp.views

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulerapp.R
import com.example.schedulerapp.listeners.ItemClickListener
import com.plcoding.mvvmtodoapp.data.AppInfo
import java.util.*

private var appInfoList: List<AppInfo>? = null
var picker: TimePickerDialog? = null
var recyclerview: RecyclerView? = null
private var appListAdapter: AppListAdapter? = null
private var linearLayoutManager: LinearLayoutManager? = null

class MainActivity : AppCompatActivity(), ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview)

        appInfoList = getAppList()

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        appListAdapter = AppListAdapter(this, appInfoList!!)
        recyclerview!!.setAdapter(appListAdapter)
        recyclerview!!.setLayoutManager(linearLayoutManager)
        appListAdapter!!.setClickListener(this)
    }
    private fun getAppList(): List<AppInfo>? {
        val pm = packageManager
        val apps: MutableList<AppInfo> = ArrayList()
//        val packs = packageManager.getInstalledPackages(0)
        val packs = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
//        context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

        //List<PackageInfo> packs = getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);

        for (p in packs) {
            val appName = p.loadLabel(packageManager).toString()
            val icon = p.loadIcon(packageManager)
            val packages = p.packageName
            apps.add(AppInfo(appName, packages, ""+icon, false, Date()))
        }
        return apps
    }

    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }


    override fun onClick(view: View?, position: Int) {
        val intent =
            packageManager.getLaunchIntentForPackage(appInfoList!!.get(position).app_package_name)
        if (intent != null) {

//            startActivity(intent);
            val cldr = Calendar.getInstance()
            val hour = cldr[Calendar.HOUR_OF_DAY]
            val minutes = cldr[Calendar.MINUTE]
            // time picker dialog
            picker = TimePickerDialog(this@MainActivity,
                { tp, sHour, sMinute ->
                    Toast.makeText(
                        applicationContext,
                        "$sHour:$sMinute",
                        Toast.LENGTH_SHORT
                    ).show()
                }, hour, minutes, true
            )
            picker!!.show()
        } else {
            Toast.makeText(
                this@MainActivity,
                appInfoList!!.get(position).app_package_name
                    .toString() + " Error, Please Try Again...",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}