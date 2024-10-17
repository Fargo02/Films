package com.example.films.ui.films.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.films.R
import com.example.films.databinding.FragmentFilmsBinding
import com.example.films.domain.films.model.Film
import com.example.films.ui.films.view_model.FilmsViewModel
import com.example.films.ui.ui.FilmsAdapter
import com.example.films.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment(): BindingFragment<FragmentFilmsBinding>() {

    private val viewModel by viewModel<FilmsViewModel>()

    private var films = ArrayList<Film>()

    private var filmsAdapter: FilmsAdapter? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilmsBinding {
        return FragmentFilmsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.test()
        filmsAdapter = FilmsAdapter { track ->
        }

        filmsAdapter?.films = films
        binding.filmsList.layoutManager = GridLayoutManager(requireContext(),2)
        binding.filmsList.adapter = filmsAdapter

        clickToGenre(binding.fighter)
        clickToGenre(binding.detective)
        clickToGenre(binding.drama)
        clickToGenre(binding.comedy)
        clickToGenre(binding.melodrama)
        clickToGenre(binding.musical)
        clickToGenre(binding.adventures)
        clickToGenre(binding.thriller)
        clickToGenre(binding.horrors)
        clickToGenre(binding.fantasy)
    }

    private fun clickToGenre(view: View){
        view.setOnClickListener {
            if (view.isSelected) {
                view.isSelected = false
                view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                view.isSelected = true
                view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            }
        }
    }
}