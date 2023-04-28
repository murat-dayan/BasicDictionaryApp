package com.example.basicdictionaryapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "dictionary.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"words\" (\n" +
                "\t\"word_id\"\tINTEGER,\n" +
                "\t\"english\"\tTEXT,\n" +
                "\t\"turkish\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"word_id\")\n" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS words")
        onCreate(db)
    }
}