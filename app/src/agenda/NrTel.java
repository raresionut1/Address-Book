package agenda;

import java.io.Serializable;

public abstract class NrTel implements Comparable<NrTel>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String numarTelefon;
	
	public NrTel(String tel) {
		if(validareNumar(tel) == false)
			throw new IllegalArgumentException("Numar de telefon invalid.");
		
		numarTelefon = tel;
	}
	
	public abstract boolean validareNumar(String nr);
	
	public boolean isMobil() {
		if(numarTelefon.startsWith("07"))
			return true;
		else
			return false;
	}
	
	public boolean isFix() {
		if(numarTelefon.startsWith("02") || numarTelefon.startsWith("03"))
			return true;
		else
			return false;
	}
	
	public boolean equals(NrTel nr2) {
		return numarTelefon.equals( nr2.numarTelefon );
	}
	
	public int compareTo(NrTel nr2) {
		return numarTelefon.compareTo( nr2.numarTelefon );
	}
	
	public String toString() {
		return numarTelefon;
	}
	
}
