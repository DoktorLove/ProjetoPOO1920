import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe que trata das Loja que produzem as encomendas
 * 
 * @author Rui Cunha
 * @version 06/04/2020
 */
public abstract class Loja extends User
{
    private Map<String,List<Encomenda>> encomendas;//encomendas prontas a ser entregues  
    
    //Construtor vazio
    public Loja()
    {
        super();
        this.encomendas = new HashMap<>();
    }
    
    //Construtor por parametros
    public Loja(String username, String nome, Localizacao local,HashMap<String,List<Encomenda>> encomendas)
    {
        super(username,nome,local);
        setEncomendas(encomendas);
    }
    
    //Construtor copia
    public Loja(Loja novaloja)
    {
        super(novaloja);
        setEncomendas(novaloja.getEncomendas());
    }
    
    //Getters
    public HashMap<String, List<Encomenda>> getEncomendas() {
        HashMap<String,List<Encomenda>> copia = new HashMap<>();
        for(Map.Entry<String,List<Encomenda>> edb: this.encomendas.entrySet()){
            copia.put(edb.getKey(),(edb.getValue()).stream().map(Encomenda::clone)
                    .collect(Collectors.toList()));
        }
        return copia;
    }
    
    //Setters
    public void setEncomendas(HashMap<String,List<Encomenda>> edb){
        this.encomendas = new HashMap<>();
        edb.entrySet().forEach(e -> this.encomendas.put(e.getKey(),(e.getValue()).stream().map(Encomenda::clone)
                .collect(Collectors.toList())));
    }
    
    //Clone
    public abstract Loja clone();
    
    //toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("Encomendas : ").append(this.encomendas.toString() + "\n");
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
        return(super.equals(p) && this.getEncomendas().equals(p.getEncomendas()));
    }
}
