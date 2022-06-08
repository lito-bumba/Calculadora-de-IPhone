package com.example.calculadoraiphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
            //telaDeTeste()
        }
    }
}

var i = 0

@Composable
fun telaDeTeste() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        var numero by remember {
            mutableStateOf("0")
        }

        var textoTamanho by remember {
            mutableStateOf(100)
        }

        Text(
            text = numero,
            fontSize = textoTamanho.sp,
            textAlign = TextAlign.End,
            color = Preta,
            fontWeight = FontWeight.Light,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {

            when(numero.length){
                6 -> textoTamanho = 80
                9 -> textoTamanho = 60
                10 -> textoTamanho = 56
                11 -> textoTamanho = 50
            }

            if (numero == "0")
                numero = "2"
            else
                numero = numero + "2"
        }) {
            Text("Número")
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

        val textoBotaoLimpar by viewModel.textoBotaoLimpar.observeAsState("AC")
        val numeroTela by viewModel.numeroTela.observeAsState("0")

        val corFundoOperacao by viewModel.corFundoOperacao.observeAsState(Laranja)
        val corTextoOperacao by viewModel.corTextoOperacao.observeAsState(Branca)
        val operacao by viewModel.operacao.observeAsState(' ')
        val tamanhoTexto by viewModel.tamanhoTexto.observeAsState(100)

        SelectionContainer(
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth(.83f)
        ) {
            Crossfade(
                targetState = numeroTela,
                animationSpec = tween(300)
            ) {
                Text(
                    text = it,
                    fontSize = tamanhoTexto.sp,
                    textAlign = TextAlign.End,
                    color = Branca,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.85f)
        ) {

            /*Linha 1*/

            Linha(
                caracteres = listOf(textoBotaoLimpar, "+/-", "%", "/"),
                coresFundo = listOf(Cinza, if (operacao == '/') corFundoOperacao else Laranja),
                coresTexto = listOf(Preta, if (operacao == '/') corTextoOperacao else Branca),
                negrito = listOf(FontWeight.Normal, FontWeight.Medium),
                eventos = listOf(
                    { viewModel.onLimparDados() },
                    { viewModel.maisMenos(numeroTela) },
                    { viewModel.porcentagem(numeroTela) },
                    {
                        viewModel.onMudarOperacao('/')
                        viewModel.onLimparNumeroTela()
                    }
                )
            )

            /*Linha 2*/
            Linha(
                caracteres = listOf("7", "8", "9", "x"),
                coresFundo = listOf(
                    PretaFusca,
                    if (operacao == '*') corFundoOperacao else Laranja
                ),
                coresTexto = listOf(Branca, if (operacao == '*') corTextoOperacao else Branca),
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
                coresFundo = listOf(
                    PretaFusca,
                    if (operacao == '-') corFundoOperacao else Laranja
                ),
                coresTexto = listOf(Branca, if (operacao == '-') corTextoOperacao else Branca),
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

            /*Linha 4*/
            Linha(
                caracteres = listOf("1", "2", "3", "+"),
                coresFundo = listOf(
                    PretaFusca,
                    if (operacao == '+') corFundoOperacao else Laranja
                ),
                coresTexto = listOf(Branca, if (operacao == '+') corTextoOperacao else Branca),
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
                            viewModel.mudarNumero(validarZero(numeroTela, "0"))
                    },
                    { viewModel.inserirDecimal(numeroTela) }, {},
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
    CalculadoraIPhoneTheme() {
        telaDeTeste()
    }
}