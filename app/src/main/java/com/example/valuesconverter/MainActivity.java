
package com.example.valuesconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ValuesListFragment.ValuesHolder {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_host, new ValuesListFragment())
                .commit();

    }


    @Override
    public void showValueConverter(Values value) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_host, new ConverterFragment().newInstance(value))
                .commit();
    }
}
