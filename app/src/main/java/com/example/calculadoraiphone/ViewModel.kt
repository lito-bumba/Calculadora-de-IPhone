package com.example.calculadoraiphone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculadoraiphone.ui.theme.Branca
import com.example.calculadoraiphone.ui.theme.Laranja

class ViewModel : ViewModel() {

    var numeroTela = MutableLiveData("0")
    var textoBotaoLimpar = MutableLiveData("AC")
    var estadoCalculado = MutableLiveData(false)
    var corFundoOperacao = MutableLiveData(Laranja)
    var corTextoOperacao = MutableLiveData(Branca)
    var tamanhoTexto = MutableLiveData(100)

    private val useCases = UseCases()
    var numero1 = MutableLiveData("0")
    var numero2 = MutableLiveData("0")
    private var resultado = MutableLiveData("0")
    var operacao = MutableLiveData(' ')

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
        estadoCalculado.value = false
    }

    fun mudarNumero(numero: String) {

        if (operacao.value?.isWhitespace() == true)
            numero1.value = numero
        else
            numero2.value = numero

        numeroTela.value = numero
        textoBotaoLimpar.value = "C"

        corFundoOperacao.value = Laranja
        corTextoOperacao.value = Branca
        mudarTamanhoTexto()
    }

    fun inserirDecimal(numero: String) {
        if (!numero.contains(","))
            numeroTela.value = numero + ","
    }

    fun onMudarOperacao(op: Char) {
        operacao.value = op

        if (estadoCalculado.value!!){
            operacao.value = ' '
            mudarNumero(numeroTela.value!!)
            operacao.value = op
            estadoCalculado.value = false
        }

        corFundoOperacao.value = Branca
        corTextoOperacao.value = Laranja
    }

    fun onCalcularOperacoesBasicas() {
        resultado.value = formatarNumeroTela(
            useCases.operacoesBasicas(
                numero1.value!!,
                numero2.value!!,
                operacao.value!!
            ).toString()
        )
        numeroTela.value = resultado.value
        estadoCalculado.value = true
    }

    fun maisMenos(numero: String){
        numeroTela.value = formatarNumeroTela(
            useCases.maisMenos(numero).toString()
        )
    }

    fun porcentagem(numero: String){
        numeroTela.value = formatarNumeroTela(
            useCases.porcentagem(numero).toString()
        )
    }

    fun mudarTamanhoTexto(){
        when(numeroTela.value!!.length){
            6 -> tamanhoTexto.value = 70
            9 -> tamanhoTexto.value = 60
            10 -> tamanhoTexto.value = 50
            12 -> tamanhoTexto.value = 40
        }
    }

}