package com.example.calculadoraiphone

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UseCasesTest {

    @Test
    fun `Validar Número, Virgula e Ponto`() {
        val numero = "32,4"
        val result =  UseCases().validarNumero(numero)
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
        val result = UseCases().operacoesBasicas("30", "40", '+')
        assertThat(result).isEqualTo(70)
    }

    @Test
    fun `Operações Básicas - Subtração`() {
        val result = UseCases().operacoesBasicas("30", "40", '-')
        assertThat(result).isEqualTo(-10)
    }

    @Test
    fun `Operações Básicas - Multiplicação`() {
        val result = UseCases().operacoesBasicas("30", "40", '*')
        assertThat(result).isEqualTo(1200)
    }

    @Test
    fun `Operações Básicas - Divisão`() {
        val result = UseCases().operacoesBasicas("30", "40", '/')
        assertThat(result).isEqualTo(0.75)
    }

}