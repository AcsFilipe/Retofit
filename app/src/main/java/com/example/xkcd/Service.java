package com.example.xkcd;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

   @GET("{num}/info.0.json")
    Call<Imagem> getImagem(@Path("num")String num);

}
