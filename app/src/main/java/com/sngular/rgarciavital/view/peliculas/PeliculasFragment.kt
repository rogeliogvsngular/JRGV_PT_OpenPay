package com.sngular.rgarciavital.view.peliculas

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.annotation.NonNull
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialFadeThrough
import com.sngular.rgarciavital.BuildConfig
import com.sngular.rgarciavital.R
import com.sngular.rgarciavital.data.model.Pelicula
import com.sngular.rgarciavital.databinding.FragmentPeliculasBinding
import com.sngular.rgarciavital.view.peliculas.adapter.PelisAdapter
import com.sngular.rgarciavital.view.peliculas.adapter.RecyclerViewHomeClickListener
import com.sngular.rgarciavital.util.Resource
import com.sngular.rgarciavital.viewmodel.peliculas.PeliculasViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeliculasFragment : Fragment(), RecyclerViewHomeClickListener {

    private val pelisViewModel: PeliculasViewModel by viewModels()
    private val pelisAdapter: PelisAdapter by lazy {
        PelisAdapter(
            requireContext(),
            this@PeliculasFragment
        )
    }

    private val pelisTopAdapter: PelisAdapter by lazy {
        PelisAdapter(
            requireContext(),
            this@PeliculasFragment
        )
    }

    private val peliRecsAdapter: PelisAdapter by lazy {
        PelisAdapter(
            requireContext(),
            this@PeliculasFragment
        )
    }
    private lateinit var binding: FragmentPeliculasBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeliculasBinding.inflate(inflater, container, false)
        binding.recyclerView.apply {
            adapter = pelisAdapter

        }

        binding.recyclerView1.apply {
            adapter = pelisTopAdapter
        }

        binding.recyclerView2.apply {
            adapter = peliRecsAdapter
        }
        pelisViewModel.obtenerPopular(BuildConfig.API_TMDB_TOKEN)
        observeUI()

        return binding.root
    }


    private fun observeUI() {

        pelisViewModel.pelisPopular.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val value = it.data!!
                    val data = value.peliculas
                    pelisAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
        pelisViewModel.pelisTopReview.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val value = it.data!!
                    val data = value.peliculas
                    pelisTopAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                }
            }

        }
        pelisViewModel.pelisTopRecommended.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val value = it.data!!
                    val data = value.peliculas
                    peliRecsAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                }
            }

        }

    }

    override fun clickOnItem(data: Pelicula, card: View) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}