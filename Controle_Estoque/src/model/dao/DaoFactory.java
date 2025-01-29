package model.dao;

import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
import model.entities.Fornecedor;
import model.entities.Produto;

public class DaoFactory {
	
	public static BaseDao<Produto> createProdutoDao() {
		return new ProdutoDaoJDBC();
	}
	
	public static BaseDao<Fornecedor> createFornecedorDao(){
		return new FornecedorDaoJDBC();
	}
 
}
