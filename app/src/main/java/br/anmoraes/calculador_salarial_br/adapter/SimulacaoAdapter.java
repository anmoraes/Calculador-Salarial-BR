package br.anmoraes.calculador_salarial_br.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.model.Simulacao;

public class SimulacaoAdapter extends ArrayAdapter<Simulacao> {

    private final Context context;
    private final ArrayList<Simulacao> elementos;

    public SimulacaoAdapter(Context context, ArrayList<Simulacao> elementos) {

        super(context, R.layout.linha_lista, elementos);
        this.context = context;
        this.elementos = elementos;
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_lista, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.edtNome);
        TextView salarioB = (TextView) rowView.findViewById(R.id.edtSalarioB);
        TextView txtValorINSS = (TextView) rowView.findViewById(R.id.txtValorINSS);
        TextView txtValorIRPF = (TextView) rowView.findViewById(R.id.txtValorIRPF);
        TextView txtPensao = (TextView) rowView.findViewById(R.id.txtPensao);
        TextView txtOutros = (TextView) rowView.findViewById(R.id.txtOutros);
        TextView txtBase = (TextView) rowView.findViewById(R.id.txtBase);
        TextView txtFDepositado = (TextView) rowView.findViewById(R.id.txtFDepositado);
        TextView salarioL = (TextView) rowView.findViewById(R.id.edtSalarioL);


        nome.setText(elementos.get(position).getNomeCadastro());
        salarioB.setText("Salário Bruto = " +Double.toString(elementos.get(position).getSalariobruto()));
        txtValorINSS.setText("Valor INSS = " +Double.toString(elementos.get(position).getValorINSS()));
        txtValorIRPF.setText("Valor IFPF = " +Double.toString(elementos.get(position).getValorIRPF()));
        txtPensao.setText("Desconto de Pensão = " +Double.toString(elementos.get(position).getPensaoAlimenticia()));
        txtOutros.setText("Outros Descontos = " +Double.toString(elementos.get(position).getOutrosDescontos()));
        txtBase.setText("Salario Base IRPF = " +Double.toString(elementos.get(position).getBaseIRPF()));
        txtFDepositado.setText("FGTS Depositado = " +Double.toString(elementos.get(position).getFgtsDespositado()));
        salarioL.setText("Salário Líquido = " +Double.toString(elementos.get(position).getSalarioLiquido()));


        return rowView;
    }
}

