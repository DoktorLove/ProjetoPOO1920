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
    private String password; //password de utilizador para aceder a aplicacao
    private Localizacao posicao; //posicao atual do utilizador
    //raio de acao
    
    //Construtor por omissao
    public User()
    {
        this.posicao = new Localizacao();
        this.username = "N/A";
        this.password = "N/A";
    }
    
    //Construtor por parametros
    public User(String username, String password, Localizacao local)
    {
        this.username = username;
        this.password = password;
        this.posicao = local.clone();
    }
    
    //Construtor copia
    public User(User newUser)
    {
        this.username = newUser.getUsername();
        this.password = newUser.getPassword();
        this.posicao = newUser.getPosicao();
    }
    
    //Gets
    public String getUsername()
    {
        return this.username;
    }
    
    public String getPassword()
    {
        return this.password;
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
    
    public void setPassword(String password)
    {
        this.password = password;
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
               this.password.equals(t.getPassword()) &&
               this.posicao.equals(t.getPosicao());
    }
    
    //toString
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUsername: ").append(this.username)
          .append("\nPassword: ").append(this.password + "\n")
          .append(this.posicao.toString());
        return sb.toString();
    }
    
    //Clone
    public abstract User clone();
}
