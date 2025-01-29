package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCliente;
	private String nomeCliente;
	private Integer telefone;
	private String endercoCliente;
	private String cidade;
	private String bairro;
	private String cep;
	private String uf;
	
	public Cliente() {
		
	}

	public Cliente(Integer idCliente, String nomeCliente, Integer telefone, String endercoCliente, String cidade,
			String bairro, String cep, String uf) {
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.telefone = telefone;
		this.endercoCliente = endercoCliente;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.uf = uf;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getEndercoCliente() {
		return endercoCliente;
	}

	public void setEndercoCliente(String endercoCliente) {
		this.endercoCliente = endercoCliente;
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

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, cidade, endercoCliente, idCliente, nomeCliente, telefone, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(endercoCliente, other.endercoCliente)
				&& Objects.equals(idCliente, other.idCliente) && Objects.equals(nomeCliente, other.nomeCliente)
				&& Objects.equals(telefone, other.telefone) && Objects.equals(uf, other.uf);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", telefone=" + telefone
				+ ", endercoCliente=" + endercoCliente + ", cidade=" + cidade + ", bairro=" + bairro + ", cep=" + cep
				+ ", uf=" + uf + "]";
	}
	
	
	
	
}
