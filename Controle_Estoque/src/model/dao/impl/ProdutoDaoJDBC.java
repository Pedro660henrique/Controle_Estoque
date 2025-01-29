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
	import model.entities.Produto;
	
	public class ProdutoDaoJDBC implements BaseDao<Produto>{
		
		private Connection conn;
		
		public ProdutoDaoJDBC(Connection conn) {
			this.conn = conn;
		}
	
		@Override
		public void insert(Produto obj) {
			PreparedStatement st = null;
			try {
				st = conn.prepareStatement(
						 "INSERT INTO produto (nomeProduto, marcaProduto, precoProduto, lote, quantidade, idFornecedor) "
						          + "VALUES"
						          + " (?, ?, ?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
				st.setString(1, obj.getNomeProduto());
				st.setString(2, obj.getMarcaProduto());
				st.setDouble(3, obj.getPrecoProduto());
				st.setInt(4, obj.getLote());
				st.setInt(5, obj.getQuantidade());	
				st.setObject(6, (obj.getFornecedor() != null) ? obj.getFornecedor().getIdFornecedor() : null);
				
				int rowsAffected = st.executeUpdate();
				
				if(rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if(rs.next()) {
						obj.setIdProduto(rs.getInt(1));
					}
					rs.close();
				}
				
			}catch (SQLException e) {
				throw new RuntimeException("Erro ao inserir produto: " + e.getMessage(), e);
		    } finally {
		        try {
		            if (st != null) st.close();
		        } catch (SQLException e) {
		            throw new RuntimeException("Erro ao fechar recurso: " + e.getMessage(), e);
		        }
		    
			}
		}
	
		@Override
		public void update(Produto obj) {
			PreparedStatement st = null;
		    try {
		        st = conn.prepareStatement("UPDATE produto SET nomeProduto = ?, marcaProduto = ?, precoProduto = ?, lote = ?, quantidade = ?, idFornecedor = ? "
		                + "WHERE idProduto = ?");
	
		        st.setString(1, obj.getNomeProduto());
		        st.setString(2, obj.getMarcaProduto());
		        st.setDouble(3, obj.getPrecoProduto());
		        st.setInt(4, obj.getLote());
		        st.setInt(5, obj.getQuantidade());
		        st.setObject(6, (obj.getFornecedor() != null) ? obj.getFornecedor().getIdFornecedor() : null);
		        st.setInt(7, obj.getIdProduto());
	
		        int rowsAffected = st.executeUpdate();
		        if (rowsAffected == 0) {
		            throw new SQLException("Nenhum produto foi atualizado.");
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
		    } finally {
		        try {
		            if (st != null) st.close();
		        } catch (SQLException e) {
		            throw new RuntimeException("Erro ao fechar recurso: " + e.getMessage(), e);
		        }
		    }
		}
	
		@Override
		public void deleteById(Integer id) {
			PreparedStatement st = null;
		    try {
		        st = conn.prepareStatement("DELETE FROM produto WHERE idProduto = ?");
		        st.setInt(1, id);
	
		        int rowsAffected = st.executeUpdate();
	
		        if (rowsAffected == 0) {
		            throw new SQLException("Nenhum produto foi excluído.");
		        }
		    } catch (SQLException e) {
		        throw new RuntimeException("Erro ao excluir produto: " + e.getMessage(), e);
		    } finally {
		        try {
		            if (st != null) st.close();
		        } catch (SQLException e) {
		            throw new RuntimeException("Erro ao fechar recurso: " + e.getMessage(), e);
		        }
		    }
		}
	
		@Override
		public Produto findById(Integer id) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT p.idProduto, p.nomeProduto, p.marcaProduto, p.precoProduto, "
				          + "p.lote, p.quantidade, f.idFornecedor, f.nomeFornecedor "
				          + "FROM produto p "
				          + "LEFT JOIN fornecedor f ON p.idFornecedor = f.idFornecedor "
				          + "WHERE p.idProduto = ?");
				
				st.setInt(1, id);
				rs = st.executeQuery();
				if(rs.next()) {
					Produto produto = new Produto();
					produto.setIdProduto(rs.getInt("idProduto"));
					produto.setNomeProduto(rs.getString("nomeProduto"));
					produto.setMarcaProduto(rs.getString("marcaProduto"));
					produto.setPrecoProduto(rs.getDouble("precoProduto"));
					produto.setLote(rs.getInt("lote"));
					produto.setQuantidade(rs.getInt("Quantidade"));
					
					Integer fornecedorId = rs.getInt("idFornecedor");
					if(fornecedorId != null) {
						Fornecedor fornecedor = new Fornecedor();
						fornecedor.setIdFornecedor(fornecedorId);
						fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
						produto.setFornecedor(fornecedor);
					}
					return produto;
				}
				return null;
			}catch(SQLException e) {
				throw new RuntimeException("Erro ao buscar produto por ID: " + e.getMessage(), e);
			}finally {
				try {
					if(rs != null) rs.close();
					if(st != null) st.close();
				}catch(SQLException e) {
					throw new RuntimeException("Erro ao fechar recursos: " + e.getMessage(), e);
				}
			}
		}
	
		@Override
		public List<Produto> findAll() {
			PreparedStatement st = null;
			ResultSet rs = null;
			List<Produto> produtos = new ArrayList();
			
			try {
				st = conn.prepareStatement("SELECT p.idProduto, p.nomeProduto, p.marcaProduto, p.precoProduto, "
				          + "p.lote, p.quantidade, f.idFornecedor, f.nomeFornecedor "
				          + "FROM produto p "
				          + "LEFT JOIN fornecedor f ON p.idFornecedor = f.idFornecedor ");
				
				rs = st.executeQuery();
				if(rs.next()) {
					Produto produto = new Produto();
					produto.setIdProduto(rs.getInt("idProduto"));
					produto.setNomeProduto(rs.getString("nomeProduto"));
					produto.setMarcaProduto(rs.getString("marcaProduto"));
					produto.setPrecoProduto(rs.getDouble("precoProduto"));
					produto.setLote(rs.getInt("lote"));
					produto.setQuantidade(rs.getInt("Quantidade"));
					
					Integer fornecedorId = rs.getInt("idFornecedor");
					if(fornecedorId != null) {
						Fornecedor fornecedor = new Fornecedor();
						fornecedor.setIdFornecedor(fornecedorId);
						fornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
						produto.setFornecedor(fornecedor);
					}
					produtos.add(produto);
				}
				return produtos;
			}catch(SQLException e) {
				throw new RuntimeException("Erro ao buscar produto por ID: " + e.getMessage(), e);
			}finally {
				try {
					if(rs != null) rs.close();
					if(st != null) st.close();
				}catch(SQLException e) {
					throw new RuntimeException("Erro ao fechar recursos: " + e.getMessage(), e);
					}
				}
			}
		}
	
