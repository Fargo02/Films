package com.example.films.ui.films.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.films.R
import com.example.films.databinding.FragmentFilmsBinding
import com.example.films.domain.films.model.Film
import com.example.films.ui.about.fragment.AboutFragment
import com.example.films.ui.films.view_model.FilmsViewModel
import com.example.films.ui.films.view_model.SearchState
import com.example.films.ui.ui.FilmsAdapter
import com.example.films.utils.BindingFragment
import com.example.films.utils.debounce
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment(): BindingFragment<FragmentFilmsBinding>() {

    private var genre = ""

    private var genreView: View? = null

    private val viewModel by viewModel<FilmsViewModel>()

    private lateinit var onFilmClickDebounce: (Film) -> Unit

    private var films = ArrayList<Film>()

    private var filmsAdapter: FilmsAdapter? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilmsBinding {
        return FragmentFilmsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //при переходе с 2 фрагмента на 1 теряется выбранный жанр

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

        filmsAdapter?.films = films
        binding.filmsList.layoutManager = GridLayoutManager(requireContext(),2)
        binding.filmsList.adapter = filmsAdapter

        viewModel.observeSearchStateLiveData().observe(viewLifecycleOwner) {
            render(it)
        }

        clickToGenre(binding.fighter, getString(R.string.fighter))
        clickToGenre(binding.detective, getString(R.string.detective))
        clickToGenre(binding.drama, getString(R.string.drama))
        clickToGenre(binding.comedy, getString(R.string.comedy))
        clickToGenre(binding.melodrama, getString(R.string.melodrama))
        clickToGenre(binding.musical, getString(R.string.musical))
        clickToGenre(binding.adventures, getString(R.string.adventures))
        clickToGenre(binding.thriller, getString(R.string.thriller))
        clickToGenre(binding.horrors, getString(R.string.horrors))
        clickToGenre(binding.fantasy, getString(R.string.fantasy))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        filmsAdapter = null
    }

    private fun clickToGenre(view: View, genre: String){
        view.setOnClickListener {
            if (view.isSelected) {
                viewModel.getFilms()
                this.genre = ""
                genreView = null
                view.isSelected = false
                view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                viewModel.getFilms(this.genre)
                genreView?.isSelected = false
                genreView?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
                this.genre = genre
                genreView = view
                view.isSelected = true
                view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            }
        }
    }

    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Loading -> showLoading()
            is SearchState.Content -> showContent(state.films)
            is SearchState.Error -> showError()
            is SearchState.Empty -> showEmpty()
        }
    }

    private fun showLoading() {
        binding.genresGroup.isVisible = false
        binding.filmsList.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showContent(films: List<Film>) {
        binding.genresGroup.isVisible = true
        binding.filmsList.isVisible = true
        binding.progressBar.isVisible = false
        filmsAdapter?.films?.clear()
        filmsAdapter?.films?.addAll(films)
        filmsAdapter?.notifyDataSetChanged()
    }

    private fun showError() {
        binding.filmsList.isVisible = false
        binding.progressBar.isVisible = false
//        binding.textPlaceholder.text = getString(R.string.problem_with_network)
//        binding.imagePlaceholder.setImageResource(R.drawable.problem_with_network)
    }

    private fun showEmpty() {
        binding.filmsList.isVisible = false
        binding.progressBar.isVisible = false
        filmsAdapter?.films?.clear()
        filmsAdapter?.notifyDataSetChanged()
//        binding.textPlaceholder.text = getString(R.string.nothing_found)
//        binding.imagePlaceholder.setImageResource(R.drawable.nothing_found)
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 300L
    }
}