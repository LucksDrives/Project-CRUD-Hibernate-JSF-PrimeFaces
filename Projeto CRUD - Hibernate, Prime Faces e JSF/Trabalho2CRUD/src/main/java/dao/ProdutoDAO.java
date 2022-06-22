/*CLASSE CLIENTE*/
package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import bd.Produto;

public class ProdutoDAO {

	EntityManagerFactory sf = Persistence.createEntityManagerFactory("EmpresaGames");

	EntityManager em2 = sf.createEntityManager();


	private List<Produto> todosProdutos= new ArrayList<Produto>();

	@SuppressWarnings("unchecked")
	public List<Produto> getTodosProdutos() {
		return em2.createQuery("From Produto").getResultList();
	}

	public void setTodosProdutos(List<Produto> todosProdutos) {
		this.todosProdutos = todosProdutos;
	}

	private Produto produto = new Produto();
	private static Produto produtoId = new Produto();

	public Produto getProdutoId() {
		System.out.println("ID DO PRODUTO NO GET:" + produtoId.getId());		
		return em2.find(Produto.class, produtoId.getId());
	}

	@SuppressWarnings("static-access")
	public void setProdutoId(Produto produtoId) {
		this.produtoId = produtoId;
	}

	public Produto getProduto() {
		return produto;
	}		

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String operacao;
	public List<Produto> listaProdutos() {
		return todosProdutos;
	}

	//CONSULTAR
	public void consultarProdutosId() {
		System.out.println("[ENTROU NO CONSULTA PRODUTO]");
		em2.getTransaction().begin();
		Query p = em2.createQuery("From Produto");
		@SuppressWarnings("unchecked") 
		List<Produto> produtos = p.getResultList();

		for (Produto c : produtos) {
			if(produto.getId() == c.getId()) {
				produtoId = em2.find(Produto.class, produto.getId());
				System.out.println("ID DO PRODUTO:" + produtoId.getId());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> consultarTodoProdutos() {
		return em2.createQuery("From Produto").getResultList();
	}
	//CADASTRAR	
	public void cadastrarGames() {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		produto.setNome(produto.getNome());

		produto.setValor(produto.getValor());

		em2.persist(produto);
		em2.getTransaction().commit();
		System.out.println("Produto Cadastrado\n");
		em2.close();
	}

	//ALTERAR
	public void alteraGame () {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		Query p = em2.createQuery("From Produto");
		@SuppressWarnings("unchecked") 
		List<Produto> produtos = p.getResultList();

		for (Produto c : produtos) {
			if(produto.getId() == c.getId()) {
				// Alterar Cliente

				produto.setNome(produto.getNome());

				produto.setValor(produto.getValor());

				em2.merge(produto);		
				em2.getTransaction().commit();
				em2.close();
				System.out.println("Produto Alterado\n");
				break;
			}
		}
		System.out.println("Produto Alterado\n");
	}

	//EXCLUIR
	public void excluiGameUnico () {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		Query p = em2.createQuery("From Produto");
		@SuppressWarnings("unchecked") 
		List<Produto> produtos = p.getResultList();

		for (Produto c : produtos) {
			if(c.getId() == c.getId()) {
				// Excluir Cliente
				System.out.println("Produto Removido\n");
				em2.remove(produto.getId());
				em2.getTransaction().commit();
				em2.close();
				break;
			}
		}
	}

	public void excluiTodosGames () {
		System.out.println("[Entrou]\n");
		em2.getTransaction().begin();
		int quant = em2.createQuery("Delete From Produto").executeUpdate();		
		em2.getTransaction().commit();
		System.out.println("Quantidade de Produtos Removidos: " + quant);
		System.out.println("Produtos Removidos com Sucesso!\n");
	}
}
