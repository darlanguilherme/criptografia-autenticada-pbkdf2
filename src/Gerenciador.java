
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Gerenciador {
    
    Scanner entrada = new Scanner(System.in);

    public Gerenciador() {

    }

    public void showMenu() {

        System.out.println("Escolha o que quer fazer: ");
        System.out.println("1- Cadastrar usuário");
        System.out.println("2- Enviar mensagem");
        System.out.println("3- Sair");

        String opcao = this.entrada.nextLine();

        switch (opcao) {
            case "1":
                this.cadastrarUsuario();
                break;
            case "2":
                this.enviarMensagem();
                break;
            case "3":
                break;
            default:
                System.out.println("aaaaaaaaaaaa");
        }
    }

    private void cadastrarUsuario() {

        System.out.println("Digite o nome do usuário");

        String nome = this.entrada.nextLine();

        if (nome == null || nome.equals("")) {
            System.out.println("Nome inválido");
            this.showMenu();
            return;
        }

        Usuario usuario = new Usuario(nome, "aa");

        JSONObject usuariosJson = this.getJsonUsuarios();

        if (usuariosJson == null) {
            usuariosJson = this.criarJson(this.usuarioTojsonObj(usuario));
        } else {
            this.insereUsuarioJson(this.usuarioTojsonObj(usuario), usuariosJson);
        }

    }

    public JSONObject usuarioTojsonObj(Usuario obj) {

        JSONObject usuario = new JSONObject();

        usuario.put("nome", obj.getNome());
        usuario.put("iv", obj.getIv());

        return usuario;
    }

    private JSONObject criarJson(JSONObject jsonObj) {
        try {

            FileWriter writeFile = new FileWriter("usuarios.json");
            JSONObject usuarioJson = new JSONObject();
            usuarioJson.put(jsonObj.get("nome"), jsonObj);
            writeFile.write(usuarioJson.toJSONString());
            writeFile.close();

            return this.getJsonUsuarios();
        } catch (IOException e) {
            return null;
        }
    }

    private JSONObject getJsonUsuarios() {

        try {

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(new FileReader("usuarios.json"));

        } catch (Exception e) {
            System.out.println("Falha ao ler arquivo json");
            return null;
        }
    }

    private void insereUsuarioJson(JSONObject usuarioJson, JSONObject jsonObj) {

        try {
            jsonObj.put(usuarioJson.get("nome"), usuarioJson);
            FileWriter writeFile = new FileWriter("usuarios.json");
            writeFile.write(jsonObj.toJSONString());
            writeFile.close();
        } catch (IOException ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void enviarMensagem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
