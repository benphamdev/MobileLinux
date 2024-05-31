package com.example.convertcurrency.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.convertcurrency.dto.dao.ExchangeRate;
import com.example.convertcurrency.dto.dao.ExchangeRateDao;

@Database(entities = {ExchangeRate.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExchangeRateDao exchangeRateDao();
}
