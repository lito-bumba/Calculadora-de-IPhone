package com.example.calculadoraiphone

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.calculadoraiphone.ui.theme.Branca
import com.example.calculadoraiphone.ui.theme.CalculadoraIPhoneTheme
import com.example.calculadoraiphone.ui.theme.Laranja
import com.example.calculadoraiphone.ui.theme.Preta
import kotlinx.coroutines.launch

class TelaHorizontal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIPhoneTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TelaHorizontalUI() {
    var cor by remember { mutableStateOf(Laranja) }
    var currentPage by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(onClick = {
            cor = if (cor.equals(Preta)) Laranja else Preta
            currentPage = (0..0xFFFFFF).random()
        }) { Text(text = "Clicar") }

        Spacer(modifier = Modifier.height(30.dp))

        Crossfade(
            targetState = {

            },
            animationSpec = tween(durationMillis = 1000),
            content = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(cor)
                        .size(140.dp, 70.dp)
                ) {
                    Text(text = "Aparecer", color = Branca)
                }
            }
        )
    }

}

@Composable
fun TestingTexto(trocar: () -> Unit, mensagem: () -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = trocar) { Text(text = "Add Text") }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = mensagem) { Text(text = "Limpar") }
    }
}

@Composable
fun Conteudo() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var numero by remember {
        mutableStateOf("0")
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        val configuracao = LocalConfiguration.current
        when (configuracao.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {

                    val context = LocalContext.current
                    var textoHorizontal by remember {
                        mutableStateOf("")
                    }
                    var baixo by remember {
                        mutableStateOf(false)
                    }


                    val addText = fun() {
                        Toast.makeText(context, textoHorizontal, Toast.LENGTH_SHORT)
                            .show()
                        textoHorizontal = "$textoHorizontal Texto "
                        baixo = !baixo
                    }
                    val mensagem = fun() {
                        scope.launch {
                            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = textoHorizontal,
                                actionLabel = "Acção",
                                duration = SnackbarDuration.Short
                            )
                            when (snackbarResult) {
                                SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                                SnackbarResult.ActionPerformed -> Log.d(
                                    "SnackbarDemo",
                                    "Snackbar's button clicked"
                                )
                            }
                        }
                    }

                    Text(textoHorizontal)
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Button(onClick = addText) { Text(text = "Add Text") }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = {
                            Toast.makeText(context, textoHorizontal, Toast.LENGTH_SHORT)
                                .show()
                            textoHorizontal = ""
                        }) { Text(text = "Limpar") }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TestingTexto(addText, mensagem)
                }
                Text("Falta desenhar o modo Horizontal")


            }
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Preta),
                    content = {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                )
            }
        }

    }
}