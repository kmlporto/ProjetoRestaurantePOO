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
	private JLabel lblGarcom;
	private JButton btnSolicitar;
	private JLabel lblmsg;
	private JTextField textField_2;

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
		setBounds(100, 100, 334, 156);
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
		
		lblGarcom = new JLabel("Garçom");
		lblGarcom.setBounds(10, 74, 46, 14);
		contentPane.add(lblGarcom);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 40, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(72, 71, 86, 20);
		contentPane.add(textField_2);
		
		btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String produto = textField.getText().toLowerCase();
					int idmesa = Integer.parseInt(textField_1.getText());
					String garcom = textField_2.getText();
					if(Fachada.verificarGarcon(idmesa, garcom)) {
						Fachada.solicitarProduto(idmesa,produto);
						lblmsg.setText("produto "+ produto + " solicitado com sucesso");
					}else {
						lblmsg.setText("este garçom não tem acesso a esta conta");
					}
				
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnSolicitar.setBounds(217, 70, 98, 23);
		contentPane.add(btnSolicitar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 96, 305, 32);
		contentPane.add(lblmsg);
		
		
	}
}
