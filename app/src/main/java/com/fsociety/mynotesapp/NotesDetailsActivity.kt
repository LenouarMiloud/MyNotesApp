package com.fsociety.mynotesapp

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_notes_details.*

class NotesDetailsActivity : AppCompatActivity() {

    var database:SQLiteDatabase? = null
    var noteId:Int = 0
    var curson:Cursor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_details)

        val myNotesDatabaseHelper = myNoteSQLiteOpenHelper(this)
        database = myNotesDatabaseHelper.writableDatabase

        noteId = intent.extras!!.get("NOTE_ID").toString().toInt()
        //read the note title and description
        if(noteId != 0){
            curson = database!!.query("myNotes",
                arrayOf("TITLE","DESCRIPTION"),
                "_id=?", arrayOf(noteId.toString())
                ,null,null,null)

            if(curson!!.moveToFirst()){
               titleEditText.setText(curson!!.getString(0))
               descriptionEditText.setText(curson!!.getString(1))
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId!! == R.id.saveNote ){
            //Insert Note into Database
            val newNoteValue = ContentValues()

            if (titleEditText.text.isEmpty()) {
                newNoteValue.put("TITLE", "Untitled")
            } else {
                newNoteValue.put("TITLE", titleEditText.text.toString())
            }

            if(noteId == 0){
                insertData(newNoteValue)
            }else{
                updateNote(newNoteValue)
            }
        }else if(item.itemId!! == R.id.deleteNote){
            deleteNote()
        }


        return super.onOptionsItemSelected(item)
    }

    private fun insertData(newNoteValue:ContentValues) {

        newNoteValue.put("DESCRIPTION", descriptionEditText.text.toString())

        database!!.insert("myNotes", null, newNoteValue)
        Toast.makeText(this, "Note saved successful", Toast.LENGTH_LONG).show()

        //clear the EditText again
        titleEditText.setText("")
        descriptionEditText.setText("")

        //shifting the focus to title editText
        titleEditText.requestFocus()
    }

    private fun updateNote(newNoteValue:ContentValues){
        database!!.update("myNotes",newNoteValue,"_id=?", arrayOf(noteId.toString()))
        Toast.makeText(this,"Note Updated!!",Toast.LENGTH_LONG).show()
    }

    private fun deleteNote(){

        var dialogue:AlertDialog
        var builder = AlertDialog.Builder(this)

        //set the title
        builder.setTitle("Delete Note")
        builder.setMessage("Are you sure you want to delete '${titleEditText.text}'?")

        //set the alert dialogue positive button
        builder.setPositiveButton("YES",dialogueClickListner)
        //set the alert dialogue neutral button
        builder.setNeutralButton("Cancel",dialogueClickListner)

        dialogue = builder.create()
        //display the dialogue
        dialogue.show()

    }
    val dialogueClickListner = DialogInterface.OnClickListener { _, which ->

        if(which == DialogInterface.BUTTON_POSITIVE){
            database!!.delete("myNotes","_id=?", arrayOf(noteId.toString()))
            Toast.makeText(this,"Note deleted!!",Toast.LENGTH_LONG).show()
            //close the NoteDetailsActivity 
            finish()
        }

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