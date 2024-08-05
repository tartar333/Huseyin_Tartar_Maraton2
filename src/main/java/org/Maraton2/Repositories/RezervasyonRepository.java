package org.Maraton2.Repositories;

import org.Maraton2.Models.Rezervasyon;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonRepository {
	private final List<Rezervasyon> rezervasyonlar = new ArrayList<>();
	
	public boolean rezervasyonIDVarMi(String rezervasyonID) {
		return rezervasyonlar.stream()
		                     .anyMatch(r -> r.getRezervasyonID().equals(rezervasyonID));
	}
	
	public void rezervasyonYap(Rezervasyon rezervasyon) {
		rezervasyonlar.add(rezervasyon);
	}
	
	public List<Rezervasyon> rezervasyonlariListele() {
		return new ArrayList<>(rezervasyonlar);
	}
	public void rezervasyonSil(String rezervasyonID) {
	
	}
}