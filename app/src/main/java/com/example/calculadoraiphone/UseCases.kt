package com.example.calculadoraiphone

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseCases constructor(private val dto: Dto) {

    fun validarNumero(num: String): Double = when (num.contains(',')) {
        true -> num.replace(',', '.').toDouble()
        else -> num.toDouble()
    }

    fun maisMenos(): Double = (validarNumero(dto.numero1) * (-1))

    fun porcentagem(): Double = (validarNumero(dto.numero1) / 100)

    fun operacoesBasicas(): Double = when (dto.operacao) {
        '+' -> validarNumero(dto.numero1) + validarNumero(dto.numero2)
        '-' -> validarNumero(dto.numero1) - validarNumero(dto.numero2)
        '*' -> validarNumero(dto.numero1) * validarNumero(dto.numero2)
        else -> validarNumero(dto.numero1) / validarNumero(dto.numero2)
    }

    fun operacoesBasicasII(): Flow<Double> = flow {
        emit(operacoesBasicas())
    }

}