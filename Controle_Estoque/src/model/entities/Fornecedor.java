package model.entities;

import java.util.Objects;

public class Fornecedor {
	
	private Integer idFornecedor;
	private String nomeFornecedor;
	private String enderecoFornecedor;
	private String cidade;
	private String bairro;
	private String cep;
	private String uf;
	private Integer telefone;
	private String cnpj;
	
	public Fornecedor() {
	}

	public Fornecedor(Integer idFornecedor, String nomeFornecedor, String enderecoFornecedor, String cidade,
			String bairro, String cep, String uf, Integer telefone, String cnpj) {
		this.idFornecedor = idFornecedor;
		this.nomeFornecedor = nomeFornecedor;
		this.enderecoFornecedor = enderecoFornecedor;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.uf = uf;
		this.telefone = telefone;
		this.cnpj = cnpj;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getEnderecoFornecedor() {
		return enderecoFornecedor;
	}

	public void setEnderecoFornecedor(String enderecoFornecedor) {
		this.enderecoFornecedor = enderecoFornecedor;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, cnpj, enderecoFornecedor, idFornecedor, nomeFornecedor, telefone, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(cnpj, other.cnpj)
				&& Objects.equals(enderecoFornecedor, other.enderecoFornecedor)
				&& Objects.equals(idFornecedor, other.idFornecedor)
				&& Objects.equals(nomeFornecedor, other.nomeFornecedor) && Objects.equals(telefone, other.telefone)
				&& Objects.equals(uf, other.uf);
	}

	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nomeFornecedor=" + nomeFornecedor
				+ ", enderecoFornecedor=" + enderecoFornecedor + ", cidade=" + cidade + ", bairro=" + bairro + ", cep="
				+ cep + ", uf=" + uf + ", telefone=" + telefone + ", cnpj=" + cnpj + "]";
	}
	
}
