package com.example.calculadoraiphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel(private val dto: Dto = Dto("0")) : ViewModel() {

    private val useCases = UseCases(dto)
    var numero1 = MutableLiveData("0")
    var numero2 = MutableLiveData("0")
    var resultado = MutableLiveData("0")
    var operacao = MutableLiveData(' ')

    fun onMudarNumero(num: String){
        dto.numero1 = "10"
        numero2.value = num
        dto.numero2 = numero2.value!!
        dto.operacao = '+'

    }

    fun onOperacoesBasicas(){
        resultado.value = useCases.operacoesBasicas().toString()
    }

}