package com.saunik.gitsample.api;

import com.saunik.gitsample.pojo.GitDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Saunik Singh on 27/8/18.
 * Cars24 Services Private Limited.
 */
public interface ApiInterface {
    @GET("search/repositories")
    Call<GitDetails> getTopTrendingRepo(@Query("sort") String sort, @Query("order") String order, @Query("page") int page, @Query("per_page")
            int perpage, @Query("q") String query);
}
