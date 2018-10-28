package agenda;

import java.time.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class Contact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nume;
	private String prenume;
	private LocalDate dataNastere;
	private NrTel numarTelefon;
	
	private static final DateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	public Contact(String nume, String prenume, String dataNastere, String numarTelefon) {
		if(nume.matches("[a-zA-Z]+") == false  || prenume.matches("[a-zA-Z]+") == false)
			throw new IllegalArgumentException("Numele si prenumele trebuie sa contina DOAR litere.");
		
		if(nume.length() < 2 || prenume.length() < 2)
			throw new IllegalArgumentException("Numele si prenumele trebuie sa aiba cel putin 2 litere (FIECARE).");
		
		formatData.setLenient(false);
		
		try {
            formatData.parse(dataNastere);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data trebuie sa fie una valida si sa fie in formatul ZZ.LL.AAAA.");
        }
		
		LocalDate data = LocalDate.parse(dataNastere, formatter);
		
		if(data.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Data de nastere trebuie sa fie in trecut.");
		
		this.nume = nume.substring(0,1).toUpperCase() + nume.substring(1);
		this.prenume = prenume.substring(0,1).toUpperCase() + prenume.substring(1);;
		this.dataNastere = data;
		
		if(numarTelefon.startsWith("07"))
			this.numarTelefon = new NrMobil(numarTelefon);
		if(numarTelefon.startsWith("02") || numarTelefon.startsWith("03"))
			this.numarTelefon = new NrFix(numarTelefon);
		
	}
	
	public boolean isMale() {
		if(prenume.endsWith("a"))
			return false;
		return true;
	}
	
	public String getNume() {
		return nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public LocalDate getDataNastere() {
		return dataNastere;
	}
	
	public String getDataNastereString() {
		return formatter.format(dataNastere);
	}
	
	public NrTel getNumarTelefon() {
		return numarTelefon;
	}
	
	public void setNume(String nume) {
		this.nume = nume; 
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public void setLocalDate(LocalDate dataNastere) {
		this.dataNastere = dataNastere;
	}
	
	public void setNumarTelefon(NrTel numarTelefon) {
		this.numarTelefon = numarTelefon;
	}
	
	public boolean equals(Object contact2) {
		if(this.nume.equalsIgnoreCase(((Contact)contact2).nume) == false)
			return false;
		
		if(this.prenume.equalsIgnoreCase(((Contact)contact2).prenume) == false)
			return false;
		
		if(this.dataNastere.equals(((Contact)contact2).dataNastere) == false)
			return false;
		
		if(this.numarTelefon.equals(((Contact)contact2).numarTelefon) == false)
			return false;
		
		return true;
	}

	public String toString() {
		return prenume + " " + nume + ", " + numarTelefon + ", " + formatter.format(dataNastere);
	}
}
