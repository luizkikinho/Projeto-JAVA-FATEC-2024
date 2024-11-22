package principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

import fontes.Login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class Cadastrar extends JFrame {
	private JTextField campoNome;
	private JTextField campoEmail;
    public Cadastrar() {
        // Configuração da janela (frame)
    	setTitle("CADASTRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        getContentPane().setLayout(null);
        
/*        JButton botaoCadastrarCliente = new JButton("");
        botaoCadastrarCliente.setBounds(46, 124, 92, 58);

        botaoCadastrarCliente.setContentAreaFilled(false);
        botaoCadastrarCliente.setBorderPainted(false);
        botaoCadastrarCliente.setFocusPainted(false);

        botaoCadastrarCliente.addActionListener(e -> {
                Cadastrar telaCadastro = new Cadastrar();
                telaCadastro.setVisible(true);
                dispose();   //Caso precise que feche;
            }
        );
       getContentPane().add(botaoCadastrarCliente); */
       
       
       JButton botaoExcluirUsuario = new JButton("");
       botaoExcluirUsuario.setFocusPainted(false);
       botaoExcluirUsuario.setContentAreaFilled(false);
       botaoExcluirUsuario.setBorderPainted(false);
       botaoExcluirUsuario.setBounds(46, 195, 92, 58);
       
       botaoExcluirUsuario.addActionListener(e -> {
    	   ExcluirUsuario telaExcluirUsuario = new ExcluirUsuario();
    	   telaExcluirUsuario.setVisible(true);
    	   dispose();
       });
       getContentPane().add(botaoExcluirUsuario);
       
       JButton botaoListaMateriais = new JButton("");
       botaoListaMateriais.setFocusPainted(false);
       botaoListaMateriais.setContentAreaFilled(false);
       botaoListaMateriais.setBorderPainted(false);
       botaoListaMateriais.setBounds(46, 266, 92, 58);
       
       botaoListaMateriais.addActionListener(e -> {
    	   ListaMateriais telaListaMateriais = new ListaMateriais();
    	   telaListaMateriais.setVisible(true);
    	   dispose();
       });
       getContentPane().add(botaoListaMateriais);     
       
       JButton botaoNovaVenda = new JButton("");
       botaoNovaVenda.setFocusPainted(false);
       botaoNovaVenda.setContentAreaFilled(false);
       botaoNovaVenda.setBorderPainted(false);
       botaoNovaVenda.setBounds(46, 337, 92, 58);
       
       botaoNovaVenda.addActionListener(e -> {
    	   NovaVenda telaNovaVenda = new NovaVenda();
     	   telaNovaVenda.setVisible(true);
    	   dispose();
       });
       getContentPane().add(botaoNovaVenda);   
       
       JButton botaoRelatorio = new JButton("");
       botaoRelatorio.setFocusPainted(false);
       botaoRelatorio.setContentAreaFilled(false);
       botaoRelatorio.setBorderPainted(false);
       botaoRelatorio.setBounds(46, 408, 92, 58);
       
       botaoRelatorio.addActionListener(e -> {
    	   Relatorio telaRelatorio = new Relatorio();
    	   telaRelatorio.setVisible(true);
    	   dispose();
       });
       getContentPane().add(botaoRelatorio);  
       
        // Botão Encerrar Seção
        JButton botaoEncerrarSecao = new JButton("");
        botaoEncerrarSecao.setFocusPainted(false);
        botaoEncerrarSecao.setContentAreaFilled(false);
        botaoEncerrarSecao.setBorderPainted(false);
        botaoEncerrarSecao.setBounds(46, 479, 92, 58);
        
        botaoEncerrarSecao.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Você realmente deseja encerrar a sessão?", 
                "Confirmar Encerramento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE 
            );

            if (resposta == JOptionPane.YES_OPTION) {
                Login telaLogin = new Login();
                telaLogin.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(botaoEncerrarSecao);
        
        campoNome = new JTextField();
        campoNome.setBounds(390, 185, 360, 64);
        campoNome.setOpaque(false);
        getContentPane().add(campoNome);
        campoNome.setColumns(10);
        
        campoEmail = new JTextField();
        campoEmail.setBounds(390, 298, 360, 64);
        campoEmail.setOpaque(false);
        getContentPane().add(campoEmail);
        campoEmail.setColumns(10);
        
        JButton botaoCadastrarCliente = new JButton("");
        botaoCadastrarCliente.setFocusPainted(false);
        botaoCadastrarCliente.setContentAreaFilled(false);
        botaoCadastrarCliente.setBorderPainted(false);
        botaoCadastrarCliente.setBounds(390, 418, 360, 64);
        
        botaoCadastrarCliente.addActionListener(e -> {
        	String nome = campoNome.getText();
        	String email = campoEmail.getText();
        	
        	String url = "jdbc:mysql://localhost/estoque"; // URL do banco de dados
            String usuario = "root"; // Usuário do banco de dados
            String senha = ""; // Senha do banco de dados

            String query = "INSERT INTO clientes (nome, email) VALUES (?, ?)";

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(query)) {

                // Inserir dados
                stmt.setString(1, nome);
                stmt.setString(2, email);

                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
                    // Limpar campos após cadastro
                    campoNome.setText("");
                    campoEmail.setText("");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage());
            }
        });
        getContentPane().add(botaoCadastrarCliente);
        
        
        // Imagem de fundo
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setIcon(new ImageIcon(Principal.class.getResource("/img/TELACADASTROCLIENTE.png")));
        imagemFundo.setBounds(0, 0, 978, 600);
        getContentPane().add(imagemFundo);
    }

    public static void main(String[] args) {
        // Cria uma instância de Principal e exibe a janela
        Principal frame = new Principal();
        frame.setVisible(true);
    }
}
