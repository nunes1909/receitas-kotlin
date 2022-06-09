package com.example.receitas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.receitas.databinding.ListaReceitasBinding

class ListaReceitas : AppCompatActivity() {

    private val binding by lazy {
        ListaReceitasBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}