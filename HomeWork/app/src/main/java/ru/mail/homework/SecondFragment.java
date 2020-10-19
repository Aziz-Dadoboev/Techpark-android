package ru.mail.homework;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class SecondFragment extends Fragment {
    String number;
    int color;

    public void Params(String Num, int Col) {
        number = Num;
        color = Col;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                              Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment, container, false);
        TextView text = v.findViewById(R.id.chosen_num);
        System.out.println("Error in textview");
        text.setText(number);
        text.setTextColor(color);
        return v;
    }
}
