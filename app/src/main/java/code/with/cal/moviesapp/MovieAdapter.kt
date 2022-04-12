package code.with.cal.moviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import code.with.cal.moviesapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide
import code.with.cal.moviesapp.model.Result

class MovieAdapter(private var showDetails: (Result) -> Unit) :
    ListAdapter<Result, MovieAdapter.MovieListViewHolder>(MovieDiffCallback2()) {

    class MovieListViewHolder(
        private val binding: MovieItemBinding,
        private var showDetails: (Result) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
       private var titleView = binding.name
        private var image = binding.imageview


        fun bind(result: Result) {

            titleView.text = result.title

            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${result.backdrop_path}").into(image)
            binding.root.setOnClickListener {
                showDetails(result)
            }
            }

           }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        return MovieListViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
showDetails
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MovieDiffCallback2 : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}
//class MovieAdapter(private val photolist: List<Result>):RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
//
//    class MyViewHolder(item: View) :RecyclerView.ViewHolder(item){
//        private val binding :MovieItemBinding = DataBindingUtil.bind(item)!!
//        private var image = binding.imageview
//        private var title=binding.name
//        fun fill(photo: Result){
//            title.text=photo.title
//         Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${photo.poster_path}").into(image)
//
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//
//        val viewholder =
//            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
//        return MyViewHolder(viewholder)
//
//
//    }
//
//
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.apply {
//            fill(photolist[position])
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return photolist.size
//    }
//}