package com.example.calculadoraiphone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculadoraiphone.ui.theme.Branca
import com.example.calculadoraiphone.ui.theme.Laranja

class ViewModel : ViewModel() {

    var dados by mutableStateOf(Dados())
    var estado by mutableStateOf(EstadoEcra())

    fun onLimparNumeroTela() {
        estado = EstadoEcra()
    }

    fun onLimparDados() {
        estado = EstadoEcra()
        dados = Dados()
    }

    private fun _mudarNumero(numero: String) {
        if (dados.operacao.isWhitespace()) {
            dados = dados.copy(numero1 = numero)
            estado = estado.copy(numeroTela = dados.numero1)
        } else {
            dados = dados.copy(numero2 = numero)
            estado = estado.copy(numeroTela = dados.numero2)
        }

        estado = estado.copy(
            textoBotaoLimpar = "C",
            corFundoBotaoOperacao = Laranja,
            corTextoBotaoOperacao = Branca,
            tamanhoTexto = numero.mudarTamanho()
        )
    }

    private fun String.mudarTamanho(): Int=
        if (this.length < 6) 90
        else if (this.length in 6..7) 70
        else if (this.length in 8..9) 60
        else if (this.length in 10..11) 50 else 45

    fun mudarNumero(numero: String) {
        if (estado.limparNumero || estado.resultadoCalculado) onLimparNumeroTela()
        _mudarNumero(validarZero(estado.numeroTela, numero))
    }

    fun inserirDecimal(numero: String) {
        if (!numero.contains(","))
            estado = estado.copy(
                limparNumero = false,
                numeroTela = numero + ",",
                resultadoCalculado = false
            )
    }

    fun onMudarOperacao(op: Char) {
        if (estado.resultadoCalculado) {
            dados = dados.copy(operacao = ' ')
            mudarNumero(estado.numeroTela)
            estado = estado.copy(resultadoCalculado = false)
        }

        dados = dados.copy(operacao = op)
        estado = estado.copy(
            limparNumero = true,
            corFundoBotaoOperacao = Branca,
            corTextoBotaoOperacao = Laranja
        )
    }

    fun onCalcularOperacoesBasicas() {
        dados = dados.copy(resultado = dados.operacoesBasicas())
        estado = estado.copy(
            numeroTela = dados.resultado,
            resultadoCalculado = true
        )
    }

    fun maisMenos(numero: String) {
        estado = estado.copy(numeroTela = numero.maisOuMenos().formatarNumeroTela())
    }

    fun porcentagem(numero: String) {
        estado = estado.copy(numeroTela = numero.porcentagem().formatarNumeroTela())
    }
}