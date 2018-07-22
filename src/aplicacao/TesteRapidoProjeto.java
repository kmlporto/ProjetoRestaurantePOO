package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import fachada.Fachada;
import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;

public class TesteRapidoProjeto {
	
	public static void main (String[] args) {  
		parte1();
		//parte2();
		//parte3();
		parte4();
		parte5();
		//parte6();
		//parte7();
		//parte8();
		//parte9();
		//parte10();
		System.out.println("fim do teste");
	}

	public static void parte1(){
		try {	
			Fachada.cadastrarProduto("feijoada", 25.0);
			Fachada.cadastrarProduto("bode guisado", 20.0);
			Fachada.cadastrarProduto("galinhada", 15.0);
			Fachada.cadastrarProduto("cerveja", 6.0);
			Fachada.cadastrarProduto("refrigerante", 5.0);
			Fachada.cadastrarProduto("agua", 2.0);
			Fachada.cadastrarProduto("peixada", 200.0);
			Fachada.cadastrarProduto("lagosta", 100.0);

			Fachada.criarMesas(20);

			Fachada.cadastrarGarcom("baixinho", 1,5);
			Fachada.cadastrarGarcom("esperto", 6,10);
			Fachada.cadastrarGarcom("zezinho", 11,15);
			Fachada.cadastrarGarcom("luiz", 16,20);

			ArrayList<Produto> produtos = Fachada.listarProdutos();
			//System.out.println("produtos cadastrados:");
			//System.out.println(produtos);

			ArrayList<Mesa> mesas = Fachada.listarMesas();
			//System.out.println("mesas criadas:");
			//System.out.println(mesas);
			
			TreeMap<String, Garcom> garcons = Fachada.listarGarcons();
			//System.out.println("garcons cadastrados:");
			//System.out.println(garcons);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public static void parte2() {
		try {
			Fachada.criarConta(1);	//mesa 1
			Fachada.solicitarProduto(1, "galinhada");
			Fachada.solicitarProduto(1, "cerveja");
			Fachada.solicitarProduto(1, "refrigerante");
			//System.out.println("conta da mesa 1: \n"+ Fachada.consultarConta(1)); 
			Fachada.solicitarProduto(1, "cerveja");
			Fachada.solicitarProduto(1, "cerveja");
			Fachada.fecharConta(1);
			//System.out.println("conta da mesa 1: \n"+ Fachada.consultarConta(1)); 

			Fachada.criarConta(5);	//mesa 5
			Fachada.solicitarProduto(5, "feijoada");
			Fachada.solicitarProduto(5, "cerveja");
			//System.out.println("conta da mesa 5: "+ Fachada.consultarConta(5));
			Fachada.fecharConta(5);
			Fachada.pagarConta(5, "dinheiro", 0, "nenhum", 0);
			//System.out.println("conta da mesa 5: "+ Fachada.consultarConta(5)); 
						
			double gorjeta = Fachada.calcularGorjeta("baixinho");
			System.out.println("gorjeta do baixinho="+gorjeta);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	public static void parte3() {
		try {
			Fachada.criarConta(7);
			Fachada.solicitarProduto(7, "galinhada");
			Fachada.solicitarProduto(7, "cerveja");
			//System.out.println("conta da mesa 7: "+ Fachada.consultarConta(7));
			
			Fachada.criarConta(18);
			Fachada.solicitarProduto(18, "agua");
			Fachada.solicitarProduto(18, "refrigerante");
			//System.out.println("conta da mesa 18: "+ Fachada.consultarConta(18));
			
			Fachada.transferirConta(7, 18);
			//System.out.println("conta da mesa 18: "+ Fachada.consultarConta(18));
			//System.out.println("listando as mesas "+Fachada.listarMesas());
			/*consultando a conta que foi fechada */
			//System.out.println("conta da mesa 7: \n"+ Fachada.consultarConta(7));
			
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void parte4() {
		try {
			/*esse teste é pra permitir a parcela em 4 vezes e o total tem que dar 480*/
			Fachada.criarConta(10);
			Fachada.solicitarProduto(10, "peixada");
			Fachada.solicitarProduto(10, "peixada");
			Fachada.fecharConta(10);
			Fachada.pagarConta(10, "cartao", 5, "hiper", 4);
			System.out.println("consultando a conta da mesa 10: \n"+ Fachada.consultarConta(10));
			//System.out.println("pagando a conta 10: \n"+ Fachada.pagarConta(10, "cartao", 5, "hiper", 4));

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	public static void parte5() {
		try {
			/*esse teste é pra permitir a parcela em 4 vezes e o total tem que dar 330*/
			Fachada.criarConta(2);
			Fachada.solicitarProduto(2, "lagosta");
			Fachada.solicitarProduto(2, "peixada");
			Fachada.fecharConta(2);
			Fachada.pagarConta(2, "cartão", 5, "hiper", 3);
			System.out.println("consultando a conta da mesa 10: \n"+ Fachada.consultarConta(2));
			//System.out.println("pagando a conta 2: \n"+ Fachada.pagarConta(2, "cartao", 5, "hiper", 3));

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void parte6() {
		try {
			/*esse teste é pra permitir a parcela em 2 vezes e o total tem que dar 200*/
			Fachada.criarConta(3);
			Fachada.solicitarProduto(3, "peixada");
			Fachada.fecharConta(3);
			Fachada.pagarConta(3, "dinheiro", 5, "hiper",2);
			System.out.println("consultando a conta da mesa 3: \n"+ Fachada.consultarConta(3));
			//System.out.println("pagando a conta 3: \n"+ Fachada.pagarConta(3, "cartao", 5, "hiper",2));
			
			

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void parte7() {
		try {
			/*esse teste é pra pra dar o valor de 196*/
			Fachada.criarConta(4);
			Fachada.solicitarProduto(4, "peixada");
			Fachada.fecharConta(4);
			Fachada.pagarConta(4, "dinheiro", 2, "hiper",2);
			System.out.println("consultando a conta da mesa 4: \n"+ Fachada.consultarConta(4));
			

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void parte8() {
		try {
			/*esse teste é pra pra tirar zezinho da lista de garcoms e ao listar as mesas nao aparecer o seu nome*/
			Fachada.excluirGarcom("zezinho");
			System.out.println(Fachada.listarGarcons());
			//System.out.println(Fachada.listarMesas());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	public static void parte9() {
		try {
			System.out.println(Fachada.calcularPercentualMedio("zezinho"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void parte10() {
		try {
			System.out.println(Fachada.listarProdutos());//em ordem alfabetica
			System.out.println(Fachada.listarProdutos("ada"));//em ordem alfabetica
			System.out.println(Fachada.listarGarcons());//em ordem alfabetica
			System.out.println(Fachada.listarContas());
			System.out.println(Fachada.consultarConta(10));
			Fachada.criarConta(4);
			Fachada.solicitarProduto(4, "peixada");
			Fachada.fecharConta(4);	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
