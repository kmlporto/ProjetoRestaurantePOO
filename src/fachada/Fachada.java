package fachada;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import modelo.Produto;
import repositorio.Restaurante;
import modelo.Garcom;
import modelo.Conta;
import modelo.Mesa;


public class Fachada {
	public static Restaurante restaurante = new Restaurante();
	public static int numconta =0;
	
	public static ArrayList<Produto>listarProdutos(){
		return restaurante.getProdutos();
	}
	public static ArrayList<Produto>listarProdutos(String nome){
		ArrayList<Produto> auxprod = new ArrayList<>();
		for(Produto p: restaurante.getProdutos()) {
			if(p.getNome().contains(nome))auxprod.add(p);
		}
		return auxprod;
	}
	public static ArrayList<Mesa>listarMesas(){
		return restaurante.getMesas();
	}
	public static ArrayList<Garcom>listarGarcons(){
		return restaurante.getGarcons();
	}
	public static ArrayList<Conta>listarContas(){
		return restaurante.getContas();
	}
	
	public static void criarMesas (int num) throws Exception{
		if (num>0) {
			for (int i = 1; i<=num; i++) {
				Mesa m = new Mesa(i);
				restaurante.adicionar(m);
			}
		}else
			throw new Exception("n�mero de mesas inv�lido!");
	}
	
	public static Produto cadastrarProduto(String nome, double preco)throws Exception {
		Produto aux = restaurante.localizarProduto(nome);
		if (aux !=null) {
			throw new Exception("produto j� cadastrado!");	
		}
		if(preco <= 0) {
			throw new Exception("valor do pre�o inv�lido!");
		}
		if(nome.isEmpty()) {
			throw new Exception ("nome do produto inv�lido!");
		}
		Produto p = new Produto(nome, preco);
		restaurante.adicionar(p);
		return p;
	}
	public static Garcom cadastrarGarcom(String apelido, int mesainicial, int mesafinal) throws Exception{
		if(mesafinal - mesainicial != 4) {
			throw new Exception ("intervalo de mesas inv�lidos!");
		}
		Garcom g = new Garcom(apelido);
		for (int aux = mesainicial; aux<=mesafinal ; aux++) {
			Mesa mesa = restaurante.localizarMesa(aux);
			if(mesa == null) {
				throw new Exception ("mesa inexistente");
			}else if(mesa.getGarcom()!= null) {
				throw new Exception ("mesa j� possui gar�om");
			}
			g.adicionar(mesa);
		}
		for (Mesa m : g.getMesas())
			
			m.setGarcom(g);
		restaurante.adicionar(g);
		return g;
	}
	public static Conta criarConta(int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesa(idmesa);
		if (m == null) {
			throw new Exception("mesa inexistente");
		}
		if (m.isOcupada()) {
			throw new Exception("mesa ocupada");
		}
		numconta++;
		Conta c = new Conta(numconta, m);
		m.setOcupada(true);
		c.setTotal(0);
		m.adicionar(c);
		restaurante.adicionar(c);
		return c;
	}
	public static Conta consultarConta(int idmesa) throws Exception{
		Mesa m = restaurante.localizarMesa(idmesa);
		if (m == null) {
			throw new Exception("mesa n "+ idmesa+" n�o existe");
		}else if(m.getContas().isEmpty()) {
			throw new Exception("mesa n "+ idmesa+" n�o possui conta");
		}else {
			return m.getUltimaConta();
		}
	}
	public static Produto solicitarProduto(int idmesa, String nomeproduto) throws Exception{
		Produto p = restaurante.localizarProduto(nomeproduto);
		Mesa m = restaurante.localizarMesa(idmesa);
		Conta c = m.getUltimaConta();
		if (p==null) {
			throw new Exception ("produto " + nomeproduto +" n�o existe");
		}else if (m == null){
			throw new Exception ("mesa n "+ idmesa + " n�o existe");
		}else if (!m.isOcupada()){
			criarConta(m.getId());
		}
		
		c.adicionar(p);
		c.setTotal((c.getTotal()+p.getPreco()));
		return p;
		
	}
	public static void cancelarConta(int idmesa) throws Exception{
		Mesa m = restaurante.localizarMesa(idmesa);
		if (m ==null){
			throw new Exception ("mesa n "+ idmesa + " n�o existe");
		}else if (!m.isOcupada()) {
			throw new Exception ("mesa n "+ idmesa + " n�o est� ocupada");
		}
		m.setOcupada(false);
		m.remover(m.getUltimaConta());
		restaurante.remover(m.getUltimaConta());
	}
	public static void transferirConta(int idmesaorigem, int idmesadestino) throws Exception{
		Mesa m1 = restaurante.localizarMesa(idmesaorigem);
		Mesa m2 = restaurante.localizarMesa(idmesadestino);
		
		// s� pode transferir de uma mesa origem, se ela existir conta e se a conta est� em aberto
		if(m1.getUltimaConta() == null) {
			throw new Exception ("mesa de origem, n " + idmesaorigem +" n�o possui conta");
		}else if (m1.isOcupada()==false){
			throw new Exception ("mesa origem, n " + idmesaorigem + " n�o possui conta em aberto");
		}
		
		// s� pode transferir pra uma mesa destino, se ela existir 
		if (m2 == null) {
			throw new Exception ("mesa destino, n " + idmesadestino +" n�o existe");
		}
		
		// tirar a conta de uma mesa(cancelar conta) e adicionar na outra
		for(Produto p: m1.getUltimaConta().getProdutos()) {
			m2.getUltimaConta().adicionar(p);
		}
		
		cancelarConta(idmesaorigem);
		
		// mudar a mesa na conta
		m2.getUltimaConta().setMesa(m2);
	}
	public static void fecharConta(int idmesa) throws Exception{
		Mesa m1 = restaurante.localizarMesa(idmesa);
		if (m1 == null) {
			throw new Exception ("mesa n " + idmesa + " n�o existe");
		}else if (!m1.isOcupada()) {
			throw new Exception ("mesa n " + idmesa + " n�o possui conta aberta");
		}
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formato.format(data);
		m1.getUltimaConta().setDtfechamento(dataFormatada);
		m1.setOcupada(false);
	}
	public static double calcularGorjeta(String nome)throws Exception{
		Garcom g = restaurante.localizarGarcom(nome);
		double total = 0;
		if (g == null) {
			throw new Exception ("n�o existe gar�om " + nome);
		}
		for (Mesa m: g.getMesas()) {
			for (Conta c: m.getContas())
				total += c.getTotal();
		}
		return (total*10)/100;
	}
}