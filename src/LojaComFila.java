import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe que trata das Lojas com Fila de Espera
 * 
 * @author Rui Cunha
 * @version 13/04/2020
 */
public class LojaComFila extends Loja
{
    private List<Utilizador> fila_espera;//fila de espera
    
    //Construtor vazio
    public LojaComFila()
    {
        super();
        this.fila_espera = new ArrayList<>();
    }
    
    //Construtor por parametros
    public LojaComFila(String username, String nome, Localizacao local,
    HashMap<String,List<Encomenda>> encomendas,List<Utilizador> fila_espera)
    {
        super(username,nome,local,encomendas);
        this.setFila(fila_espera);
    }
    
    //Construtor por copia
    public LojaComFila(LojaComFila loj)
    {
        super(loj);
        this.setFila(loj.getFila());
    }
    
    //Getters
    public List<Utilizador> getFila()
    {
        List<Utilizador> res = new ArrayList<>();
        this.fila_espera.stream()
            .forEach(e -> res.add(e.clone()));
        return res;
    }
    
    //Setters
    public void setFila(List<Utilizador> fil)
    {
        this.fila_espera = new ArrayList<>();
        for(Utilizador u : fil)
        {
            this.fila_espera.add(u.clone());
        }
    }
    
    //Metodo toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("Fila de Espera: ").append(this.fila_espera.toString());
        return sb.toString();
    }
    
    //Metodo Equals
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o==null) || (this.getClass() != o.getClass()))
            return false;
            
        LojaComFila p = (LojaComFila) o;
        return(super.equals(p) &&
            this.fila_espera.equals(p.getFila()));
    }
    
    //Clone
    public LojaComFila clone()
    {
        return new LojaComFila(this);
    }
}
