package cadastrobd.model;

public class Pessoa {
    // Campos da classe
    private int id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    // Construtor padrão
    public Pessoa() {
        this.id = 0;
        this.nome = "";
        this.logradouro = "";
        this.cidade = "";
        this.estado = "";
        this.telefone = "";
        this.email = "";
    }

    // Construtor completo
    public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;   
        this.telefone = telefone;
        this.email = email;
    }

    

    // Getters e setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return this.telefone;   
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;   
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método exibir
    public void exibir() {
        System.out.println("==============================");
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("Logradouro: " + this.logradouro);
        System.out.println("Cidade: " + this.cidade);
        System.out.println("Estado: " + this.estado);
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Email: " + this.email);
    }
}