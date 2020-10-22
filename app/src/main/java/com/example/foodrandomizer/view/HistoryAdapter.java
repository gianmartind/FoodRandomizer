package com.example.foodrandomizer.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private List<History> foodList;
    private Context fragment;

    public HistoryAdapter(Context fragment){
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

    public void updateList(List<History> newList){
        this.foodList = newList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(this.fragment).inflate(R.layout.history_item, viewGroup,false);
        HistoryAdapter.ViewHolder viewHolder = new HistoryAdapter.ViewHolder(view);;
        History current = (History) this.getItem(i);
        viewHolder.updateView(current);
        view.setTag(viewHolder);

        return view;
    }

    public class ViewHolder{
        protected TextView datetime;
        protected TextView menuName;

        public ViewHolder(View view){
            this.datetime = view.findViewById(R.id.datetime);
            this.menuName = view.findViewById(R.id.menu_name);
        }

        public void updateView(final History history){
            this.menuName.setText(history.getFoodName());
            this.datetime.setText(history.getDatetime());
        }
    }
}
