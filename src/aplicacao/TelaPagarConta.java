package aplicacao;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Produto;

public class TelaPagarConta extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JLabel lblNome;
	private JButton btnPagar;
	private JLabel lblmsg;
	private JTextField ti;
	private JTextField per;
	private JTextField car;
	private JTextField qnt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPagarConta frame = new TelaPagarConta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPagarConta() {
		setTitle("Pagar Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNome = new JLabel("mesa n: ");
		lblNome.setBounds(10, 11, 86, 14);
		contentPane.add(lblNome);

		id = new JTextField();
		id.setBounds(105, 11, 57, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblTipoPag = new JLabel("cartao/dinheiro:");
		lblTipoPag.setBounds(10, 41, 86, 14);
		contentPane.add(lblTipoPag);
		
		ti = new JTextField();
		ti.setColumns(10);
		ti.setBounds(105, 41, 57, 20);
		contentPane.add(ti);
		
		JLabel lblCarto = new JLabel("cart\u00E3o:");
		lblCarto.setBounds(182, 11, 86, 14);
		contentPane.add(lblCarto);
		
		car = new JTextField();
		car.setColumns(10);
		car.setBounds(253, 11, 81, 20);
		contentPane.add(car);

		JLabel lblQntParc = new JLabel("qnt parc:");
		lblQntParc.setBounds(182, 41, 86, 14);
		contentPane.add(lblQntParc);
				
		qnt = new JTextField();
		qnt.setColumns(10);
		qnt.setBounds(277, 41, 57, 20);
		contentPane.add(qnt);

		JLabel lblPercDesc = new JLabel("perc. desc:");
		lblPercDesc.setBounds(10, 72, 86, 14);
		contentPane.add(lblPercDesc);

		per = new JTextField();
		per.setColumns(10);
		per.setBounds(105, 72, 57, 20);
		contentPane.add(per);
				
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 115, 324, 14);
		contentPane.add(lblmsg);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int idmesa = Integer.parseInt(id.getText());
					String tipo = ti.getText();
					int percentual = Integer.parseInt(per.getText());
					String cartao = car.getText();
					int quantidade = Integer.parseInt(qnt.getText());
					
					Fachada.pagarConta(idmesa, tipo, percentual, cartao, quantidade);
					lblmsg.setText("conta na mesa n "+ idmesa + " paga. Valor pago: R$"+ Fachada.consultarConta(idmesa).getPagamento().getValorpago());
					
					id.setText("");
					ti.setText("");
					per.setText("");
					car.setText("");
					qnt.setText("");
					id.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnPagar.setBounds(219, 81, 115, 23);
		contentPane.add(btnPagar);

	}
}
