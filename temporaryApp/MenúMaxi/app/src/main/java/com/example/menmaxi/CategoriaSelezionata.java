package com.example.menmaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoriaSelezionata extends AppCompatActivity {

    ListView listViewContatti;
    static ArrayList<Piatto> piatti = new ArrayList<>();
    PiattiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_selezionata);
        listViewContatti = findViewById(R.id.listview);
    }


}
