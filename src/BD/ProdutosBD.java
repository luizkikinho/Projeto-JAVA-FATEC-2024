package BD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosBD {
    private static final String URL = "jdbc:mysql://localhost/estoque";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DefaultTableModel carregarProdutos() {
        String[] colunas = {"ID", "Descrição", "Preço", "Quantidade"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        String query = "SELECT id_produto, descricao, preco, quantidade FROM produtos";

        try (Connection con = conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_produto"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getDouble("quantidade")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public static double obterPrecoProduto(int idProduto) {
        String query = "SELECT preco FROM produtos WHERE id_produto = ?";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, idProduto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getDouble("preco");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<String> carregarClientes() {
        List<String> clientes = new ArrayList<>();
        String query = "SELECT nome FROM clientes";
        try (Connection con = conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clientes.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
