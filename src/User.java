import java.time.LocalDate;
/**
 *Classe abstrata dos Utilizadores
 * 
 * @author Rui
 * @version 06/04/2020
 */
public abstract class User
{
    private String username; //nome de utilizador para aceder a aplicacao
    private String nome; //nome do utilizador
    private Localizacao posicao; //posicao atual do utilizador
    //raio de acao
    
    //Construtor por omissao
    public User()
    {
        this.posicao = new Localizacao();
        this.username = "N/A";
        this.nome = "N/A";
    }
    
    //Construtor por parametros
    public User(String username, String nome, Localizacao local)
    {
        this.username = username;
        this.nome = nome;
        this.posicao = local.clone();
    }
    
    //Construtor copia
    public User(User newUser)
    {
        this.username = newUser.getUsername();
        this.nome = newUser.getNome();
        this.posicao = newUser.getPosicao();
    }
    
    //Gets
    public String getUsername()
    {
        return this.username;
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public Localizacao getPosicao()
    {
        return this.posicao.clone();
    }
    
    //Sets
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public void setLocalizacao(Localizacao local)
    {
        this.posicao = local.clone();
    }
    
    //Equals
    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null||o.getClass() != this.getClass()) return false;
        User t = (User) o;
        return this.username.equals(t.getUsername()) && 
               this.nome.equals(t.getNome()) &&
               this.posicao.equals(t.getPosicao());
    }
    
    //toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUsername: ").append(this.username)
          .append("\nNome: ").append(this.nome + "\n")
          .append(this.posicao.toString());
        return sb.toString();
    }
    
    //Clone
    public abstract User clone();
}
