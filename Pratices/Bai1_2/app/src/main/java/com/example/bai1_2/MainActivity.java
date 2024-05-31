package com.example.bai1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bai1_2.api.ApiService;
import com.example.bai1_2.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvTerms, tvSource, tvUSDVND;
    Button btnCallAPI;
    //    API: https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    private String access = "843d4d34ae72b3882e3db642c51e28e6";
    private String currencies = "VND";
    private String source = "format";
    private int format = 1;


    private void AnhXa(){
        tvTerms = (TextView) findViewById(R.id.tvTerms);
        tvSource = (TextView) findViewById(R.id.tvSource);
        tvUSDVND = (TextView) findViewById(R.id.tvUSDVND);
        btnCallAPI = (Button) findViewById(R.id.btnCallAPI);
    }

    private void SuKien(){
        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallAPI();
            }
        });
    }

    private void clickCallAPI() {
        ApiService.API_SERVICE.converUsdToVnd(access, currencies, source, format)
                .enqueue(new Callback<Currency>() {
                    @Override
                    //Call thành công
                    public void onResponse(Call<Currency> call, Response<Currency> response) {
                        Toast.makeText(MainActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                        Currency currency = response.body();
                        if(currency != null && currency.isSuccess()){
                            tvTerms.setText(currency.getTerms());
                            tvSource.setText(currency.getSource());
                            tvUSDVND.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                        }
                    }

                    @Override
                    //Call thất bại
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        SuKien();
    }
}