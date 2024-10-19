package com.example.films.ui.about.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.films.R
import com.example.films.databinding.FragmentAboutBinding
import com.example.films.domain.films.model.Film
import com.example.films.ui.about.view_model.AboutViewModel
import com.example.films.utils.BindingFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class AboutFragment(): BindingFragment<FragmentAboutBinding>() {

    private lateinit var currentFilm: Film

    private val viewModel by viewModel<AboutViewModel>()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAboutBinding {
        return FragmentAboutBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val track = requireArguments().getString(ARGS_FILM) ?: ""
        val type = object : TypeToken<Film>() {}.type
        currentFilm = Gson().fromJson(track, type) as Film

        val genres = currentFilm.genres.takeIf {
            it.isNotEmpty()
        }?.joinToString(", ", postfix = ",") ?: ""

        val stringBuilder = StringBuilder()
        stringBuilder.append(genres)
        stringBuilder.append(if (genres.isNotEmpty()) " " else "")
        stringBuilder.append(currentFilm.year)
        stringBuilder.append(" год")

        binding.toolbarTitle.text = currentFilm.name
        binding.name.text = currentFilm.localizedName
        binding.genreAndYear.text = stringBuilder.toString()
        binding.rating.text = roundOffDecimal(currentFilm.rating)
        binding.description.text = currentFilm.description
        Glide.with(binding.cover)
            .load(currentFilm.imageUrl)
            .transform(CenterCrop(), RoundedCorners(resources.getDimensionPixelSize(R.dimen.mark_4dp)))
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.cover)

        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun roundOffDecimal(number: Double): String {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.HALF_UP
        return df.format(number).toString().replace(",", ".")
    }

    companion object {

        private const val ARGS_FILM = "film"

        const val TAG = "AboutFragment"

        fun newInstance(film: String): Fragment {
            return AboutFragment().apply {
                arguments = bundleOf(
                    ARGS_FILM to film
                )
            }
        }

    }
}