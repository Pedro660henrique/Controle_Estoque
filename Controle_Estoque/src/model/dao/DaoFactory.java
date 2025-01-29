package model.dao;

import db.DB;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.EntradaDaoJDBC;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
import model.dao.impl.SaidaDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Cliente;
import model.entities.Entrada;
import model.entities.Fornecedor;
import model.entities.Saida;
import model.entities.Usuario;	

public class DaoFactory {
	
	public static ProdutoDaoJDBC createProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
	
	public static BaseDao<Fornecedor> createFornecedorDao(){
		return new FornecedorDaoJDBC(DB.getConnection());
	}
	public static BaseDao<Usuario> createUsuarioDao(){
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	public static BaseDao<Cliente> createClienteDao(){
		return new ClienteDaoJDBC(DB.getConnection());
	}
	public static BaseDao<Entrada> createEntradaDao(){
		return new EntradaDaoJDBC(DB.getConnection());
	}
	public static BaseDao<Saida> createSaidaDao(){
		return new SaidaDaoJDBC(DB.getConnection());
	}
	
}
