package com.fsociety.mynotesapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cursoradapter.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var accessDB :SQLiteDatabase? = null
    var cursor : Cursor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //open the Notes Details Activity
        floatingActionButton.setOnClickListener {
            openNotesDetailsActivity(0)
        }

        listViewNotes.setOnItemClickListener { parent, view, position, id ->
            openNotesDetailsActivity(id)
        }

    }

    fun openNotesDetailsActivity(noteId:Long){
        val intent = Intent(this,NotesDetailsActivity::class.java)
        intent.putExtra("NOTE_ID",noteId)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        /*Creating the datasource*/
        //create our Databse "myNotesDB"
        var objectDataBase = myNoteSQLiteOpenHelper(this)
        //access to our database
        accessDB = objectDataBase.readableDatabase
        //get the data from our DataBase
        cursor = accessDB!!.query("myNotes", arrayOf("_id","title"),
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