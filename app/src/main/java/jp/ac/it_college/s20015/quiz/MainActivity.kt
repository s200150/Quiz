package jp.ac.it_college.s20015.quiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import jp.ac.it_college.s20015.quiz.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL


class MainActivity : AppCompatActivity() {

    companion object {
        const val DEBUG_TAG = "quiztask"
        const val quizApi_URL =
            "https://script.google.com/macros/s/AKfycbznWpk2m8q6lbLWSS6qaz3uS6j3L4zPwv7CqDEiC433YOgAdaFekGJmjoAO60quMg6l/execf="
        const val VER_ID = "version"
        const val DATE_ID = "date"
    }

    private fun checkAPI(url: String) : String{
        var result = ""
        val url = URL(url)
        val con = url.openConnection() as? HttpURLConnection
        con?.let {
            try {
                it.connectTimeout = 1000
                it.readTimeout = 1000
                it.requestMethod = "GET"
                it.connect()
                val stream = it.inputStream
                result = returns(stream)
                stream.close()
            }
            catch (ex: SocketTimeoutException){
                Log.w(DEBUG_TAG , "通信タイムアウト" , ex)
            }
            it.disconnect()
        }
        return result
    }

    private fun returns(stream: InputStream): String{
        val reader = BufferedReader(InputStreamReader(stream , "UTF-8"))
        return reader.readText()
    }

    private var defoVer:Int = 20211118

    private lateinit var binding: ActivityMainBinding
    private val helper = DatabaseHelper(this)

    fun receiveVerRunner(result: String): Int{
        val rootJSON = JSONObject(result)
        val version = rootJSON.getString("version").toInt()
        return version
    }

    fun receiveQuizInfoRunner(result: String) {
        val rootJSON = JSONObject(result)
        val choicesArray = rootJSON.getJSONArray("choices")
        val choices = choicesArray.getJSONObject(0)

        val db = helper.writableDatabase

        for (i in 0 until rootJSON.length()){
            val data = JSONArray(result).getJSONObject(i)
            val sqlInsert = """
            INSERT INTO quizrecord
            (_id , question , answers , choices)
            VALUES (? , ? , ? , ? )
            """.trimIndent()
            val stmt = db.compileStatement(sqlInsert)
            stmt.bindLong(1, rootJSON.getLong("id"))
            stmt.bindString(2, rootJSON.getString("questions"))
            stmt.bindString(3, rootJSON.getString("answers"))
            stmt.bindString(4,choices.getString("choices"))
            stmt.executeInsert()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = helper.writableDatabase

        val nowVer:Int = receiveVerRunner(checkAPI("$quizApi_URL&$VER_ID"))

        if (nowVer != defoVer) {
            defoVer = nowVer

            receiveQuizInfoRunner(checkAPI("$quizApi_URL&$DATE_ID"))
        }
    }

    override fun onDestroy() {
        helper.close()
        super.onDestroy()
    }
}