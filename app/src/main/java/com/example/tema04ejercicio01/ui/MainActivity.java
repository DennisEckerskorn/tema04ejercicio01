package com.example.tema04ejercicio01.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tema04ejercicio01.R;
import com.example.tema04ejercicio01.adapters.CountryAdapter;
import com.example.tema04ejercicio01.modelos.Country;
import com.example.tema04ejercicio01.modelos.DomParser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DomParser parser = new DomParser();
            parser.loadFile(this, R.raw.countries);
            List<Country> countries = parser.getCountries("country", this);
            CountryAdapter countryAdapter = new CountryAdapter(this, countries);
            ListView lvCountries = findViewById(R.id.lvCountries);
            lvCountries.setAdapter(countryAdapter);
        }catch(ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al cargar los datos de los países.", Toast.LENGTH_SHORT).show();
        }
    }
}