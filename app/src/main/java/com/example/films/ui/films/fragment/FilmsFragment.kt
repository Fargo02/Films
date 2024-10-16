package com.example.films.ui.films.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity.INPUT_METHOD_SERVICE
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.databinding.FragmentFilmsBinding
import com.example.films.domain.films.model.Film
import com.example.films.ui.ui.FilmsAdapter
import com.example.films.utils.BindingFragment

class FilmsFragment(): BindingFragment<FragmentFilmsBinding>() {

    private var films = ArrayList<Film>()

    private var filmsAdapter: FilmsAdapter? = null

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFilmsBinding {
        return FragmentFilmsBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        films = arrayListOf(
            Film("111"),
            Film("222"),
            Film("333"),
            Film("444"),
        )

        filmsAdapter = FilmsAdapter { track ->
        }

        filmsAdapter?.films = films
        binding.filmsList.layoutManager = GridLayoutManager(requireContext(),2)
        binding.filmsList.adapter = filmsAdapter

    }
}