package org.Maraton2.Interfaces;

import org.Maraton2.Models.Rezervasyon;

import java.time.LocalDateTime;
import java.util.List;

public interface IRezervasyonService {
	boolean rezervasyonIDVarMi(String rezervasyonID);
	void rezervasyonEkle(Rezervasyon rezervasyon);
	void rezervasyonSil(String rezervasyonID);
	List<Rezervasyon> rezervasyonlariListele();
	Rezervasyon rezervasyonIDileAra(String rezervasyonID);
	List<Rezervasyon> tarihileRezervasyonlariListele(LocalDateTime tarih);
	
}