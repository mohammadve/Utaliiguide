package com.utaliiguides.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.utaliiguides.R;
import com.utaliiguides.models.countryList.CountryListData;

import java.util.ArrayList;

public class CountryListAdapterNew extends BaseAdapter {

    private Context mContext;
    private ArrayList<CountryListData> mCountryList;
    private LayoutInflater mInflater;

    public CountryListAdapterNew(Context context, ArrayList<CountryListData> list)
    {
        this.mContext = context;
        this.mCountryList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCountryList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCountryList.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_custom_spinner, parent, false);
            holder = new ViewHolder();
            holder.tv_spinnerText = (TextView) convertView.findViewById(R.id.tv_spinnerTextNew);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_spinnerText.setText(mCountryList.get(position).getName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_row_spinner_drop_down_window, parent, false);
            holder = new ViewHolder();

            holder.tv_spinnerDropdownText = (TextView) convertView.findViewById(R.id.tv_spinnerDropdownText_new);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_spinnerDropdownText.setText(mCountryList.get(position).getName());

        //holder.mRadioButton.setOnCheckedChangeListener(new Vi);

        return convertView;
    }

    static class ViewHolder {
        TextView tv_spinnerText, tv_spinnerDropdownText;
        RadioButton mRadioButton;
    }
}
