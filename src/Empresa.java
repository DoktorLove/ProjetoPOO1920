import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Classe que tratas das empresas de transporte
 * 
 * @author Rui Cunha
 * @version 06/04/2020
 */
public class Empresa extends Transportador
{
    private double custo_km; //taxa por km percorrido durante entrega
    private double custo_peso;

    //Construtor vazio
    public Empresa()
    {
        super();
        this.custo_km = 0.0;
        this.custo_peso = 0.0;
    }
    
    //Construtor por parametros
    public Empresa(String username, String password, Localizacao posicao,
                   boolean transport, boolean transporte_medico,
                   Map<String,Integer> classificacao, HashMap<String, List<Encomenda>> encomendas,
                   double custo_km, double custo_peso)
    {
        super(username,password,posicao,transport,transporte_medico,classificacao,encomendas);
        this.custo_km = custo_km;
        this.custo_peso = custo_peso;
    }
    
    //Construtor copia
    public Empresa(Empresa emp)
    {
        super(emp);
        this.custo_km = emp.getCusto_Km();
        this.custo_peso = emp.getCusto_Peso();
    }
    
    //Getters
    public double getCusto_Km()
    {
        return this.custo_km;
    }

    public double getCusto_Peso() {
        return this.custo_peso;
    }

    //Setters
    public void setCusto_Km(double custo)
    {
        this.custo_km = custo;
    }

    public void setCusto_peso(double custo_peso) {
        this.custo_peso = custo_peso;
    }

    //Metodo toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("Custo_Km : ").append(this.custo_km + "\n")
        .append("Custo por peso:").append(this.custo_peso + "\n");
        return sb.toString();
    }
    
    //Metodo Equals
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o==null) || (this.getClass() != o.getClass()))
            return false;
        
        Empresa p = (Empresa) o;
        return(super.equals(p) &&
                this.custo_km == p.getCusto_Km() &&
                this.custo_peso == p.getCusto_Peso());
    }
    
    public Empresa clone()
    {
        return new Empresa(this);
    }
}
