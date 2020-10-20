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
    Button nameEdit, descEdit, bahanEdit, langkahEdit, restoranEdit;
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
        this.save = view.findViewById(R.id.save);

        this.nameEdit = view.findViewById(R.id.menu_name_edit);
        this.descEdit = view.findViewById(R.id.menu_desc_edit);
        this.bahanEdit = view.findViewById(R.id.menu_bahan_edit);
        this.langkahEdit = view.findViewById(R.id.menu_langkah_edit);
        this.restoranEdit = view.findViewById(R.id.menu_restoran_edit);

        this.delete.setOnClickListener(this);
        this.save.setOnClickListener(this);
        this.nameEdit.setOnClickListener(this);
        this.descEdit.setOnClickListener(this);
        this.bahanEdit.setOnClickListener(this);
        this.langkahEdit.setOnClickListener(this);
        this.restoranEdit.setOnClickListener(this);

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
        this.menuBahan.setText(item.getBahan());
        this.menuLangkah.setText(item.getLangkah());
        this.menuRestoran.setText(item.getRestoran());
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
        else if(view == this.save){
            String name = this.menuName.getText().toString();
            String desc = this.menuDesc.getText().toString();
            String bahan = this.menuBahan.getText().toString();
            String langkah = this.menuLangkah.getText().toString();
            String restoran = this.menuRestoran.getText().toString();
            this.menuDetailsPresenter.saveRecord(name, desc, bahan, langkah, restoran);
        }

        else if(view == this.nameEdit){
            this.menuName.setFocusableInTouchMode(true);
            this.menuName.setFocusable(true);
            this.menuName.requestFocus();
        }
        else if(view == this.descEdit){
            this.menuDesc.setFocusableInTouchMode(true);
            this.menuDesc.setFocusable(true);
            this.menuDesc.requestFocus();
        }
        else if(view == this.bahanEdit){
            this.menuBahan.setFocusableInTouchMode(true);
            this.menuBahan.setFocusable(true);
            this.menuBahan.requestFocus();
        }
        else if(view == this.langkahEdit){
            this.menuLangkah.setFocusableInTouchMode(true);
            this.menuLangkah.setFocusable(true);
            this.menuLangkah.requestFocus();
        }
        else if(view == this.restoranEdit){
            this.menuRestoran.setFocusableInTouchMode(true);
            this.menuRestoran.setFocusable(true);
            this.menuRestoran.requestFocus();
        }
    }
}
