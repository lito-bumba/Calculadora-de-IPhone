package com.example.calculadoraiphone

class Dados(
    var numero1: Double = 0.0,
    var numero2: Double = 0.0,
    var operacao: Char? = null
)

fun Calcular(dados: Dados): Double {

    return when (dados.operacao) {
        '+' -> dados.numero1 + dados.numero2
        '-' -> dados.numero1 - dados.numero2
        'x' -> dados.numero1 * dados.numero2
        '/' -> dados.numero1 / dados.numero2
        else -> 0.0
    }
}

fun ValidarZero(numeroExistente: String, numero: String): String {
    return VerificarDecimal(
        when (numeroExistente) {
            "0" -> numero
            else -> "${numeroExistente}$numero"
        }.toDouble()
    )
}

fun VerificarDecimal(num: Double): String {
    var decimal = false
    var texto = num.toString()
    texto = texto.substring(texto.indexOf(".") + 1)

    texto.forEach {
        if (it.toString().toInt() > 0)
            decimal = true
    }

    if (decimal == true) {
        texto = num.toString()
        texto = texto.replace('.', ',')
    } else {
        texto = num.toString()
        texto = texto.substring(0, texto.indexOf("."))
    }

    return texto
}