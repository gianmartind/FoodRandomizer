package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.presenter.MenuDetailsPresenter;

public class MenuDetailsFragment extends Fragment implements MenuDetailsPresenter.IMenuDetails, View.OnClickListener {
    FragmentListener fragmentListener;
    DBHandler db;
    EditText menuName, menuDesc, menuBahan, menuLangkah, menuRestoran;
    Button save, delete;
    MenuDetailsPresenter menuDetailsPresenter;

    public MenuDetailsFragment(){};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_details, container, false);
        int id = this.getArguments().getInt("id", 0);
        this.db = new DBHandler(this.getContext());
        this.menuDetailsPresenter = new MenuDetailsPresenter(id, this.db, this);
        this.menuName = view.findViewById(R.id.menu_name);
        this.menuDesc = view.findViewById(R.id.menu_desc);
        this.menuBahan = view.findViewById(R.id.menu_bahan);
        this.menuLangkah = view.findViewById(R.id.menu_langkah);
        this.menuRestoran = view.findViewById(R.id.menu_restoran);
        this.delete = view.findViewById(R.id.delete);

        this.delete.setOnClickListener(this);
        this.menuDetailsPresenter.readData();
        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        } else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    public static MenuDetailsFragment newInstance(int id){
        MenuDetailsFragment fragment = new MenuDetailsFragment();

        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void writeData(Food item) {
        this.menuName.setText(item.getName());
        this.menuDesc.setText(item.getDesc());
        String[] bahanArray = item.getBahanArray();
        String bahan = "";
        for(int i = 0; i < bahanArray.length; i++){
            bahan += bahanArray[i] + "\n";
        }
        this.menuBahan.setText(bahan);

        String[] langkahArray = item.getLangkahArray();
        String langkah = "";
        for(int i = 0; i < langkahArray.length; i++){
            langkah += langkahArray[i] + "\n";
        }
        this.menuLangkah.setText(langkah);

        String[] restoranArray = item.getRestoranArray();
        String restoran = "";
        for(int i = 0; i < restoranArray.length; i++){
            restoran += restoranArray[i] + "\n";
        }
        this.menuRestoran.setText(restoran);
    }

    @Override
    public void changePage() {
        fragmentListener.changePage(2);
    }

    @Override
    public void onClick(View view) {
        if(view == this.delete){
            this.menuDetailsPresenter.deleteRecord();
        }
    }
}
