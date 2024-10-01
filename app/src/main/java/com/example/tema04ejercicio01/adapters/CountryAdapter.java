package com.example.tema04ejercicio01.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tema04ejercicio01.R;
import com.example.tema04ejercicio01.modelos.Country;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private List<Country> countries;

    public CountryAdapter(@NonNull Context context, List<Country> countries) {
        super(context, R.layout.listitem_country, countries);
        this.countries = countries;
    }

    /**
     * Método encargado de crear y devolver la vista para cada elemento en la lista.
     *
     * @param position    Posición del elemento en la lista de datos.
     * @param convertView Vista reciclada o nula si es la primera vez que se crea.
     * @param parent      El padre al que la vista creada será adjuntada.
     * @return La vista correspondiente al elemento en la posición especificada.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CountryViewHolder holder;

        // Si la vista no existe, la inflamos y configuramos el ViewHolder
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listitem_country, parent, false);

            //Inicializar el holder y asignar las vistas:
            holder = new CountryViewHolder();
            holder.ivFlag = convertView.findViewById(R.id.ivFlag);
            holder.tvCountryName = convertView.findViewById(R.id.tvCountryName);
            holder.tvCapital = convertView.findViewById(R.id.tvCapital);
            holder.tvPopulation = convertView.findViewById(R.id.tvPopulation);

            convertView.setTag(holder);
        } else {
            // Recuperamos el ViewHolder de la vista reciclada
            holder = (CountryViewHolder) convertView.getTag();
        }

        // Obtenemos el país correspondiente a la posición actual
        Country country = countries.get(position);

        if (country != null) {
            // Asignamos los valores de los atributos del país a las vistas correspondientes
            holder.ivFlag.setImageResource(country.getFlagResource());
            holder.tvCountryName.setText(country.getCountryName());

            // Usamos recursos de strings para mostrar los textos formateados
            String capitalText = getContext().getString(R.string.tvCapital) + " " + country.getCapital();
            holder.tvCapital.setText(capitalText);

            String populationText = getContext().getString(R.string.tvPopulation) + " " + country.getPopulation();
            holder.tvPopulation.setText(populationText);
        }
        return convertView;
    }

    /**
     * ViewHolder utilizado para almacenar referencias a las vistas de un elemento
     * del ListView, optimizando el rendimiento al evitar llamadas repetidas a findViewById.
     */
    static class CountryViewHolder {
        private ImageView ivFlag;
        private TextView tvCountryName;
        private TextView tvCapital;
        private TextView tvPopulation;

    }
}
