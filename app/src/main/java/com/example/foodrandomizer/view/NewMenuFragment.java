package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.R;
import com.example.foodrandomizer.presenter.NewMenuPresenter;

public class NewMenuFragment extends Fragment implements View.OnClickListener, NewMenuPresenter.INewMenu {
    private FragmentListener fragmentListener;
    EditText menuName, menuDesc, menuBahan, menuLangkah, menuRestoran;
    Button addNew;
    DBHandler db;
    NewMenuPresenter newMenuPresenter;
    public NewMenuFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_menu, container, false);
        this.menuName = view.findViewById(R.id.menu_name);
        this.menuDesc = view.findViewById(R.id.menu_desc);
        this.menuBahan = view.findViewById(R.id.menu_bahan);
        this.menuLangkah = view.findViewById(R.id.menu_langkah);
        this.menuRestoran = view.findViewById(R.id.menu_restoran);
        this.addNew = view.findViewById(R.id.add_new);

        this.db = new DBHandler(this.getContext());
        this.newMenuPresenter = new NewMenuPresenter(this, this.db);

        this.addNew.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        String name = this.menuName.getText().toString();
        String desc = this.menuDesc.getText().toString();
        String bahan = this.menuBahan.getText().toString();
        String langkah = this.menuLangkah.getText().toString();
        String restoran = this.menuRestoran.getText().toString();
        this.newMenuPresenter.addNewMenu(name, desc, bahan, langkah, restoran);
    }


    @Override
    public void changePage() {
        fragmentListener.changePage(2);
    }

    @Override
    public void clearForm() {
        this.menuName.setText("");
        this.menuDesc.setText("");
        this.menuBahan.setText("");
        this.menuLangkah.setText("");
        this.menuRestoran.setText("");
    }
}
