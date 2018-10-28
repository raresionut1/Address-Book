package agenda;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SplashScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
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
	public SplashScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/res/contact.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
		
		JLabel label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setBounds(-19, 0, 482, 302);
		ImageIcon intro = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/intro.gif"));
		ImageIcon intro_scaled = new ImageIcon(intro.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		
		JLabel lblAgendaDeContacte = new JLabel("AGENDA DE CONTACTE");
		lblAgendaDeContacte.setForeground(new Color(99, 170, 156));
		lblAgendaDeContacte.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendaDeContacte.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblAgendaDeContacte.setBounds(10, 22, 430, 56);
		contentPane.add(lblAgendaDeContacte);
		
		JLabel lblDevelopedBy = new JLabel("developed by");
		lblDevelopedBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedBy.setForeground(new Color(99, 170, 156));
		lblDevelopedBy.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblDevelopedBy.setBounds(10, 219, 430, 56);
		contentPane.add(lblDevelopedBy);
		
		JLabel lblRaresConstantin = new JLabel("Rares Constantin");
		lblRaresConstantin.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaresConstantin.setForeground(new Color(99, 170, 156));
		lblRaresConstantin.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblRaresConstantin.setBounds(10, 246, 430, 56);
		contentPane.add(lblRaresConstantin);
		label.setIcon(intro_scaled);
		contentPane.add(label);
	}
}
