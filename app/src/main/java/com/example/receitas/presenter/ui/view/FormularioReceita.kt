package com.example.receitas.presenter.ui.view

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.receitas.R
import com.example.receitas.databinding.FormularioReceitaBinding
import com.example.receitas.presenter.model.ReceitaPresenter
import com.example.receitas.presenter.ui.viewmodel.FormularioReceitaViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormularioReceita : AppCompatActivity() {

    private val binding by lazy {
        FormularioReceitaBinding.inflate(layoutInflater)
    }
    private val viewModel: FormularioReceitaViewModel by viewModel()
    private var receitaId = 0L
    private var presenterReceita: ReceitaPresenter? = null
    private var imagemReceita: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarId()
        observers()
        lifecycleScope.launch {
            configuraFormulario()
        }
        configuraFabsImage()
    }

    private fun tentaCarregarId() {
        val idRecebido = intent.getLongExtra("receita_id", 0L)
        receitaId = idRecebido
    }

    fun observers() {
        observerConfiguraFormulario()
        observerCrudReceita()
        observerValidacoesFormulario()
        observerLimpaFormulario()
    }

    private suspend fun configuraFormulario() {
        viewModel.configuraFormulario()
        viewModel.buscaPorId(receitaId)
    }

    /**
     * Inicio config image
     */
    private fun configuraFabsImage() {
        binding.formularioReceitaFabCam.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                startActivityForResult(this, 1)
            }
        }

        binding.formularioReceitaFabAlbum.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result cam
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            viewModel.carregaImagem(image)
        }

        // Result album
        if (requestCode == 2 && resultCode == RESULT_OK) {
            val imageUri = data?.data

            if (imageUri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                viewModel.carregaImagem(bitmap)
            }
        }
    }

    /**
     * Fim config image
     */

    private fun observerConfiguraFormulario() {
        // Observa o dropDown Tipo
        viewModel.buscaTipo.observe(this@FormularioReceita) { listTipo ->
            val tipoAdapter =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, listTipo)
            binding.formularioReceitaTipo.setAdapter(tipoAdapter)
        }

        // Observa o dropDown Nivel
        viewModel.buscaNivel.observe(this@FormularioReceita) { listNivel ->
            val nivelAdapter =
                ArrayAdapter(this@FormularioReceita, R.layout.drop_values_receita, listNivel)
            binding.formularioReceitaNivel.setAdapter(nivelAdapter)
        }
    }

    private fun observerCrudReceita() {
        // Observa a criação da receita
        viewModel.salvaReceita.observe(this@FormularioReceita) { ifSave ->
            if (ifSave) finish()
        }

        // Observer carrega foto
        viewModel.carregaFoto.observe(this@FormularioReceita) { imagem ->
            binding.formularioReceitaImagem.load(imagem)
            imagemReceita = imagem
        }

        // Observa a remoção da receita
        viewModel.mRemoveReceita.observe(this@FormularioReceita) { ifDelete ->
            if (ifDelete) finish()
        }

        // Observa a busca da receita pelo Id e popula os campos
        viewModel.buscaReceitaPorId.observe(this@FormularioReceita) { resource ->
            presenterReceita = resource.buscaPresenterReceita()
            imagemReceita = resource.buscaPresenterReceita().imagem

            binding.run {
                formularioReceitaTitulo.setText(presenterReceita!!.titulo)
                formularioReceitaTipo.setText(
                    formularioReceitaTipo.adapter.getItem(resource.buscaTipoNivel().tipoId!!)
                        .toString(), false
                )
                formularioReceitaNivel.setText(
                    formularioReceitaNivel.adapter.getItem(resource.buscaTipoNivel().nivelId!!)
                        .toString(), false
                )
                formularioReceitaIngrediente.setText(presenterReceita!!.ingredientes)
                formularioReceitaPreparo.setText(presenterReceita!!.preparo)
                formularioReceitaImagem.load(presenterReceita!!.imagem)
                if (presenterReceita!!.exibeImagem == 1) formularioReceitaSwitch.isChecked = true
            }
        }
    }

    private fun observerValidacoesFormulario() {
        // Observers de validação
        viewModel.validaTitulo.observe(this@FormularioReceita) {
            if (!it) binding.formularioReceitaLayoutTitulo.error = "Título obrigatório"
            else binding.formularioReceitaLayoutTitulo.error = null
        }

        viewModel.validaTipo.observe(this@FormularioReceita) {
            if (!it) binding.formularioReceitaLayoutTipo.error = "Tipo obrigatório"
            else binding.formularioReceitaLayoutTipo.error = null
        }

        viewModel.validaNivel.observe(this@FormularioReceita) {
            if (!it) binding.formularioReceitaLayoutNivel.error = "Nível obrigatório"
            else binding.formularioReceitaLayoutNivel.error = null
        }
    }

    private fun observerLimpaFormulario() {
        // Observer limpa formulario
        viewModel.limpaForm.observe(this@FormularioReceita) { ifClear ->
            if (ifClear) {
                binding.run {
                    formularioReceitaTitulo.setText("")
                    formularioReceitaTipo.setText("")
                    formularioReceitaNivel.setText("")
                    formularioReceitaIngrediente.setText("")
                    formularioReceitaPreparo.setText("")
                    formularioReceitaImagem.load(R.drawable.default_background)
                    formularioReceitaSwitch.isChecked = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_receita, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_action_save -> salvaReceita()
            R.id.ic_action_delete -> removeReceita()
            R.id.ic_action_clear -> limparFormulario()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salvaReceita() {
        val titulo = binding.formularioReceitaTitulo.text.toString().trim()
        val tipo = binding.formularioReceitaTipo.text.toString()
        val nivel = binding.formularioReceitaNivel.text.toString()
        val ingredientes = binding.formularioReceitaIngrediente.text.toString().trim()
        val preparo = binding.formularioReceitaPreparo.text.toString()
        val switch = if (binding.formularioReceitaSwitch.isChecked) 1 else 0

        lifecycleScope.launch {
            viewModel.salvaReceita(
                ReceitaPresenter(
                    id = receitaId,
                    titulo = titulo,
                    tipo = tipo,
                    nivel = nivel,
                    ingredientes = ingredientes,
                    preparo = preparo,
                    imagem = imagemReceita,
                    exibeImagem = switch
                )
            )
        }
    }

    private fun removeReceita() {
        presenterReceita?.let { receita ->
            AlertDialog.Builder(this@FormularioReceita)
                .setIcon(R.drawable.ic_action_warning)
                .setTitle("Apagar essa receita?")
                .setPositiveButton("Sim") { _, _ ->
                    lifecycleScope.launch { viewModel.removeReceita(receita) }
                }
                .setNegativeButton("Não") { _, _ -> }
                .show()
        }
    }

    private fun limparFormulario() {
        viewModel.limpaFormulario()
    }
}
