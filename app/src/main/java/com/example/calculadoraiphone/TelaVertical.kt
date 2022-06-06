package com.example.calculadoraiphone

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.snap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_2
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraiphone.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TelaVertical : ComponentActivity() {

    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*val configuracao = LocalConfiguration.current
            when (configuracao.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    Text(text = "Falta Desenhar a Tela Horizontal")
                    TelaHorizontalUI()
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    TelaVerticalUI()
                }
            }*/

            telaDeTeste(viewModel = viewModel)
        }
    }
}



@Composable
fun telaDeTeste(viewModel: ViewModel){
    val numero2: String by viewModel.numero2.observeAsState("0")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = viewModel.resultado.value!!, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.onMudarNumero("44")

        }) {
            Text(text = "Texto")
        }
    }

}




val dto = Dto("0")

@Composable
fun TelaVerticalUI() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Preta),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var numero by remember { mutableStateOf("0") }
        var numeroEstado by remember { mutableStateOf(false) }
        SelectionContainer(
            modifier = Modifier
                .fillMaxHeight(0.18f)
                .fillMaxWidth()
                .padding(end = 25.dp)
        ) {
            Text(
                text = numero,
                fontSize = 100.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.End,
                color = Branca,
                letterSpacing = 0.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
        ) {

            Linha( /*Linha 1*/
                caracteres = listOf(
                    if (numero.length <= 1 && numero.equals("0")) "AC" else "C",
                    "+/-", "%", "/"
                ),
                coresFundo = listOf(Cinza, if (!numeroEstado) Laranja else Branca),
                coresTexto = listOf(Preta, if (!numeroEstado) Branca else Laranja),
                eventos = listOf(
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = "0"
                    }, { numero = "+$numero" },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = "$numero %"
                    },
                    {
                        dto.numero1 = numero
                        numeroEstado = true
                        dto.operacao = '/'
                    }
                )
            )
            Linha( /*Linha 2*/
                caracteres = listOf("7", "8", "9", "x"),
                coresFundo = listOf(PretaFusca, if (!numeroEstado) Laranja else Branca),
                coresTexto = listOf(Branca, if (!numeroEstado) Branca else Laranja),
                eventos = listOf(
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "7")
                    },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "8")
                    },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "9")
                    },
                    {
                        dto.numero1 = numero
                        dto.operacao = 'x'
                        numeroEstado = true
                    }
                )
            )
            Linha( /*Linha 3*/
                caracteres = listOf("4", "5", "6", "-"),
                coresFundo = listOf(PretaFusca, Laranja),
                eventos = listOf(
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "4")
                    },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "5")
                    },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "6")
                    },
                    {
                        dto.numero1 = numero
                        dto.operacao = '-'
                        numeroEstado = true
                    }
                )
            )
            Linha( /*Linha 4*/
                caracteres = listOf("1", "2", "3", "+"),
                coresFundo = listOf(PretaFusca, Laranja),
                eventos = listOf(
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "1")
                    },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "2")
                    },
                    {
                        if (numeroEstado) {
                            numero = ""
                            numeroEstado = false
                        }
                        numero = ValidarZero(numero, "3")
                    },
                    {
                        dto.numero1 = numero
                        dto.operacao = '+'
                        numeroEstado = true
                    }
                )
            )
            Linha( /*Linha 5*/
                caracteres = listOf("0", ",", "=", "="),
                coresFundo = listOf(PretaFusca, Laranja),
                quatroBotoes = false,
                eventos = listOf(
                    { numero = ValidarZero(numero, "0") },
                    { numero = if (!numero.contains(",")) "$numero," else numero },
                    {
                        dto.operacao.let {
                            val resultado = Calcular(
                                Dados(
                                    dto.numero1.toDouble(),
                                    numero.toDouble(),
                                    it
                                )
                            )

                            numero = VerificarDecimal(resultado)
                        }
                    },
                    { numero = "" }
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = PIXEL_2
)
@Composable
fun previzualizarLinha1() {
    CalculadoraIPhoneTheme {

    }
}