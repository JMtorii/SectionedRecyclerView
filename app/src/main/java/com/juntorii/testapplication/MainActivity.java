package com.juntorii.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    private BestRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new BestRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.bindData(generateMockData());
    }

    private List<SectionDataModel> generateMockData() {
        List<SectionDataModel> data = new ArrayList<>();
        data.add(new SectionDataModel(
                "People that I hate",
                Arrays.asList(
                        new Person("Donald Trump", 71),
                        new Person("Kim Kardashian", 37),
                        new Person("Squidward Tentacles", 20)
                )
        ));
        data.add(new SectionDataModel(
                "People that inspire me",
                new ArrayList<Person>()
        ));
        data.add(new SectionDataModel(
                "Rush Hour cast that matter",
                Arrays.asList(
                        new Person("Jackie Chan", 63),
                        new Person("Chris Tucket", 46)
                )
        ));
        data.add(new SectionDataModel(
                "People that scare me",
                Arrays.asList(
                        new Person("Stewie Griffin", 1),
                        new Person("Kim Jung Un", 10),
                        new Person("Leela", 25),
                        new Person("Bob the Builder", 5),
                        new Person("Dora the Explorer", 4),
                        new Person("Ross Geller", 31)
                    )
        ));
        return data;
    }
}
