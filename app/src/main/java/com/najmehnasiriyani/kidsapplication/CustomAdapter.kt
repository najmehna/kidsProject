package com.najmehnasiriyani.kidsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.zip.Inflater

class CustomAdapter(var context:Context,var dataSource: ArrayList<Child>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null){
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.custom_list_item, parent, false)
            val txtName = view.findViewById<View>(R.id.childNameText) as TextView
            val txtid = view.findViewById<View>(R.id.idText) as TextView
            txtName.text = dataSource[position].name
            txtid.text = dataSource[position].id
        }
    return view!!}

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
return position.toLong()  }

    override fun getCount(): Int {
        return dataSource.count()
    }
}