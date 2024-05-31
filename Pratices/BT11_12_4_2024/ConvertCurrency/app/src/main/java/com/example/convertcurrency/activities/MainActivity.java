package com.example.convertcurrency.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;

import com.example.convertcurrency.database.AppDatabase;
import com.example.convertcurrency.dto.dao.ExchangeRate;
import com.example.convertcurrency.httprequest.APIService;
import com.example.convertcurrency.dto.response.CurrencyExchangeResponse;
import com.example.convertcurrency.R;
import com.example.convertcurrency.dto.response.RealtimeCurrencyExchangeRate;
import com.example.convertcurrency.httprequest.RetrofitClient;
import com.example.convertcurrency.worker.UpdateExchangeRateWorker;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//    String [] fromCurrencies = {"USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "SGD", "HKD", "KRW", "THB"};

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Double ans;
    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "exchange_rate_db").build();

        // you can change the fromCurrencyCode and toCurrencyCode to any currency code you want
        // for example: fromCurrencyCode = "EUR", toCurrencyCode = "USD"
        String fromCurrencyCode = "CNY";
        String toCurrencyCode = "VND";
        // fetch the exchange rate from the database
        Double exchangeRate = getFromCurrency(fromCurrencyCode, toCurrencyCode);

        // Get the current time
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Set the time you want the task to run
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(Calendar.HOUR_OF_DAY, 22);
        targetCalendar.set(Calendar.MINUTE, 37);
        targetCalendar.set(Calendar.SECOND, 0);

        // If the current time is past 7:58 AM, schedule the task for next day
        if (calendar.after(targetCalendar)) {
            targetCalendar.add(Calendar.DATE, 1);
        }

        // Calculate the initial delay
        long initialDelay = targetCalendar.getTimeInMillis() - calendar.getTimeInMillis();

        // Convert the initial delay to minutes
        long initialDelayMinutes = TimeUnit.MILLISECONDS.toMinutes(initialDelay);

        // Create the work request
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(UpdateExchangeRateWorker.class)
                .setInitialDelay(initialDelayMinutes, TimeUnit.MINUTES)
                .build();

        // Enqueue the work request
        WorkManager.getInstance(this).enqueue(workRequest);

    }

    public Double getFromCurrency(String fromCurrencyCode, String toCurrencyCode) {
        ans = 0.0;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ExchangeRate exchangeRate = appDatabase.exchangeRateDao().getExchangeRate("EUR", "VND");
                Log.d("getFromCurrency", "Exchange rate: " + exchangeRate.getExchangeRate());
                ans =  Double.valueOf(exchangeRate.getExchangeRate());
            }
        });
        return ans;
    }
}