package fontes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BD.AcessoBD;
import principal.Principal;

public class CadastrarUsuario extends JFrame {
    private JPanel contentPane;
    private JPasswordField campoSenha;
    private JTextField campoLogin;
    private JTextField campoNomeUsuario;
    private JTextField campoNomeReal;
    private JTextField campoEmail;
    private JPasswordField passwordField;
    private JTextField campoTelefone;
    private JTextField campoEndereço;
    private JTextField campoPergunta;
    private JTextField campoResposta;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastrarUsuario() {
        setTitle("Acesso ao sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setSize(960, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        
        JButton botaoCadastrar = new JButton("");
        botaoCadastrar.setBounds(361, 463, 238, 72);
        botaoCadastrar.setFocusPainted(false);
        botaoCadastrar.setContentAreaFilled(false);
        botaoCadastrar.setBorderPainted(false);
        botaoCadastrar.addActionListener(e -> {
            String usuario = campoNomeUsuario.getText();
            String nome = campoNomeReal.getText();
            String email = campoEmail.getText();
            String endereço = campoEndereço.getText();
            String telefone = campoTelefone.getText();
            String perguntaSegurança = campoPergunta.getText();
            String respostaPergunta = campoResposta.getText();
            // Utilize passwordField ao invés de campoSenha
            String senha = new String(passwordField.getPassword());

            // Cadastro no banco de dados
            AcessoBD AcessoBD = new AcessoBD();
            boolean sucesso = AcessoBD.cadastrarUsuario(usuario, senha, nome, email, telefone, endereço, perguntaSegurança, respostaPergunta);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(botaoCadastrar);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(197, 371, 246, 49);
        passwordField.setOpaque(false);
        contentPane.add(passwordField);
        
        campoTelefone = new JTextField();
        campoTelefone.setBounds(517, 102, 246, 49);
        campoTelefone.setOpaque(false);
        contentPane.add(campoTelefone);
        campoTelefone.setColumns(10);
        
        campoResposta = new JTextField();
        campoResposta.setBounds(517, 372, 246, 49);
        campoResposta.setOpaque(false);
        contentPane.add(campoResposta);
        campoResposta.setColumns(10);
        
        campoEmail = new JTextField();
        campoEmail.setBounds(197, 280, 246, 49);
        campoEmail.setOpaque(false);
        contentPane.add(campoEmail);
        campoEmail.setColumns(10);
        
        campoNomeReal = new JTextField();
        campoNomeReal.setBounds(197, 189, 246, 49);
        campoNomeReal.setOpaque(false);
        contentPane.add(campoNomeReal);
        campoNomeReal.setColumns(10);
        
        campoEndereço = new JTextField();
        campoEndereço.setBounds(517, 192, 246, 49);
        campoEndereço.setOpaque(false);
        contentPane.add(campoEndereço);
        campoEndereço.setColumns(10);
        
        campoNomeUsuario = new JTextField();
        campoNomeUsuario.setBounds(197, 99, 246, 49);
        campoNomeUsuario.setOpaque(false);
        contentPane.add(campoNomeUsuario);
        campoNomeUsuario.setColumns(10);
        
        campoPergunta = new JTextField();
        campoPergunta.setBounds(517, 282, 246, 49);
        campoPergunta.setOpaque(false);
        contentPane.add(campoPergunta);
        campoPergunta.setColumns(10);
        
        // Imagem de fundo
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setIcon(new ImageIcon(Principal.class.getResource("/img/TELACADASTRO.png")));
        imagemFundo.setBounds(0, 0, 978, 600);
        getContentPane().add(imagemFundo);

    }

    // Método para carregar a imagem
    private Image carregarImagem(String caminho) {
        try {
            return ImageIO.read(getClass().getResource(caminho));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Retorna null se houver erro
        }
    }
}
