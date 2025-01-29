package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idProduto;
	private String nomeProduto;
	private String marcaProduto;
	private Double precoProduto;
	private Integer lote;
	private Integer quantidade;
	
	private Fornecedor fornecedor;
	
	public Produto() {
		
	}

	public Produto(Integer idProduto, String nomeProduto, String marcaProduto, Double precoProduto, Integer lote,
			Integer quantidade, Fornecedor fornecedor) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.marcaProduto = marcaProduto;
		this.precoProduto = precoProduto;
		this.lote = lote;
		this.quantidade = quantidade;
		this.fornecedor = fornecedor;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarcaProduto() {
		return marcaProduto;
	}

	public void setMarcaProduto(String marcaProduto) {
		this.marcaProduto = marcaProduto;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public Integer getLote() {
		return lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fornecedor, idProduto, lote, marcaProduto, nomeProduto, precoProduto, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(fornecedor, other.fornecedor) && Objects.equals(idProduto, other.idProduto)
				&& Objects.equals(lote, other.lote) && Objects.equals(marcaProduto, other.marcaProduto)
				&& Objects.equals(nomeProduto, other.nomeProduto) && Objects.equals(precoProduto, other.precoProduto)
				&& Objects.equals(quantidade, other.quantidade);
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", marcaProduto=" + marcaProduto
				+ ", precoProduto=" + precoProduto + ", lote=" + lote + ", quantidade=" + quantidade + ", fornecedor="
				+ fornecedor + "]";
	}
	
	
}
