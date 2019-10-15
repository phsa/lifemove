package br.com.lifemove.RView;

public class Evento {
    String nome, local, hora, data;

    public Evento(String nome, String local, String hora, String data) {
        this.nome = nome;
        this.local = local;
        this.hora = hora;
        this.data = data;
    }

    public Evento(String nome, String local){
        this.nome = nome;
        this.local = local;
    }

    public Evento(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nome='" + nome + '\'' +
                ", local='" + local + '\'' +
                ", hora='" + hora + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
