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
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbydany.R
import com.example.moviesbydany.databinding.FragmentMovieListBinding
import com.example.moviesbydany.features.movies.domain.model.MovieSearchResult
import com.example.moviesbydany.features.movies.presentation.MoviesRecyclerViewAdapter
import com.example.moviesbydany.features.movies.presentation.viewModel.MovieListViewModel
import com.example.moviesbydany.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var movieName = "Marvel"
    private var index = 1
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
        binding.rcMovies.addOnScrollListener(rcOnScrollListener)
        setObservers()
        getMovies()
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
                    query?.let {
                        movieName = query
                        index = 1
                        getMovies()
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun getMovies() {
        movieListViewModel.getUsers(movieName, index)
    }

    private fun setObservers() {
        movieListViewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("DD", "SUCCESS" + resource.data.toString())
                        binding.bottomProgress.visibility = View.GONE
                        var result = resource.data as MovieSearchResult
                        if (!result.Search.isNullOrEmpty())
                            adapter.setList(resource.data.Search, (index == 1))
                    }
                    Status.ERROR -> {
                        binding.bottomProgress.visibility = View.GONE
                        resource.message?.let { it1 ->
                            Snackbar.make(
                                binding.root,
                                it1, Snackbar.LENGTH_LONG
                            ).setAction("Retry", null).show()
                        }

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    private val rcOnScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val canScrollMore = binding.rcMovies.canScrollVertically(1)
                if (!canScrollMore) {
                    binding.bottomProgress.visibility = View.VISIBLE
                    index++
                    getMovies()
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}