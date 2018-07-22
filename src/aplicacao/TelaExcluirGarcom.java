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


public class TelaExcluirGarcom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNome;
	private JButton btnExcluir;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaExcluirGarcom frame = new TelaExcluirGarcom();
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
	public TelaExcluirGarcom() {
		setTitle("Excluir Garçom");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 48, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Por favor, digite abaixo o nome do gar\u00E7om");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(10, 14, 285, 23);
		contentPane.add(lblNome);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nome = textField.getText();
					Fachada.excluirGarcom(nome);
					
					lblmsg.setText("garçom "+ nome+ " excluído ");
					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					textField.setText("");
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnExcluir.setBounds(194, 47, 101, 23);
		contentPane.add(btnExcluir);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 88, 285, 20);
		contentPane.add(lblmsg);
	}
}
