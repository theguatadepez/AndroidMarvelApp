package com.example.marvelapiapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.marvelapiapp.databinding.FragmentHeroDetailBinding
import com.example.marvelapiapp.ui.viewmodel.SuperHeroViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private val viewModel: SuperHeroViewModel by activityViewModels()
    private val mNavArgs: HeroDetailFragmentArgs by navArgs()
    private var  _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewDetailSuperHeroName.text = mNavArgs.superHeroID.toString()
        binding.textViewSuperDetailHeroDescription.text = mNavArgs.superHeroID.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
//        viewModel.getSuperHeroDetail(mNavArgs.superHeroID)
        observeData()
        return binding.root
    }

    private fun observeData(){
        viewModel.superHeroDetail.observe(viewLifecycleOwner, Observer{ superHero ->
            binding.superhero = superHero
        })
        viewModel.errorLivedata.observe(viewLifecycleOwner, Observer{
            Snackbar.make(requireView(),"Ups, there was an error, please try again!", Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onPause() {
        super.onPause()
        binding.superhero = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}