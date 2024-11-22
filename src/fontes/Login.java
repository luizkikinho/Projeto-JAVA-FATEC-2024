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

public class Login extends JFrame {
    private JPanel contentPane;
    private JPasswordField campoSenha;
    private JTextField campoLogin;

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

    public Login() {
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
        
                // Botão "Entrar"
                JButton botaoEntrar = new JButton("");
                botaoEntrar.setBounds(527, 511, 169, 57);
                botaoEntrar.setFocusPainted(false);
                botaoEntrar.setContentAreaFilled(false);
                botaoEntrar.setBorderPainted(false);
                botaoEntrar.addActionListener(e -> {
                    String senha = new String(campoSenha.getPassword()); // Obtém a senha como String
                    String login = new String(campoLogin.getText());

                    // Autenticação
                    if (AcessoBD.autenticarUsuario(login, senha)) {
                        Principal p = new Principal();
                        p.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário e/ou senha não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                });
                contentPane.add(botaoEntrar);
        
        JButton botaoCriarConta = new JButton("");
        botaoCriarConta.setBounds(265, 511, 168, 57);
        botaoCriarConta.setFocusPainted(false);
        botaoCriarConta.setContentAreaFilled(false);
        botaoCriarConta.setBorderPainted(false);
        botaoCriarConta.addActionListener(e -> {
        	CadastrarUsuario telaCadastro = new CadastrarUsuario();
        	telaCadastro.setVisible(true);
        });
        contentPane.add(botaoCriarConta);
        
        campoLogin = new JTextField();
        campoLogin.setBounds(353, 348, 294, 38);
        contentPane.add(campoLogin);
        campoLogin.setColumns(10);

        // Campo de Senha
        campoSenha = new JPasswordField();
        campoSenha.setBounds(353, 440, 294, 38);
        contentPane.add(campoSenha);
        campoSenha.setColumns(10);

        // Imagem de fundo
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setIcon(new ImageIcon(Principal.class.getResource("/img/TELALOGIN.png")));
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
