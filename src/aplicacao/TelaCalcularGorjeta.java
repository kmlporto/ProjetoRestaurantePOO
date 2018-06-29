package aplicacao;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
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


public class TelaCalcularGorjeta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNome;
	private JButton btnCalcular;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCalcularGorjeta frame = new TelaCalcularGorjeta();
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
	public TelaCalcularGorjeta() {
		setTitle("Calcular Gorjeta");
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

		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nome = textField.getText();
					double g = Fachada.calcularGorjeta(nome);
					
					lblmsg.setText("gar�om "+ nome+ " tem uma gorjeta de R$ "+ g);
					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					textField.setText("");
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCalcular.setBounds(194, 47, 101, 23);
		contentPane.add(btnCalcular);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 88, 285, 20);
		contentPane.add(lblmsg);
	}
}
