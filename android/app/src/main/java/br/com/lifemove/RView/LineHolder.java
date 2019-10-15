package br.com.lifemove.RView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import br.com.lifemove.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView nome, local, horario, data;
    public Button participar, dispensar;

    public LineHolder(View itemView) {
        super(itemView);
        nome = (TextView)itemView.findViewById(R.id.nome);
        local = (TextView)itemView.findViewById(R.id.local);
        horario = (TextView)itemView.findViewById(R.id.hora);
        data = (TextView)itemView.findViewById(R.id.data);

        participar = (Button) itemView.findViewById(R.id.participar);
        dispensar = (Button) itemView.findViewById(R.id.dispensar);
    }
}