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
import java.awt.Font;

public class TelaFecharConta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblMesa;
	private JButton btnFechar;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFecharConta frame = new TelaFecharConta();
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
	public TelaFecharConta() {
		setTitle("Fechar Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 123);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 40, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblMesa = new JLabel("Digite o n\u00FAmero da mesa que deseja fechar a conta");
		lblMesa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMesa.setBounds(10, 14, 285, 14);
		contentPane.add(lblMesa);

		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int idmesa = Integer.parseInt(textField.getText());
					Fachada.fecharConta(idmesa);
					lblmsg.setText("conta da mesa n " +idmesa + " fechada");
					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnFechar.setBounds(180, 39, 115, 23);
		contentPane.add(btnFechar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 71, 285, 20);
		contentPane.add(lblmsg);
	}
}
