package com.example.calculadoraiphone

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UseCasesTest {

    @Test
    fun `Validar Número, Virgula e Ponto`() {
        val numero = "32,4"
        val result =  numero.validarDecimal()
        assertThat(result).isEqualTo(32.4)
    }

    @Test
    fun `Mais ou Menos`() {
        val numero = "-32.04"
        val result = UseCases().maisMenos(numero)
        assertThat(result).isEqualTo(32.04)
    }

    @Test
    fun `Porcentagem`() {
        val numero = "30"
        val result = UseCases().porcentagem(numero)
        assertThat(result).isEqualTo(0.3)
    }

    @Test
    fun `Operações Básicas - Soma`() {
        val dados = Dados("30", "40", '+')
        val result = UseCases().operacoesBasicas(dados)
        assertThat(result).isEqualTo(70)
    }

    @Test
    fun `Operações Básicas - Subtração`() {
        val dados = Dados("30", "40", '-')
        val result = UseCases().operacoesBasicas(dados)
        assertThat(result).isEqualTo(-10)
    }

    @Test
    fun `Operações Básicas - Multiplicação`() {
        val dados = Dados("30", "40", '*')
        val result = UseCases().operacoesBasicas(dados)
        assertThat(result).isEqualTo(1200)
    }

    @Test
    fun `Operações Básicas - Divisão`() {
        val dados = Dados("30", "40", '/')
        val result = UseCases().operacoesBasicas(dados)
        assertThat(result).isEqualTo(0.75)
    }

}