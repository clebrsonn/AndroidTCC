package com.hyteck.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyteck.project.SearchOptions;
import com.hyteck.project.api.TecnologyApi;
import com.hyteck.project.entity.Tecnology;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TecnologyService {

    private static final double HEC_M = 10000;

    private static final String BASE_URL = "https://tcc-hyteck.herokuapp.com/";
    private List<Tecnology> technologies = new ArrayList<>();



    public TecnologyApi calculateTecnologies(SearchOptions searchOptions) {

        if(searchOptions.getOptions()!=null && searchOptions.getOptions().containsKey("hectares")){
            final double distancy = Double.parseDouble(Objects.requireNonNull(searchOptions.getOptions().get("distancy"))) * HEC_M;
            searchOptions.getOptions().replace("distancy", String.valueOf(distancy));
        };
       return start(TecnologyApi.class);
//        technologies.add(new Tecnology());

//        return technologies;
    }

    public static <S> S start(Class<S> serviceClass){
        //Instancia do interceptador das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS);

        httpClient.addInterceptor(loggingInterceptor);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

       return retrofit.create(serviceClass);
    }
}
