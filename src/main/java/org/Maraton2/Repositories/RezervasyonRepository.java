package org.Maraton2.Repositories;

import org.Maraton2.Models.Rezervasyon;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RezervasyonRepository {
	private final List<Rezervasyon> rezervasyonlar = new ArrayList<>();
	
	// Rezervasyon ID ile var olup olmadığını kontrol etme
	public boolean rezervasyonIDVarMi(String rezervasyonID) {
		return rezervasyonlar.stream()
		                     .anyMatch(r -> r.getRezervasyonID().equals(rezervasyonID));
	}
	
	// Rezervasyon ekleme
	public void rezervasyonYap(Rezervasyon rezervasyon) {
		rezervasyonlar.add(rezervasyon);
	}
	
	// Rezervasyonları listeleme
	public List<Rezervasyon> rezervasyonlariListele() {
		return new ArrayList<>(rezervasyonlar);
	}
	
	// Rezervasyon ID ile silme
	public void rezervasyonSil(String rezervasyonID) {
		rezervasyonlar.removeIf(r -> r.getRezervasyonID().equals(rezervasyonID));
	}
	
	// Müşteri ID ile rezervasyonları listeleme
	public List<Rezervasyon> musteriIDileRezervasyonlariListele(String musteriID) {
		return rezervasyonlar.stream()
		                     .filter(r -> r.getMusteriID().equals(musteriID))
		                     .collect(Collectors.toList());
	}
	
	// Tarihe göre rezervasyonları listeleme
	public List<Rezervasyon> tarihleRezervasyonlariListele(LocalDateTime tarih) {
		return rezervasyonlar.stream()
		                     .filter(r -> r.getRezervasyonTarihi().toLocalDate().equals(tarih.toLocalDate()))
		                     .collect(Collectors.toList());
	}
}