package com.sngular.rgarciavital.view.perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialFadeThrough
import com.sngular.rgarciavital.BuildConfig
import com.sngular.rgarciavital.R
import com.sngular.rgarciavital.adapters.PerfilAdapter
import com.sngular.rgarciavital.adapters.RecyclerViewHomeClickListener
import com.sngular.rgarciavital.data.model.Author
import com.sngular.rgarciavital.data.model.Review
import com.sngular.rgarciavital.databinding.FragmentPerfilBinding
import com.sngular.rgarciavital.util.Resource
import com.sngular.rgarciavital.viewmodel.perfil.PerfilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerfilFragment : Fragment(), RecyclerViewHomeClickListener {

    private val perfilViewModel: PerfilViewModel by viewModels()
    private val perfilAdapter: PerfilAdapter by lazy {
        PerfilAdapter(
            requireContext(),
            this@PerfilFragment
        )
    }


    private lateinit var binding: FragmentPerfilBinding;


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
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        binding.userdata
        binding.recyclerView.apply {
            adapter = perfilAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) { //1 for down
                        perfilViewModel.obtenerReviews(BuildConfig.API_TMDB_TOKEN)
                    }
                }
            })
        }

        perfilViewModel.obtenerReviews(BuildConfig.API_TMDB_TOKEN)
        observeUI()

        return binding.root
    }


    private fun observeUI() {

        perfilViewModel.dataProfile.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Success -> {
                    binding.apply {
                        userdata = it.data
                        executePendingBindings()
                    }
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

        perfilViewModel.reviewsPopular.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val value = it.data!!
                   // it.data.reviews.get(0).author
                    val data = value.reviews
                    perfilAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun clickOnItem(data: Review, card: View) {
        TODO("Not yet implemented")
    }
}