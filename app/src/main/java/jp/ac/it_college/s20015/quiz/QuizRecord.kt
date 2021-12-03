package jp.ac.it_college.s20015.quiz

import android.app.Application

data class QuizRecord (
    val question:String = "",
    val answers:Long = 0,
    val choice1:String = "" ,
    val choice2:String = "" ,
    val choice3:String = "" ,
    val choice4:String = "" ,
    val choice5:String = "" ,
    val choice6:String = ""
)


/*
private val helper = DatabaseHelper(this)

            override fun onCreate(){
        super.onCreate()

        val db = helper.writableDatabase

        checkVersion("${QuizApp.quizApi_URL}&${QuizApp.VER_ID}")

        val sqlDelete = """
                DELETE FROM quizrecord
                WHERE _id = ?
            """.trimIndent()
        var stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1, questionId.toLong())
        stmt.executeUpdateDelete()

        val sqlInsert = """
                INSERT INTO quizrecord (_id , question , answers , choice1 , choice2 , choice3 , choice4 , choice5 , choice6)
                VALUES (? , ? , ?)
            """.trimIndent()
        stmt = db.compileStatement(sqlInsert)
        stmt.bindLong(1, questionId.toLong())
        stmt.bindString(2, question)
        stmt.bindString(3, answers)
        stmt.bindString(4,choice1)
        stmt.bindString(5,choice2)
        stmt.bindString(6,choice3)
        stmt.bindString(7,choice4)
        stmt.bindString(8,choice5)
        stmt.bindString(9,choice6)
        stmt.executeInsert()
    }

            private fun receiveQuizRecord(result: String) {
        val rootJSON = JSONObject(result)
        val question = rootJSON.getString("question")
        val answers = rootJSON.getInt(answers)
        val choicesJSONarray = rootJSON.getJSONArray("choices")
        val choice1JSON = choicesJSONarray.getJSONObject(0)
        val choice2JSON = choicesJSONarray.getJSONObject(1)
        val choice3JSON = choicesJSONarray.getJSONObject(2)
        val choice4JSON = choicesJSONarray.getJSONObject(3)
        val choice5JSON = choicesJSONarray.getJSONObject(4)
        val choice6JSON = choicesJSONarray.getJSONObject(5)
    }
   */