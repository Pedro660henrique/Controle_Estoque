package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dao.BaseDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements BaseDao<Usuario> {

    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario obj) {
        String sql = "INSERT INTO usuario (nomeUsuario, dataNascimento, senha) VALUES (?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getNomeUsuario());
            st.setObject(2, obj.getDataNascimento());
            st.setString(3, obj.getSenha());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdUsuario(rs.getInt(1)); 
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Usuario obj) {
        String sql = "UPDATE usuario SET nomeUsuario = ?, dataNascimento = ?, senha = ? WHERE idUsuario = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNomeUsuario());
            st.setObject(2, obj.getDataNascimento());
            st.setString(3, obj.getSenha());
            st.setInt(4, obj.getIdUsuario());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário: " + e.getMessage(), e);
        }
    }

    @Override
    public Usuario findById(Integer id) {
        String sql = "SELECT * FROM usuario WHERE idUsuario = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                    usuario.setDataNascimento(rs.getObject("dataNascimento", LocalDate.class));
                    usuario.setSenha(rs.getString("senha"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                usuario.setDataNascimento(rs.getObject("dataNascimento", LocalDate.class));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os usuários: " + e.getMessage(), e);
        }
        return usuarios;
    }
}