package principal;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarProduto extends JDialog {
    private ListaMateriais janelaPrincipal; // A referência para a janela principal
    private JTextField campoCodigo;
    private JTextField campoDescricao;
    private JTextField campoQuantidade;
    private JTextField campoPreco;

    public CadastrarProduto(ListaMateriais janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        setTitle("Cadastrar Produto");
        setSize(400, 300); // Tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(janelaPrincipal); // Centraliza a janela na principal
        setModal(true); // Janela modal (não permite interagir com a janela principal até fechá-la)
        
        // Layout da janela de cadastro
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas e 2 colunas, com espaçamento entre componentes

        JLabel labelCodigo = new JLabel("Código:");
        campoCodigo = new JTextField(20);
        
        JLabel labelDescricao = new JLabel("Descrição:");
        campoDescricao = new JTextField(20);
        
        JLabel labelQuantidade = new JLabel("Quantidade:");
        campoQuantidade = new JTextField(20);
        
        JLabel labelPreco = new JLabel("Preço:");
        campoPreco = new JTextField(20);

        // Botões de Salvar e Cancelar
        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoCancelar = new JButton("Cancelar");

        // Adiciona os componentes ao painel
        painel.add(labelCodigo);
        painel.add(campoCodigo);
        painel.add(labelDescricao);
        painel.add(campoDescricao);
        painel.add(labelQuantidade);
        painel.add(campoQuantidade);
        painel.add(labelPreco);
        painel.add(campoPreco);
        painel.add(botaoSalvar);
        painel.add(botaoCancelar);

        // Define o painel como conteúdo da janela
        add(painel);

        // Ação para o botão Salvar
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });

        // Ação para o botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de cadastro
            }
        });
    }

    private void cadastrarProduto() {
        String codigoProduto = campoCodigo.getText();
        String descricaoProduto = campoDescricao.getText();
        String quantidadeProdutoStr = campoQuantidade.getText();
        String precoProdutoStr = campoPreco.getText();

        // Verifica se todos os campos foram preenchidos
        if (codigoProduto.isEmpty() || descricaoProduto.isEmpty() || quantidadeProdutoStr.isEmpty() || precoProdutoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        // Valida a quantidade e o preço
        int quantidadeProduto = 0;
        double precoProduto = 0.0;
        try {
            quantidadeProduto = Integer.parseInt(quantidadeProdutoStr);
            precoProduto = Double.parseDouble(precoProdutoStr.replace(",", "."));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade ou Preço inválidos. Insira valores numéricos.");
            return;
        }

        // Inserir o produto no banco de dados
        String sql = "INSERT INTO produtos (id_produto, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, codigoProduto);
            pst.setString(2, descricaoProduto);
            pst.setInt(3, quantidadeProduto);
            pst.setDouble(4, precoProduto);
            pst.executeUpdate();

            // Atualiza a tabela na janela principal
            if (janelaPrincipal != null) {
                janelaPrincipal.atualizarTabela();
            }

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            dispose(); // Fecha a janela de cadastro

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto: " + ex.getMessage());
        }
    }
}
