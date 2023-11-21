
public class User {

    protected String password;
    protected String name;
    protected String ID;
    protected String email;
    protected String IC;

    public User () {}
    public User (String IC, String password) {
        this.IC = IC;
        this.password = password;
    }
    public User (String ID, String IC, String password, String name, String email) {
        this.password = password;
        this.IC = IC;
        this.name = name;
        this.ID = ID;
        this.email = email;
    }
}