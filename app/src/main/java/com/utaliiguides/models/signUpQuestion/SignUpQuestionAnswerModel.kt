package com.utaliiguides.models.signUpQuestion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpQuestionAnswerModel {
    //[{"ques_id":1,ans:"1"},{"ques_id":1,ans:"1"}]
    @SerializedName("ques_id")
    @Expose
    private var ques_id: Int? = null
    @SerializedName("ans")
    @Expose
    private var ans: String? = null

    fun getQuestionId(): Int? {
        return ques_id
    }

    fun setQuestionId(id: Int?) {
        this.ques_id = id
    }

    fun getAnswer(): String? {
        return ans
    }

    fun setAnswer(question: String) {
        this.ans = question
    }
}