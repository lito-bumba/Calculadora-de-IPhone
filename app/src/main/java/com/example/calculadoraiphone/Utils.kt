package com.example.calculadoraiphone

fun validarDecimal(num: String): Double = when (num.contains(',')) {
    true -> num.replace(',', '.').toDouble()
    else -> num.toDouble()
}

fun validarZero(numeroExistente: String, numero: String): String {

    return verificarDecimal(
        when (numeroExistente) {
            "0" -> validarDecimal(numero)
            else -> validarDecimal("${numeroExistente}$numero")
        }.toDouble()
    )
}

fun verificarDecimal(num: Double): String {
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

fun formatarNumeroTela(numero: String): String {
    var numeros = numero.substring(numero.indexOf(".") + 1)
    var decimal = false

    numeros.forEach {
        if (it.toString().toInt() > 0)
            decimal = true
    }

    numeros = numero
    return when (decimal) {
        true -> numeros.replace('.', ',')
        false -> numeros.substring(0, numeros.indexOf("."))
    }
}