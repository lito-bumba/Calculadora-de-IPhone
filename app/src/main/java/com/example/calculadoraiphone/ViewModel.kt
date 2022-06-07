package com.example.calculadoraiphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    var numeroTela = MutableLiveData("0")
    var textoBotaoLimpar = MutableLiveData("AC")

    private val useCases = UseCases()
    private var numero1 = MutableLiveData("0")
    private var numero2 = MutableLiveData("0")
    private var resultado = MutableLiveData("0")
    private var operacao = MutableLiveData(' ')

    fun onLimparNumeroTela() {
        numeroTela.value = "0"
    }

    fun onLimparDados() {
        numeroTela.value = "0"
        numero1.value = "0"
        numero2.value = "0"
        operacao.value = ' '
        resultado.value = "0"
        textoBotaoLimpar.value = "AC"
    }

    fun mudarNumero(numero: String) {
        if (operacao.value?.isWhitespace() == true) {
            numero1.value = numero
            numeroTela.value = numero
        } else {
            numero2.value = numero
            numeroTela.value = numero
        }
        textoBotaoLimpar.value = "C"
    }

    fun onMudarOperacao(op: Char) {
        operacao.value = op
    }

    fun onCalcularOperacoesBasicas() {
        resultado.value = useCases.operacoesBasicas(
            numero1.value!!,
            numero2.value!!,
            operacao.value!!
        ).toString()
        numeroTela.value = resultado.value
    }

}