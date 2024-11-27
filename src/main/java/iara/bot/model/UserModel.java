package iara.bot.model;


public class UserModel {
    protected String user;
    protected long cep;
    protected int idade;
    protected String email;
    protected long id;


    public String getuser() {
        return user;
    }
    public void setuser(String user) {
        this.user = user;
    }
    public long getCep() {
        return cep;
    }
    public void setCep(long cep) {
        this.cep = cep;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(long id) {
        this.id = id;
    }




}
