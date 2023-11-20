package cadastrobd.model;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {
    private String cpf;

    public PessoaFisica() {
    }

    public PessoaFisica(int id, String nome, String logradouro, 
                        String cidade, String estado, String telefone, 
                        String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + this.cpf );
        System.out.println("==============================");
    }

    public void remove(PessoaFisica pessoaFisica) {
    }
}
