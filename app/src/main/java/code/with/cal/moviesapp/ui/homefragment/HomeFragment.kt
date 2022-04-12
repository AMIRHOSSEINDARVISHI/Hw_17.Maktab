package code.with.cal.moviesapp.ui.homefragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import code.with.cal.moviesapp.ComingSoonAdapter
import code.with.cal.moviesapp.Dp.App
import code.with.cal.moviesapp.MovieAdapter
import code.with.cal.moviesapp.NetworkConnection
import code.with.cal.moviesapp.R
import code.with.cal.moviesapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var mLayoutManagerHorizontal: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    private val viewmodel by viewModels<HomeFragmentViewModel>(factoryProducer = {
        HomeViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    val adapter = MovieAdapter(showDetails = { movie ->
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                movie.id
            )
        )
    })
    val adapter2 = ComingSoonAdapter(showDetails = { movie ->
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                movie.id
            )
        )
    })
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        recyclerView = binding.recyclerView

        recyclerView2 = binding.comingsoonrecyclerView

        mLayoutManagerHorizontal = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerView2.layoutManager = mLayoutManagerHorizontal

        val networkConnection = NetworkConnection(requireContext())

        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                lifecycleScope.launch {
                    withContext(Dispatchers.Default) {
                        viewmodel.insertMovies()
                        viewmodel.insertComingSoon()
                    }
                }
                search()

            } else {
                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show()

            }
        }

        viewmodel.getComingSoonFromLocal()

        viewmodel.getFromLocal()

        lifecycleScope.launch {
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewmodel.listMovies.collect {
                        adapter.submitList(it)
                        recyclerView.adapter = adapter
                    }
                }
            }
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewmodel.listComingSoon.collect {
                        adapter2.submitList(it)
                        recyclerView2.adapter = adapter2
                    }
                }
            }
        }

    }

    fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewmodel.searchMovie(query!!)
                viewmodel.searchMovie.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                    recyclerView.adapter = adapter
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }


}

