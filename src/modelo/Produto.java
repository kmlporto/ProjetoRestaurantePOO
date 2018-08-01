package modelo;
public class Produto {
	private String nome;
	private double preco;
	
	// gets e sets
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	// construtor e toString
	public Produto(String nome, double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "\nProduto \n nome: " + nome + "\n preço: R$" + preco;
	}
	
}
