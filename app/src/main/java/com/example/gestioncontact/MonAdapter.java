package com.example.gestioncontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MonAdapter extends BaseAdapter implements Filterable {

    private Context con;
    private List<Contact> listAll = new ArrayList<>();
    MonAdapter(Context con){
        this.con = con;
        Iterator<Contact> it = AcceuilActivity.data.iterator();
        while(it.hasNext()) {
            listAll.add(it.next());
        }
    }
    @Override
    public int getCount() {
        return AcceuilActivity.data.size();
    }

    @Override
    public Object getItem(int position) {
        return AcceuilActivity.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(con);
        //parse code xml
        LinearLayout l = (LinearLayout) inf.inflate(R.layout.view_contact,null);
        TextView tvnom = l.findViewById(R.id.tvnom_view);
        TextView tvprenom = l.findViewById(R.id.tvprenom_view);
        TextView tvnum = l.findViewById(R.id.tvnum_view);
        Contact c = AcceuilActivity.data.get(position);
        tvnom.setText(c.nom);
        tvprenom.setText(c.prenom);
        tvnum.setText(c.numero);
        return l;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint.toString().isEmpty()){
                filterResults.values = listAll;
                filterResults.count = listAll.size();
            }else{
                List<Contact> listA = new ArrayList<Contact>();
                for(Contact c:listAll){
                    if(c.getNom().toLowerCase().contains(constraint.toString().toLowerCase())){
                        listA.add(c);
                    }
                }
                filterResults.values = listA;
                filterResults.count = listA.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            AcceuilActivity.data.clear();
            AcceuilActivity.data.addAll((Collection<? extends Contact>) results.values);
            notifyDataSetChanged();
        }
    };
    @Override
    public Filter getFilter() {
        return filter;
    }
}
