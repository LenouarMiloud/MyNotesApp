package com.fsociety.mynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create our Databse "myNotesDB"
        var objectDataBase = myNoteSQLiteOpenHelper(this)
        //access to our database
        var accessDB = objectDataBase.readableDatabase
        //get the data from our DataBase
        var cursor = accessDB.query("myNotes", arrayOf("id","title"),
                null,null,null,null,null,
                )




    }
}