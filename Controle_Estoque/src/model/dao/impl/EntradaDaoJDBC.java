package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.BaseDao;
import model.entities.Entrada;
import model.entities.Fornecedor;
import model.entities.Produto;
import model.entities.Usuario;

public class EntradaDaoJDBC implements BaseDao<Entrada> {

    private Connection conn;

    public EntradaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Entrada obj) {
        String sql = "INSERT INTO entrada (dataEntrada, idUsuario, idProduto, idFornecedor) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setDate(1, java.sql.Date.valueOf(obj.getDataEntrada()));  
            st.setInt(2, obj.getUsuario().getIdUsuario());
            st.setInt(3, obj.getProduto().getIdProduto());
            st.setInt(4, obj.getFornecedor().getIdFornecedor());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdEntrada(rs.getInt(1));  
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir entrada: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Entrada obj) {
        String sql = "UPDATE entrada SET dataEntrada = ?, idUsuario = ?, idProduto = ?, idFornecedor = ? WHERE idEntrada = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, java.sql.Date.valueOf(obj.getDataEntrada())); 
            st.setInt(2, obj.getUsuario().getIdUsuario());
            st.setInt(3, obj.getProduto().getIdProduto());
            st.setInt(4, obj.getFornecedor().getIdFornecedor());
            st.setInt(5, obj.getIdEntrada());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar entrada: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM entrada WHERE idEntrada = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir entrada: " + e.getMessage(), e);
        }
    }

    @Override
    public Entrada findById(Integer id) {
        String sql = "SELECT * FROM entrada WHERE idEntrada = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Entrada entrada = new Entrada();
                    entrada.setIdEntrada(rs.getInt("idEntrada"));
                    entrada.setDataEntrada(rs.getDate("dataEntrada").toLocalDate());
                    
                   
                    Usuario usuario = new Usuario();  
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    entrada.setUsuario(usuario);

                    Produto produto = new Produto();  
                    produto.setIdProduto(rs.getInt("idProduto"));
                    entrada.setProduto(produto);

                    Fornecedor fornecedor = new Fornecedor();  
                    fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                    entrada.setFornecedor(fornecedor);

                    return entrada;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entrada por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Entrada> findAll() {
        String sql = "SELECT * FROM entrada";
        List<Entrada> entradas = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Entrada entrada = new Entrada();
                entrada.setIdEntrada(rs.getInt("idEntrada"));
                entrada.setDataEntrada(rs.getDate("dataEntrada").toLocalDate());

            
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                entrada.setUsuario(usuario);

                Produto produto = new Produto(); 
                produto.setIdProduto(rs.getInt("idProduto"));
                entrada.setProduto(produto);

                Fornecedor fornecedor = new Fornecedor(); 
                fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                entrada.setFornecedor(fornecedor);

                entradas.add(entrada);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as entradas: " + e.getMessage(), e);
        }
        return entradas;
    }
}
