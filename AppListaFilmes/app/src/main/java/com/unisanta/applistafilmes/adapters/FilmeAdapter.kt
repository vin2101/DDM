import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unisanta.applistafilmes.models.Filme
import com.unisanta.applistafilmes.R

class FilmeAdapter(private val movies: MutableList<Filme>) :
    RecyclerView.Adapter<FilmeAdapter.MovieViewHolder>() {


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView
        val textViewGenre: TextView
        val ratingBar: RatingBar

        init {

            textViewTitle = itemView.findViewById(R.id.textViewTitle)
            textViewGenre = itemView.findViewById(R.id.textViewGenre)
            ratingBar = itemView.findViewById(R.id.ratingBar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_item_filme, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.textViewTitle.text = movie.titulo
        holder.textViewGenre.text = movie.genero
        holder.ratingBar.rating = movie.avaliacao
    }
}