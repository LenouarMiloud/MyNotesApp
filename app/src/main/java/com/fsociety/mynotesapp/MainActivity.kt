package com.fsociety.mynotesapp

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cursoradapter.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var accessDB :SQLiteDatabase? = null
    var cursor : Cursor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Creating the datasource*/
        //create our Databse "myNotesDB"
        var objectDataBase = myNoteSQLiteOpenHelper(this)
        //access to our database
        accessDB = objectDataBase.readableDatabase
        //get the data from our DataBase
        cursor = accessDB!!.query("myNotes", arrayOf("id","title"),
                null,null,null,null,null,
                )

        //creating the adapter
        val listAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor,
                arrayOf("title"),
                intArrayOf(android.R.id.text1),
                0
        )
        listViewNotes.adapter = listAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
        accessDB!!.close()
    }

}