package com.example.partnerworkflowdemoapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class ExpandableListAdapter(
    private val context: Context,
    private val listItems: List<ListData>,
    selectedItemData: ArrayList<String>
) : BaseExpandableListAdapter() {
    var selItemData = selectedItemData
    override fun getGroupCount(): Int {
        return listItems.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listItems[groupPosition].subItemList.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listItems[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listItems[groupPosition].subItemList[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        val groupTitle = view.findViewById<TextView>(R.id.action)
        groupTitle.text = listItems[groupPosition].item
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.sub_item_layout, parent, false)
        val textView = view.findViewById<TextView>(R.id.subList)
        textView.text = listItems[groupPosition].subItemList[childPosition]
        if(selItemData.contains(listItems[groupPosition].subItemList[childPosition])){
            textView.setBackgroundColor(ContextCompat.getColor(context, R.color.skyBlue))
        }
        else{
            textView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
        }

        // Set the initial background color (optional)

        // Handle the click event to change background color
        textView.setOnClickListener {
            if(selItemData.contains(listItems[groupPosition].subItemList[childPosition])) {
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent))
                selItemData.remove(listItems[groupPosition].subItemList[childPosition])
            }
            else{
                textView.setBackgroundColor(ContextCompat.getColor(context, R.color.skyBlue))
                selItemData.add(listItems[groupPosition].subItemList[childPosition])
            }
            Log.d("List",selItemData.toString())
        }

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}