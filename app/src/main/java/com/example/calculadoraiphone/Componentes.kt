package com.example.calculadoraiphone.ui.theme

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
        Crossfade(
            targetState = corTexto,
            animationSpec = tween(1000)
        ) { corDoTexto ->
            Text(
                text = texto,
                color = corDoTexto,
                textAlign = alinhamento,
                fontWeight = negrito,
                style = TextoBotao
            )
        }
    }
}

@Composable
fun Linha(
    caracteres: List<String>,
    coresFundo: List<Color> = listOf(PretaFusca, Laranja),
    coresTexto: List<Color> = listOf(Branca, Branca),
    quatroBotoes: Boolean = true,
    negrito: List<FontWeight> = listOf(FontWeight.Medium, FontWeight.Medium),
    eventos: List<() -> Unit>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(.92f)
    ) {
        Botao(
            texto = caracteres[0], corFundo = coresFundo[0], corTexto = coresTexto[0],
            largura = if (!quatroBotoes) 170 else 80,
            alinhamento = if (!quatroBotoes) TextAlign.Left else TextAlign.Center,
            negrito = negrito[0], evento = eventos[0]
        )
        Botao(
            texto = caracteres[1], corFundo = coresFundo[0], corTexto = coresTexto[0],
            negrito = negrito[0], evento = eventos[1]
        )
        if (quatroBotoes)
            Botao(
                texto = caracteres[2], corFundo = coresFundo[0], corTexto = coresTexto[0],
                negrito = negrito[0], evento = eventos[2]
            )

        Crossfade(
            targetState = coresFundo[1],
            animationSpec = tween(1000)
        ) { corFundo ->
            Botao(
                texto = caracteres[3], corFundo = corFundo, corTexto = coresTexto[1],
                negrito = negrito[1], evento = eventos[3]
            )
        }
    }
}