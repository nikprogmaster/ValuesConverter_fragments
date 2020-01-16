package com.example.valuesconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ValuesListFragment extends Fragment {

    private RecyclerView valuesRecycler;
    private ValuesAdapter adapter;

    public ValuesListFragment(){ super(R.layout.activity_main);}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        valuesRecycler = root.findViewById(R.id.recyclerr);
        valuesRecycler.setLayoutManager(new LinearLayoutManager(root.getContext()));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ValuesAdapter(new ValuesAdapter.OnValueClickListener() {
            @Override
            public void OnValueClick(Values value) {
                FragmentActivity activity = getActivity();
                if (activity instanceof ValuesHolder) {
                    ((ValuesHolder) activity).showValueConverter(value);
                }
            }
        });
        valuesRecycler.setAdapter(adapter);
        List<Values> vals = Arrays.asList(Values.values());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getContext(),
                DividerItemDecoration.VERTICAL);
        valuesRecycler.addItemDecoration(dividerItemDecoration);
        adapter.setValues(vals);
    }

    public interface ValuesHolder {
        void showValueConverter(Values value);
    }

}
