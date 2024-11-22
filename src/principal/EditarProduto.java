package principal;

import javax.swing.*;

import java.awt.*;
import java.sql.*;

public class EditarProduto extends JFrame {
    private JTextField campoDescricao;
    private JTextField campoQuantidade;
    private JTextField campoPreco;
    private String codigoProduto;

    // Construtor para editar o produto
    public EditarProduto(ListaMateriais listaMateriais, String codigoProduto) {
        this.codigoProduto = codigoProduto; // Salva o código do produto
        setTitle("Editar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout e componentes da janela
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // Aumentei para 5 linhas para incluir o botão de deletar

        panel.add(new JLabel("Descrição:"));
        campoDescricao = new JTextField();
        panel.add(campoDescricao);

        panel.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField();
        panel.add(campoQuantidade);

        panel.add(new JLabel("Preço:"));
        campoPreco = new JTextField();
        panel.add(campoPreco);

        // Botão de salvar
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            atualizarProduto();
            setVisible(false);
        });
        panel.add(botaoSalvar);

        // Botão de deletar
        JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> {
            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar este produto?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                deletarProduto();
                listaMateriais.atualizarTabela(); // Atualiza a tabela na janela principal
                setVisible(false); // Fecha a janela após exclusão
            }
        });
        panel.add(botaoDeletar);

        add(panel);
        
        // Carrega os dados do produto para edição
        carregarDadosProduto();
    }
    
	private void carregarDadosProduto() {
        // Aqui você deve buscar os dados do produto no banco
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM produtos WHERE id_produto = ?");
            pst.setString(1, codigoProduto);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                campoDescricao.setText(rs.getString("descricao"));
                campoQuantidade.setText(rs.getString("quantidade"));
                campoPreco.setText(rs.getString("preco"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do produto: " + ex.getMessage());
        }
    }

    private void atualizarProduto() {
        // Atualiza o produto no banco
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
            PreparedStatement pst = con.prepareStatement("UPDATE produtos SET descricao = ?, quantidade = ?, preco = ? WHERE id_produto = ?");
            pst.setString(1, campoDescricao.getText());
            pst.setString(2, campoQuantidade.getText());
            pst.setString(3, campoPreco.getText());
            pst.setString(4, codigoProduto);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar produto: " + ex.getMessage());
        }
    }

    private void deletarProduto() {
        // Deleta o produto do banco
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
            PreparedStatement pst = con.prepareStatement("DELETE FROM produtos WHERE id_produto = ?");
            pst.setString(1, codigoProduto);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar produto: " + ex.getMessage());
        }
    }
}
