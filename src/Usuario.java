public class Usuario {
    
    public String nome;
    public String iv;

    public Usuario(String nome, String iv) {
        this.nome = nome;
        this.iv = iv;
    }
    
    public Usuario() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    
    
}
