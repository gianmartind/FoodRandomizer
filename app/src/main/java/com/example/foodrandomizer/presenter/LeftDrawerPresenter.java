package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.MockItemFragmentLeft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftDrawerPresenter {
    private List<String> itemList;
    private ILeftDrawer leftDrawer;

    public LeftDrawerPresenter(ILeftDrawer leftDrawer){
        this.itemList = new ArrayList<>();
        this.leftDrawer = leftDrawer;
    }

    public void loadData(){
        this.itemList.addAll(Arrays.asList(MockItemFragmentLeft.itemListArr));
        this.leftDrawer.updateList(this.itemList);
    }

    public void closeApp(){
        this.leftDrawer.closeApplication();
    }

    public void changePage(int i){
        this.leftDrawer.changePage(i);
    }

    public interface ILeftDrawer{
        void updateList(List<String> itemList);
        void changePage(int i);
        void closeApplication();
    }

}
