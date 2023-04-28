package com.example.basicdictionaryapp

class Wordsdao {

    fun getWordsFilterByWordName(dbh:DatabaseHelper, word_name:String):ArrayList<Words>{
        val wordsListFromSql= ArrayList<Words>()

        val db= dbh.writableDatabase

        val cursor= db.rawQuery("SELECT*FROM words WHERE words.english like '${word_name}%'",null)

        while (cursor.moveToNext()){
            val word= Words(cursor.getInt(cursor.getColumnIndexOrThrow("word_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("english")),
                cursor.getString(cursor.getColumnIndexOrThrow("turkish")))

            wordsListFromSql.add(word)
        }

        return wordsListFromSql
    }

}