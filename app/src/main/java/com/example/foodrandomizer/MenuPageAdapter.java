package com.example.foodrandomizer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MenuPageAdapter extends BaseAdapter {
    private List<Food> foodList;
    private Context fragment;

    public MenuPageAdapter(Context fragment){
        this.fragment = fragment;
        this.foodList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void updateList(List<Food> newList){
        this.foodList = newList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(this.fragment).inflate(R.layout.menu_list_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);;
        Food current = (Food) this.getItem(i);
        viewHolder.updateView(current);
        view.setTag(viewHolder);

        return view;
    }

    public class ViewHolder{
        protected TextView menuName;
        protected TextView menuDesc;

        public ViewHolder(View view){
            this.menuName = view.findViewById(R.id.menu_name);
            this.menuDesc = view.findViewById(R.id.menu_desc);
        }

        public void updateView(final Food food){
            this.menuName.setText(food.getName());
            this.menuDesc.setText(food.getDesc());
        }
    }
}
