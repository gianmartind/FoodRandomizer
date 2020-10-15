package com.example.foodrandomizer.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.R;

import java.util.ArrayList;
import java.util.List;

public class LeftDrawerAdapter extends BaseAdapter {
    private List<String> listItems;
    private Context leftDrawer;

    public LeftDrawerAdapter(Context leftDrawer){
        this.leftDrawer = leftDrawer;
        this.listItems = new ArrayList<String>();
    }

    public void addLine(String newItem){
        this.listItems.add(newItem);
        this.notifyDataSetChanged();
    }

    public void updateList(List<String> list){
        this.listItems = list;
    }

    @Override
    public int getCount(){
        return this.listItems.size();
    }

    @Override
    public Object getItem(int i){
        return this.listItems.get(i);
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder viewHolder;
        String current = this.getItem(i).toString();

        if(view == null){
            view = LayoutInflater.from(this.leftDrawer).inflate(R.layout.item_list_drawer_left, viewGroup,false);
            viewHolder = new ViewHolder(view);
            viewHolder.updateView(current);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }

    public class ViewHolder{
        protected TextView tvWord;

        public ViewHolder(View view){
            this.tvWord = view.findViewById(R.id.isi_fragment_left);
        }

        public void updateView(final String word){
            this.tvWord.setText(word);
        }
    }
}
