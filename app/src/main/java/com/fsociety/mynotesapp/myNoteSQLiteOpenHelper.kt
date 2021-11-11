package com.fsociety.mynotesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class myNoteSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(context,"myNotesDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE myNotes("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"title TEXT"
                +"description TEXT)"
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}