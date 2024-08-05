package org.Maraton2.Repositories.Entities;

import java.time.LocalDateTime;

public class Rezervasyon {
	private String rezervasyonID;
	private String musteriID;
	private String restoranID;
	private LocalDateTime rezervasyonTarihi;
	private int kapasite;
	
	public Rezervasyon(String rezervasyonID, String musteriID, String restoranID, LocalDateTime rezervasyonTarihi, int kapasite) {
		this.rezervasyonID = rezervasyonID;
		this.musteriID = musteriID;
		this.restoranID = restoranID;
		this.rezervasyonTarihi = rezervasyonTarihi;
		this.kapasite = kapasite;
	}
	
	public String getRezervasyonID() {
		return rezervasyonID;
	}
	
	public String getMusteriID() {
		return musteriID;
	}
	
	public String getRestoranID() {
		return restoranID;
	}
	
	public LocalDateTime getRezervasyonTarihi() {
		return rezervasyonTarihi;
	}
	
	public int getKapasite() { // Getter metodunu güncelleyelim
		return kapasite;
	}
	
	@Override
	public String toString() {
		return "Rezervasyon{" +
				"rezervasyonID='" + rezervasyonID + '\'' +
				", musteriID='" + musteriID + '\'' +
				", restoranID='" + restoranID + '\'' +
				", rezervasyonTarihi=" + rezervasyonTarihi +
				", kapasite=" + kapasite +
				'}';
	}
}