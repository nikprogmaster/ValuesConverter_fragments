package com.example.valuesconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ConverterFragment extends Fragment {

    private static String ITEM_EXTRA_KEY = "item_key";
    private TextView to_tw, from_tw;
    private Spinner from, to;
    private SpinnerAdapter fromAdapter, toAdapter;
    private String from_str_selected, to_str_selected, convertion_result;
    private EditText editText_from, editText_to;
    private ConverterFragment converterFragment;
    private Values value;
    private View root;

    private ArrayList<String> values;

    public ConverterFragment newInstance(Values value){
        Bundle args = new Bundle();
        this.value = value;
        args.putString(ITEM_EXTRA_KEY, this.value.toString());
        converterFragment = new ConverterFragment();
        converterFragment.setArguments(args);
        return converterFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        values = new ArrayList<>();

        value = Values.searchByString(getArguments().getString(ITEM_EXTRA_KEY));
        if (value.getUnits() != null){
            List<Unit> list = value.getUnits();
            for (int i=0; i<list.size(); i++) {
                values.add(getString(list.get(i).getLabelRecourse()));
            }
        }

        initSpinner(root);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_value, container, false);
        from_tw = root.findViewById(R.id.textView_from);
        to_tw = root.findViewById(R.id.textView_to);
        editText_from = root.findViewById(R.id.editText_from);
        editText_to = root.findViewById(R.id.editText_to);

        editText_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculate();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
        return root;
    }

    private void initSpinner (View root){
        from = root.findViewById(R.id.spinner_from);
        to = root.findViewById(R.id.spinner_to);
        fromAdapter = new SpinnerAdapter(values);
        from.setAdapter(fromAdapter);
        toAdapter = new SpinnerAdapter(values);
        to.setAdapter(toAdapter);
        from_str_selected = fromAdapter.getItem(0);
        to_str_selected = toAdapter.getItem(0);

        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from_tw.setText(fromAdapter.getItem(i));
                from_str_selected = fromAdapter.getItem(i);
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to_tw.setText(values.get(i));
                to_str_selected = toAdapter.getItem(i);
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void calculate(){
        if (editText_from.getText().toString().length()!=0) {
            double val = Double.valueOf(editText_from.getText().toString());
            double result = value.calculate(getContext(), from_str_selected, to_str_selected, val);
            convertion_result = String.valueOf(result);
            editText_to.setText(convertion_result);
        }
    }

}
