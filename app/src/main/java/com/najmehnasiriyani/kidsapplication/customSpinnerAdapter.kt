package com.najmehnasiriyani.kidsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.country_spinner_item.view.*
import kotlin.collections.ArrayList

class customSpinnerAdapter ( val myArray: ArrayList< String>,
                             val context: Context
): BaseAdapter() {

    override fun getItem(position: Int): Any {
        return myArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return myArray.count()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
       // val view = inflater.inflate(R.layout.country_spinner_item, parent)
        val view = inflater.inflate(R.layout.country_spinner_item, parent, false)
        view.country_name_text.text = myArray[position]
       // view.country_flag_text.text = myArray[position]["flag"]
        return view
    }
}