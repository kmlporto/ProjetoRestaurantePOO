package fachada;


import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import modelo.Produto;
import repositorio.Restaurante;
import modelo.Garcom;
import modelo.Conta;
import modelo.Mesa;
import modelo.Pagamento;
import modelo.PagamentoCartao;
import modelo.PagamentoDinheiro;


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
	public static ArrayList<Garcom> listarGarcons(){
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
			throw new Exception("número de mesas inválido!");
	}
	
	public static Produto cadastrarProduto(String nome, double preco)throws Exception {
		Produto aux = restaurante.localizarProduto(nome);
		if (aux !=null) {
			throw new Exception("produto já cadastrado!");	
		}
		if(preco <= 0) {
			throw new Exception("valor do preço inválido!");
		}
		if(nome.isEmpty()) {
			throw new Exception ("nome do produto inválido!");
		}
		Produto p = new Produto(nome, preco);
		restaurante.adicionar(p);
		return p;
	}
	public static Garcom cadastrarGarcom(String apelido, int mesainicial, int mesafinal) throws Exception{
		Garcom g = restaurante.localizarGarcom(apelido);
		if (g != null) {
			throw new Exception ("garçom já cadastrado");
		}
		if(mesafinal - mesainicial != 4) {
			throw new Exception ("intervalo de mesas inválidos!");
		}
		g = new Garcom (apelido);
		for (int aux = mesainicial; aux<=mesafinal ; aux++) {
			Mesa mesa = restaurante.localizarMesa(aux);
			if(mesa == null) {
				throw new Exception ("mesa inexistente");
			}else if(mesa.getGarcom()!= null) {
				throw new Exception ("mesa já possui garçom");
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
		
		if (m.getUltimaConta()!=null)
			if (m.getUltimaConta().getPagamento()==null)
				throw new Exception ("ultima conta não foi paga");
		if (m.getGarcom()==null)
			throw new Exception ("mesa sem garçom");
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
			return null;
		}else {
			return m.getUltimaConta();
		}
	}
	public static Produto solicitarProduto(int idmesa, String nomeproduto) throws Exception{
		Produto p = restaurante.localizarProduto(nomeproduto);
		Mesa m = restaurante.localizarMesa(idmesa);
		Conta c = m.getUltimaConta();
		if (p==null) {
			throw new Exception ("produto " + nomeproduto +" não existe");
		}
		if (m == null){
			throw new Exception ("mesa n "+ idmesa + " não existe");
		}
		if (!m.isOcupada()){
			throw new Exception ("mesa não está ocupada");
		}
		
		c.adicionar(p);
		c.setTotal((c.getTotal()+p.getPreco()));
		return p;
		
	}
	public static void cancelarConta(int idmesa) throws Exception{
		Mesa m = restaurante.localizarMesa(idmesa);
		if (m ==null){
			throw new Exception ("mesa n "+ idmesa + " não existe");
		}else if (!m.isOcupada()) {
			throw new Exception ("mesa n "+ idmesa + " não está ocupada");
		}
		m.setOcupada(false);
		m.remover(m.getUltimaConta());
		restaurante.remover(m.getUltimaConta());
	}
	public static void transferirConta(int idmesaorigem, int idmesadestino) throws Exception{
		Mesa m1 = restaurante.localizarMesa(idmesaorigem);
		Mesa m2 = restaurante.localizarMesa(idmesadestino);
		double t2 = m2.getUltimaConta().getTotal();
		double t1 = m1.getUltimaConta().getTotal();
		
		// só pode transferir de uma mesa origem, se ela existir conta e se a conta está em aberto
		if(m1.getUltimaConta() == null) {
			throw new Exception ("mesa de origem, n " + idmesaorigem +" não possui conta");
		}else if (m1.isOcupada()==false){
			throw new Exception ("mesa origem, n " + idmesaorigem + " não possui conta em aberto");
		}
		
		// só pode transferir pra uma mesa destino, se ela existir 
		if (m2 == null) {
			throw new Exception ("mesa destino, n " + idmesadestino +" não existe");
		}
		
		// tirar a conta de uma mesa(cancelar conta) e adicionar na outra
		for(Produto p: m1.getUltimaConta().getProdutos()) {
			m2.getUltimaConta().adicionar(p);
		}
		
		cancelarConta(idmesaorigem);
		
		// mudar a mesa na conta
		m2.getUltimaConta().setMesa(m2);
		
		// mudar o total da conta destino
		m2.getUltimaConta().setTotal(t2+t1);
	}
	public static void fecharConta(int idmesa) throws Exception{
		Mesa m1 = restaurante.localizarMesa(idmesa);
		if (m1 == null) {
			throw new Exception ("mesa n " + idmesa + " não existe");
		}else if (!m1.isOcupada()) {
			throw new Exception ("mesa n " + idmesa + " não possui conta aberta");
		}
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat formatodor = new SimpleDateFormat("dd/MM/yyyy");
		String data = formatodor.format(c.getTime());
		m1.getUltimaConta().setDtfechamento(data);
		m1.setOcupada(false);
	}
	public static double calcularGorjeta(String nome)throws Exception{
		Garcom g = restaurante.localizarGarcom(nome);
		double total = 0;
		if (g == null) {
			throw new Exception ("não existe garçom " + nome);
		}
		for (Mesa m: g.getMesas()) {
			for (Conta c: m.getContas()) 
				if(c.getDtfechamento()!= null && c.getPagamento()!= null)
					total += c.getPagamento().calcularGorjeta();
		}
		return total;
	}
	public static boolean verificarGarcon(int idmesa, String nome)throws Exception{
		String garcom = restaurante.localizarMesa(idmesa).getGarcom().getApelido().toString();
		return  (garcom.equals(nome));
	}
	public static boolean verificarGarcon(int idmesaOrig, int idmesaDest)throws Exception{
		String garcom1 = restaurante.localizarMesa(idmesaOrig).getGarcom().getApelido().toString();
		String garcom2 = restaurante.localizarMesa(idmesaDest).getGarcom().getApelido().toString();
		return  (garcom1.equals(garcom2));
	}
	public static Pagamento pagarConta(int idmesa, String tipo, int percentual, String cartao, int quantidade)throws Exception {	
		Conta c = restaurante.localizarMesa(idmesa).getUltimaConta();
		double total = c.getTotal();
		String dataFechamento = c.getDtfechamento();
		
		if (dataFechamento == null) {
			throw new Exception ("a conta na mesa " + idmesa +" não foi fechada");
		}
		if (tipo.equals("dinheiro")){
			PagamentoDinheiro p = new PagamentoDinheiro();
			p.setPercentualdesconto(percentual);
			p.calcularPagamento(total);
			c.setPagamento(p);
			return p;
		}else if (tipo.equals("cartão") || tipo.equals("cartao")) {
			PagamentoCartao p = new PagamentoCartao();
			p.setCartao(cartao);
			p.setQuantidadeparcelas(quantidade);
			p.calcularPagamento(total);
			c.setPagamento(p);
			return p;
		}else {
			throw new Exception ("tipo de pagamento " + tipo +" não existe");
		}
	}
	
	public static void excluirGarcom(String nome) throws Exception{
		Garcom g = restaurante.localizarGarcom(nome);
		if (g == null) {
			throw new Exception ("não existe garçom " + nome);
		}
		for (Mesa m: g.getMesas()) {
			if(m.isOcupada())
				throw new Exception ("este garçom está atendendo, não pode ser excluido");
		}
		for (Mesa m: g.getMesas()) {
			m.setGarcom(null);
		}
		restaurante.remover(g);	
	}
	
	public static double calcularPercentualMedio(String apelido) throws Exception {
		double total = 0;
		int i = 0;
		Garcom g = restaurante.localizarGarcom(apelido);
		if (g == null) {
			throw new Exception ("não existe garçom " + apelido);
		}
		for (Mesa m: g.getMesas()) {
			for (Conta c: m.getContas())
				if(c.getPagamento()!= null 
				&& c.getPagamento().getClass().getSimpleName().equals("PagamentoDinheiro")) {
					PagamentoDinheiro p = (PagamentoDinheiro) c.getPagamento();
					total += p.getPercentualdesconto();
					i++;
				}
		}
		if (total==0) return total;
		return total/i;
	}
}
