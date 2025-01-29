package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Saida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idSaida;
	private LocalDate dataSaida;
	private Double precoSaida;
	
	private Usuario usuario;
	private Produto produto;
	private Cliente cliente;
	
	public Saida() {
		
	}

	public Saida(Integer idSaida, LocalDate dataSaida, Double precoSaida, Usuario usuario, Produto produto,
			Cliente cliente) {
		this.idSaida = idSaida;
		this.dataSaida = dataSaida;
		this.precoSaida = precoSaida;
		this.usuario = usuario;
		this.produto = produto;
		this.cliente = cliente;
	}

	public Integer getIdSaida() {
		return idSaida;
	}

	public void setIdSaida(Integer idSaida) {
		this.idSaida = idSaida;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Double getPrecoSaida() {
		return precoSaida;
	}

	public void setPrecoSaida(Double precoSaida) {
		this.precoSaida = precoSaida;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataSaida, idSaida, precoSaida, produto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Saida other = (Saida) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(dataSaida, other.dataSaida)
				&& Objects.equals(idSaida, other.idSaida) && Objects.equals(precoSaida, other.precoSaida)
				&& Objects.equals(produto, other.produto) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Saida [idSaida=" + idSaida + ", dataSaida=" + dataSaida + ", precoSaida=" + precoSaida + ", usuario="
				+ usuario + ", produto=" + produto + ", cliente=" + cliente + "]";
	}
	
}
