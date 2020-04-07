import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda {
    private String utilizador;
    private String loja;
    private double peso;
    private List<String> produtos;
    private boolean medica;

    public Encomenda(){
        this.utilizador = "N/A";
        this.loja = "N/A";
        this.peso = 0.0;
        this.produtos = new ArrayList<>();
        this.medica = false;
    }

    public Encomenda(String utilizador, String loja,
                     double peso, List<String> produtos, boolean medica){
        this.utilizador = utilizador;
        this.loja = loja;
        this.peso = peso;
        setProdutos(produtos);
        this.medica = medica;
    }

    public Encomenda(Encomenda e){
        this.utilizador = e.getUtilizador();
        this.loja = e.getLoja();
        this.peso = e.getPeso();
        setProdutos(e.getProdutos());
        this.medica = e.getMedica();
    }

    public String getUtilizador() {
        return this.utilizador;
    }

    public String getLoja() {
        return this.loja;
    }

    public double getPeso() {
        return this.peso;
    }

    public List<String> getProdutos() {
        return new ArrayList<>(this.produtos);
    }

    public boolean getMedica(){
        return this.medica;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = new ArrayList<>(produtos);
    }

    public void setMedica(boolean medica) {
        this.medica = medica;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador: ").append(this.utilizador + "\n")
                .append("Loja: ").append(this.loja + "\n")
                .append("Peso : ").append(this.peso + "\n")
                .append("Produtos: ").append(this.produtos + "\n")
                .append("Contém material médico?: ").append(this.medica).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return this.getUtilizador().equals(e.getUtilizador()) &&
                this.getLoja().equals(e.getLoja()) &&
                this.getPeso() == e.getPeso() &&
                this.getProdutos().equals(e.getProdutos()) &&
                this.getMedica() == e.getMedica();
    }
}
