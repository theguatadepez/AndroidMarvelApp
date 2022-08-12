package com.example.marvelapiapp.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapiapp.R
import com.example.marvelapiapp.data.model.SuperHero
import com.example.marvelapiapp.databinding.FragmentSuperHeroBinding
import com.example.marvelapiapp.ui.RecyclerViewClickListener
import com.example.marvelapiapp.ui.viewmodel.SuperHeroViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroFragment : Fragment(), RecyclerViewClickListener {

    private val viewModel: SuperHeroViewModel by viewModels()
    private var  _binding: FragmentSuperHeroBinding? = null
    private val binding get() = _binding!!
    private var state: Parcelable? = null

    companion object {
        fun newInstance() = SuperHeroFragment()
    }

    override fun onPause() {
        super.onPause()
        state = binding.recyclerviewSuperHero.layoutManager?.onSaveInstanceState()
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerviewSuperHero.layoutManager?.onRestoreInstanceState(state)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSuperHeroes(100)
        viewModelListener()
    }

    private fun viewModelListener(){
        viewModel.superHeroesData.observe(viewLifecycleOwner,Observer{ superHeroes ->
            binding.recyclerviewSuperHero.also {
                binding.loadingPanel.visibility = View.GONE
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = SuperHeroAdapter(superHeroes,this)
            }
            binding.recyclerviewSuperHero.layoutManager?.onRestoreInstanceState(state)
        })
        viewModel.errorLivedata.observe(viewLifecycleOwner, Observer{
            binding.loadingPanel.visibility = View.GONE
            Snackbar.make(requireView(),"Ups, there was an error, please try again!",Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClick(view: View, superHero: SuperHero) {
        val action: NavDirections
        when(view.id){
            R.id.buttonSuperHeroPage -> {
                action = SuperHeroFragmentDirections.actionSuperHeroFragmentToHeroDetailFragment(superHero.id)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }
}