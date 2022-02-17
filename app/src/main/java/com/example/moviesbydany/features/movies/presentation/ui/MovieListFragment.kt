package com.example.moviesbydany.features.movies.presentation.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesbydany.R
import com.example.moviesbydany.databinding.FragmentMovieListBinding
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.features.movies.presentation.MoviesRecyclerViewAdapter
import com.example.moviesbydany.features.movies.presentation.viewModel.MovieListViewModel
import com.example.moviesbydany.utils.Status
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var movieName = "Marvel"
    private var _binding: FragmentMovieListBinding? = null
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    private val movieListViewModel: MovieListViewModel by viewModels()

    private val binding get() = _binding!!
    private lateinit var adapter: MoviesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesRecyclerViewAdapter()
        binding.rcMovies.layoutManager = GridLayoutManager(context, 2)
        binding.rcMovies.adapter = adapter
        setObservers()
        getMovies()
        binding.buttonFirst.setOnClickListener {
            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_list_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as SearchView
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i("DD", "onQueryTextSubmit$query")
                    query?.let { movieName = query }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.i("DD", "onQueryTextChange$newText")
                    return true
                }

            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun getMovies() {
        movieListViewModel.getUsers(movieName, 1)
    }

    private fun setObservers() {
        movieListViewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("DD", "SUCCESS" + resource.data.toString())
                        resource.data as MovieSearchResult
                        if (!resource.data.Search.isNullOrEmpty())
                            adapter.setList(resource.data.Search)
                    }
                    Status.ERROR -> {
                        Log.d("DD", "ERROR")
                    }
                    Status.LOADING -> {
                        Log.d("DD", "LOADING")
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}