package modelo;
import java.util.ArrayList;

public class Conta {
	private int numero;
	private String dtfechamento;
	private double total;
	private Mesa mesa;
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private Pagamento pagamento;
	
	// gets e sets
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDtfechamento() {
		return dtfechamento;
	}
	public void setDtfechamento(String dtfechamento) {
		this.dtfechamento = dtfechamento;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}	
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	// adicionar, remover e localizar
	public void adicionar(Produto p) {
		produtos.add(p);
	}
	public void remover(Produto p) {
		produtos.remove(p);
	}
	public Produto localizar(String nome) {
		for (Produto p: produtos)
			if(p.getNome().equals(nome)) return p;
		return null;
	}
	
	// construtor e toString
	public Conta(int numero, Mesa mesa) {
		super();
		this.numero = numero;
		this.mesa = mesa;
	}
	@Override
	public String toString() {
		String txt = "\nConta \n número: " + numero + "\n data fechamento: " + dtfechamento + "\n total: R$" + total +"\n mesa: " + mesa.getId() + "\n produtos: ";
		if (produtos != null)
			for (Produto p: produtos)
				txt +=  "\n   nome: "+ p.getNome()+ "\n   preço: R$" + p.getPreco();
		if(this.getPagamento() != null)
			txt += this.getPagamento();
		return txt;
	}
	
	
}
