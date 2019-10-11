package com.najmehnasiriyani.kidsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.zip.Inflater

class CustomAdapter(var context:Context,var dataSource: ArrayList<Profile>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.custom_list_item, parent, false)
            val txtName = view.findViewById<View>(R.id.childNameText) as TextView
            val txtDOB = view.findViewById<View>(R.id.DOBText) as TextView
            txtName.text = dataSource[position].name
            txtDOB.text = dataSource[position].DOB
        }
    return view!!}

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return dataSource.count()
    }
}