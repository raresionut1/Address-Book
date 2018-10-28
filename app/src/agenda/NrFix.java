package agenda;

import java.io.Serializable;

public class NrFix extends NrTel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NrFix(String nrFix) {
		super(nrFix);
	}

	@Override
	public int compareTo(NrTel nr2) {
		return super.numarTelefon.compareTo( nr2.numarTelefon );
	}

	@Override
	public boolean validareNumar(String nr) {
		if( nr.matches("0[2-3][0-9]{8}") == false)
			return false;
		return true;
	}

}
