package br.anmoraes.calculador_salarial_br.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.anmoraes.calculador_salarial_br.R;
import br.anmoraes.calculador_salarial_br.model.Simulacao;

public class SimulacaoAdapter extends ArrayAdapter<Simulacao> {

    private final Context context;
    private final ArrayList<Simulacao> elementos;

    private double salarioBruto, descontoINSS, descontoIRPF,
            descontoPensao, outrosDescontos, baseIRPF,
            fgtsValor, salarioLiquido;

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
        TextView nome = rowView.findViewById(R.id.edtNome);
        TextView salarioB = rowView.findViewById(R.id.edtSalarioB);
        TextView txtValorINSS = rowView.findViewById(R.id.txtValorINSS);
        TextView txtValorIRPF = rowView.findViewById(R.id.txtValorIRPF);
        TextView txtPensao = rowView.findViewById(R.id.txtPensao);
        TextView txtOutros = rowView.findViewById(R.id.txtOutros);
        TextView txtBase = rowView.findViewById(R.id.txtBase);
        TextView txtFDepositado = rowView.findViewById(R.id.txtFDepositado);
        TextView salarioL = rowView.findViewById(R.id.edtSalarioL);

        nome.setText(elementos.get(position).getNomeCadastro());

        DecimalFormat decimal = new DecimalFormat("0.00");

        salarioBruto = elementos.get(position).getSalariobruto();
        String sBruto = decimal.format(salarioBruto);
        salarioB.setText("Salário Bruto = R$ " +sBruto);

        descontoINSS = elementos.get(position).getValorINSS();
        String dINSS = decimal.format(descontoINSS);
        txtValorINSS.setText("Valor INSS = R$ " +dINSS);

        descontoIRPF = elementos.get(position).getValorIRPF();
        String dIRPF = decimal.format(descontoIRPF);
        txtValorIRPF.setText("Valor IFPF = R$ " +dIRPF);

        descontoPensao = elementos.get(position).getPensaoAlimenticia();
        String dPensao = decimal.format(descontoPensao);
        txtPensao.setText("Desconto de Pensão = R$ " +dPensao);

        outrosDescontos = elementos.get(position).getOutrosDescontos();
        String oDescontos = decimal.format(outrosDescontos);
        txtOutros.setText("Outros Descontos = R$ " +oDescontos);

        baseIRPF = elementos.get(position).getBaseIRPF();
        String bIRPF = decimal.format(baseIRPF);
        txtBase.setText("Salario Base IRPF = R$ " +bIRPF);

        fgtsValor = elementos.get(position).getFgtsDespositado();
        String fValor = decimal.format(fgtsValor);
        txtFDepositado.setText("FGTS Depositado = R$ " +fValor);

        salarioLiquido = elementos.get(position).getSalarioLiquido();
        String sLiquido = decimal.format(salarioLiquido);
        salarioL.setText("Salário Líquido = R$ " +sLiquido);

        return rowView;
    }
}

