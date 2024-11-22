package principal;

import fontes.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ExcluirUsuario extends JFrame {
	
    private JPanel contentPane;
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    
    public ExcluirUsuario() {
        // Configuração da janela (frame)
    	setTitle("EXCLUIR USUÁRIO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        getContentPane().setLayout(null);
        
        String[] colunas = {"Nome", "Nome Real", "Email"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(242, 110, 656, 374);
        getContentPane().add(scrollPane);
        
        carregarUsuarios();

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
        
        JButton botaoExcluir = new JButton("");
        botaoExcluir.setFocusPainted(false);
        botaoExcluir.setContentAreaFilled(false);
        botaoExcluir.setBorderPainted(false);
        botaoExcluir.setBounds(512, 507, 116, 58);
        
        botaoExcluir.addActionListener(e -> {
     	   excluirUsuarioSelecionado();
     	   setVisible(false);
        });
        getContentPane().add(botaoExcluir); 
       
        // Imagem de fundo
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setIcon(new ImageIcon(Principal.class.getResource("/img/TELAEXCLUIRUSUARIO.png")));
        imagemFundo.setBounds(0, 0, 978, 600);
        getContentPane().add(imagemFundo);

    }

    private void carregarUsuarios() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nome, nomeReal, email FROM usuarios");

            // Limpa os dados anteriores na tabela
            modeloTabela.setRowCount(0);

            // Adiciona os dados dos usuários à tabela
            while (rs.next()) {
                String nome = rs.getString("nome");
                String nomeReal = rs.getString("nomeReal");
                String email = rs.getString("email");
                modeloTabela.addRow(new Object[]{nome, nomeReal, email});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + ex.getMessage());
        }
    }

    // Método para excluir o usuário selecionado
    private void excluirUsuarioSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
            return;
        }

        String nome = (String) modeloTabela.getValueAt(linhaSelecionada, 0);

        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja excluir o usuário " + nome + "?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
                PreparedStatement pst = con.prepareStatement("DELETE FROM usuarios WHERE nome = ?");
                pst.setString(1, nome);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
                
                ExcluirUsuario telaExcluirUsuario = new ExcluirUsuario();
                telaExcluirUsuario.setVisible(true);
                dispose();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir usuário: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ExcluirUsuario frame = new ExcluirUsuario();
        frame.setVisible(true);
    }
}

