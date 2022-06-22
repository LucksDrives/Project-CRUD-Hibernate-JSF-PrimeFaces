/*CLASSE CLIENTE*/
package bd;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity //Declarar para Utilização de Framework
public class Cliente {
	
	@Id //Declarar para Utilização de Framework
	@GeneratedValue(strategy = GenerationType.AUTO) //Declarar para Utilização de Framework
	
	//Variáveis
	private int id;
	private String nome;
	private String cpf;
	
	//Criação da Relação das Entidades UM PARA UM
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Endereco endereco = new Endereco();
	
	
	/*Get e Set dos Valores*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	//Gets e Sets Classes Relacionais
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	/*Verificar se o ID é Igual [Fazer increment ao gerar o banco]*/
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}
}
