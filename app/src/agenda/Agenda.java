package agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Agenda implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Contact> contacte = new ArrayList<Contact>();
	
	private boolean isSaved = false;
	
	public boolean isSaved() {
		return isSaved;
	}
	
	Map<CriteriuOrdonare, Comparator<Contact>> ordonare = new HashMap<CriteriuOrdonare, Comparator<Contact>>();
	
	public Predicate<Contact> filtru = c -> true;
	
	CriteriuOrdonare criteriuOrdonare = CriteriuOrdonare.DUPA_NUME;
	
	public Agenda() {
		ordonare.put(CriteriuOrdonare.DUPA_NUME, (c1, c2) -> c1.getNume().compareTo(c2.getNume()));
		ordonare.put(CriteriuOrdonare.DUPA_PRENUME, (c1, c2) -> c1.getPrenume().compareTo(c2.getPrenume()));
		ordonare.put(CriteriuOrdonare.DUPA_NUMARUL_DE_TELEFON, (c1, c2) -> c1.getNumarTelefon().compareTo(c2.getNumarTelefon()));
		ordonare.put(CriteriuOrdonare.DUPA_DATA_DE_NASTERE, (c1, c2) -> c1.getDataNastere().compareTo(c2.getDataNastere()));
	}
	
	public void adaugareContact(Contact c){
		if(contacte.contains(c)) {
			System.out.println("WOW");
			throw new ContactDuplicatException();
		}		
		
		isSaved = false;
		contacte.add(c);
	}
	
	public void stergereContact(Contact c){
		isSaved = false;
		contacte.remove(c);
	}
	
	public void modificareContact(Contact cVechi, Contact cNou) {
		isSaved = false;
		contacte.remove(cVechi);
		contacte.add(cNou);
	}
	
	public void faraFiltrare() {
		filtru = c -> true;
	}
	
	
	public void filtreazaNrMobil(){
		filtru = c -> c.getNumarTelefon().isMobil();
	}
	
	public void filtreazaNrFix(){
		filtru = c -> c.getNumarTelefon().isFix();
	}
	
	public void filtreazaNascutiAstazi(){
		filtru = c -> c.getDataNastere().withYear(Year.now().getValue()).equals(LocalDate.now());
	}
	
	public void filtreazaNascutiLunaCurenta(){
		filtru = c -> c.getDataNastere().getMonth().equals(LocalDate.now().getMonth());
	}
	
	public boolean cautarePersonalizata(Contact c, String cautare) {
		boolean nrTel_intalnit = false;
		
		String cautare_lower = cautare.toLowerCase();
		String[] stringuri = cautare_lower.split(" ");
		
		for(String cuvant : stringuri) {
			if(cuvant.length() > 0)
				if(Character.isDigit(cuvant.charAt(0))) {
					if(nrTel_intalnit)
						return false;
					else
						nrTel_intalnit = true;
				}
			
			if(!c.getNume().toLowerCase().contains(cuvant) &&
			   !c.getPrenume().toLowerCase().contains(cuvant) &&
			   !c.getNumarTelefon().toString().toLowerCase().contains(cuvant))
				return false;
		}
		
		return true;
	}
	
	public void filtreazaPersonalizat(String cautare){
		filtru = c -> cautarePersonalizata(c, cautare);
			
	}

	
	public void ordoneaza(CriteriuOrdonare criteriu) {
		criteriuOrdonare = criteriu;
	}
	
	public List<Contact> contacte(){
		return contacte.stream()
						.filter(filtru)
						.sorted(ordonare.get(criteriuOrdonare))
						.collect(Collectors.toList());
	}
	
	//TODO: salveaza(Path) si incarca(Path)
	public void salveaza(String path) {
		
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(contacte);
			oos.close();
			isSaved = true;
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException("Fisierul selectat nu poate fi gasit.");
		}
		catch(StreamCorruptedException e) {
			throw new RuntimeException("Fisierul selectat nu este compatibil.\nSelectati alt fisier.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void incarca(String path) {
		
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.contacte = (List<Contact>)ois.readObject();
			ois.close();
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException("Fisierul selectat nu poate fi gasit.");
		}
		catch(StreamCorruptedException e) {
			throw new RuntimeException("Fisierul selectat nu este compatibil.\nSelectati alt fisier.");
		}
		catch(ClassNotFoundException e) {
			throw new RuntimeException("Fisierul selectat nu este compatibil.\nSelectati alt fisier.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
}
