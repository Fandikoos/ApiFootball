package com.svalero.apifootball.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.apifootball.Model.Country;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class TeamService {
    private static final String BASE_URL = "https://v3.football.api-sports.io/";
    private static final String API_KEY = "8347edb908016ad2b310b59a798508b5";
    private FootballApi footballApi;

    //Configuramos el cliente http
    public TeamService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                //Tenemos que agregar una cabecera como se inidica en la API para poner la apikey y poder conectar
                .addInterceptor(chain -> {
                    okhttp3.Request original = chain.request();
                    okhttp3.Request request = original.newBuilder()
                            .header("x-apisports-key", API_KEY)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonParser))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.footballApi = retrofit.create(FootballApi.class);

        System.out.println("API CONECTADA");
    }


    // Método para obtener equipos por país y extraer el nombre de cada equipo


    public void listCountries() {
        footballApi.getCountries()
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                    List<Country> countries = response.getCountries();
                    // Manejar los países aquí usando Streams, por ejemplo:
                    countries.forEach(country -> System.out.println(country.getName()));
                }, throwable -> {
                    // Manejar errores
                    System.out.println("Error: " + throwable.getMessage());
                });
    }


}
