package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.presenter.NewMenuPresenter;

public class NewMenuFragment extends Fragment implements View.OnClickListener, NewMenuPresenter.INewMenu {
    private FragmentListener fragmentListener;
    TextView menuName, menuDesc, menuBahan, menuLangkah, menuRestoran;
    Button addNew;
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

        this.newMenuPresenter = new NewMenuPresenter(this);

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
        this.newMenuPresenter.addNewMenu(this.menuName.getText().toString(), this.menuDesc.getText().toString());
    }


    @Override
    public void changePage() {
        fragmentListener.changePage(2);
    }
}
