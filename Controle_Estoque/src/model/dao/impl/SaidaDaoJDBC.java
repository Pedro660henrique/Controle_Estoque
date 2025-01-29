package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.BaseDao;
import model.entities.Saida;
import model.entities.Produto;
import model.entities.Usuario;
import model.entities.Cliente;

public class SaidaDaoJDBC implements BaseDao<Saida> {

    private Connection conn;

    public SaidaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Saida obj) {
        String sql = "INSERT INTO saida (dataSaida, precoSaida, idUsuario, idProduto, idCliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setDate(1, java.sql.Date.valueOf(obj.getDataSaida()));
            st.setDouble(2, obj.getPrecoSaida());
            st.setInt(3, obj.getUsuario().getIdUsuario());
            st.setInt(4, obj.getProduto().getIdProduto());
            st.setInt(5, obj.getCliente().getIdCliente());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdSaida(rs.getInt(1)); 
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir saída: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Saida obj) {
        String sql = "UPDATE saida SET dataSaida = ?, precoSaida = ?, idUsuario = ?, idProduto = ?, idCliente = ? WHERE idSaida = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, java.sql.Date.valueOf(obj.getDataSaida())); 
            st.setDouble(2, obj.getPrecoSaida());
            st.setInt(3, obj.getUsuario().getIdUsuario());
            st.setInt(4, obj.getProduto().getIdProduto());
            st.setInt(5, obj.getCliente().getIdCliente());
            st.setInt(6, obj.getIdSaida());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar saída: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM saida WHERE idSaida = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir saída: " + e.getMessage(), e);
        }
    }

    @Override
    public Saida findById(Integer id) {
        String sql = "SELECT * FROM saida WHERE idSaida = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Saida saida = new Saida();
                    saida.setIdSaida(rs.getInt("idSaida"));
                    saida.setDataSaida(rs.getDate("dataSaida").toLocalDate());
                    saida.setPrecoSaida(rs.getDouble("precoSaida"));
                    
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    saida.setUsuario(usuario);

                    Produto produto = new Produto();
                    produto.setIdProduto(rs.getInt("idProduto"));
                    saida.setProduto(produto);

                    Cliente cliente = new Cliente(); 
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    saida.setCliente(cliente);

                    return saida;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar saída por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Saida> findAll() {
        String sql = "SELECT * FROM saida";
        List<Saida> saidas = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Saida saida = new Saida();
                saida.setIdSaida(rs.getInt("idSaida"));
                saida.setDataSaida(rs.getDate("dataSaida").toLocalDate());
                saida.setPrecoSaida(rs.getDouble("precoSaida"));

                
                Usuario usuario = new Usuario();  
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                saida.setUsuario(usuario);

                Produto produto = new Produto();  
                produto.setIdProduto(rs.getInt("idProduto"));
                saida.setProduto(produto);

                Cliente cliente = new Cliente();  
                cliente.setIdCliente(rs.getInt("idCliente"));
                saida.setCliente(cliente);

                saidas.add(saida);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as saídas: " + e.getMessage(), e);
        }
        return saidas;
    }
}