package repositorio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;

public class Restaurante {
	private ArrayList<Produto> produtos = new ArrayList <Produto>();
	private ArrayList<Mesa> mesas = new ArrayList <Mesa>();
	private ArrayList<Conta> contas = new ArrayList <Conta>();
	private TreeMap<String, Garcom> garcons = new TreeMap<>();
	
	// adicionar, remover e localizar
	public void adicionar(Produto p) {
		produtos.add(p);
	}
	public void remover(Produto p) {
		produtos.remove(p);
	}
	public Produto localizarProduto(String nome) {
		for (Produto p: produtos) {
			if(p.getNome().equals(nome)) 
				return p;
		}
		return null;
	}
	public void adicionar(Mesa m) {
		mesas.add(m);
	}
	public void remover(Mesa m) {
		mesas.remove(m);
	}
	public Mesa localizarMesa(int id) {
		for (Mesa m: mesas) {
			if(m.getId() == id) return m;
		}
		return null;
	}
	public void adicionar(Garcom g) {
		garcons.put(g.getApelido(),g);
	}
	public void remover(Garcom g) {
		garcons.remove(g.getApelido());
	}
	public Garcom localizarGarcom(String apelido) {
		return garcons.get(apelido);
	}
	public void adicionar(Conta c) {
		contas.add(c);
	}
	public void remover(Conta c) {
		contas.remove(c);
	}
	public Conta localizarConta(int numero) {
		for (Conta c: contas) {
			if(c.getNumero() == numero) return c;
		}
		return null;
	}
	
	// get's
	public ArrayList<Produto> getProdutos(){
		Collections.sort(produtos);
		return produtos;
	}
	public ArrayList <Garcom> getGarcons() {
		return new ArrayList<Garcom> (garcons.values());
	}
	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	public ArrayList<Conta> getContas() {
		return contas;
	}
	

}
