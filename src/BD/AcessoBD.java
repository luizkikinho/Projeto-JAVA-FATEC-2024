package BD;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessoBD {

    private static final String URL = "jdbc:mysql://localhost/estoque";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para autenticação de usuário
    public static boolean autenticarUsuario(String login, String senha) {
        String query = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            
            pst.setString(1, login);
            pst.setString(2, senha);
            
            try (ResultSet res = pst.executeQuery()) {
                return res.next();  // Verifica se encontrou o usuário
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para cadastrar um novo usuário
    public static boolean cadastrarUsuario(String nomeUsr, String senha, String nomeReal, String email, String telefone, String endereco, String perguntaSeguranca, String respostaPergunta) {
        String query = "INSERT INTO usuarios (nome, senha, nomeReal, email, telefone, endereco, perguntaSeguranca, respostaPergunta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            
            pst.setString(1, nomeUsr);   // Nome de usuário
            pst.setString(2, senha);     // Senha
            pst.setString(3, nomeReal);  // Nome real
            pst.setString(4, email);     // E-mail
            pst.setString(5, telefone);  // Telefone
            pst.setString(6, endereco);  // Endereço
            pst.setString(7, perguntaSeguranca);  // Pergunta de segurança
            pst.setString(8, respostaPergunta);   // Resposta da pergunta
            
            pst.executeUpdate();  // Executa a inserção
            return true;  // Cadastro realizado com sucesso
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Método para cadastrar um novo cliente
    public static boolean cadastrarCliente(String nome, String email) {
        String query = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {
            
            pst.setString(1, nome);
            pst.setString(2, email);
            
            pst.executeUpdate();  // Executa a inserção
            return true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Método para salvar uma venda
    public static int salvarVenda(String nomeCliente, double valorTotal) {
        int idVenda = -1;
        String sqlVenda = "INSERT INTO vendas (nomeCliente, valorTotal) VALUES (?, ?)";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstVenda = con.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
            
            pstVenda.setString(1, nomeCliente);
            pstVenda.setDouble(2, valorTotal);
            pstVenda.executeUpdate();  // Executa a inserção da venda

            // Recupera o ID da venda recém inserida
            try (ResultSet rs = pstVenda.getGeneratedKeys()) {
                if (rs.next()) {
                    idVenda = rs.getInt(1);  // Retorna o ID da venda
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return idVenda;
    }

    // Método para salvar os itens da venda
    public static boolean salvarItensVenda(int idVenda, String nomeProduto, int quantidade, double precoUnitario, double total) {
        String sqlItemVenda = "INSERT INTO itens_venda (idVenda, produto, quantidade, precoUnitario, total) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstItemVenda = con.prepareStatement(sqlItemVenda)) {
            
            pstItemVenda.setInt(1, idVenda);
            pstItemVenda.setString(2, nomeProduto);
            pstItemVenda.setInt(3, quantidade);
            pstItemVenda.setDouble(4, precoUnitario);
            pstItemVenda.setDouble(5, total);
            pstItemVenda.executeUpdate();  // Executa a inserção dos itens da venda
            
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar itens da venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
