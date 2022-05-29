package com.example.calculadoraiphone.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Botao(
    texto: String, alinhamento: TextAlign = TextAlign.Center,
    altura: Int = 80, largura: Int = 80,
    negrito: FontWeight = FontWeight.Medium,
    corFundo: Color = PretaFusca, corTexto: Color = Branca,
    evento: () -> Unit = {}
) {
    Button(
        shape = CircleShape,
        modifier = Modifier
            .height(altura.dp)
            .width(largura.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = corFundo),
        onClick = evento
    ) {
        Text(
            text = texto,
            color = corTexto,
            textAlign = alinhamento,
            fontWeight = negrito,
            style = TextoBotao
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Linha(
    caracteres: List<String>,
    coresFundo: List<Color> = listOf(PretaFusca, Laranja),
    coresTexto: List<Color> = listOf(Branca, Branca),
    quatroBotoes: Boolean = true,
    eventos: List<() -> Unit>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Botao(
            texto = caracteres[0], corFundo = coresFundo[0], corTexto = coresTexto[0],
            largura = if (!quatroBotoes) 180 else 80,
            alinhamento = if (!quatroBotoes) TextAlign.Left else TextAlign.Center,
            evento = eventos[0]
        )
        Botao(
            texto = caracteres[1], corFundo = coresFundo[0], corTexto = coresTexto[0],
            evento = eventos[1]
        )
        if (quatroBotoes)
            Botao(
                texto = caracteres[2], corFundo = coresFundo[0], corTexto = coresTexto[0],
                evento = eventos[2]
            )

        Botao(
            texto = caracteres[3], corFundo = coresFundo[1], corTexto = coresTexto[1],
            evento = eventos[3]
        )
    }
}