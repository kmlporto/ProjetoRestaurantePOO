package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;
import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal {

	private JFrame frmPrincipal;
	private JMenu mnProduto;
	private JMenuItem mntmCadastrarP;
	private JMenuItem mntmListarP;
	private JMenu mnGarcom;
	private JMenuItem mntmCadastrarG;
	private JMenuItem mntmListarG;
	private JMenuItem mntmCalcularG;
	private JMenu mmMesa;
	private JMenuItem mntmListarM;
	private JMenuItem mntmSolicitarP;
	private JMenuItem mntmPagarConta;
	private JMenuItem mntmExcluir;
	private JMenuItem mntmPercMed;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Restaurante - Kamila Freitas");
		try {
			frmPrincipal.setContentPane(new FundoTela("imagem1.jpeg"));
		} catch (IOException e1) {
		}	

		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try{
					//  pre-cadastro
					Fachada.criarMesas(20);	
					
					Fachada.cadastrarProduto("tortilhas", 15.0);
					Fachada.cadastrarProduto("burritos", 17.0);
					Fachada.cadastrarProduto("tacos", 16.0);
					Fachada.cadastrarProduto("nachos", 20.0);
					Fachada.cadastrarProduto("mixiote", 8.0);
					Fachada.cadastrarProduto("quesadilha", 15.0);
					Fachada.cadastrarProduto("mojito", 18.0);
					Fachada.cadastrarProduto("tequila", 17.0);
					Fachada.cadastrarProduto("suco de laranja", 12.0);
					Fachada.cadastrarProduto("água", 2.50);
					
					Fachada.cadastrarGarcom("luiz", 1, 5);
					Fachada.cadastrarGarcom("antonio", 6, 10);
					Fachada.cadastrarGarcom("manoel", 11, 15);
					Fachada.cadastrarGarcom("hermano", 16, 20);
					
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "até breve !");
			}
		});

		frmPrincipal.setBounds(100, 100, 450, 320);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		mnProduto = new JMenu("   Produto   ");
		menuBar.add(mnProduto);

		mntmCadastrarP = new JMenuItem("Cadastrar");
		mntmCadastrarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroProduto j = new TelaCadastroProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmCadastrarP);

		mntmListarP = new JMenuItem("Listar");
		mntmListarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemProduto j = new TelaListagemProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmListarP);
		
 
		mmMesa = new JMenu("       Mesa       ");
		menuBar.add(mmMesa);

		mntmListarM = new JMenuItem("Listar");
		mntmListarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemMesa j = new TelaListagemMesa();
				j.setVisible(true);
			}
		});
		mmMesa.add(mntmListarM);
		
		mntmSolicitarP = new JMenuItem("Solic. Prod.");
		mntmSolicitarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSolicitarProduto j = new TelaSolicitarProduto();
				j.setVisible(true);
			}
		});
		mmMesa.add(mntmSolicitarP);

		
		mnGarcom = new JMenu("   Garçom   ");
		menuBar.add(mnGarcom);

		mntmCadastrarG = new JMenuItem("Cadastrar");
		mntmCadastrarG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroGarcom j = new TelaCadastroGarcom();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmCadastrarG);

		mntmListarG = new JMenuItem("Listar");
		mntmListarG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemGarcom j = new TelaListagemGarcom();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmListarG);
		
		mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExcluirGarcom j = new TelaExcluirGarcom();
				j.setVisible(true);
			}
		});
		
		mnGarcom.add(mntmExcluir);
		
		
		mntmCalcularG = new JMenuItem("Gorjeta");
		mntmCalcularG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCalcularGorjeta j = new TelaCalcularGorjeta();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmCalcularG);
		
		mntmPercMed = new JMenuItem("Perc Med");
		mntmPercMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPercentualMed j = new TelaPercentualMed();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmPercMed);
		
		
		JMenu mnConta = new JMenu("     Conta     ");
		menuBar.add(mnConta); 

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAbrirConta j = new TelaAbrirConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmAbrir);

		JMenuItem mntmListarConta = new JMenuItem("Listar");
		mntmListarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemConta j = new TelaListagemConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmListarConta);

		JMenuItem mntmConsultarConta = new JMenuItem("Consultar");
		mntmConsultarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConsultarConta j = new TelaConsultarConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmConsultarConta);
		
		JMenuItem mntmCancelarConta = new JMenuItem("Cancelar");
		mntmCancelarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCancelarConta j = new TelaCancelarConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmCancelarConta);
		
		JMenuItem mntmTransferirConta = new JMenuItem("Transferir");
		mntmTransferirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaTransferirConta j = new TelaTransferirConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmTransferirConta);
		
		JMenuItem mntmFecharConta = new JMenuItem("Fechar");
		mntmFecharConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaFecharConta j = new TelaFecharConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmFecharConta);
		
		mntmPagarConta = new JMenuItem("Pagar");
		mnConta.add(mntmPagarConta);
	
	}
}
