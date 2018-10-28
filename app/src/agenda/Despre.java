package agenda;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Despre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Despre frame = new Despre();
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
	public Despre() {
		setBackground(new Color(237, 234, 224));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Despre.class.getResource("/res/about.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(237, 234, 224));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JTextPane txtpn = new JTextPane();
		txtpn.setEditable(false);
		txtpn.setBackground(new Color(237, 234, 224));
		txtpn.setForeground(new Color(99, 170, 156));
		txtpn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtpn.setText("   Aplicatia a fost implementata integral de Rares Constantin.\n\n"
				+ "   Utilizatorul are la dispozitie o agenda de contacte in care poate adauga, edita si sterge contacte. "
				+ "De asemenea, lista de contacte poate fi ordonata si filtrata dupa diferite criterii.\n\n"
				+ "   Pentru a debloca modul Premium, unde optiunile de incarcare si salvare sunt deblocate, "
				+ "trebuie cumparat un Licence-Key. La activarea modului Premium, aplicatia va trece in modul NO-ADDS.");
		txtpn.setBounds(10, 11, 414, 172);
		contentPane.add(txtpn);
		
		JButton btnBuyPremium = new JButton("<html><center>Buy Licence-Key<br/>3.99$</center></html>");
		btnBuyPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBuyPremium.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
				btnBuyPremium.setText("<html><center>Licence-Key:<br/>CRI2209</center></html>");
				btnBuyPremium.setEnabled(false);
			}
		});
		btnBuyPremium.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnBuyPremium.setBackground(new Color(99, 170, 156));
		btnBuyPremium.setForeground(new Color(255, 255, 255));
		btnBuyPremium.setBounds(117, 190, 200, 48);
		btnBuyPremium.repaint();
		contentPane.add(btnBuyPremium);
		
		Dimension dimm = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dimm.width/2 - this.getSize().width/2, dimm.height/2 - this.getSize().height/2);
	}
}
