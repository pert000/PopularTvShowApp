package com.example.populartvshowapp.ui.details.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.populartvshowapp.R
import com.example.populartvshowapp.model.CreatedBy

class CreatorAdapter(context: Context, arrayList: List<CreatedBy>) :
    ArrayAdapter<CreatedBy>(context, 0, arrayList ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentItemView = convertView

        if (currentItemView == null) {
            currentItemView =
                LayoutInflater.from(context).inflate(R.layout.creator_item, parent, false)
        }
        val name = getItem(position)



        currentItemView?.findViewById<TextView>(R.id.creator)?.apply {

            text = name?.name
        }

        return currentItemView!!
    }
}