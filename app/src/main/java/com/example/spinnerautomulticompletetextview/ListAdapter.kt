package com.example.spinnerautomulticompletetextview

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter (context: Context, employeesList: MutableList<Employee>) :
    ArrayAdapter<Employee>(context, R.layout.list_item, employeesList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val employee = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val nameTV = view?.findViewById<TextView>(R.id.nameTV)
        val secondNameTV = view?.findViewById<TextView>(R.id.secondNameTV)
        val ageTV = view?.findViewById<TextView>(R.id.ageTV)
        val roleTV = view?.findViewById<TextView>(R.id.roleTV)

        nameTV?.text = employee?.name
        secondNameTV?.text = employee?.secondName
        ageTV?.text = employee?.age
        roleTV?.text = employee?.role
        return view!!
    }
}