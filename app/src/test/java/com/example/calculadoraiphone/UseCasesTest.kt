package com.example.calculadoraiphone

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UseCasesTest {

    @Test
    fun `Validar Número, Virgula e Ponto`() {
        val dto = Dto("32,4")
        val result = UseCases(dto).validarNumero(dto.numero1)
        assertThat(result).isEqualTo(32.4)
    }

    @Test
    fun `Mais ou Menos`() {
        val dto = Dto("-32.04")
        val result = UseCases(dto).maisMenos()
        assertThat(result).isEqualTo(32.04)
    }

    @Test
    fun `Porcentagem`() {
        val dto = Dto("30")
        val result = UseCases(dto).porcentagem()
        assertThat(result).isEqualTo(0.3)
    }

    @Test
    fun `Operações Básicas - Soma`() {
        val dto = Dto("30", "40", '+')
        val result = UseCases(dto).operacoesBasicas()
        assertThat(result).isEqualTo(70)
    }

    @Test
    fun `Operações Básicas - Subtração`() {
        val dto = Dto("30", "40", '-')
        val result = UseCases(dto).operacoesBasicas()
        assertThat(result).isEqualTo(-10)
    }

    @Test
    fun `Operações Básicas - Multiplicação`() {
        val dto = Dto("30", "40", '*')
        val result = UseCases(dto).operacoesBasicas()
        assertThat(result).isEqualTo(1200)
    }

    @Test
    fun `Operações Básicas - Divisão`() {
        val dto = Dto("30", "40", '/')
        val result = UseCases(dto).operacoesBasicas()
        assertThat(result).isEqualTo(0.75)
    }

}