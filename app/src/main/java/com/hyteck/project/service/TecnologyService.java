package com.hyteck.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyteck.project.entity.SearchOptions;
import com.hyteck.project.api.TecnologyApi;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TecnologyService {

    private static final double HEC_M = 10000;
    private static final String BASE_URL = "http://10.0.0.6:8080/";
    private static final String BASE_URL_PROD = "https://tcc-hyteck.herokuapp.com/";

    public TecnologyApi calculateTecnologies(SearchOptions searchOptions, boolean urlPrd) {


        String url = urlPrd ? BASE_URL_PROD :BASE_URL;

        if (searchOptions.getOptions() != null && searchOptions.getOptions().containsKey("hectares")) {
            searchOptions.getOptions().remove("hectares");
            final double distancy = Double.parseDouble(Objects.requireNonNull(searchOptions.getOptions().get("distance"))) * HEC_M;
            searchOptions.getOptions().replace("distance", String.valueOf(distancy));
        }
        assert searchOptions.getOptions() != null;
        searchOptions.getOptions().remove("M2");
        return start(TecnologyApi.class,url);
    }

    public static <S> S start(Class<S> serviceClass, String url) {
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
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(serviceClass);
    }
}
