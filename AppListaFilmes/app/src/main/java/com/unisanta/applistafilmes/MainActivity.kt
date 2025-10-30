package com.unisanta.applistafilmes

import FilmeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.unisanta.applistafilmes.models.Filme

class MainActivity : AppCompatActivity() {

    // MUDANÇA: Removemos a referência ao "binding".
    // private lateinit var binding: ActivityMainBinding

    private lateinit var movieAdapter: FilmeAdapter
    private val movieList = mutableListOf<Filme>()

    // MUDANÇA: Declaramos as Views que a Activity vai controlar.
    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var fabAddMovie: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // MUDANÇA: Usamos o método tradicional para definir o layout.
        setContentView(R.layout.activity_main)

        // MUDANÇA: Inicializamos nossas Views usando findViewById.
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies)
        fabAddMovie = findViewById(R.id.fabAddMovie)

        setupRecyclerView()

        // MUDANÇA: Usamos a variável fabAddMovie diretamente.
        fabAddMovie.setOnClickListener {
            showAddMovieDialog()
        }
    }

    private fun setupRecyclerView() {
        movieAdapter = FilmeAdapter(movieList)

        // MUDANÇA: Usamos a variável recyclerViewMovies diretamente.
        recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }
    }

    private fun showAddMovieDialog() {
        // MUDANÇA: Para o diálogo, o processo é um pouco diferente.
        // Primeiro, inflamos a View do diálogo.
        val dialogView = LayoutInflater.from(this).inflate(R.layout.modal_add_filme, null)

        // MUDANÇA: Agora, usamos essa "dialogView" para encontrar os elementos DENTRO dela.
        val editTextMovieTitle = dialogView.findViewById<EditText>(R.id.editTextMovieTitle)
        val seekBarRating = dialogView.findViewById<SeekBar>(R.id.seekBarRating)
        val textViewRatingLabel = dialogView.findViewById<TextView>(R.id.textViewRatingLabel)
        // ... e assim por diante para os CheckBoxes
        val checkboxAction = dialogView.findViewById<CheckBox>(R.id.checkboxAction)
        val checkboxComedy = dialogView.findViewById<CheckBox>(R.id.checkboxComedy)
        // ... etc.

        seekBarRating.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val rating = progress / 2.0f
                textViewRatingLabel.text = "Sua Avaliação: $rating/5.0"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        AlertDialog.Builder(this)
            .setTitle("Adicionar Novo Filme")
            .setView(dialogView) // Usamos a View que inflamos
            .setPositiveButton("Adicionar") { dialog, _ ->
                val title = editTextMovieTitle.text.toString()
                // A lógica para pegar os gêneros precisaria usar as variáveis dos checkboxes
                val selectedGenres = getSelectedGenres(dialogView)
                val rating = seekBarRating.progress / 2.0f

                if (title.isNotBlank() && selectedGenres.isNotEmpty()) {
                    val newMovie = Filme(title, selectedGenres, rating)
                    addMovieToList(newMovie)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    // MUDANÇA: Esta função agora recebe a View do diálogo para encontrar os checkboxes
    private fun getSelectedGenres(dialogView: View): String {
        var genres = ""
        if (dialogView.findViewById<CheckBox>(R.id.checkboxAction).isChecked) genres = "Ação"
        if (dialogView.findViewById<CheckBox>(R.id.checkboxComedy).isChecked) genres = "Comédia"
        if (dialogView.findViewById<CheckBox>(R.id.checkboxDrama).isChecked) genres = "Drama"
        if (dialogView.findViewById<CheckBox>(R.id.checkboxFantasy).isChecked) genres = "Fantasia"
        if (dialogView.findViewById<CheckBox>(R.id.checkboxSciFi).isChecked) genres = "Ficção C."
        if (dialogView.findViewById<CheckBox>(R.id.checkboxHorror).isChecked) genres = "Terror"
        return genres
    }


    private fun addMovieToList(movie: Filme) {
        movieList.add(movie)
        movieAdapter.notifyItemInserted(movieList.size - 1)
    }

}