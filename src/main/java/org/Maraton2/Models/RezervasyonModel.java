package org.Maraton2.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class RezervasyonModel {
	private String rezervasyonID;
	private String musteriID;
	private String restoranID;
	private LocalDateTime rezervasyonTarihi;
	private int kapasite;
	

	public RezervasyonModel(String rezervasyonID, String musteriID, String restoranID, LocalDateTime rezervasyonTarihi) {
		this.rezervasyonID = rezervasyonID;
        this.musteriID = musteriID;
        this.restoranID = restoranID;
        this.rezervasyonTarihi = rezervasyonTarihi;
        this.kapasite = 0; // Rezervasyon kapasitesi 0 olarak başlangıç yapıyoruz
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