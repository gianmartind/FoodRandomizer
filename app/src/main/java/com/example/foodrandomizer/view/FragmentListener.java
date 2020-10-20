package com.example.foodrandomizer.view;

public interface FragmentListener {
    void changePage(int page);
    void loadSettings();
    void closeApplication();
    void changeMenuId(int id);
}
