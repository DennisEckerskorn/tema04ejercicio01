package com.example.tema04ejercicio01.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    /**
     * Método llamado cuando se crea la actividad.
     * Se encarga de inicializar la interfaz de usuario y cargar los datos de los países
     * desde un archivo XML utilizando DomParser.
     *
     * @param savedInstanceState Si la actividad se está recreando, este parámetro contiene el estado guardado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Cargar y analizar el archivo XML con los países
            DomParser parser = new DomParser();
            parser.loadFile(this, R.raw.countries);

            // Obtener la lista de países desde el parser
            List<Country> countries = parser.getCountries("country", this);

            // Crear el adaptador para los países y asociarlo al ListView
            CountryAdapter countryAdapter = new CountryAdapter(this, countries);
            ListView lvCountries = findViewById(R.id.lvCountries);

            lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this, countries.get(i).getCountryName(), Toast.LENGTH_SHORT).show();

                }
            });

            lvCountries.setAdapter(countryAdapter);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            // Manejo de excepciones en caso de error al cargar los datos
            e.printStackTrace();
            Toast.makeText(this, "Error al cargar los datos de los países.", Toast.LENGTH_SHORT).show();
        }
    }
}