package br.unicamp.training.movieapp.services;


import br.unicamp.training.movieapp.model.Page;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Service {
    private static final String API_URL = "http://api.themoviedb.org/";

    public interface PopularMovie {

        @GET("/3/movie/popular?page=1&language=pt-BR")
        Call<Page> getPage(@Query("api_key") String key);

        @GET("/3/movie/popular?page=1&language=pt-BR")
        Call<Page> getPage(@Query("api_key") String key, @Query("page") int page);

        @GET("/3/movie/popular?page=1")
        Call<Page> getPage(@Query("api_key") String key, @Query("language") String language);

        @GET("/3/movie/popular")
        Call<Page> getPage(@Query("api_key") String key, @Query("language") String language, @Query("page") int page);
    }

    public static PopularMovie getPopularMovieAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PopularMovie result = retrofit.create(PopularMovie.class);
        return result;
    }
}
