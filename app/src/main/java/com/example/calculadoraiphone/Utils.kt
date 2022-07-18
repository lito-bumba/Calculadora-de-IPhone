package com.example.calculadoraiphone

fun String.validarDecimal(): String = when (this.contains(',')) {
    true -> this.replace(',', '.')
    else -> this
}

fun validarZero(numeroExistente: String, numero: String): String {
    return when (numeroExistente) {
        "0" -> numero.validarDecimal()
        else -> "${numeroExistente}$numero".validarDecimal()
    }.verificarDecimal()
}

fun String.verificarDecimal(): String {
    var decimal = false
    var texto = this.substring(this.indexOf(".") + 1)

    if (this.contains('E') || this.contains('e'))
        return this

    texto.forEach {
        if (it.toString().toInt() > 0)
            decimal = true
    }

    texto = this
    if (decimal)
        texto = texto.replace('.', ',')
    else
        texto = texto.substring(0, texto.indexOf("."))

    return texto
}

fun String.formatarNumeroTela(): String {
    var numeros = this.substring(this.indexOf(".") + 1)
    var decimal = false

    if (this.contains('E'))
        return this.replace('.', ',')

    numeros.forEach {
        if (it.toString().toInt() > 0)
            decimal = true
    }

    numeros = this
    return when (decimal) {
        true -> numeros.replace('.', ',')
        false -> numeros.substring(0, numeros.indexOf("."))
    }
}