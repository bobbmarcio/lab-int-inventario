public class Predio{
    String nome;
    Unidade unidade;

    public Predio(String nome, Unidade unidade) {
        this.nome = nome;
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public Unidade getUnidade() {
        return unidade;
    }
}