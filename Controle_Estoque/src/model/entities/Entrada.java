package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Entrada implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idEntrada;
	private LocalDate dataEntrada;
	
	private Usuario usuario;
	private Produto produto;
	private Fornecedor fornecedor;
	
	public Entrada() {
		
	}

	public Entrada(Integer idEntrada, LocalDate dataEntrada, Usuario usuario, Produto produto, Fornecedor fornecedor) {
		this.idEntrada = idEntrada;
		this.dataEntrada = dataEntrada;
		this.usuario = usuario;
		this.produto = produto;
		this.fornecedor = fornecedor;
	}

	public Integer getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataEntrada, fornecedor, idEntrada, produto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return Objects.equals(dataEntrada, other.dataEntrada) && Objects.equals(fornecedor, other.fornecedor)
				&& Objects.equals(idEntrada, other.idEntrada) && Objects.equals(produto, other.produto)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Entrada [idEntrada=" + idEntrada + ", dataEntrada=" + dataEntrada + ", usuario=" + usuario
				+ ", produto=" + produto + ", fornecedor=" + fornecedor + "]";
	}
	
}
