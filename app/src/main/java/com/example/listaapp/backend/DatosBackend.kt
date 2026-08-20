

package com.example.listaapp.backend

import com.example.listaapp.model.Lista

object DatosBackend {

    private val datos = listOf(
        Lista(1, "Shampoo", "Producto", "Azul"),
        Lista(2, "Acondicionador", "Producto", "Blanco"),
        Lista(3, "Mascarilla", "Tratamiento", "Rosa"),
        Lista(4, "Crema", "Producto", "Verde")
    )

    fun obtenerDatos(): List<Lista> {
        return datos
    }
}