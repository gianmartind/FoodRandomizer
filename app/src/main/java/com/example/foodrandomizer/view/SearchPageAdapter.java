package com.example.foodrandomizer.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchPageAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private List<Food> foodlist;
    private ArrayList<Food> arraylist;

    public SearchPageAdapter(Context context, List<Food> foodlist){
        context = context;
        this.foodlist = foodlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Food>();
        this.arraylist.addAll(foodlist);
    }

    @Override
    public int getCount(){
        return foodlist.size();
    }

    @Override
    public Food getItem(int position){
        return foodlist.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent){
        final ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.menu_list_item,null);
            holder.namaMakanan = (TextView) view.findViewById(R.id.menu_name);
            holder.deskripsiMakanan = (TextView) view.findViewById(R.id.menu_desc);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.namaMakanan.setText(foodlist.get(position).getName());
        holder.deskripsiMakanan.setText(foodlist.get(position).getDesc());
        return view;
    }

    public void filter(String text){
        text = text.toLowerCase(Locale.getDefault());
        foodlist.clear();
        if(text.length()==0){
            foodlist.addAll(arraylist);
        }
        else{
            for (Food makan : arraylist){
                if(makan.getName().toLowerCase(Locale.getDefault()).contains(text)){
                    foodlist.add(makan);
                }
            }
        }
        notifyDataSetChanged();
    }
    public class ViewHolder{
        TextView namaMakanan;
        TextView deskripsiMakanan;
    }

}
