package com.example.calculadoraiphone

fun validarZero(numeroExistente: String, numero: String): String {
    return verificarDecimal(
        when (numeroExistente) {
            "0" -> numero
            else -> "${numeroExistente}$numero"
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

fun formatoNumero(numero: String): String {
    var texto = numero.substring(numero.indexOf(".") + 1)
    var decimal = false

    texto.forEach {
        if (it.toString().toInt() > 0)
            decimal = true
    }

    if (decimal == true) {
        texto = numero
        texto = texto.replace('.', ',')
    } else {
        texto = numero
        texto = texto.substring(0, texto.indexOf("."))
    }

    return texto
}