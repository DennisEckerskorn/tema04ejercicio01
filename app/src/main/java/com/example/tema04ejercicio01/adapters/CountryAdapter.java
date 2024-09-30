package com.example.tema04ejercicio01.adapters;

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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CountryViewHolder holder;

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
            holder = (CountryViewHolder) convertView.getTag();
        }

        Country country = countries.get(position);

        if (country != null) {
            holder.ivFlag.setImageResource(country.getFlagResource());
            holder.tvCountryName.setText(country.getCountryName());
            holder.tvCapital.setText(country.getCapital());
            holder.tvPopulation.setText(String.valueOf(country.getPopulation()));

            /**TODO:
             *  Aquí utilizamos la cadena desde los recursos
             *  String populationText = getContext().getString(R.string.population_label, country.getPopulation());
             *  holder.tvPopulation.setText(populationText);
             */

        }
        return convertView;
    }

    static class CountryViewHolder {
        private ImageView ivFlag;
        private TextView tvCountryName;
        private TextView tvCapital;
        private TextView tvPopulation;

    }
}
