package com.example.films.ui.films.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.R
import com.example.films.databinding.FragmentFilmsBinding
import com.example.films.domain.films.model.Film
import com.example.films.ui.about.fragment.AboutFragment
import com.example.films.ui.ui.GenresAdapter
import com.example.films.ui.films.view_model.FilmsViewModel
import com.example.films.ui.films.view_model.SearchState
import com.example.films.ui.ui.FilmsAdapter
import com.example.films.utils.BindingFragment
import com.example.films.utils.debounce
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment(): BindingFragment<FragmentFilmsBinding>() {

    private var lastSearch = ""

    private val viewModel by viewModel<FilmsViewModel>()

    private lateinit var onFilmClickDebounce: (Film) -> Unit

    private var films = ArrayList<Film>()
    private var genres = ArrayList<String>()

    private var filmsAdapter: FilmsAdapter? = null
    private var genresAdapter: GenresAdapter? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilmsBinding {
        return FragmentFilmsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFilmClickDebounce = debounce<Film>(CLICK_DEBOUNCE_DELAY, viewLifecycleOwner.lifecycleScope, false) { film ->
            val json = Gson().toJson(film)
            parentFragmentManager.commit {
                replace(
                    R.id.rootFragmentContainerView,
                    AboutFragment.newInstance(
                        film = json
                    ),
                    AboutFragment.TAG
                )
                addToBackStack(AboutFragment.TAG)
            }
        }

        filmsAdapter = FilmsAdapter { film ->
            onFilmClickDebounce(film)
        }

        genresAdapter = GenresAdapter { genre, position->
            viewModel.getGenres(genre, position)
        }

        filmsAdapter?.films = films
        binding.filmsList.layoutManager = GridLayoutManager(requireContext(),2)
        binding.filmsList.adapter = filmsAdapter

        genresAdapter?.genres = genres
        binding.genresList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.genresList.adapter = genresAdapter

        viewModel.observeSelectedGenres().observe(viewLifecycleOwner) {
            genresAdapter?.selectedPosition = it
            genresAdapter?.notifyDataSetChanged()
        }

        viewModel.observeSearchStateLiveData().observe(viewLifecycleOwner) {
            render(it)
        }

        binding.placeholderButton.setOnClickListener {
            viewModel.getFilms(lastSearch)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        filmsAdapter = null
        genresAdapter = null
    }

    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Loading -> showLoading()
            is SearchState.Content -> showContent(state.films, state.genres)
            is SearchState.Error -> showError(state.lastSearch)
            is SearchState.Empty -> showEmpty()
        }
    }

    private fun showLoading() {
        binding.genresGroup.isVisible = false
        binding.filmsGroup.isVisible = false
        binding.progressBar.isVisible = true
        binding.placeholder.isVisible = false
    }

    private fun showContent(films: List<Film>, genres: List<String>) {
        binding.genresGroup.isVisible = true
        binding.filmsGroup.isVisible = true
        binding.progressBar.isVisible = false
        binding.placeholder.isVisible = false
        filmsAdapter?.films?.clear()
        genresAdapter?.genres?.clear()
        filmsAdapter?.films?.addAll(films)
        genresAdapter?.genres?.addAll(genres)
        filmsAdapter?.notifyDataSetChanged()
        genresAdapter?.notifyDataSetChanged()
    }

    private fun showError(lastSearch: String) {
        this.lastSearch = lastSearch
        binding.filmsGroup.isVisible = false
        binding.progressBar.isVisible = false
        binding.placeholder.isVisible = true
    }

    private fun showEmpty() {
        binding.filmsGroup.isVisible = false
        binding.progressBar.isVisible = false
        binding.placeholder.isVisible = false
        filmsAdapter?.films?.clear()
        genresAdapter?.genres?.clear()
        filmsAdapter?.notifyDataSetChanged()
        genresAdapter?.notifyDataSetChanged()
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 300L
    }
}