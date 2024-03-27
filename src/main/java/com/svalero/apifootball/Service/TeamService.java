package com.svalero.apifootball.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.apifootball.Model.Coach;
import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Model.Venue;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

        //System.out.println("API CONECTADA");
    }

    //Observable que nos va a devolver un objeto Team y a partir de ahi podriamos acceder al objeto y el consumidor (TeamTask) podria acceder al nombre, fundation, etc...
    public Observable<Team> getTeamsByCountry (String country){
        return this.footballApi.getTeamsByCountry(country).flatMapIterable(teamResponse -> teamResponse.getResponse())
                .map(teamVenueWrapper -> teamVenueWrapper.getTeam()).map(team -> team);
    }

    // Observable para recoger el nombre de un estadio en funcion del equipo
    public Observable<String> getVenueByTeam (String country){
        return this.footballApi.getTeamsByCountry(country).flatMapIterable(teamResponse -> teamResponse.getResponse())
                .map(teamVenueWrapper -> teamVenueWrapper.getVenue()).map(venue -> venue.getName());
    }

    //Manera para recoger el objeto venue y luego en el cosumer poder seleccionar la informacion que queramos
    public Observable<Venue> getVenueByTeamforObject (String country){
        return this.footballApi.getTeamsByCountry(country).flatMapIterable(teamResponse -> teamResponse.getResponse())
                .map(teamVenueWrapper -> teamVenueWrapper.getVenue()).map(venue -> venue);
    }

    public Observable<Coach> getCoachByName (String nameCoach){
        return this.footballApi.getCoachByName(nameCoach).flatMapIterable(coachResponse -> coachResponse.getResponse())
                .map(coach -> coach);
    }

    public Long getTotalCoachsByName(String nameCoach) {
        return getCoachByName(nameCoach)
                .count()
                .blockingGet();
    }


    //Metodo para obtener el numero de equipos en funcion de un país para que el progreso de la ejecucion del programa concuerde
    public Long getTotalTeamsByCountry(String country) {
        return getTeamsByCountry(country)
                .count()
                .blockingGet(); // Obtener el valor emitido de forma síncrona
    }
}
