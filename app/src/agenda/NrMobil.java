package agenda;

import java.io.Serializable;

public class NrMobil extends NrTel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NrMobil(String nrMobil) {
		super(nrMobil);
	}
	
	@Override
	public int compareTo(NrTel nr2) {
		return super.numarTelefon.compareTo( nr2.numarTelefon );
	}

	@Override
	public boolean validareNumar(String nr) {
		if( nr.matches("07[0-9]{8}") == false)
			return false;
		return true;
	}
	
}
