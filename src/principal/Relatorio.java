package principal;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import fontes.Login;

public class Relatorio extends JFrame {
    private JTextField campoQuantidadeVendas;
    private JTextField campoValorTotalVendas;
    private JTextField campoClienteMaisComprou;
    private JTextField campoMaiorValorGasto;

    public Relatorio() {
        setTitle("Tela de Relatórios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 630);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Caixa de texto para a quantidade de vendas no mês
        campoQuantidadeVendas = new JTextField();
        campoQuantidadeVendas.setEditable(false);
        campoQuantidadeVendas.setBounds(459, 131, 219, 44);
        getContentPane().add(campoQuantidadeVendas);

        // Caixa de texto para o valor total vendido no mês
        campoValorTotalVendas = new JTextField();
        campoValorTotalVendas.setEditable(false);
        campoValorTotalVendas.setBounds(459, 234, 219, 44);
        getContentPane().add(campoValorTotalVendas);

        // Caixa de texto para o nome do cliente que mais comprou
        campoClienteMaisComprou = new JTextField();
        campoClienteMaisComprou.setEditable(false);
        campoClienteMaisComprou.setBounds(459, 340, 219, 44);
        getContentPane().add(campoClienteMaisComprou);

        // Caixa de texto para o maior valor gasto por um cliente
        campoMaiorValorGasto = new JTextField();
        campoMaiorValorGasto.setEditable(false);
        campoMaiorValorGasto.setBounds(459, 445, 219, 44);
        getContentPane().add(campoMaiorValorGasto);

        // Carregar os dados nos campos
        carregarRelatorio();

        // Botões
        JButton botaoCadastrarCliente = new JButton("");
        botaoCadastrarCliente.setBounds(46, 124, 92, 58);
        botaoCadastrarCliente.setContentAreaFilled(false);
        botaoCadastrarCliente.setBorderPainted(false);
        botaoCadastrarCliente.setFocusPainted(false);
        botaoCadastrarCliente.addActionListener(e -> {
            Cadastrar telaCadastro = new Cadastrar();
            telaCadastro.setVisible(true);
            dispose();
        });
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
        getContentPane().add(botaoRelatorio);

        JButton botaoEncerrarSecao = new JButton("");
        botaoEncerrarSecao.setFocusPainted(false);
        botaoEncerrarSecao.setContentAreaFilled(false);
        botaoEncerrarSecao.setBorderPainted(false);
        botaoEncerrarSecao.setBounds(46, 479, 92, 58);
        botaoEncerrarSecao.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(this, "Você realmente deseja encerrar a sessão?",
                    "Confirmar Encerramento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resposta == JOptionPane.YES_OPTION) {
                Login telaLogin = new Login();
                telaLogin.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(botaoEncerrarSecao);

        // Imagem de fundo
        JLabel imagemFundo = new JLabel("");
        imagemFundo.setIcon(new ImageIcon(Principal.class.getResource("/img/TELARELATORIO.png")));
        imagemFundo.setBounds(0, 0, 978, 600);
        getContentPane().add(imagemFundo);
    }

    private void carregarRelatorio() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "")) {
            // Quantidade de vendas no mês
            String queryVendasMes = "SELECT COUNT(*) FROM vendas WHERE MONTH(dataVenda) = MONTH(CURRENT_DATE())";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(queryVendasMes)) {
                if (rs.next()) {
                    campoQuantidadeVendas.setText(String.valueOf(rs.getInt(1)));
                }
            }

            // Valor total vendido no mês
            String queryValorTotalVendido = "SELECT SUM(valorTotal) FROM vendas WHERE MONTH(dataVenda) = MONTH(CURRENT_DATE())";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(queryValorTotalVendido)) {
                if (rs.next()) {
                    campoValorTotalVendas.setText("R$ " + String.format("%.2f", rs.getDouble(1)));
                }
            }

            // Cliente que mais comprou
            String queryClienteMaisComprou = "SELECT nomeCliente FROM vendas GROUP BY nomeCliente ORDER BY SUM(valorTotal) DESC LIMIT 1";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(queryClienteMaisComprou)) {
                if (rs.next()) {
                    campoClienteMaisComprou.setText(rs.getString("nomeCliente"));
                }
            }

            // Maior valor gasto por um cliente
            String queryMaiorValorGasto = "SELECT MAX(valorTotal) FROM vendas";
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(queryMaiorValorGasto)) {
                if (rs.next()) {
                    campoMaiorValorGasto.setText("R$ " + String.format("%.2f", rs.getDouble(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar relatório", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Relatorio telaRelatorio = new Relatorio();
            telaRelatorio.setVisible(true);
        });
    }
}
