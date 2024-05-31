package com.example.bt_29_3_2024.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bt_29_3_2024.utils.models.DetailedMeal;

import java.util.List;

@Dao
public interface CartDAO {
    @Insert
    void insertMealCart(DetailedMeal detailedMeal);

    @Query("SELECT * FROM cart_item")
    List<DetailedMeal> getAllCartItems();

    @Delete
    void deleteMealCart(DetailedMeal detailedMeal);

    @Query("UPDATE  cart_item SET quantity = :quantity WHERE id = :id")
    void updateMealQuantity(int id, int quantity);

    @Query("UPDATE cart_item SET price = :price WHERE id = :id")
    void updatePrice(int id, double price);
}
