package com.example.calculadoraiphone

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseCases() {

    fun validarNumero(num: String): Double = when (num.contains(',')) {
        true -> num.replace(',', '.').toDouble()
        else -> num.toDouble()
    }

    fun maisMenos(numero: String): Double = (validarNumero(numero) * (-1))

    fun porcentagem(numero: String): Double = (validarNumero(numero) / 100)

    fun operacoesBasicas(numero1: String, numero2: String, operacao: Char): Double =
        when (operacao) {
        '+' -> validarNumero(numero1) + validarNumero(numero2)
        '-' -> validarNumero(numero1) - validarNumero(numero2)
        '*' -> validarNumero(numero1) * validarNumero(numero2)
        else -> validarNumero(numero1) / validarNumero(numero2)
    }

}