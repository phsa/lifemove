package br.com.lifemove.RView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.lifemove.R;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<Evento> eventoLis;

    public LineAdapter(ArrayList evento) {
        eventoLis = evento;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.telaevento, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {

        holder.nome.setText(eventoLis.get(position).getNome());
        holder.local.setText(eventoLis.get(position).getLocal());
        holder.horario.setText(eventoLis.get(position).getHora());
        holder.data.setText(eventoLis.get(position).getData());


        //holder.moreButton.setOnClickListener(view -> updateItem(position));
        //holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return eventoLis != null ? eventoLis.size() : 0;
    }

    public void updateList(Evento evento) {
        insertItem(evento);
    }

    public void insertItem(Evento evento) {
        eventoLis.add(evento);
        notifyItemInserted(getItemCount());
    }

    private void updateItem(int position) {
        Evento evento = eventoLis.get(position);
        notifyItemChanged(position);
    }

    //private void removerItem(int position) {
     //   UserModel userModel = mUsers.get(position);
      //  mUsers.remove(userModel);
       // notifyDataSetChanged();
    //}
}