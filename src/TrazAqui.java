import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrazAqui {

    private String nome;

    private Map<String, User> users;
    private Map<String, Encomenda> encomendas;
    private List<String> aceites;

    public  TrazAqui(){
        this.nome = "TrazAqui";
        this.users = new HashMap<String, User>();
        this.encomendas = new HashMap<String, Encomenda>();
        this.aceites = new ArrayList<String>();
    }

    public TrazAqui(TrazAqui t){
        this.nome = t.getNome();
        this.users = t.getUsers();
        this.encomendas = t.getEncomendas();
        this.aceites = t.getAceites();
    }

    public TrazAqui(String nome, Map<String, User> users,
                    Map<String,Encomenda> encomendas,
                    List<String> aceites){
        this.nome = nome;
        this.users = new HashMap<String, User>();
        this.encomendas = new HashMap<String, Encomenda>();
        this.aceites = new ArrayList<String>();
        setUsers(users);
        setEncomendas(encomendas);
        setAceites(aceites);
    }
}
