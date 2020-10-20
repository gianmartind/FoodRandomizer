package com.example.foodrandomizer;

import com.example.foodrandomizer.model.Food;

public class MockFood {

    public static String[] foodStringArr = { "Nasi Goreng Biasa", "Nasi Goreng Telor", "Nasi Goreng Ayam",
            "Nasi Goreng Sapi", "Nasi Goreng Seafood", "Mie Goreng Biasa", "Mie Goreng Telor", "Mie Goreng Ayam",
            "Mie Goreng Sapi", "Mie Goreng Seafood", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso", "Baso" };

    public static Food[] foodObjectArr = {
            new Food(0 ,"Nasi Goreng","pake nasi", new String[5], new String[5], new String[5]),
            new Food(1, "Mie Goreng","pake mie", new String[5], new String[5], new String[5]),
            new Food(2, "Makanan 1","detail makanan 1", new String[5], new String[5], new String[5]),
            new Food(3, "Makanan 2","detail makanan 2", new String[5], new String[5], new String[5]),
            new Food(4, "Makanan 3","detail makanan 3", new String[5], new String[5], new String[5]),
            new Food(5, "Makanan 4","detail makanan 4", new String[5], new String[5], new String[5]),
            new Food(6, "Makanan 5","detail makanan 5", new String[5], new String[5], new String[5])
    };
}
