package com.example.receitas.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.receitas.databinding.FormularioReceitaBinding

class FormularioReceita : AppCompatActivity() {

    private val binding by lazy {
        FormularioReceitaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}