public class Competicao {
    private String temporada;
    private String nome;
    private String resultado;


    public Competicao(String temporada, String nome, String resultado) {
        this.temporada = temporada;
        this.nome = nome;
        this.resultado = resultado;

    }
    public String getTemporada() {
        return temporada;
    }
    public String getNome() {
        return nome;
    }
    public String getResultado() {
        return resultado;
    }
}

