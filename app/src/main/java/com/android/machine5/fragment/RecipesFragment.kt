package com.android.machine5.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.machine5.R
import com.android.machine5.RecipeViewModelFactory
import com.android.machine5.adapter.RecipeAdapter
import com.android.machine5.databinding.RecipesFragmentBinding
import com.android.machine5.model.Recipe
import com.android.machine5.network.RecipeApiService
import com.android.machine5.repository.RecipeRepository
import com.android.machine5.viewmodel.RecipeViewModel

class RecipesFragment : Fragment() {
    private lateinit var binding : RecipesFragmentBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipesFragmentBinding.inflate(layoutInflater)


        initViews()
        initViewModels()
        initObserver()
        initAdapter()
        initListener()

        recipeViewModel.fetchRecipes()
        return binding.root
    }
    private fun initListener(){

        binding.recyclerRecipes.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        recipeViewModel.fetchRecipes()
                    }
                }
            })
        recipeAdapter.onrecipeClickListener =
            object : RecipeAdapter.OnRecipeClickListener {
                override fun onRecipeClick(
                    recipe: Recipe,
                    position: Int,
                    recipeAdapter: RecipeAdapter
                ) {
                    showDetailsFragment(recipe)
                }
            }
    }

    private fun showDetailsFragment(recipe: Recipe) {
        val recipeDetailsFragment = RecipeDetailsFragment()

        val bundles = Bundle()
        bundles.putSerializable("recipes",recipe)
        recipeDetailsFragment.arguments = bundles

        parentFragmentManager.beginTransaction()
            .replace(R.id.mainCintainer, recipeDetailsFragment, null)
            .addToBackStack(null)
            .commit()
    }

    private fun initAdapter(){
        recipeAdapter = RecipeAdapter(recipeViewModel.recipes)
        binding.recyclerRecipes.adapter = recipeAdapter

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        recipeViewModel.recipeMutableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                recipeAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initViewModels(){
        recipeViewModel = ViewModelProvider(
            this,
            RecipeViewModelFactory(
                RecipeRepository(
                   RecipeApiService.getInstance()
                )
            )
        )[RecipeViewModel::class.java]
    }
    private fun initViews(){
        binding.recyclerRecipes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
    }
}