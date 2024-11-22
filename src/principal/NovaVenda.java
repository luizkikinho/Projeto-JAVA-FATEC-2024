package principal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import BD.ProdutosBD;
import fontes.Login;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class NovaVenda extends JFrame {
    private JButton botaoAdicionarProduto;
    private JButton botaoEditarItem;
    private JButton botaoFinalizarVenda;
    private JComboBox<String> comboBoxClientes;
    private JTable tabelaProdutos;
    private JTextField campoQuantidade;
    private JLabel labelTotal;
    private double precoProdutoSelecionado = 0.0;
    private JPanel contentPane;

    private DefaultTableModel modelItensVenda;
    private JTable tabelaItensVenda;
    
    private static final String DB_URL = "jdbc:mysql://localhost/estoque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public NovaVenda() {
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
        
        // Configuração da tabela de itens da venda
        modelItensVenda = new DefaultTableModel(new String[]{"Produto", "Quantidade", "Preço Unitário", "Total"}, 0);
        tabelaItensVenda = new JTable(modelItensVenda);
        JScrollPane scrollPaneItensVenda = new JScrollPane(tabelaItensVenda);
        scrollPaneItensVenda.setBounds(242, 110, 656, 374); // Posição e tamanho da tabela
        getContentPane().add(scrollPaneItensVenda);

        // Botões para adicionar, editar e finalizar a venda
        botaoAdicionarProduto = new JButton("");
        botaoAdicionarProduto.setBounds(242, 508, 116, 58); // Posição e tamanho
        botaoAdicionarProduto.setFocusPainted(false);
        botaoAdicionarProduto.setContentAreaFilled(false);
        botaoAdicionarProduto.setBorderPainted(false);
        getContentPane().add(botaoAdicionarProduto);

        botaoEditarItem = new JButton("");
        botaoEditarItem.setBounds(380, 508, 116, 58); // Posição e tamanho
        botaoEditarItem.setFocusPainted(false);
        botaoEditarItem.setContentAreaFilled(false);
        botaoEditarItem.setBorderPainted(false);
        getContentPane().add(botaoEditarItem);

        botaoFinalizarVenda = new JButton("");
        botaoFinalizarVenda.setBounds(782, 508, 116, 58); // Posição e tamanho
        botaoFinalizarVenda.setFocusPainted(false);
        botaoFinalizarVenda.setContentAreaFilled(false);
        botaoFinalizarVenda.setBorderPainted(false);
        getContentPane().add(botaoFinalizarVenda);

        // ComboBox para seleção do cliente
        comboBoxClientes = new JComboBox<>();
        comboBoxClientes.addItem("Selecione um Cliente");
        comboBoxClientes.setBounds(518, 508, 116, 58); // Posição e tamanho
        getContentPane().add(comboBoxClientes);

        // Label para mostrar o total
        labelTotal = new JLabel("Total: R$ 0.00");
        labelTotal.setBounds(242, 575, 300, 30); // Posição e tamanho do label total
        getContentPane().add(labelTotal);

        // Ações dos botões
        botaoAdicionarProduto.addActionListener(e -> abrirTelaSelecaoProduto());
        botaoEditarItem.addActionListener(e -> editarItemSelecionado());
        botaoFinalizarVenda.addActionListener(e -> finalizarVenda());

        // Definir o painel de fundo com a imagem
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setBounds(0, 0, 978, 600);
        imagemFundo.setIcon(new ImageIcon(ListaMateriais.class.getResource("/img/TELANOVAVENDA.png")));
        getContentPane().add(imagemFundo);

        // Carregar clientes ao inicializar a tela
        carregarClientes();
    }

    private void carregarClientes() {
        // Método para carregar a lista de clientes
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "")) {
            String query = "SELECT nome FROM clientes";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    comboBoxClientes.addItem(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar clientes", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaSelecaoProduto() {
        JFrame frameSelecao = new JFrame("Seleção de Produto");
        frameSelecao.setSize(600, 300);
        frameSelecao.setLocationRelativeTo(null);

        tabelaProdutos = new JTable(ProdutosBD.carregarProdutos());
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        campoQuantidade = new JTextField(10);
        JButton botaoConfirmar = new JButton("Confirmar");

        botaoConfirmar.addActionListener(e -> confirmarSelecao(frameSelecao));

        JPanel painelInferior = new JPanel();
        painelInferior.add(new JLabel("Quantidade:"));
        painelInferior.add(campoQuantidade);
        painelInferior.add(botaoConfirmar);

        frameSelecao.add(scrollPane, BorderLayout.CENTER);
        frameSelecao.add(painelInferior, BorderLayout.SOUTH);

        frameSelecao.setVisible(true);
    }

    private void confirmarSelecao(JFrame frameSelecao) {
        int row = tabelaProdutos.getSelectedRow();
        if (row != -1 && !campoQuantidade.getText().isEmpty()) {
            String nomeProduto = (String) tabelaProdutos.getValueAt(row, 1);
            int quantidade = Integer.parseInt(campoQuantidade.getText());
            precoProdutoSelecionado = ProdutosBD.obterPrecoProduto((int) tabelaProdutos.getValueAt(row, 0));
            double total = quantidade * precoProdutoSelecionado;

            modelItensVenda.addRow(new Object[]{nomeProduto, quantidade, precoProdutoSelecionado, total});

            atualizarTotalVenda();
            frameSelecao.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto e informe a quantidade", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editarItemSelecionado() {
        int row = tabelaItensVenda.getSelectedRow();
        if (row != -1) {
            String nomeProduto = (String) modelItensVenda.getValueAt(row, 0);
            int quantidadeAtual = (int) modelItensVenda.getValueAt(row, 1);
            double precoAtual = (double) modelItensVenda.getValueAt(row, 2);

            String novaQuantidadeStr = JOptionPane.showInputDialog(this, "Nova Quantidade:", quantidadeAtual);
            String novoPrecoStr = JOptionPane.showInputDialog(this, "Novo Preço:", precoAtual);

            try {
                int novaQuantidade = Integer.parseInt(novaQuantidadeStr);
                double novoPreco = Double.parseDouble(novoPrecoStr);
                double novoTotal = novaQuantidade * novoPreco;

                modelItensVenda.setValueAt(novaQuantidade, row, 1);
                modelItensVenda.setValueAt(novoPreco, row, 2);
                modelItensVenda.setValueAt(novoTotal, row, 3);

                atualizarTotalVenda();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Valores inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limparTela() {
        // Limpar a tabela de itens da venda
        modelItensVenda.setRowCount(0);  // Limpar todas as linhas da tabela

        // Limpar o campo de quantidade
        campoQuantidade.setText(""); 

        // Limpar o campo de cliente (resetar o JComboBox)
        comboBoxClientes.setSelectedIndex(0);  // Ou outro método para resetar o valor

        // Limpar o total
        labelTotal.setText("Total: R$ 0.00");
    }

    private void finalizarVenda() {
        // Obtendo o cliente selecionado e a data da venda
        String nomeCliente = comboBoxClientes.getSelectedItem().toString();
        double totalVenda = 0;
        int quantidadeCompras = 0;

        // Verificando se há itens na venda
        if (modelItensVenda.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione itens à venda antes de finalizar", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Calculando o total da venda
        for (int i = 0; i < modelItensVenda.getRowCount(); i++) {
            totalVenda += (double) modelItensVenda.getValueAt(i, 3);
        }

        // Registrando a venda na tabela 'vendas'
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
             PreparedStatement stmtVenda = con.prepareStatement("INSERT INTO vendas (nomeCliente, dataVenda, valorTotal) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmtVenda.setString(1, nomeCliente);
            stmtVenda.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Data atual
            stmtVenda.setDouble(3, totalVenda);
            stmtVenda.executeUpdate();

            // Obtendo o ID da venda inserida
            try (ResultSet rs = stmtVenda.getGeneratedKeys()) {
                if (rs.next()) {
                    int idVenda = rs.getInt(1);

                    // Subtraindo a quantidade dos produtos na tabela 'produtos' e registrando os itens da venda
                    for (int i = 0; i < modelItensVenda.getRowCount(); i++) {
                        String nomeProduto = (String) modelItensVenda.getValueAt(i, 0);
                        int quantidade = (int) modelItensVenda.getValueAt(i, 1);
                        double precoUnitario = (double) modelItensVenda.getValueAt(i, 2);

                        // Atualizando o estoque de produtos
                        try (PreparedStatement stmtEstoque = con.prepareStatement("UPDATE produtos SET quantidade = quantidade - ? WHERE descricao = ?")) {
                            stmtEstoque.setInt(1, quantidade);
                            stmtEstoque.setString(2, nomeProduto);
                            stmtEstoque.executeUpdate();
                        }

                        // Inserindo os itens da venda na tabela 'itens_venda'
                        try (PreparedStatement stmtItem = con.prepareStatement("INSERT INTO itens_venda (idVenda, produto, quantidade, precoUnitario) VALUES (?, ?, ?, ?)")) {
                            stmtItem.setInt(1, idVenda);
                            stmtItem.setString(2, nomeProduto);
                            stmtItem.setInt(3, quantidade);
                            stmtItem.setDouble(4, precoUnitario);
                            stmtItem.executeUpdate();
                        }
                    }
                } else {
                    throw new SQLException("Falha ao obter o ID da venda.");
                }
            }

            // Atualizando ou registrando no relatório
            try (PreparedStatement stmtRelatorio = con.prepareStatement("SELECT * FROM relatorios WHERE nome_cliente = ?")) {
                stmtRelatorio.setString(1, nomeCliente);
                try (ResultSet rsRelatorio = stmtRelatorio.executeQuery()) {
                    if (rsRelatorio.next()) {
                        // Se o cliente já tem um registro, atualizamos o relatório
                        quantidadeCompras = rsRelatorio.getInt("quantidade_compras") + 1;

                        try (PreparedStatement stmtUpdateRelatorio = con.prepareStatement("UPDATE relatorios SET quantidade_compras = ?, valor_total = valor_total + ? WHERE nome_cliente = ?")) {
                            stmtUpdateRelatorio.setInt(1, quantidadeCompras);
                            stmtUpdateRelatorio.setDouble(2, totalVenda);
                            stmtUpdateRelatorio.setString(3, nomeCliente);
                            stmtUpdateRelatorio.executeUpdate();
                        }
                    } else {
                        // Caso seja a primeira compra do cliente, inserimos um novo registro no relatório
                        try (PreparedStatement stmtInsertRelatorio = con.prepareStatement("INSERT INTO relatorios (nome_cliente, quantidade_compras, valor_total) VALUES (?, ?, ?)")) {
                            stmtInsertRelatorio.setString(1, nomeCliente);
                            stmtInsertRelatorio.setInt(2, 1); // Primeira compra
                            stmtInsertRelatorio.setDouble(3, totalVenda);
                            stmtInsertRelatorio.executeUpdate();
                        }
                    }
                }
            }

            // Exibindo mensagem de sucesso e limpando a tela
            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparTela();  // Chama o método para limpar a tela
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao finalizar a venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarTotalVenda() {
        double totalVenda = 0.0;
        for (int i = 0; i < modelItensVenda.getRowCount(); i++) {
            totalVenda += (double) modelItensVenda.getValueAt(i, 3);
        }
        labelTotal.setText("Total: R$ " + String.format("%.2f", totalVenda));
    }
}
