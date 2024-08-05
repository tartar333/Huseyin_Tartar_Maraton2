package org.Maraton2.Models;

public class Musteri {
	private final String id;
	private final String isim;
	private final String soyisim;
	private final String telefon;
	private final String mail;
	
	public Musteri(String id, String isim, String soyisim, String telefon, String mail) {
		this.id = id;
		this.isim = isim;
		this.soyisim = soyisim;
		this.telefon = telefon;
		this.mail = mail;
	}
	
	public String getId() {
		return id;
	}
	
	public String getIsim() {
		return isim;
	}
	
	public String getSoyisim() {
		return soyisim;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public String getMail() {
		return mail;
	}
	
	
	@Override
	public String toString() {
		return "Müşteri ID: " + id + ", İsim: " + isim + ", Soyisim: " + soyisim + ", Telefon: " + telefon + ", Mail: " + mail;
	}
}