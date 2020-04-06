import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Classe que trata das Loja que produzem as encomendas
 * 
 * @author Rui Cunha
 * @version 06/04/2020
 */
public class Loja extends User
{
    //Lista de encomendas ja prontas a ser entregues
    //private Map<String,Encomenda> encomendas_prontas;
    //Possivel Indicar pessoas na fila ???
    //private boolean existe_fila ???
    //Pessoas na fila de espera?
    private List<Utilizador> fila_espera;
    
    //Construtor vazio
    public Loja()
    {
        super();
        this.fila_espera = new ArrayList<>();
    }
    
    //Construtor por parametros
    public Loja(String username, String password, Localizacao local, List<Utilizador> fila_espera)
    {
        super(username,password,local);
        this.setFilaEspera(fila_espera);
    }
    
    //Construtor copia
    public Loja(Loja novaloja)
    {
        super(novaloja);
        this.setFilaEspera(novaloja.getFilaEspera());
    }
    
    //Getters
    public List<Utilizador> getFilaEspera()
    {
        List<Utilizador> res = new ArrayList<>();
        this.fila_espera.stream().forEach(e -> res.add(e.clone()));
        return res;
    }
    
    //Setters
    public void setFilaEspera(List<Utilizador> fila)
    {
        this.fila_espera = new ArrayList<>();
        for(Utilizador util : fila)
        {
            this.fila_espera.add(util.clone());
        }
    }
    
    //Clone
    public Loja clone()
    {
        return new Loja(this);
    }
    
    //toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("Fila Espera : ").append(this.fila_espera + "\n");
        return sb.toString();
    }
    
    //Equals
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o==null) || (this.getClass() != o.getClass()))
            return false;
            
        Loja p = (Loja) o;
        return(super.equals(p) && this.fila_espera.equals(p.getFilaEspera()));
    }
}
