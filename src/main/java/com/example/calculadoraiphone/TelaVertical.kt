package com.example.calculadoraiphone

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_2
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraiphone.ui.theme.*

class TelaVertical : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaVerticalUI()
        }
    }
}

@Composable
fun TelaVerticalUI() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .background(Preta),
        content = {
            EditText()
            Spacer(modifier = Modifier.height(20.dp))
            Coluna()
        }
    )
}

fun LancarToast(contexto: Context, texto: String) = Toast.makeText(contexto, texto, Toast.LENGTH_SHORT).show()

@Composable
fun EditText() {
    SelectionContainer(
        modifier = Modifier
            .fillMaxHeight(0.18f)
            .fillMaxWidth()
            .padding(end = 25.dp)
    ) {
        Text(
            text = "0",
            fontSize = 100.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.End,
            color = Branca
        )
    }
}

@Composable
fun Coluna() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.92f)
    ) {
        Linha(
            caracteres = listOf("AC", "+/-", "%", "/"),
            coresFundo = listOf(Cinza, Laranja),
            coresTexto = listOf(Preta, Branca)
        )
        Linha(
            caracteres = listOf("7", "8", "9", "x"),
            coresFundo = listOf(PretaFusca, Laranja)
        )
        Linha(
            caracteres = listOf("4", "5", "6", "-"),
            coresFundo = listOf(PretaFusca, Laranja)
        )
        Linha(
            caracteres = listOf("1", "2", "3", "+"),
            coresFundo = listOf(PretaFusca, Laranja)
        )
        Linha(
            caracteres = listOf("0", ",", "=", "="),
            coresFundo = listOf(PretaFusca, Laranja),
            quatroBotoes = false
        )
    }
}

@Preview(
    showBackground = true,
    device = PIXEL_2
)
@Composable
fun PrevizualizarLinha1() {
    CalculadoraIPhoneTheme {
        TelaVerticalUI()
    }
}