import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

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
    private Map<String,List<Encomenda>> encomendas;//map que mostra as encomendas a ser transportadas
    
    //Construtor por omissao
    public Transportador()
    {
        this.transporte = true;
        this.transporte_medico = false;
        this.classificacao = new HashMap<>();
        this.encomendas = new HashMap<>();
    }
    
    //Construtor por parametros
    public Transportador(String username,String password, Localizacao posicao, 
    boolean tp, boolean tp_medico, Map<String,Integer> clas, HashMap<String,List<Encomenda>> encomendas)
    {
        super(username, password, posicao);
        this.transporte = tp;
        this.transporte_medico = tp_medico;
        setClassificacoes(clas);
        setEncomendas(encomendas);
    }
    
    //Construtor copia
    public Transportador(Transportador trans)
    {
        this.transporte = trans.getTransporte();
        this.transporte_medico = trans.getTransporteMedico();
        setClassificacoes(trans.getClassificacoes());
        setEncomendas(trans.getEncomendas());
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

    public HashMap<String, List<Encomenda>> getEncomendas() {
        HashMap<String,List<Encomenda>> copia = new HashMap<>();
        for(Map.Entry<String,List<Encomenda>> edb: this.encomendas.entrySet()){
            copia.put(edb.getKey(),(edb.getValue()).stream().map(Encomenda::clone)
                    .collect(Collectors.toList()));
        }
        return copia;
    }

    public boolean getTransporte()
    {
        return this.transporte;
    }

    public boolean getTransporteMedico()
    {
        return this.transporte_medico;
    }
    
    //Sets
    public void setClassificacoes(Map<String,Integer> clas)
    {
        this.classificacao = new HashMap<>();
        clas.entrySet().forEach(e -> this.classificacao.put(e.getKey(), e.getValue()));
    }

    public void setEncomendas(HashMap<String,List<Encomenda>> edb){
        this.encomendas = new HashMap<>();
        edb.entrySet().forEach(e -> this.encomendas.put(e.getKey(),(e.getValue()).stream().map(Encomenda::clone)
                .collect(Collectors.toList())));
    }
    
    public void setTransporte(boolean tp)
    {
        this.transporte = tp;
    }
    
    public void setTransporteMedico(boolean tp_medico)
    {
        this.transporte_medico = tp_medico;
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

    public boolean aceitoTransportMedico(){
        return this.transporte_medico;
    }

    public void aceitaMedicamentos(boolean med){
        this.transporte_medico = med;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
        .append("\nTransporte disponivel: ").append(this.transporte)
        .append("\nTransporte médico: ").append(this.transporte_medico + "\n")
        .append(this.classificacao.toString()+"\n")
        .append(this.encomendas.toString()+"\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Voluntario v = (Voluntario) o;
        return this.getTransporte() == v.getTransporte() &&
                this.getTransporteMedico() == v.getTransporteMedico() &&
                this.getClassificacoes().equals(v.getClassificacoes()) &&
                this.getEncomendas().equals(v.getEncomendas());
    }

    public abstract Transportador clone();
}
