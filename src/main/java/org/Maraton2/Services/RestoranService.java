package org.Maraton2.Services;

import org.Maraton2.Models.Restoran;
import org.Maraton2.Repositories.RestoranRepository;

import java.util.List;

public class RestoranService {
	private final RestoranRepository restoranRepository;
	
	public RestoranService(RestoranRepository restoranRepository) {
		this.restoranRepository = restoranRepository;
	}
	
	public void restoranEkle(Restoran restoran) {
		restoranRepository.restoranEkle(restoran);
	}
	
	public void restoranSil(String restoranID) {
		restoranRepository.restoranSil(restoranID);
	}
	
	public Restoran restoranIDileAra(String restoranID) {
		return restoranRepository.restoranIDileAra(restoranID).orElse(null);
	}
	
	public List<Restoran> restoranlariListele() {
		return restoranRepository.restoranlariListele();
	}
	
	public boolean restoranIDVarMi(String restoranID) {
		return restoranRepository.restoranIDVarMi(restoranID);
	}
	
	public void restoranKapasitesiniGuncelle(String restoranID, int yeniKapasite) {
		restoranRepository.restoranKapasitesiniGuncelle(restoranID, yeniKapasite);
	}
	
	public void kapasiteAzalt(String restoranID) {
		restoranRepository.kapasiteAzalt(restoranID);
	}
}