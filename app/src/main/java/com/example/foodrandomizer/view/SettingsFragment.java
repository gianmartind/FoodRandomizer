package com.example.foodrandomizer.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.presenter.SettingsPagePresenter;

public class SettingsFragment extends Fragment implements View.OnClickListener, SettingsPagePresenter.ISettingsPage {
    RadioGroup darkMode;
    RadioButton darkYes, darkNo, darkDefault;
    Button save;
    FragmentListener fragmentListener;
    SettingsPagePresenter settingsPagePresenter;
    public SettingsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_page, container, false);

        this.darkMode = view.findViewById(R.id.dark_mode);
        this.darkYes = view.findViewById(R.id.dark_yes);
        this.darkNo = view.findViewById(R.id.dark_no);
        this.darkDefault = view.findViewById(R.id.dark_default);
        this.save = view.findViewById(R.id.save);

        this.settingsPagePresenter = new SettingsPagePresenter(this, this.getActivity());
        this.save.setOnClickListener(this);

        this.settingsPagePresenter.loadSettings();
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
        if(this.darkNo.isChecked()){
            this.settingsPagePresenter.saveSettings(0);
        } else if(this.darkYes.isChecked()){
            this.settingsPagePresenter.saveSettings(1);
        } else if(this.darkDefault.isChecked()){
            this.settingsPagePresenter.saveSettings(2);
        }
    }

    @Override
    public void setDarkModeButton(int i) {
        if(i == 0){
            this.darkNo.setChecked(true);
        } else if(i == 1){
            this.darkYes.setChecked(true);
        } else if(i == 2){
            this.darkDefault.setChecked(true);
        }
    }

    @Override
    public void changePage() {
        this.fragmentListener.changePage(0);
    }

    @Override
    public void loadSettings() {
        this.fragmentListener.loadSettings();
    }
}
