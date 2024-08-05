package org.Maraton2.Repositories.Entities;

public class Musteri {
	private String musteriID;
	private String isim;
	private String soyisim;
	private String telefon;
	private String eposta;
	
	public Musteri(String musteriID, String isim, String soyisim, String telefon, String eposta) {
		this.musteriID = musteriID;
		this.isim = isim;
		this.soyisim = soyisim;
		this.telefon = telefon;
		this.eposta = eposta;
	}
	
	public String getMusteriID() {
		return musteriID;
	}
	
	public void setMusteriID(String musteriID) {
		this.musteriID = musteriID;
	}
	
	public String getIsim() {
		return isim;
	}
	
	public void setIsim(String isim) {
		this.isim = isim;
	}
	
	public String getSoyisim() {
		return soyisim;
	}
	
	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public String getEposta() {
		return eposta;
	}
	
	public void setEposta(String eposta) {
		this.eposta = eposta;
	}
	@Override
	public String toString() {
		return "Müşteri ID: " + musteriID + "\n" +
				"İsim: " + isim + "\n" +
				"Soyisim: " + soyisim + "\n" +
				"Telefon: " + telefon + "\n" +
				"E-posta: " + eposta +  "\n" +
				"----------------------------------------------------------------";
	}
}