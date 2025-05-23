package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.BaseDao;
import model.entities.Fornecedor;

public class FornecedorDaoJDBC implements BaseDao<Fornecedor> {
    
    private Connection conn;
    
    public FornecedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insert(Fornecedor obj) {
        String sql = "INSERT INTO fornecedor (nomeFornecedor, endereco, cidade, bairro, cep, uf, tel, cnpj) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getNomeFornecedor());
            st.setString(2, obj.getEnderecoFornecedor());
            st.setString(3, obj.getCidade());
            st.setString(4, obj.getBairro());
            st.setString(5, obj.getCep());
            st.setString(6, obj.getUf());
            st.setString(7, obj.getTelefone()); 
            st.setString(8, obj.getCnpj());
            
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdFornecedor(rs.getInt(1)); 
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fornecedor: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Fornecedor obj) {
        String sql = "UPDATE fornecedor SET nomeFornecedor = ?, endereco = ?, cidade = ?, bairro = ?, cep = ?, uf = ?, tel = ?, cnpj = ? "
                   + "WHERE idFornecedor = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNomeFornecedor());
            st.setString(2, obj.getEnderecoFornecedor());
            st.setString(3, obj.getCidade());
            st.setString(4, obj.getBairro());
            st.setString(5, obj.getCep());
            st.setString(6, obj.getUf());
            st.setString(7, obj.getTelefone()); 
            st.setString(8, obj.getCnpj());
            st.setInt(9, obj.getIdFornecedor());
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fornecedor: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM fornecedor WHERE idFornecedor = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir fornecedor: " + e.getMessage(), e);
        }
    }

    @Override
    public Fornecedor findById(Integer id) {
        String sql = "SELECT * FROM fornecedor WHERE idFornecedor = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                    fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
                    fornecedor.setEnderecoFornecedor(rs.getString("endereco"));
                    fornecedor.setCidade(rs.getString("cidade"));
                    fornecedor.setBairro(rs.getString("bairro"));
                    fornecedor.setCep(rs.getString("cep"));
                    fornecedor.setUf(rs.getString("uf"));
                    fornecedor.setTelefone(rs.getString("tel"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    return fornecedor;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedor por ID: " + e.getMessage(), e);
        }
        return null;
    }
    
    @Override
    public List<Fornecedor> findAll() {
        String sql = "SELECT * FROM fornecedor";
        List<Fornecedor> fornecedores = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
                fornecedor.setEnderecoFornecedor(rs.getString("endereco"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setTelefone(rs.getString("tel"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedores: " + e.getMessage(), e);
        }
        return fornecedores;
    }
    
    public List<Fornecedor> findByName(String nomeFornecedor) {
        String sql = "SELECT * FROM fornecedor WHERE nomeFornecedor LIKE ?";
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + nomeFornecedor + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                    fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
                    fornecedor.setEnderecoFornecedor(rs.getString("endereco"));
                    fornecedor.setCidade(rs.getString("cidade"));
                    fornecedor.setBairro(rs.getString("bairro"));
                    fornecedor.setCep(rs.getString("cep"));
                    fornecedor.setUf(rs.getString("uf"));
                    fornecedor.setTelefone(rs.getString("tel"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedores.add(fornecedor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedores pelo nome: " + e.getMessage(), e);
        }

        if (!fornecedores.isEmpty()) {
            Fornecedor fornecedor = fornecedores.get(0);
            System.out.println(fornecedor);
        } else {
            System.out.println("Fornecedor não encontrado.");
        }
        return fornecedores;
    	}
	}

