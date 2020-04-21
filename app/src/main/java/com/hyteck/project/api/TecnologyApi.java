package com.hyteck.project.api;

import com.hyteck.project.entity.SearchOptions;
import com.hyteck.project.entity.Tecnology;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TecnologyApi {

    @GET("tecnologies")
    Call<List<Tecnology>> list();

    @POST("tecnologies/search")
    Call<List<Tecnology>> search(@Body SearchOptions searchOptions);

}
