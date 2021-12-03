package jp.ac.it_college.s20015.quiz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context , DATABASE_NAME , null , DATABASE_VARSION
){
    companion object {
        private const val DATABASE_NAME = "quizrecord.db"
        private const val DATABASE_VARSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE quizrecord(
                _id INTEGER PRIMARY KEY, 
                question TEXT,
                answers TEXT,
                choice1 TEXT,
                choice2 TEXT,
                choice3 TEXT,
                choice4 TEXT,
                choice5 TEXT,
                choice6 TEXT
           ); 
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldver: Int, newver: Int) {
    }
}