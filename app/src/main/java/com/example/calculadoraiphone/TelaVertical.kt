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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            TelaVerticalUI(viewModel = viewModel)
        }
    }
}

@Composable
fun telaDeTeste() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        var numero by remember { mutableStateOf("0") }
        var textoTamanho by remember { mutableStateOf(85) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(PretaFusca),
            contentAlignment = Alignment.BottomEnd
        ) {
            SelectionContainer {
                Text(
                    text = numero,
                    fontSize = textoTamanho.sp,
                    color = Branca,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                textoTamanho =
                    if (numero.length in 0..4) 85
                    else if (numero.length in 5..8) 75
                    else if (numero.length in 9..10) 65
                    else 60

                if (numero.length <= 11) {
                    if (numero == "0")
                        numero = "2"
                    else
                        numero = numero + "2"
                }
            }) { Text("Número") }

            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                numero = "0"
                textoTamanho = 85
            }) { Text("Zerar") }
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

        val estado = viewModel.estado
        val dados = viewModel.dados

        Box(
            modifier = Modifier
                .fillMaxWidth(.85F)
                .height(110.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            SelectionContainer {
                Crossfade(
                    targetState = estado.numeroTela,
                    animationSpec = tween(300)
                ) {
                    Text(
                        text = it,
                        fontSize = estado.tamanhoTexto.sp,
                        textAlign = TextAlign.End,
                        color = Branca,
                        maxLines = 1,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
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
                caracteres = listOf(estado.textoBotaoLimpar, "+/-", "%", "/"),
                coresFundo = listOf(
                    Cinza,
                    if (dados.operacao == '/') estado.corFundoBotaoOperacao else Laranja
                ),
                coresTexto = listOf(
                    Preta,
                    if (dados.operacao == '/') estado.corTextoBotaoOperacao else Branca
                ),
                negrito = listOf(FontWeight.Normal, FontWeight.Medium),
                eventos = listOf(
                    { viewModel.onLimparDados() },
                    { viewModel.maisMenos(estado.numeroTela) },
                    { viewModel.porcentagem(estado.numeroTela) },
                    { viewModel.onMudarOperacao('/') }
                )
            )

            /*Linha 2*/
            Linha(
                caracteres = listOf("7", "8", "9", "x"),
                coresFundo = listOf(
                    PretaFusca,
                    if (dados.operacao == '*') estado.corFundoBotaoOperacao else Laranja
                ),
                coresTexto = listOf(
                    Branca,
                    if (dados.operacao == '*') estado.corTextoBotaoOperacao else Branca
                ),
                eventos = listOf(
                    { viewModel.mudarNumero("7") },
                    { viewModel.mudarNumero("8") },
                    { viewModel.mudarNumero("9") },
                    { viewModel.onMudarOperacao('*') }
                )
            )

            /*Linha 3*/
            Linha(
                caracteres = listOf("4", "5", "6", "-"),
                coresFundo = listOf(
                    PretaFusca,
                    if (dados.operacao == '-') estado.corFundoBotaoOperacao else Laranja
                ),
                coresTexto = listOf(
                    Branca,
                    if (dados.operacao == '-') estado.corTextoBotaoOperacao else Branca
                ),
                eventos = listOf(
                    { viewModel.mudarNumero("4") },
                    { viewModel.mudarNumero("5") },
                    { viewModel.mudarNumero("6") },
                    { viewModel.onMudarOperacao('-') }
                )
            )

            /*Linha 4*/
            Linha(
                caracteres = listOf("1", "2", "3", "+"),
                coresFundo = listOf(
                    PretaFusca,
                    if (dados.operacao == '+') estado.corFundoBotaoOperacao else Laranja
                ),
                coresTexto = listOf(
                    Branca,
                    if (dados.operacao == '+') estado.corTextoBotaoOperacao else Branca
                ),
                eventos = listOf(
                    { viewModel.mudarNumero("1") },
                    { viewModel.mudarNumero("2") },
                    { viewModel.mudarNumero("3") },
                    { viewModel.onMudarOperacao('+') }
                )
            )
            Linha( /*Linha 5*/
                caracteres = listOf("0", ",", "=", "="),
                coresFundo = listOf(PretaFusca, Laranja),
                quatroBotoes = false,
                eventos = listOf(
                    { if (estado.numeroTela != "0") viewModel.mudarNumero("0") },
                    { viewModel.inserirDecimal(estado.numeroTela) }, {},
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