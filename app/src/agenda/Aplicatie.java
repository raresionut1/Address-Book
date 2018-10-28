package agenda;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Aplicatie {
	
	private String licence_key = "cri2209";

	private JFrame frmContacte;
	private JTextField textFieldCautare;
	
	private int addCurent = 0;
	private String fisierSalvare;
	
	private JPanel home = new JPanel();
	private JPanel adaugareContact = new JPanel();
	private JPanel editareContact = new JPanel();
	
	private Agenda agenda = new Agenda();
	private DefaultListModel<Contact> modelLista = new DefaultListModel<Contact>();
	private JTextField textFieldNume_add;
	private JTextField textFieldPrenume_add;
	private JTextField textFieldDataNastere_add;
	private JTextField textFieldNrTel_add;
	private JTextField textFieldNume_edit;
	private JTextField textFieldPrenume_edit;
	private JTextField textFieldDataNastere_edit;
	private JTextField textFieldNrTel_edit;
	private JScrollPane scrollPane = new JScrollPane();
	private JList<Contact> list = new JList<Contact>();
	private JLabel adds = new JLabel("");
	private Despre infoFrame = new Despre();
	
	private String ordonare0 = "Dupa nume";
	private String ordonare1 = "Dupa prenume";
	private String ordonare2 = "Dupa data de nastere";
	private String ordonare3 = "Dupa numarul de telefon";
	
	private String filtrare0 = "Numere mobile";
	private String filtrare1 = "Numere fixe";
	private String filtrare2 = "Cu ziua de nastere astazi";
	private String filtrare3 = "Cu ziua de nastere luna aceasta";
	private String filtrare4 = "Personalizata";
	private final ButtonGroup buttonGroup_edit = new ButtonGroup();
	private final ButtonGroup buttonGroup_add = new ButtonGroup();
	private String[] linkuriReclame = new String[] {
			"https://us.royalquest.com", 
			"http://www.ntltrust.com/", 
			"https://www.psfk.com/event/psfk-2016-conference", 
			"https://www.trojan.build/", 
			"https://www.bluebeam.com/", 
			"https://bransonontheweb.com/", 
			"https://123movie.cc/", 
			"https://www.888poker.ro", 
			"https://www.motordoctor.pt/"};
	
	public void actualizareModel() {
		modelLista.clear();
		for(Contact c : agenda.contacte())
			modelLista.addElement(c);
	}
	
	public void setPanel(JPanel panel) {
		frmContacte.setContentPane(panel);
		frmContacte.getContentPane().add(adds);
		frmContacte.validate();
	}
	
	public static void Splash() {
		SplashScreen splash = new SplashScreen();
		splash.setVisible(true);
		try
        {
           Thread.sleep(5000);
        }
        catch(InterruptedException e){}
		splash.dispose();
	}
	
	public void setReclama(int x) {
		try {
			ImageIcon reclama = new ImageIcon(Aplicatie.class.getResource("/res/advertisement"+x+".gif"));
			ImageIcon reclama_scaled = new ImageIcon(reclama.getImage().getScaledInstance(adds.getWidth(), adds.getHeight(), Image.SCALE_DEFAULT));
			adds.setIcon(reclama_scaled);
		} catch (NullPointerException e) {}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Splash();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Aplicatie window = new Aplicatie();
					window.frmContacte.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicatie() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmContacte = new JFrame();
		frmContacte.setResizable(false);
		
		adaugareContact.setBackground(new Color(237, 234, 224));
		adaugareContact.setBounds(0, 0, 434, 240);
		adaugareContact.setLayout(null);
		
		JLabel lblNume_add = new JLabel("Nume:");
		lblNume_add.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNume_add.setBounds(30, 20, 55, 14);
		adaugareContact.add(lblNume_add);
		
		JLabel lblPrenume_add = new JLabel("Prenume:");
		lblPrenume_add.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPrenume_add.setBounds(30, 55, 75, 14);
		adaugareContact.add(lblPrenume_add);
		
		JLabel lblNumarDeTelefon_add = new JLabel("Numar de telefon:");
		lblNumarDeTelefon_add.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNumarDeTelefon_add.setBounds(30, 125, 150, 14);
		adaugareContact.add(lblNumarDeTelefon_add);
		
		JLabel lblDataDeNastere_add = new JLabel("Data de nastere:");
		lblDataDeNastere_add.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDataDeNastere_add.setBounds(30, 90, 131, 14);
		adaugareContact.add(lblDataDeNastere_add);
		
		textFieldNume_add = new JTextField();
		textFieldNume_add.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldNume_add.setBounds(95, 15, 150, 25);
		adaugareContact.add(textFieldNume_add);
		textFieldNume_add.setColumns(10);
		
		textFieldPrenume_add = new JTextField();
		textFieldPrenume_add.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldPrenume_add.setBounds(115, 50, 150, 25);
		adaugareContact.add(textFieldPrenume_add);
		textFieldPrenume_add.setColumns(10);
		
		textFieldDataNastere_add = new JTextField();
		textFieldDataNastere_add.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldDataNastere_add.setBounds(165, 85, 150, 25);
		adaugareContact.add(textFieldDataNastere_add);
		textFieldDataNastere_add.setColumns(10);
		
		textFieldNrTel_add = new JTextField();
		textFieldNrTel_add.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldNrTel_add.setBounds(185, 120, 150, 25);
		adaugareContact.add(textFieldNrTel_add);
		textFieldNrTel_add.setColumns(10);
		
		JRadioButton rdbtnMobil_add = new JRadioButton("Mobil");
		buttonGroup_add.add(rdbtnMobil_add);
		rdbtnMobil_add.setBackground(new Color(237, 234, 224));
		rdbtnMobil_add.setBounds(342, 109, 55, 23);
		adaugareContact.add(rdbtnMobil_add);
		
		JRadioButton rdbtnFix_add = new JRadioButton("Fix");
		buttonGroup_add.add(rdbtnFix_add);
		rdbtnFix_add.setBackground(new Color(237, 234, 224));
		rdbtnFix_add.setBounds(342, 133, 55, 23);
		adaugareContact.add(rdbtnFix_add);
		
		JButton btnSalveazaContact_add = new JButton("<html><center>Salveaza<br/>Contact</center></html>");
		btnSalveazaContact_add.setForeground(new Color(255, 255, 255));
		btnSalveazaContact_add.setBackground(new Color(99, 170, 156));
		btnSalveazaContact_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Contact c = new Contact(textFieldNume_add.getText(),
											textFieldPrenume_add.getText(),
											textFieldDataNastere_add.getText(),
											textFieldNrTel_add.getText());
					
					if(rdbtnMobil_add.isSelected()) {
						if(c.getNumarTelefon().isFix())
							throw new IllegalArgumentException("Numarul de telefon este fix.\nSelectati butonul pentru numere fixe.");
						}
					else
						if(rdbtnFix_add.isSelected()) {
							if(c.getNumarTelefon().isMobil())
								throw new IllegalArgumentException("Numarul de telefon este mobil.\nSelectati butonul pentru numere mobile.");
						}
						else
							throw new IllegalArgumentException("Selectati un tip de numar de telefon (mobil/fix).");
					
					agenda.adaugareContact(c);
					actualizareModel();
					
					ImageIcon icon_birthday = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/res/birthday_cake.png"));
					Image img_birthday = icon_birthday.getImage();
					Image img_birthday_resized = img_birthday.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
					ImageIcon icon_birthday_resized = new ImageIcon(img_birthday_resized);
					
					
					if(c.getDataNastere().withYear(Year.now().getValue()).equals(LocalDate.now())) {
						if(c.isMale())
							JOptionPane.showMessageDialog(frmContacte, "Astazi este ziua de nastere a lui " + c.getPrenume() + " !\n" +
																		"Ureaza-i \"La Multi Ani\" !",
																		"Info", JOptionPane.INFORMATION_MESSAGE, icon_birthday_resized);
						else
							JOptionPane.showMessageDialog(frmContacte, "Astazi este ziua de nastere a " +
																		c.getPrenume().substring(0, c.getPrenume().length() - 1) + "ei!\n" +
																		"Ureaza-i \"La Multi Ani\" !",
																		"Info", JOptionPane.INFORMATION_MESSAGE, icon_birthday_resized);
					}
						
					textFieldNume_add.setText("");
					textFieldPrenume_add.setText("");
					textFieldDataNastere_add.setText("");
					textFieldNrTel_add.setText("");
					setPanel(home);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmContacte, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalveazaContact_add.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSalveazaContact_add.setBounds(60, 170, 140, 50);
		adaugareContact.add(btnSalveazaContact_add);
		
		JButton btnAnulare_add = new JButton("Anulare");
		btnAnulare_add.setForeground(new Color(255, 255, 255));
		btnAnulare_add.setBackground(new Color(99, 170, 156));
		btnAnulare_add.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAnulare_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNume_add.setText("");
				textFieldPrenume_add.setText("");
				textFieldDataNastere_add.setText("");
				textFieldNrTel_add.setText("");
				setPanel(home);
			}
		});
		btnAnulare_add.setBounds(234, 170, 140, 50);
		adaugareContact.add(btnAnulare_add);
		
		
		editareContact.setLayout(null);
		editareContact.setBackground(new Color(237, 234, 224));
		editareContact.setBounds(0, 0, 434, 240);
		
		JLabel lblNume_edit = new JLabel("Nume:");
		lblNume_edit.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNume_edit.setBounds(30, 20, 55, 14);
		editareContact.add(lblNume_edit);
		
		JLabel lblPrenume_edit = new JLabel("Prenume:");
		lblPrenume_edit.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPrenume_edit.setBounds(30, 55, 75, 14);
		editareContact.add(lblPrenume_edit);
		
		JLabel lblNrTel_edit = new JLabel("Numar de telefon:");
		lblNrTel_edit.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNrTel_edit.setBounds(30, 125, 145, 14);
		editareContact.add(lblNrTel_edit);
		
		JLabel lblDataNastere_edit = new JLabel("Data de nastere:");
		lblDataNastere_edit.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDataNastere_edit.setBounds(30, 90, 131, 14);
		editareContact.add(lblDataNastere_edit);
		
		textFieldNume_edit = new JTextField();
		textFieldNume_edit.setColumns(10);
		textFieldNume_edit.setBounds(95, 18, 150, 25);
		editareContact.add(textFieldNume_edit);
		
		textFieldPrenume_edit = new JTextField();
		textFieldPrenume_edit.setColumns(10);
		textFieldPrenume_edit.setBounds(115, 53, 150, 25);
		editareContact.add(textFieldPrenume_edit);
		
		textFieldDataNastere_edit = new JTextField();
		textFieldDataNastere_edit.setColumns(10);
		textFieldDataNastere_edit.setBounds(165, 88, 150, 25);
		editareContact.add(textFieldDataNastere_edit);
		
		JRadioButton rdbtnMobil_edit = new JRadioButton("Mobil");
		buttonGroup_edit.add(rdbtnMobil_edit);
		
		textFieldNrTel_edit = new JTextField();
		textFieldNrTel_edit.setColumns(10);
		textFieldNrTel_edit.setBounds(185, 123, 150, 25);
		editareContact.add(textFieldNrTel_edit);
		rdbtnMobil_edit.setBackground(new Color(237, 234, 224));
		rdbtnMobil_edit.setBounds(342, 112, 55, 23);
		editareContact.add(rdbtnMobil_edit);
		
		JRadioButton rdbtnFix_edit = new JRadioButton("Fix");
		buttonGroup_edit.add(rdbtnFix_edit);
		rdbtnFix_edit.setBackground(new Color(237, 234, 224));
		rdbtnFix_edit.setBounds(342, 136, 55, 23);
		editareContact.add(rdbtnFix_edit);
		
		JButton btnSalveazaModificarile_edit = new JButton("<html><center>Salveaza<br/>Modificarile</center></html>");
		btnSalveazaModificarile_edit.setForeground(new Color(255, 255, 255));
		btnSalveazaModificarile_edit.setBackground(new Color(99, 170, 156));
		btnSalveazaModificarile_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Contact cVechi = list.getSelectedValue();
					Contact cNou = new Contact(textFieldNume_edit.getText(),
							textFieldPrenume_edit.getText(),
							textFieldDataNastere_edit.getText(),
							textFieldNrTel_edit.getText());
					
					if(rdbtnMobil_edit.isSelected()) {
						if(cNou.getNumarTelefon().isFix())
							throw new IllegalArgumentException("Numarul de telefon este fix.\nSelectati butonul pentru numere fixe.");
						}
					else
						if(rdbtnFix_edit.isSelected()) {
							if(cNou.getNumarTelefon().isMobil())
								throw new IllegalArgumentException("Numarul de telefon este mobil.\nSelectati butonul pentru numere mobile.");
						}
						else
							throw new IllegalArgumentException("Selectati un tip de numar de telefon (mobil/fix).");
					
					agenda.modificareContact(cVechi, cNou);
					actualizareModel();
					textFieldNume_edit.setText("");
					textFieldPrenume_edit.setText("");
					textFieldDataNastere_edit.setText("");
					textFieldNrTel_edit.setText("");
					setPanel(home);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frmContacte, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalveazaModificarile_edit.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSalveazaModificarile_edit.setBounds(60, 170, 140, 50);
		editareContact.add(btnSalveazaModificarile_edit);
		
		JButton btnAnulare_edit = new JButton("Anulare");
		btnAnulare_edit.setForeground(new Color(255, 255, 255));
		btnAnulare_edit.setBackground(new Color(99, 170, 156));
		btnAnulare_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNume_edit.setText("");
				textFieldPrenume_edit.setText("");
				textFieldDataNastere_edit.setText("");
				textFieldNrTel_edit.setText("");
				setPanel(home);
			}
		});
		btnAnulare_edit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAnulare_edit.setBounds(234, 170, 140, 50);
		editareContact.add(btnAnulare_edit);
		
		
		
		frmContacte.setBackground(new Color(255, 255, 255));
		frmContacte.setFont(new Font("Arial", Font.BOLD, 20));
		frmContacte.getContentPane().setBackground(new Color(237, 234, 224));
		frmContacte.setIconImage(Toolkit.getDefaultToolkit().getImage(Toolkit.getDefaultToolkit().getClass().getResource("/res/contact.png")));
		frmContacte.setTitle("Contacte");
		frmContacte.setBounds(100, 100, 450, 348);
		frmContacte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmContacte.getContentPane().setLayout(null);
		
		frmContacte.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	int confirmare;
				
				if(!agenda.contacte.isEmpty() && !agenda.isSaved())
					 confirmare = JOptionPane.showConfirmDialog(frmContacte, "Agenda curenta nu este salvata.\n"
																		   	+ "Doriti sa inchideti aplicatia fara a o salva?", 
																			"Confirm", JOptionPane.YES_NO_OPTION, 
																			JOptionPane.WARNING_MESSAGE);
				else
					 confirmare = JOptionPane.showConfirmDialog(frmContacte, "Doriti sa inchideti aplicatia?", 
							 												 "Confirm", JOptionPane.YES_NO_OPTION, 
							 												 JOptionPane.QUESTION_MESSAGE);
					
					
				if(confirmare == JOptionPane.NO_OPTION) {
					return;
				}
					 
				System.exit(0);
		    }
		});	
		
		home.setBackground(new Color(237, 234, 224));
		home.setBounds(0, 0, 434, 240);
		frmContacte.getContentPane().add(home);
		home.setLayout(null);
		
		JLabel lblFiltrare = new JLabel("Filtrare");
		lblFiltrare.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFiltrare.setBounds(10, 83, 46, 14);
		home.add(lblFiltrare);
		
		JLabel lblOrdonare = new JLabel("Ordonare");
		lblOrdonare.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrdonare.setBounds(10, 49, 65, 14);
		home.add(lblOrdonare);
		
		JButton btnAdaugaContact = new JButton("Adauga Contact");
		btnAdaugaContact.setFont(new Font("Arial", Font.BOLD, 12));
		btnAdaugaContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnMobil_add.setSelected(true);
				setPanel(adaugareContact);
			}
		});
		btnAdaugaContact.setForeground(new Color(255, 255, 255));
		btnAdaugaContact.setBackground(new Color(99, 170, 156));
		btnAdaugaContact.setBounds(10, 11, 130, 23);
		home.add(btnAdaugaContact);
		
		JButton btnEditeazaContact = new JButton("Editeaza Contact");
		btnEditeazaContact.setFont(new Font("Arial", Font.BOLD, 12));
		btnEditeazaContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(list.isSelectionEmpty())
						throw new IllegalArgumentException("Selecteaza un contact pentru a-l edita.");
					
					Contact c = list.getSelectedValue();
					
					if(c.getNumarTelefon().isMobil())
						rdbtnMobil_edit.setSelected(true);
					
					if(c.getNumarTelefon().isFix())
						rdbtnFix_edit.setSelected(true);
					
					textFieldNume_edit.setText(c.getNume());
					textFieldPrenume_edit.setText(c.getPrenume());
					textFieldDataNastere_edit.setText(c.getDataNastereString());
					textFieldNrTel_edit.setText(c.getNumarTelefon().toString());
					
					setPanel(editareContact);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frmContacte, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
		btnEditeazaContact.setForeground(new Color(255, 255, 255));
		btnEditeazaContact.setBackground(new Color(99, 170, 156));
		btnEditeazaContact.setBounds(152, 11, 130, 23);
		home.add(btnEditeazaContact);
		
		JButton btnStergeContact = new JButton("Sterge Contact");
		btnStergeContact.setFont(new Font("Arial", Font.BOLD, 12));
		btnStergeContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(list.isSelectionEmpty())
						throw new IllegalArgumentException("Selecteaza un contact pentru a-l sterge.");
					
					Contact c = list.getSelectedValue();
					
					int confirmare = JOptionPane.showConfirmDialog(frmContacte, "Contactul \"" + c.getNume() + 
																				" " + c.getPrenume() + "\" va fi sters.\n" +
																				"Doriti sa continuati?", 
																				"Confirm", JOptionPane.YES_NO_OPTION, 
																				JOptionPane.QUESTION_MESSAGE);
					if(confirmare == JOptionPane.NO_OPTION) {
						return;
					}
					
					agenda.stergereContact(c);
					actualizareModel();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frmContacte, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
		btnStergeContact.setBackground(new Color(99, 170, 156));
		btnStergeContact.setForeground(new Color(255, 255, 255));
		btnStergeContact.setBounds(294, 11, 130, 23);
		home.add(btnStergeContact);
		
		JComboBox<String> comboBoxOrdonare = new JComboBox<String>();
		comboBoxOrdonare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxOrdonare.getSelectedIndex()) {
					case 0:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_NUME);
						break;
					case 1:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_PRENUME);
						break;
					case 2:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_DATA_DE_NASTERE);
						break;
					case 3:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_NUMARUL_DE_TELEFON);
						break;
					default:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_NUME);
				}
			}
		});
		comboBoxOrdonare.setBackground(new Color(240, 240, 240));
		comboBoxOrdonare.setBounds(74, 46, 208, 20);
		home.add(comboBoxOrdonare);
		comboBoxOrdonare.addItem(ordonare0);
		comboBoxOrdonare.addItem(ordonare1);
		comboBoxOrdonare.addItem(ordonare2);
		comboBoxOrdonare.addItem(ordonare3);
		
		JButton btnFiltreaza = new JButton("Filtreaza");
		btnFiltreaza.setForeground(new Color(255, 255, 255));
		btnFiltreaza.setFont(new Font("Arial", Font.BOLD, 12));
		btnFiltreaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizareModel();
			}
		});
		btnFiltreaza.setBackground(new Color(99, 170, 156));
		btnFiltreaza.setBounds(294, 79, 130, 23);
		btnFiltreaza.setVisible(false);
		home.add(btnFiltreaza);
		
		JButton btnOrdoneaza = new JButton("Ordoneaza");
		btnOrdoneaza.setForeground(new Color(255, 255, 255));
		btnOrdoneaza.setFont(new Font("Arial", Font.BOLD, 12));
		btnOrdoneaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizareModel();
			}
		});
		btnOrdoneaza.setBackground(new Color(99, 170, 156));
		btnOrdoneaza.setBounds(294, 45, 130, 23);
		home.add(btnOrdoneaza);
		
		JComboBox<String> comboBoxFiltrare = new JComboBox<String>();
		comboBoxFiltrare.setBackground(new Color(240, 240, 240));
		comboBoxFiltrare.setBounds(74, 80, 208, 20);
		home.add(comboBoxFiltrare);
		comboBoxFiltrare.setVisible(false);
		comboBoxFiltrare.addItem(filtrare0);
		comboBoxFiltrare.addItem(filtrare1);
		comboBoxFiltrare.addItem(filtrare2);
		comboBoxFiltrare.addItem(filtrare3);
		comboBoxFiltrare.addItem(filtrare4);
		
		list.setForeground(new Color(255, 255, 255));
		list.setModel(modelLista);
		list.setFont(new Font("Segoe UI", Font.BOLD, 14));
		list.setBackground(new Color(165, 204, 196));
		list.setBounds(10, 108, 414, 121);
		scrollPane.setBounds(10, 108, 414, 121);
		scrollPane.setViewportView(list);
		home.add(scrollPane);
		
		JCheckBox checkBoxFiltrare = new JCheckBox("");
		checkBoxFiltrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(checkBoxFiltrare.isSelected()) {
						comboBoxFiltrare.setSelectedIndex(0);
						comboBoxFiltrare.setVisible(true);
						btnFiltreaza.setVisible(true);
						agenda.filtreazaNrMobil();
					}
					else {
						agenda.faraFiltrare();
						actualizareModel();
						comboBoxFiltrare.setVisible(false);
						btnFiltreaza.setVisible(false);
						textFieldCautare.setVisible(false);
					}
			}
		});
		checkBoxFiltrare.setBounds(50, 79, 21, 23);
		home.add(checkBoxFiltrare);
		
		textFieldCautare = new JTextField();
		textFieldCautare.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    agenda.filtreazaPersonalizat(textFieldCautare.getText());
			    actualizareModel();
			  }
			  public void removeUpdate(DocumentEvent e) {
				agenda.filtreazaPersonalizat(textFieldCautare.getText());
				actualizareModel();
			  }
			  public void insertUpdate(DocumentEvent e) {
				agenda.filtreazaPersonalizat(textFieldCautare.getText());
				actualizareModel();
			  }
		});
		textFieldCautare.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textFieldCautare.setBounds(294, 80, 130, 20);
		home.add(textFieldCautare);
		textFieldCautare.setColumns(10);
		adds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
				    Desktop.getDesktop().browse(new URL(linkuriReclame[addCurent]).toURI());
				} catch (Exception e) {}
			}
		});
		
		adds.setBounds(1, 240, 442, 55);
		frmContacte.getContentPane().add(adds);
		
		ActionListener reclame = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(addCurent == 8)
					addCurent = -1;
				
				setReclama(++addCurent);
			}
		};
		
		setReclama(addCurent);
		javax.swing.Timer timerReclame = new javax.swing.Timer(5000, reclame);
		timerReclame.start();
		
		textFieldCautare.setVisible(false);
		
		comboBoxFiltrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxFiltrare.getSelectedIndex()) {
					case 0:
						agenda.filtreazaNrMobil();
						btnFiltreaza.setVisible(true);
						textFieldCautare.setVisible(false);
						break;
					case 1:
						agenda.filtreazaNrFix();
						btnFiltreaza.setVisible(true);
						textFieldCautare.setVisible(false);
						break;
					case 2:
						agenda.filtreazaNascutiAstazi();
						btnFiltreaza.setVisible(true);
						textFieldCautare.setVisible(false);
						break;
					case 3:
						agenda.filtreazaNascutiLunaCurenta();
						btnFiltreaza.setVisible(true);
						textFieldCautare.setVisible(false);
						break;
					case 4:
						btnFiltreaza.setVisible(false);
						textFieldCautare.setText("");
						agenda.filtreazaPersonalizat("");
						actualizareModel();
						textFieldCautare.setVisible(true);
						break;
					default:
						btnFiltreaza.setVisible(true);
						textFieldCautare.setVisible(false);
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(2, 2, 2, 2));
		menuBar.setBackground(new Color(98, 170, 156));
		frmContacte.setJMenuBar(menuBar);
		
		JMenu mnFisiere = new JMenu("Fisiere");
		menuBar.add(mnFisiere);
		
		JMenuItem mntmDeschidere = new JMenuItem("Deschidere");
		mntmDeschidere.setEnabled(false);
		mntmDeschidere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirmare;
				try {
					if(!agenda.contacte.isEmpty() && !agenda.isSaved()) {
						 confirmare = JOptionPane.showConfirmDialog(frmContacte, "Agenda curenta va fi pierduta.\n"
																					+ "Doriti sa continuati fara a o salva?", 
																					"Confirm", JOptionPane.YES_NO_OPTION, 
																					JOptionPane.WARNING_MESSAGE);
						 if(confirmare == JOptionPane.NO_OPTION) {
							 return;
						 }
					}
					
					
					JFileChooser alegeFisier = new JFileChooser();
					alegeFisier.setFileSelectionMode(JFileChooser.FILES_ONLY);
					alegeFisier.setMultiSelectionEnabled(false);
					int result = alegeFisier.showOpenDialog(frmContacte);
					if (result == JFileChooser.CANCEL_OPTION)
						return;
					
					agenda.incarca(alegeFisier.getSelectedFile().getAbsolutePath());
					actualizareModel();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmContacte, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		mntmDeschidere.setIcon(new ImageIcon(Aplicatie.class.getResource("/res/load.png")));
		mnFisiere.add(mntmDeschidere);
		
		
		ActionListener salvareAutomata = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agenda.salveaza(fisierSalvare);
			}
		};
		
		javax.swing.Timer timerSalvare = new javax.swing.Timer(60000, salvareAutomata);
		
		JMenuItem mntmSalvare = new JMenuItem("Salvare");
		mntmSalvare.setEnabled(false);
		mntmSalvare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser alegeFisier = new JFileChooser();
					alegeFisier.setFileSelectionMode(JFileChooser.FILES_ONLY);
					alegeFisier.setMultiSelectionEnabled(false);
					int result = alegeFisier.showSaveDialog(frmContacte);
					if (result == JFileChooser.CANCEL_OPTION)
						return;
					
					fisierSalvare = alegeFisier.getSelectedFile().getAbsolutePath();
					agenda.salveaza(fisierSalvare);
					if(timerSalvare.isRunning() == false)
						timerSalvare.start();
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmContacte, e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		mntmSalvare.setIcon(new ImageIcon(Aplicatie.class.getResource("/res/save_as.png")));
		mnFisiere.add(mntmSalvare);
		
		JMenuItem mntmIesire =	new JMenuItem("Iesire");
		mntmIesire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirmare;
				
				if(!agenda.contacte.isEmpty() && !agenda.isSaved())
					 confirmare = JOptionPane.showConfirmDialog(frmContacte, "Agenda curenta nu este salvata.\n"
																		   	+ "Doriti sa inchideti aplicatia fara a o salva?", 
																			"Confirm", JOptionPane.YES_NO_OPTION, 
																			JOptionPane.WARNING_MESSAGE);
				else
					 confirmare = JOptionPane.showConfirmDialog(frmContacte, "Doriti sa inchideti aplicatia?", 
							 												 "Confirm", JOptionPane.YES_NO_OPTION, 
							 												 JOptionPane.QUESTION_MESSAGE);
					
					
				if(confirmare == JOptionPane.NO_OPTION) {
					return;
				}
					 
				System.exit(0);
					
			}
		});
		mntmIesire.setIcon(new ImageIcon(Aplicatie.class.getResource("/res/close.png")));
		mnFisiere.add(mntmIesire);
		
		JMenu mnAjutor = new JMenu("Ajutor");
		menuBar.add(mnAjutor);
		
		JMenuItem mntmInregistrare = new JMenuItem("Inregistrare");
		mntmInregistrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = JOptionPane.showInputDialog(frmContacte, "Introduceti codul de inregistrare:", "Register", JOptionPane.PLAIN_MESSAGE);
				if(code == null)
					return;
				if(code.toLowerCase().equals(licence_key)) {
					adds.setVisible(false);
					timerReclame.stop();
					frmContacte.setSize(frmContacte.getWidth(), 300);
					mntmDeschidere.setEnabled(true);
					mntmSalvare.setEnabled(true);
					mntmInregistrare.setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(frmContacte, "Codul introdus nu este un Licence-Key valid.", "Eroare", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		mntmInregistrare.setIcon(new ImageIcon(Aplicatie.class.getResource("/res/write.png")));
		mnAjutor.add(mntmInregistrare);
		
		JMenuItem mntmDespre = new JMenuItem("Despre");
		mntmDespre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoFrame.setVisible(true);
			}
		});
		mntmDespre.setIcon(new ImageIcon(Aplicatie.class.getResource("/res/about.png")));
		mnAjutor.add(mntmDespre);
		
		Dimension dimm = Toolkit.getDefaultToolkit().getScreenSize();
		frmContacte.setLocation(dimm.width/2 - frmContacte.getSize().width/2, dimm.height/2 - frmContacte.getSize().height/2);
		
	}
}
