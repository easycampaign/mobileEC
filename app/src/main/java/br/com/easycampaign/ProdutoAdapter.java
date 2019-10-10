package br.com.easycampaign;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.com.easycampaign.model.Produto;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    private Activity context;
    private List<Produto> produtoList;

    public ProdutoAdapter(Activity context, List<Produto> produtoList) {
        super(context, R.layout.list_layout, produtoList);
        this.context = context;
        this.produtoList = produtoList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView txtProduto = listViewItem.findViewById(R.id.txtItem);
        TextView txtQtd = listViewItem.findViewById(R.id.txtSubItem);

        Produto produto = produtoList.get(position);

        txtProduto.setText(produto.getProdutoNome());
        txtQtd.setText(produto.getProdutoQtd());
        return listViewItem;
    }
}
