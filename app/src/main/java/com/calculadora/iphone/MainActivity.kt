package com.calculadora.iphone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.calculadora.iphone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var num1Bool: Boolean = false
    var total: Boolean = false
    var virgula = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var operacao: Char = ' '
        var num: Double = 0.0
        var num2: Double = 0.0

        binding.btn0.setOnClickListener {
            if (binding.txtResultado.text.toString() != "0")
                AddNumero(binding.btn0.text.toString(), num1Bool)
            else
                binding.txtResultado.text = "0"
        }
        binding.btn1.setOnClickListener { AddNumero(binding.btn1.text.toString(), num1Bool) }
        binding.btn2.setOnClickListener { AddNumero(binding.btn2.text.toString(), num1Bool) }
        binding.btn3.setOnClickListener { AddNumero(binding.btn3.text.toString(), num1Bool) }
        binding.btn4.setOnClickListener { AddNumero(binding.btn4.text.toString(), num1Bool) }
        binding.btn5.setOnClickListener { AddNumero(binding.btn5.text.toString(), num1Bool) }
        binding.btn6.setOnClickListener { AddNumero(binding.btn6.text.toString(), num1Bool) }
        binding.btn7.setOnClickListener { AddNumero(binding.btn7.text.toString(), num1Bool) }
        binding.btn8.setOnClickListener { AddNumero(binding.btn8.text.toString(), num1Bool) }
        binding.btn9.setOnClickListener { AddNumero(binding.btn9.text.toString(), num1Bool) }
        binding.btnVirgula.setOnClickListener {
            if (!virgula) {
                binding.txtResultado.text =
                    binding.txtResultado.text.toString() + binding.btnVirgula.text.toString()
                virgula = true
                binding.btnLimpar.text = "C"
            }
        }

        binding.btnLimpar.setOnClickListener {
            binding.btnLimpar.text = "AC"
            num1Bool = false
            virgula = false
            binding.txtResultado.text = "0"
        }
        binding.btnMaisMenos.setOnClickListener {
            num = VirgulaPonto(binding.txtResultado.text.toString())
            binding.txtResultado.text = VerificarDecimal((-1 * num))
        }
        binding.btnPorcento.setOnClickListener {
            num = VirgulaPonto(binding.txtResultado.text.toString())
            binding.txtResultado.text = VerificarDecimal((num / 100))
        }
        binding.btnDividir.setOnClickListener {
            num = VirgulaPonto(binding.txtResultado.text.toString())
            operacao = '/'
            num1Bool = true
            virgula = false
            binding.txtResultado.text = "0"
        }
        binding.btnMultiplicar.setOnClickListener {
            num = VirgulaPonto(binding.txtResultado.text.toString())
            operacao = '*'
            num1Bool = true
            virgula = false
            binding.txtResultado.text = "0"
        }
        binding.btnSomar.setOnClickListener {
            num = VirgulaPonto(binding.txtResultado.text.toString())
            operacao = '+'
            num1Bool = true
            virgula = false
            binding.txtResultado.text = "0"
        }
        binding.btnSubtrair.setOnClickListener {
            num = VirgulaPonto(binding.txtResultado.text.toString())
            operacao = '-'
            num1Bool = true
            virgula = false
            binding.txtResultado.text = "0"
        }
        binding.btnIgual.setOnClickListener {
            num2 = VirgulaPonto(binding.txtResultado.text.toString())
            binding.txtResultado.text = VerificarDecimal(Total(num, num2, operacao))
        }
    }

    fun VerificarDecimal(num: Double): String {
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
        }
        else {
            texto = num.toString()
            texto = texto.substring(0, texto.indexOf("."))
        }

        return texto
    }

    fun VirgulaPonto(num: String): Double {
        var txt = num

        if (num.contains(','))
            txt = num.replace(',', '.')

        return txt.toDouble()
    }

    fun Total(num: Double, num2: Double, op: Char): Double {
        var total: Double = 0.0

        when (op) {
            '+' -> total = num + num2
            '-' -> total = num - num2
            '*' -> total = num * num2
            '/' -> total = num / num2
        }

        this.total = true

        return total
    }

    fun AddNumero(n: String, op: Boolean) {
        if (op)
            num1Bool = false

        if (binding.txtResultado.text.toString() != "0"){
            binding.txtResultado.text = binding.txtResultado.text.toString() + n
        }
        else{
            when (virgula) {
                false -> binding.txtResultado.text = n
                true -> binding.txtResultado.text = binding.txtResultado.text.toString() + n
            }
        }

        binding.btnLimpar.text = "C"

    }
}