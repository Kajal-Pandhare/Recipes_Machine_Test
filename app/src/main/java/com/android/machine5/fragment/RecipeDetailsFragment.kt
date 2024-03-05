package com.android.machine5.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.machine5.databinding.RecipeDetailsFragmentBinding
import com.android.machine5.model.Recipe

class RecipeDetailsFragment : Fragment() {
private lateinit var binding: RecipeDetailsFragmentBinding
    private var recipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeDetailsFragmentBinding.inflate(layoutInflater)

        if(arguments != null) {
           recipe= requireArguments().getSerializable("recipes") as Recipe
            binding.recipe = recipe
        }
        return binding.root
    }
}