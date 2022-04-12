package code.with.cal.moviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import code.with.cal.moviesapp.databinding.ComingsoonItemBinding
import code.with.cal.moviesapp.model.ResultX
import com.bumptech.glide.Glide

class ComingSoonAdapter(private var showDetails: (ResultX) -> Unit) :
    ListAdapter<ResultX, ComingSoonAdapter.ComingSoonListViewHolder>(MovieDiffCallback()) {

    class ComingSoonListViewHolder(
        private val binding: ComingsoonItemBinding,
        private var showDetails: (ResultX) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var titleView = binding.name
        private var image = binding.imageview


        fun bind(result: ResultX) {

            titleView.text = result.title

            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${result.poster_path}").into(image)
            binding.root.setOnClickListener {
                showDetails(result)
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonListViewHolder {

        return ComingSoonListViewHolder(
            ComingsoonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            showDetails
        )
    }

    override fun onBindViewHolder(holder: ComingSoonListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<ResultX>() {
    override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem == newItem
    }
}