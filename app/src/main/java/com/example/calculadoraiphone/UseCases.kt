package com.example.calculadoraiphone

class UseCases {

    fun maisMenos(numero: String): Double = (numero.validarDecimal().toDouble() * (-1))

    fun porcentagem(numero: String): Double = (numero.validarDecimal().toDouble() / 100)

    fun operacoesBasicas(dados: Dados): Double =
        when (dados.operacao) {
            '+' -> dados.numero1.validarDecimal().toDouble() + dados.numero2.validarDecimal().toDouble()
            '-' -> dados.numero1.validarDecimal().toDouble() - dados.numero2.validarDecimal().toDouble()
            '*' -> dados.numero1.validarDecimal().toDouble() * dados.numero2.validarDecimal().toDouble()
            '/' -> dados.numero1.validarDecimal().toDouble() / dados.numero2.validarDecimal().toDouble()
            else -> dados.numero1.toDouble()
        }

}