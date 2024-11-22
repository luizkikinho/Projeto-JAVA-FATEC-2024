package principal;

import javax.swing.*;
import BD.ProdutosBD;
import fontes.Login;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaMateriais extends JFrame {

    private JPanel contentPane;
    private JTable tabela;

    public ListaMateriais() {
    	setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        //setUndecorated(true);
        getContentPane().setLayout(null);
        
        // Configura o contentPane
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton botaoEditarProduto = new JButton("");
        botaoEditarProduto.setFocusPainted(false);
        botaoEditarProduto.setContentAreaFilled(false);
        botaoEditarProduto.setBorderPainted(false);
        botaoEditarProduto.setBounds(353, 507, 170, 58);
        
        botaoEditarProduto.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada != -1) { // Verifica se uma linha foi selecionada
                String codigoProduto = tabela.getValueAt(linhaSelecionada, 0).toString();
                EditarProduto telaEditarProduto = new EditarProduto(this, codigoProduto);
                telaEditarProduto.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            }
        });
                
                JButton botaoNovoProduto = new JButton("");
                botaoNovoProduto.setFocusPainted(false);
                botaoNovoProduto.setContentAreaFilled(false);
                botaoNovoProduto.setBorderPainted(false);
                botaoNovoProduto.setBounds(616, 507, 170, 58);
                contentPane.add(botaoNovoProduto);
                botaoNovoProduto.addActionListener(e -> abrirTelaProduto());
                getContentPane().add(botaoEditarProduto); 

        // Cria um JScrollPane que conterá a tabela
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(235, 111, 656, 374);
        contentPane.add(scrollPane);

        // Chama o método para obter os dados e criar a tabela
        tabela = criarTabela();
        scrollPane.setViewportView(tabela);
        

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
        
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 978, 600);
        imagemFundo.setIcon(new ImageIcon(ListaMateriais.class.getResource("/img/TELALISTAMATERIAIS.png")));
        contentPane.add(imagemFundo);
        
    }

    
    public void abrirTelaProduto() {
    	CadastrarProduto telaCadastrarProduto = new CadastrarProduto(null);
    	telaCadastrarProduto.setVisible(true);
    	dispose();
    }
    
    public void atualizarTabela() {
        // Recria a tabela com os dados mais recentes
        JTable novaTabela = criarTabela();
        JScrollPane scrollPane = (JScrollPane) contentPane.getComponent(0);
        scrollPane.setViewportView(novaTabela);  // Atualiza a tabela dentro do JScrollPane
    }
    
    private JTable criarTabela() {
        String[] colunas = {"Código", "Descrição", "Quantidade", "Preço"};
        List<String[]> dados = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM produtos");

            while (res.next()) {
                String[] linha = {
                    res.getString("id_produto"),
                    res.getString("descricao"),
                    res.getString("quantidade"),
                    res.getString("preco")
                };
                dados.add(linha);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Converte a lista de dados para um array e cria o modelo da tabela
        String[][] dadosArray = dados.toArray(new String[0][0]);
        return new JTable(dadosArray, colunas);
    }

    private void abrirJanelaEditarProduto() {
        int linhaSelecionada = tabela.getSelectedRow();
        String codigoProduto = tabela.getValueAt(linhaSelecionada, 0).toString();
        EditarProduto janelaEditar = new EditarProduto(this, codigoProduto);
        janelaEditar.setVisible(true);
    }
}
