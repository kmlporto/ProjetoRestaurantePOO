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

public class TelaSolicitarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblProd;
	private JLabel lblMesa;
	private JButton btnSolicitar;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSolicitarProduto frame = new TelaSolicitarProduto();
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
	public TelaSolicitarProduto() {
		setTitle("Solicitar Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(72, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblProd = new JLabel("Produto");
		lblProd.setBounds(10, 14, 46, 14);
		contentPane.add(lblProd);

		lblMesa = new JLabel("Mesa");
		lblMesa.setBounds(10, 43, 46, 14);
		contentPane.add(lblMesa);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 40, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String produto = textField.getText().toLowerCase();
					int idmesa = Integer.parseInt(textField_1.getText());
					
					Fachada.solicitarProduto(idmesa,produto);
					lblmsg.setText("produto "+ produto + " solicitado com sucesso");
				
					textField.setText("");
					textField_1.setText("");
				
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnSolicitar.setBounds(220, 39, 98, 23);
		contentPane.add(btnSolicitar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 73, 305, 32);
		contentPane.add(lblmsg);
		
		
	}
}
