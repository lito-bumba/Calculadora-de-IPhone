package com.example.calculadoraiphone

import androidx.compose.ui.graphics.Color
import com.example.calculadoraiphone.ui.theme.Branca
import com.example.calculadoraiphone.ui.theme.Laranja

data class EstadoEcra(
    val limparNumero: Boolean = false,
    val numeroTela: String = "0",
    val textoBotaoLimpar: String = "AC",
    val resultadoCalculado: Boolean = false,
    val corFundoBotaoOperacao: Color = Laranja,
    val corTextoBotaoOperacao: Color = Branca,
    val tamanhoTexto: Int = 90
)

data class Dados(
    val numero1: String = "0",
    val numero2: String = "0",
    val operacao: Char = ' ',
    val resultado: String = "0"
)

fun Dados.operacoesBasicas(): String =
    UseCases().operacoesBasicas(this)
        .toString().formatarNumeroTela()

fun String.maisOuMenos(): String =
    UseCases().maisMenos(this).toString()

fun String.porcentagem(): String =
    UseCases().porcentagem(this).toString()