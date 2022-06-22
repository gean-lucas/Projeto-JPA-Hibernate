package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Livro;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {
	
	public static void main(String[] args) {
		cadastrarLivro();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		List<Produto> res = produtoDao.buscarPorParametrosComCriteria("Macbook", null, null);
		res.forEach(x -> System.out.println(x.getDescricao()));
		
//		Produto produtoParaDeletar = produtoDao.buscarPorId(1l);
//		produtoParaDeletar = em.merge(produtoParaDeletar);
//		
//		em.remove(produtoParaDeletar);
		
		
	}

	
	@SuppressWarnings("unused")
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("10000"), videogames );
        Produto notebook = new Produto("Macbook", "Macboo Pro", new BigDecimal("20000"), informatica );

        Cliente cliente = new Cliente("Rodrigo", "123456");
        
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em); 

        em.getTransaction().begin();

        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(notebook);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();

}
	
    private static void cadastrarLivro(){
        EntityManager em = JPAUtil.getEntityManager();

        Livro livro1 = new Livro("Himura Keiji", 500);
        

        em.getTransaction().begin();
        em.persist(livro1);
        em.getTransaction().commit();
        em.close();
    }
	
}
