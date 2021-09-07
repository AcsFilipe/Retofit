package com.example.xkcd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    
    ImageView imageView ;
    int numero = 1;
    private String numberOfPhoto = "614";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.tirinha);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

           /*|---------------------------------------------------------|
             |     BUTTON RANDOM
             |---------------------------------------------------------|*/

        final Button button = findViewById(R.id.btnRandom);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                proximaImagem();
                localizaImagem();
            }
        });

        localizaImagem();
       // slide();

    }

    private void proximaImagem() {
        Random rand = new Random();
        numero =  rand.nextInt((2152 - 614) + 1) + 614;
        numberOfPhoto = String.valueOf(numero);
    }

    /*|---------------------------------------------------------|
      |    HANDLER SLIDES                                       |
      |---------------------------------------------------------|*/

    /*
    private void slide() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proximaImagem();
                localizaImagem();
                slide();
            }
        }, 2000);
    }
 */

    private void localizaImagem() {
    /*|---------------------------------------------------------|
      |     IMPRIMIR  IMAGEM COM GLIDE                          |
      |---------------------------------------------------------|*/
        Service service = retrofit.create(Service.class);
        service.getImagem(numberOfPhoto).enqueue(new Callback<Imagem>() {
            @Override
            public void onResponse(Call<Imagem> call, Response<Imagem> response) {
                if(response.isSuccessful()){
//                    new SetImagem(response.body()).execute();


                    Glide.with(MainActivity.this)
                            .load(response.body().getImg())
                            .into(imageView);
                }

            }

            @Override
            public void onFailure(Call<Imagem> call, Throwable t) {

            }
        });
    }

    /*|---------------------------------------------------------|
      |     IMPRIMIR  IMAGEM COM BITMAP                         |
      |---------------------------------------------------------|*/

    class SetImagem extends AsyncTask<Void,Void,Void> {

        Imagem imagem;
        public SetImagem(Imagem imagem) {
            this.imagem = imagem;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("img", imagem.toString());

            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(imagem.getImg()).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                // Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            final Bitmap finalMIcon1 = mIcon11;
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(finalMIcon1);
                }
            });

            return null;
        }
    }
}
