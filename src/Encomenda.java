import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda {
    private String utilizador;
    private String loja;
    private LocalDateTime hora;
    private double peso;
    private List<String> produtos;
    private boolean medica;
    private boolean entregue;

    public Encomenda(){
        this.utilizador = "N/A";
        this.loja = "N/A";
        this.hora = LocalDateTime.now();
        this.peso = 0.0;
        this.produtos = new ArrayList<>();
        this.medica = false;
        this.entregue = false;
    }

    public Encomenda(String utilizador, String loja, LocalDateTime hora,
                     double peso, List<String> produtos, boolean medica, boolean entregue){
        this.utilizador = utilizador;
        this.loja = loja;
        this.hora = hora;
        this.peso = peso;
        setProdutos(produtos);
        this.medica = medica;
        this.entregue = entregue;
    }

    public Encomenda(Encomenda e){
        this.utilizador = e.getUtilizador();
        this.loja = e.getLoja();
        this.hora = e.getHora();
        this.peso = e.getPeso();
        setProdutos(e.getProdutos());
        this.medica = e.getMedica();
        this.entregue =e.getEntregue();
    }

    public String getUtilizador() {
        return this.utilizador;
    }

    public String getLoja() {
        return this.loja;
    }

    public LocalDateTime getHora() {
        return this.hora;
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

    public boolean getEntregue(){
        return this.entregue;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
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

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUtilizador: ").append(this.utilizador + "\n")
                .append("Loja: ").append(this.loja + "\n")
                .append("Hora: ").append(this.hora + "\n")
                .append("Peso : ").append(this.peso + "\n")
                .append("Produtos: ").append(this.produtos + "\n")
                .append("Contém material médico?: ").append(this.medica + "\n")
                .append("Já foi entregue?: ").append(this.entregue).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Encomenda e = (Encomenda) o;
        return this.getUtilizador().equals(e.getUtilizador()) &&
                this.getLoja().equals(e.getLoja()) &&
                this.getHora().equals(e.getHora()) &&
                this.getPeso() == e.getPeso() &&
                this.getProdutos().equals(e.getProdutos()) &&
                this.getMedica() == e.getMedica() &&
                this.getEntregue() == e.getEntregue();
    }
}
