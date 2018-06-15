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

public class TelaTransferirConta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblOrig;
	private JLabel lblDest;
	private JButton btnTransferir;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTransferirConta frame = new TelaTransferirConta();
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
	public TelaTransferirConta() {
		setTitle("Transferir Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 348, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(107, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblOrig = new JLabel("Mesa de origem");
		lblOrig.setBounds(10, 14, 87, 14);
		contentPane.add(lblOrig);

		lblDest = new JLabel("Mesa destino");
		lblDest.setBounds(10, 52, 87, 14);
		contentPane.add(lblDest);

		textField_1 = new JTextField();
		textField_1.setBounds(107, 46, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int origem = Integer.parseInt(textField.getText());
					int destino = Integer.parseInt(textField_1.getText());
					Fachada.transferirConta(origem, destino);
					lblmsg.setText("conta transferida!");
					
					textField.setText("");
					textField_1.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnTransferir.setBounds(217, 43, 115, 23);
		contentPane.add(btnTransferir);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 94, 322, 34);
		contentPane.add(lblmsg);
	}
}
