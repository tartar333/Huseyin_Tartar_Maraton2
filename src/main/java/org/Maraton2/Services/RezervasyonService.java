package org.Maraton2.Services;

import org.Maraton2.Repositories.RezervasyonRepository;
import org.Maraton2.Models.Rezervasyon;

import java.util.List;

public class RezervasyonService {
	private RezervasyonRepository rezervasyonRepo = new RezervasyonRepository();
	
	public RezervasyonService() {
		this.rezervasyonRepo = rezervasyonRepo;
	}
	
	public boolean rezervasyonIDVarMi(String rezervasyonID) {
		return rezervasyonRepo.rezervasyonIDVarMi(rezervasyonID);
	}
	
	public void rezervasyonEkle(Rezervasyon rezervasyon) {
		rezervasyonRepo.rezervasyonYap(rezervasyon);
	}
	
	public List<Rezervasyon> rezervasyonlariListele() {
		return rezervasyonRepo.rezervasyonlariListele();
	}
	
	public void rezervasyonSil(String rezervasyonID) {
		rezervasyonRepo.rezervasyonYap(new Rezervasyon(rezervasyonID, "", ""));
	}
}