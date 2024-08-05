package org.Maraton2.Repositories.Entities;

import org.Maraton2.Repositories.Entities.Enums.Durum;
import org.Maraton2.Repositories.Entities.Enums.RestoranTipi;

public class Restoran {
	private String restoranID;
	private String adi;
	private String adres;
	private String telefon;
	private int kapasite;
	private Durum durum;
	private RestoranTipi tipi;
	
	
	public Restoran(String restoranID, String adi, String adres, String telefon, int kapasite, Durum durum, RestoranTipi tipi) {
		this.restoranID = restoranID;
		this.adi = adi;
		this.adres = adres;
		this.telefon = telefon;
		this.kapasite = kapasite;
		this.durum = durum;
		this.tipi = tipi;
	}
	
	
	public String getRestoranID() {
		return restoranID;
	}
	
	public void setRestoranID(String restoranID) {
		this.restoranID = restoranID;
	}
	
	public String getAdi() {
		return adi;
	}
	
	public void setAdi(String adi) {
		this.adi = adi;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public int getKapasite() {
		return kapasite;
	}
	
	public void setKapasite(int kapasite) {
		this.kapasite = kapasite;
	}
	
	public Durum getDurum() {
		return durum;
	}
	
	public void setDurum(Durum durum) {
		this.durum = durum;
	}
	
	public RestoranTipi getTipi() {
		return tipi;
	}
	
	public void setTipi(RestoranTipi tipi) {
		this.tipi = tipi;
	}
	
	@Override
	public String toString() {
		return "Restoran{" +
				"restoranID='" + restoranID + '\'' +
				", adi='" + adi + '\'' +
				", adres='" + adres + '\'' +
				", telefon='" + telefon + '\'' +
				", kapasite=" + kapasite +
				", durum=" + durum +
				", tipi=" + tipi +
				'}';
	}
}