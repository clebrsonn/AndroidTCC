package com.hyteck.project.api;

import com.hyteck.project.SearchOptions;
import com.hyteck.project.entity.Tecnology;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface TecnologyApi {

    @GET("tecnologies")
    Call<List<Tecnology>> list();

    @GET("search")
    Call<List<Tecnology>> search(@Body SearchOptions searchOptions);

}
