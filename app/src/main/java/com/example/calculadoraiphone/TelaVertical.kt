package com.example.calculadoraiphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_2
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraiphone.ui.theme.*

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

            TelaVerticalUI(viewModel = viewModel)
        }
    }
}

var i = 0

@Composable
fun telaDeTeste(viewModel: ViewModel) {
    val numero by viewModel.numeroTela.observeAsState("0")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = numero, fontSize = 40.sp)

        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            viewModel.mudarNumero("10")
            viewModel.onMudarOperacao('*')
            viewModel.mudarNumero("5")
            viewModel.onCalcularOperacoesBasicas()
        }) { Text(text = "Inicial") }

        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            val op: List<Char> = listOf('-', '+', '*', '/')
            viewModel.onMudarOperacao(op[i])
            viewModel.onCalcularOperacoesBasicas()
            if (i < 3) i++ else i = 0
        }) { Text(text = "Mudar Operação") }

        Spacer(modifier = Modifier.height(50.dp))
        Row() {
            Button(onClick = {
                viewModel.onCalcularOperacoesBasicas()
            }) { Text(text = "N 1") }
            Spacer(modifier = Modifier.width(50.dp))
            Button(onClick = {
                viewModel.onCalcularOperacoesBasicas()
            }) { Text(text = "N 2") }
        }
    }
}

@Composable
fun TelaVerticalUI(viewModel: ViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Preta),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        val numeroTela by viewModel.numeroTela.observeAsState("0")
        val textoBotaoLimpar by viewModel.textoBotaoLimpar.observeAsState("AC")

        SelectionContainer(
            modifier = Modifier
                .fillMaxHeight(0.18f)
                .fillMaxWidth()
                .padding(end = 25.dp)
        ) {
            Text(
                text = numeroTela,
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

            /*Linha 1*/
            Linha(
                caracteres = listOf(textoBotaoLimpar, "+/-", "%", "/"),
                coresFundo = listOf(Cinza, Laranja),
                coresTexto = listOf(Preta, Branca),
                eventos = listOf(
                    { viewModel.onLimparDados() },
                    { },
                    { },
                    {
                        viewModel.onMudarOperacao('/')
                        viewModel.onLimparNumeroTela()
                    }
                )
            )

            /*Linha 2*/
            Linha(
                caracteres = listOf("7", "8", "9", "x"),
                coresFundo = listOf(PretaFusca, Laranja),
                coresTexto = listOf(Branca, Branca),
                eventos = listOf(
                    { viewModel.mudarNumero(validarZero(numeroTela, "7")) },
                    { viewModel.mudarNumero(validarZero(numeroTela, "8")) },
                    { viewModel.mudarNumero(validarZero(numeroTela, "9")) },
                    {
                        viewModel.onMudarOperacao('*')
                        viewModel.onLimparNumeroTela()
                    }
                )
            )

            /*Linha 3*/
            Linha(
                caracteres = listOf("4", "5", "6", "-"),
                coresFundo = listOf(PretaFusca, Laranja),
                eventos = listOf(
                    { viewModel.mudarNumero(validarZero(numeroTela, "4")) },
                    { viewModel.mudarNumero(validarZero(numeroTela, "5")) },
                    { viewModel.mudarNumero(validarZero(numeroTela, "6")) },
                    {
                        viewModel.onMudarOperacao('-')
                        viewModel.onLimparNumeroTela()
                    }
                )
            )
            Linha( /*Linha 4*/
                caracteres = listOf("1", "2", "3", "+"),
                coresFundo = listOf(PretaFusca, Laranja),
                eventos = listOf(
                    { viewModel.mudarNumero(validarZero(numeroTela, "1")) },
                    { viewModel.mudarNumero(validarZero(numeroTela, "2")) },
                    { viewModel.mudarNumero(validarZero(numeroTela, "3")) },
                    {
                        viewModel.onMudarOperacao('+')
                        viewModel.onLimparNumeroTela()
                    }
                )
            )
            Linha( /*Linha 5*/
                caracteres = listOf("0", ",", "=", "="),
                coresFundo = listOf(PretaFusca, Laranja),
                quatroBotoes = false,
                eventos = listOf(
                    {
                        if (numeroTela!! != "0")
                            viewModel.mudarNumero(validarZero(numeroTela, "0")) },
                    {
                        //numero = if (!numero.contains(",")) "$numero," else numero
                    },
                    { },
                    { viewModel.onCalcularOperacoesBasicas() }
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