package com.utaliiguides.models.signUpQuestion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class QuestionListData {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("question")
    @Expose
    private var question: String? = null
    @SerializedName("answers")
    @Expose
    private var answers: String? = null
    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getQuestion(): String? {
        return question
    }

    fun setQuestion(question: String) {
        this.question = question
    }

    fun getAnswers(): String? {
        return answers
    }

    fun setAnswers(answers: String) {
        this.answers = answers
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String) {
        this.updatedAt = updatedAt
    }

}