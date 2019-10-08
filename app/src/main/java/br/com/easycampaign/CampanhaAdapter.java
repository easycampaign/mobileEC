package br.com.easycampaign;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import br.com.easycampaign.model.Campanha;

public class CampanhaAdapter extends ArrayAdapter<Campanha>{

    private Activity context;
    private List<Campanha> campanhaList;

    public CampanhaAdapter(Activity context, List<Campanha> campanhaList){
        super(context, R.layout.list_layout, campanhaList);
        this.context = context;
        this.campanhaList = campanhaList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView txtCampanha = listViewItem.findViewById(R.id.txtNome);
        TextView txtProduto = listViewItem.findViewById(R.id.txtProduto);

        Campanha campanha = campanhaList.get(position);

        txtCampanha.setText(campanha.getCampanhaNome());
        txtProduto.setText(campanha.getCampanhaProduto());
        return listViewItem;
    }
}
