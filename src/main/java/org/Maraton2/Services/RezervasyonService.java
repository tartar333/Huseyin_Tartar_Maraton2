package org.Maraton2.Services;

import org.Maraton2.Models.RezervasyonModel;
import org.Maraton2.Repositories.MusteriRepository;
import org.Maraton2.Repositories.RezervasyonRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RezervasyonService {
	private final List<RezervasyonModel> rezervasyonlar = new ArrayList<>();

	// Rezervasyon ID ile var olup olmadığını kontrol etme
	public boolean rezervasyonIDVarMi(String rezervasyonID) {
		return rezervasyonlar.stream()
		                     .anyMatch(r -> r.getRezervasyonID().equals(rezervasyonID));
	}
	
	// Rezervasyon ekleme
	public void rezervasyonYap(RezervasyonModel rezervasyonModel) {
		rezervasyonlar.add(rezervasyonModel);
	}
	
	// Rezervasyonları listeleme
	public List<RezervasyonModel> rezervasyonlariListele() {
		return new ArrayList<>(rezervasyonlar);
	}
	
	// Rezervasyon ID ile silme
	public void rezervasyonSil(String rezervasyonID) {
		rezervasyonlar.removeIf(r -> r.getRezervasyonID().equals(rezervasyonID));
	}

	// Tarihe göre rezervasyonları listeleme
	public List<RezervasyonModel> tarihileRezervasyonlariListele(LocalDateTime tarih) {
		return rezervasyonlar.stream()
		                     .filter(r -> r.getRezervasyonTarihi().toLocalDate().equals(tarih.toLocalDate()))
		                     .collect(Collectors.toList());
	}
}