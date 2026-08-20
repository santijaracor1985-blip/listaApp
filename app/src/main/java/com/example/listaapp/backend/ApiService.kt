

package com.example.listaapp.backend

import com.example.listaapp.model.Lista

class ApiService {

    fun obtenerListas(): List<Lista> {
        return DatosBackend.obtenerDatos()
    }
}