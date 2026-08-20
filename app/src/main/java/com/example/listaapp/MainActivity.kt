package com.example.listaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.listaapp.backend.ApiService
import com.example.listaapp.model.Lista

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ListaApp()
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun ListaApp() {

    var textoBusqueda by remember {
        mutableStateOf("")
    }

    var datos by remember {
        mutableStateOf<List<Lista>>(emptyList())
    }

    val apiService = remember {
        ApiService()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de productos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = {
                    textoBusqueda = it
                },
                label = {
                    Text("Buscar")
                },
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {

                    val todosLosDatos = apiService.obtenerListas()

                    datos = if (textoBusqueda.isBlank()) {
                        todosLosDatos
                    } else {
                        todosLosDatos.filter {
                            it.nombre.contains(
                                textoBusqueda,
                                ignoreCase = true
                            )
                        }
                    }
                }
            ) {
                Text("Buscar")
            }
        }

        Text(
            text = "Resultados: ${datos.size}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 10.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(10.dp)
        ) {

            Text(
                text = "Lista",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.8f)
            )

            Text(
                text = "Nombre",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1.5f)
            )

            Text(
                text = "Tipo",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1.2f)
            )

            Text(
                text = "Color",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1.2f)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            items(datos) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        Text(
                            text = item.lista.toString(),
                            modifier = Modifier.weight(0.8f)
                        )

                        Text(
                            text = item.nombre,
                            modifier = Modifier.weight(1.5f)
                        )

                        Text(
                            text = item.tipo,
                            modifier = Modifier.weight(1.2f)
                        )

                        Text(
                            text = item.color,
                            modifier = Modifier.weight(1.2f)
                        )
                    }
                }
            }
        }
    }
}