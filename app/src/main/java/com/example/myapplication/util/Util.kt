package com.example.myapplication.util

import android.widget.EditText

fun editTextIsEmpty(vararg editTexts: EditText): Boolean {
    for (editText in editTexts) {
        if (editText.text.toString().isEmpty()) {
            return true
        }
    }
    return false
}

fun editTextEmptyError(vararg editTexts: EditText): Unit {
    for (editText in editTexts) {
        if (editText.text.toString().isEmpty()) {
            return editText.setError("Falta preencher esse campo.")
        }
    }
}

fun saoIguais(editText1: EditText, editText2: EditText): Boolean {
    return editText1.text.toString().equals(editText2.text.toString())
}

fun minCaracteres(editText: EditText): Boolean {
    if (editText.text.toString().length < 6){
        return true
    }
    return false
}

fun emailValido(email: EditText): Boolean {
    return email.text.toString().contains("@") && email.text.toString().contains(".com")
}