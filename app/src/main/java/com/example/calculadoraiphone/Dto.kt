package com.example.calculadoraiphone

data class Dto (
    var numero1: String,
    var numero2: String = "0",
    var operacao: Char? = null
)