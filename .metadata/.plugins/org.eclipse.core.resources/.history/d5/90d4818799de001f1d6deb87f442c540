package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.BaseDao;
import model.entities.Cliente;

public class ClienteDaoJDBC implements BaseDao<Cliente> {

    private Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente obj) {
        String sql = "INSERT INTO cliente (nomeCliente, telefone, enderecoCliente, cidade, bairro, cep, uf) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getNomeCliente());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getEndercoCliente());
            st.setString(4, obj.getCidade());
            st.setString(5, obj.getBairro());
            st.setString(6, obj.getCep());
            st.setString(7, obj.getUf());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdCliente(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Cliente obj) {
        String sql = "UPDATE cliente SET nomeCliente = ?, telefone = ?, enderecoCliente = ?, cidade = ?, bairro = ?, cep = ?, uf = ? WHERE idCliente = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNomeCliente());
            st.setString(2, obj.getTelefone());
            st.setString(3, obj.getEndercoCliente());
            st.setString(4, obj.getCidade());
            st.setString(5, obj.getBairro());
            st.setString(6, obj.getCep());
            st.setString(7, obj.getUf());
            st.setInt(8, obj.getIdCliente());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public Cliente findById(Integer id) {
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setNomeCliente(rs.getString("nomeCliente"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEndercoCliente(rs.getString("enderecoCliente"));
                    cliente.setCidade(rs.getString("cidade"));
                    cliente.setBairro(rs.getString("bairro"));
                    cliente.setCep(rs.getString("cep"));
                    cliente.setUf(rs.getString("uf"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nomeCliente"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndercoCliente(rs.getString("enderecoCliente"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCep(rs.getString("cep"));
                cliente.setUf(rs.getString("uf"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os clientes: " + e.getMessage(), e);
        }
        return clientes;
    }
}
