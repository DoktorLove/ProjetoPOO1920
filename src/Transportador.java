import java.util.Map;
import java.util.HashMap;
/**
 * SuperClasse das classes transportadoras
 * 
 * @author Rui Cunha
 * @version 06/04/2020
 */
public abstract class Transportador extends User
{
    //Registo das Encomendas que trasnsportaram
    private boolean transporte;//Sinalizar disponibilidade para transporte
    private boolean transporte_medico; // aceitacao ou nao de transporte de produtos medicos
    private Map<String,Integer> classificacao;//classificacoes feitas pelos utilizadores
    
    //Construtor por omissao
    public Transportador()
    {
        this.transporte = true;
        this.transporte_medico = false;
        this.classificacao = new HashMap<>();
    }
    
    //Construtor por parametros
    public Transportador(String username,String password, Localizacao posicao, 
    boolean tp, boolean tp_medico, Map<String,Integer> clas)
    {
        super(username, password, posicao);
        this.transporte = tp;
        this.transporte_medico = tp_medico;
        setClassificacoes(clas);
    }
    
    //Construtor copia
    public Transportador(Transportador trans)
    {
        this.transporte = trans.getTransporte();
        this.transporte_medico = trans.getTransporteMedico();
        setClassificacoes(trans.getClassificacoes());
    }
    
    //Gets
    public Map<String,Integer> getClassificacoes()
    {
        Map<String,Integer> ret = new HashMap<>();
        for(Map.Entry<String,Integer> e : this.classificacao.entrySet())
        {
            ret.put(e.getKey(),e.getValue());
        }
        return ret;
    }
    
    //Sets
    public void setClassificacoes(Map<String,Integer> clas)
    {
        this.classificacao = new HashMap<>();
        clas.entrySet().forEach(e -> this.classificacao.put(e.getKey(), e.getValue()));
    }
    
    public void setTransporte(boolean tp)
    {
        this.transporte = tp;
    }
    
    public void setTransporteMedico(boolean tp_medico)
    {
        this.transporte_medico = tp_medico;
    }
    
    public boolean getTransporte()
    {
        return this.transporte;
    }
    
    public boolean getTransporteMedico()
    {
        return this.transporte_medico;
    }
    
    //Metodo que insere uma nova classificacao
    public void addClassificacao(String name, int classificacao)
    {
        this.classificacao.put(name,classificacao);
    }
    
    //Metodo que remove uma classificacao pelo nome do utilizador
    public void removeClassificacao(String name)
    {
        this.classificacao.remove(name);
    }
    
    //Metodo que mostra uma classificacao pelo nome do utilizador
    public int getClassificacao(String name)
    {
        return this.classificacao.get(name);
    }
    
    public abstract Transportador clone();
}
