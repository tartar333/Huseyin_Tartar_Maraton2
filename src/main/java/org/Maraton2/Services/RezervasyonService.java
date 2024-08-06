package org.Maraton2.Services;

import org.Maraton2.Interfaces.IRezervasyonService;
import org.Maraton2.Models.Rezervasyon;
import org.Maraton2.Repositories.RezervasyonRepository;

import java.time.LocalDateTime;
import java.util.List;

public class RezervasyonService implements IRezervasyonService {
	private final RezervasyonRepository rezervasyonRepository;
	
	public RezervasyonService(RezervasyonRepository rezervasyonRepository) {
		this.rezervasyonRepository = rezervasyonRepository;
	}
	
	// Rezervasyon ekleme
	public void rezervasyonEkle(Rezervasyon rezervasyon) {
		rezervasyonRepository.rezervasyonYap(rezervasyon);
	}
	
	// Rezervasyon silme
	public void rezervasyonSil(String rezervasyonID) {
		rezervasyonRepository.rezervasyonSil(rezervasyonID);
	}
	
	// Rezervasyon ID ile arama
	public Rezervasyon rezervasyonIDileAra(String rezervasyonID) {
		if (rezervasyonRepository.rezervasyonIDVarMi(rezervasyonID)) {
			return rezervasyonRepository.rezervasyonlariListele().stream()
			                            .filter(r -> r.getRezervasyonID().equals(rezervasyonID))
			                            .findFirst()
			                            .orElse(null);
		}
		return null;
	}
	
	// Tarihe göre rezervasyonları listeleme
	public List<Rezervasyon> tarihileRezervasyonlariListele(LocalDateTime tarih) {
		return rezervasyonRepository.tarihleRezervasyonlariListele(tarih);
	}
	
	// Tüm rezervasyonları listeleme
	public List<Rezervasyon> rezervasyonlariListele() {
		return rezervasyonRepository.rezervasyonlariListele();
	}
	
	// Rezervasyon ID'nin var olup olmadığını kontrol etme
	public boolean rezervasyonIDVarMi(String rezervasyonID) {
		return rezervasyonRepository.rezervasyonIDVarMi(rezervasyonID);
	}

}