package code.with.cal.moviesapp.ui.detailsfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import code.with.cal.moviesapp.Dp.App
import code.with.cal.moviesapp.R
import code.with.cal.moviesapp.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior

class DetailsFragment:Fragment(R.layout.fragment_details) {
    private val viewmodel by viewModels<DetailsFragmentViewModel>(factoryProducer = {
        DetailsViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDetailsBinding.bind(view)
        setBottomSheet()
        getBackToHome()
        showDetails()
    }

    fun showDetails(){
        viewmodel.getMovie(args.movieid)
        viewmodel.movieList.observe(viewLifecycleOwner){

            binding.textViewTitleDetails.text=it.title
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${it.poster_path}").into(binding.imageviewDetails)
            binding.ratingBarDetails.rating=(it.vote_average.toFloat())/2
            binding.textViewDesDetails.text=it.overview
        }

    }
    fun getBackToHome(){
        binding.backbutton.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }
 fun setBottomSheet(){
     BottomSheetBehavior.from(binding.sheet).apply {
         peekHeight=500
         this.state=BottomSheetBehavior.STATE_COLLAPSED
     }
 }


}