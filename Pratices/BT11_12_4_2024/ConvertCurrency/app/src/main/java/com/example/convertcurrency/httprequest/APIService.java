package com.example.convertcurrency.httprequest;

import com.example.convertcurrency.dto.response.CurrencyExchangeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("query/")
    Call<CurrencyExchangeResponse> getExchangeRate(@Query("function") String function,
                                                   @Query("from_currency") String fromCurrency,
                                                   @Query("to_currency") String toCurrency,
                                                   @Query("apikey") String apiKey);
}
