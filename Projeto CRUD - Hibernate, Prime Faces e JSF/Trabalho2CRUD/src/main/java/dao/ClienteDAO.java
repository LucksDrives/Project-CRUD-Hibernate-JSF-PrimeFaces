/*CLASSE CLIENTE*/
package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bd.Cliente;

public class ClienteDAO {

	EntityManagerFactory sf = Persistence.createEntityManagerFactory("EmpresaGames");

	EntityManager em2 = sf.createEntityManager();


	private List<Cliente> todosClientes= new ArrayList<Cliente>();

	@SuppressWarnings("unchecked")
	public List<Cliente> getTodosClientes() {
		return em2.createQuery("From Cliente").getResultList();
	}

	public void setTodosClientes(List<Cliente> todosClientes) {
		this.todosClientes = todosClientes;
	}

	private Cliente cliente = new Cliente();
	private static Cliente clienteId = new Cliente();

	public Cliente getClienteId() {
		System.out.println("ID DO CLIENTE NO GET:" + clienteId.getId());		
		return em2.find(Cliente.class, clienteId.getId());
	}

	@SuppressWarnings("static-access")
	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String operacao;
	public List<Cliente> listaClientes() {
		return todosClientes;
	}


	//CONSULTAR
	public void consultarClientesId() {
		System.out.println("[ENTROU NO CONSULTA CLIENTE]");
		em2.getTransaction().begin();
		Query p = em2.createQuery("From Cliente");
		@SuppressWarnings("unchecked") 
		List<Cliente> clientes = p.getResultList();

		for (Cliente c : clientes) {
			if(cliente.getId() == c.getId()) {
				clienteId = em2.find(Cliente.class, cliente.getId());
				System.out.println("ID DO PRODUTO:" + clienteId.getId());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> consultarTodosClientes() {
		System.out.println("[Entrou]");
		return em2.createQuery("From Cliente").getResultList();
	}

	//CADASTRARs
	public void cadastrarClientes() {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		// Cadastrar Clientes

		cliente.setNome(cliente.getNome());

		cliente.setCpf(cliente.getCpf());

		//Endereço

		cliente.getEndereco().setLogradouro(cliente.getEndereco().getLogradouro());

		cliente.getEndereco().setCidade(cliente.getEndereco().getCidade());

		cliente.getEndereco().setBairro(cliente.getEndereco().getBairro());

		cliente.getEndereco().setUf(cliente.getEndereco().getUf());

		cliente.getEndereco().setNumero(cliente.getEndereco().getNumero());

		cliente.getEndereco().setComplemento(cliente.getEndereco().getComplemento());

		cliente.getEndereco().setCep(cliente.getEndereco().getCep());

		em2.persist(cliente);
		em2.getTransaction().commit();
		em2.close();

		System.out.println("Cliente Cadastrado\n");
	}

	//ALTERAR
	public void alteraCliente () {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		Query p = em2.createQuery("From Cliente");
		@SuppressWarnings("unchecked") 
		List<Cliente> clientes = p.getResultList();

		for (Cliente c : clientes) {
			if(cliente.getId() == c.getId()) {
				// Alterar Clientes

				cliente = em2.find(Cliente.class, cliente.getId());

				cliente.setNome(cliente.getNome());

				cliente.setCpf(cliente.getCpf());

				//Endereço

				cliente.getEndereco().setLogradouro(cliente.getEndereco().getLogradouro());

				cliente.getEndereco().setCidade(cliente.getEndereco().getCidade());

				cliente.getEndereco().setBairro(cliente.getEndereco().getBairro());

				cliente.getEndereco().setUf(cliente.getEndereco().getUf());

				cliente.getEndereco().setNumero(cliente.getEndereco().getNumero());

				cliente.getEndereco().setComplemento(cliente.getEndereco().getComplemento());

				cliente.getEndereco().setCep(cliente.getEndereco().getCep());

				em2.merge(cliente);		
				em2.getTransaction().commit();
				em2.close();
				System.out.println("Cliente Alterado\n");
				break;
			}
		}
	}

	//EXCLUIR
	public void excluiClienteUnico () {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		Query p = em2.createQuery("From Cliente");
		@SuppressWarnings("unchecked") 
		List<Cliente> clientes = p.getResultList();

		for (Cliente c : clientes) {
			if(c.getId() == c.getId()) {
				// Excluir Cliente
				System.out.println("Cliente Removido\n");
				em2.remove(cliente.getId());
				em2.getTransaction().commit();
				em2.close();				
				break;
			}
		}
	}	

	public void excluiTodosClientes () {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		int quant = em2.createQuery("Delete From Cliente").executeUpdate();		
		em2.getTransaction().commit();
		System.out.println("Quantidade de Clientes Removidos: " + quant);
		System.out.println("Clientes Removidos com Sucesso!\n");
	}

}
