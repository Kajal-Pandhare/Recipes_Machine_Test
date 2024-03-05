package com.android.machine5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.machine5.R
import com.android.machine5.databinding.RecipeViewBinding
import com.android.machine5.model.Recipe
import com.bumptech.glide.Glide

class RecipeAdapter(private val recipes : List<Recipe>):
RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

    interface OnRecipeClickListener{
        fun onRecipeClick(recipe: Recipe,position: Int,recipeAdapter: RecipeAdapter)
    }
    var onrecipeClickListener : OnRecipeClickListener? = null

    inner class RecipeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding :RecipeViewBinding

        init {
            binding = RecipeViewBinding.bind(view)

            binding.root.setOnClickListener {
                onrecipeClickListener?.onRecipeClick(
                    recipes[adapterPosition],
                    adapterPosition,
                    this@RecipeAdapter
                )
            }

        }
    }

    override fun getItemCount() = recipes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_view,null)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val recipe =recipes[position]
        holder.binding.txtRating.text = recipe.rating.toString()
        holder.binding.txtReviewCount.text = recipe.reviewCount.toString()
        holder.binding.txtTags.text = recipe.tags.toString()


        Glide.with(holder.itemView)
            .load(recipe.image)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .circleCrop()

            .into(holder.binding.image)
    }

    }
