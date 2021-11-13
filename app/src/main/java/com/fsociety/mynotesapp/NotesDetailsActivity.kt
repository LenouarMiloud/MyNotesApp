package com.fsociety.mynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class NotesDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_details)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.notes_details_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }
}