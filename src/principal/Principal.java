package principal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fontes.Login;

public class Principal extends JFrame {
    public Principal() {
        // Configuração da janela (frame)
    	setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        getContentPane().setLayout(null);
        
        JButton botaoCadastrarCliente = new JButton("");
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
       getContentPane().add(botaoCadastrarCliente);
       
       
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
       
        // Imagem de fundo
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setIcon(new ImageIcon(Principal.class.getResource("/img/PRINCIPAL.png")));
        imagemFundo.setBounds(0, 0, 978, 600);
        getContentPane().add(imagemFundo);

        // Painel
        JPanel panel = new JPanel();
        panel.setBounds(196, 12, 782, 571);
        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        // Cria uma instância de Principal e exibe a janela
        Principal frame = new Principal();
        frame.setVisible(true);
    }
}
