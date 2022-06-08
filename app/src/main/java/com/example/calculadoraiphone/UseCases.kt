package com.example.calculadoraiphone

class UseCases() {

    fun maisMenos(numero: String): Double = (validarDecimal(numero) * (-1))

    fun porcentagem(numero: String): Double = (validarDecimal(numero) / 100)

    fun operacoesBasicas(numero1: String, numero2: String, operacao: Char): Double =
        when (operacao) {
            '+' -> validarDecimal(numero1) + validarDecimal(numero2)
            '-' -> validarDecimal(numero1) - validarDecimal(numero2)
            '*' -> validarDecimal(numero1) * validarDecimal(numero2)
            '/' -> validarDecimal(numero1) / validarDecimal(numero2)
            else -> numero1.toDouble()
        }

}