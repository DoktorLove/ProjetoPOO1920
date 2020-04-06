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
    
    //Construtor vazio
    public Empresa()
    {
        super();
        this.custo_km = 0.0;
    }
    
    //Construtor por parametros
    public Empresa(String username, String password, Localizacao posicao,
    boolean transport, boolean transporte_medico, Map<String,Integer> classificacao,double custo_km)
    {
        super(username,password,posicao,transport,transporte_medico,classificacao);
        this.custo_km = custo_km;
    }
    
    //Construtor copia
    public Empresa(Empresa emp)
    {
        super(emp);
        this.custo_km = emp.getCusto_Km();
    }
    
    //Getters
    public double getCusto_Km()
    {
        return this.custo_km;
    }
    
    //Setters
    public void setCusto_Km(double custo)
    {
        this.custo_km = custo;
    }
    
    //Metodo toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("Custo_Km : ").append(this.custo_km + "\n");
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
                this.custo_km == p.getCusto_Km());
    }
    
    public Empresa clone()
    {
        return new Empresa(this);
    }
}
