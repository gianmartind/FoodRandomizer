package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.MockItemFragmentLeft;
import com.example.foodrandomizer.model.ItemLeftDrawer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftDrawerPresenter {
    private List<ItemLeftDrawer> itemList;
    private ILeftDrawer leftDrawer;

    public LeftDrawerPresenter(ILeftDrawer leftDrawer){
        this.itemList = new ArrayList<>();
        this.leftDrawer = leftDrawer;
    }

    public void loadData(){
        this.itemList.addAll(Arrays.asList(MockItemFragmentLeft.arr));
        this.leftDrawer.updateList(this.itemList);
    }

    public void closeApp(){
        this.leftDrawer.closeApplication();
    }

    public void changePage(int i){
        this.leftDrawer.changePage(i);
    }

    public interface ILeftDrawer{
        void updateList(List<ItemLeftDrawer> itemList);
        void changePage(int i);
        void closeApplication();
    }

}
