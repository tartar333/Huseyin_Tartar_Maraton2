package org.Maraton2.Services;

import org.Maraton2.Repositories.RestoranRepository;
import org.Maraton2.Models.Restoran;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Enums.Durum;

import java.util.List;

public class RestoranService {
	private RestoranRepository restoranRepo = new RestoranRepository();
	
	public RestoranService() {
		this.restoranRepo = restoranRepo;
	}
	
	public void restoranEkle(Restoran restoran) {
		restoranRepo.restoranEkle(restoran);
	}
	
	public void restoranSil(String restoranID) {
		restoranRepo.restoranSil(restoranID);
	}
	
	public Restoran restoranIDileAra(String restoranID) {
		return restoranRepo.restoranAra(restoranID);
	}
	
	public List<Restoran> restoranlariListele() {
		return restoranRepo.restoranlariListele();
	}
	
	public List<Restoran> restoranTipiIleAra(RestoranTipi tipi) {
		return restoranRepo.restoranTipiIleAra(tipi);
	}
	
	public List<Restoran> restoranAdiIleAra(String restoranAdi) {
		return restoranRepo.restoranAdiIleAra(restoranAdi);
	}
	
	public List<Restoran> restoranDurumuIleAra(Durum durum) {
		return restoranRepo.restoranDurumuIleAra(durum);
	}
	
	public void restoranKapasitesiniGuncelle(String restoranID, int yeniKapasite) {
		restoranRepo.restoranKapasitesiniGuncelle(restoranID, yeniKapasite);
	}
	
	public void restoranGuncelle(Restoran restoran) {
	
	}
}