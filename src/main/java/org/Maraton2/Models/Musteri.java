package org.Maraton2.Models;

import java.util.ArrayList;

public class Musteri {
	private String id;
	private String adi;
	private String soyadi;
	private String telefon;
	private String mail;
	
	public Musteri(String id, String adi, String soyadi, String telefon, String mail) {
		this.id = id;
		this.adi = adi;
		this.soyadi = soyadi;
		this.telefon = telefon;
		this.mail = mail;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAdi() {
		return adi;
	}
	
	public void setAdi(String adi) {
		this.adi = adi;
	}
	
	public String getSoyadi() {
		return soyadi;
	}
	
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "Müşteri ID: " + id + ", İsim: " + adi + ", Soyisim: " + soyadi + ", Telefon: " + telefon + ", Mail: " + mail;
	}
}