package com.example.partnerworkflowdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val expandableListView: ExpandableListView = findViewById(R.id.expandableListView)
        var selectedItemData = arrayListOf<String>()

        // Example data
        val listItems = listOf(
            ListData("Group 1", arrayListOf("Item 1", "Item 2", "Item 3")),
            ListData("Group 2", arrayListOf("Item A", "Item B", "Item C"))
        )

        val adapter = ExpandableListAdapter(this, listItems,selectedItemData)
        expandableListView.setAdapter(adapter)
    }
}