package jp.ac.it_college.s20015.quiz

open class Quiz {
    var id : Long = 0
    var question : String = ""
    var answers : Int = 0
    var choices : List<String> = arrayListOf()
}

fun randomChooseQuiz(count: Int): List<Quiz> {

    val list = .where<Quiz>().findAll().shuffled()

    return if (list.size < count) list
    else list.subList(0, count)
}
