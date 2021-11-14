package com.fsociety.mynotesapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_notes_details.*

class NotesDetailsActivity : AppCompatActivity() {

    var database:SQLiteDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_details)

        val myNotesDatabaseHelper = myNoteSQLiteOpenHelper(this)
        database = myNotesDatabaseHelper.writableDatabase


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId!! == R.id.saveNote ){
            //Insert Note into Database
            val newNoteValue = ContentValues()

            if(titleEditText.text.isEmpty()){
                newNoteValue.put("TITLE","Untitled")
            }else{
                newNoteValue.put("TITLE",titleEditText.text.toString())
            }


            newNoteValue.put("DESCRIPTION",descriptionEditText.text.toString())

            database!!.insert("myNotes",null,newNoteValue)
            Toast.makeText(this,"Note saved successful",Toast.LENGTH_LONG).show()

            //clear the EditText again
            titleEditText.setText("")
            descriptionEditText.setText("")

            //shifting the focus to title editText
            titleEditText.requestFocus()

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        database!!.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.notes_details_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }
}