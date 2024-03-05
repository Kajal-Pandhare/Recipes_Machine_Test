package com.android.machine5.network

import com.android.machine5.model.RecipeResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RecipeApiService {

    @GET("recipes")
    suspend fun fetchRecipe() : RecipeResponse

    companion object{
        private var recipeApiService : RecipeApiService? = null
        fun getInstance() : RecipeApiService{
            if (recipeApiService == null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                val client =  OkHttpClient.Builder().addInterceptor(interceptor).build();

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                recipeApiService = retrofit.create(RecipeApiService::class.java)
            }
            return recipeApiService!!
        }
    }

}