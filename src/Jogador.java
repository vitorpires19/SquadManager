public class Jogador {
    private String nome;
    private int idade;
    private String posicao;
    private int overall;

    public Jogador(String nome, int idade, String posicao, int overall) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.overall = overall;


    }

    public String getNome() {
        return nome;
    }
    public int getIdade(){
        return idade;
    }
    public String getPosicao() {
        return posicao;
    }
    public int getOverall() {
        return overall;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    public void setOverall(int overall) {
        this.overall = overall;
    }
}
