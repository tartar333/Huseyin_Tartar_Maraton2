package org.Maraton2.Repositories;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Models.Restoran;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestoranRepository {
	private final List<Restoran> restoranlar = new ArrayList<>();
	
	public void restoranEkle(Restoran restoran) {
		restoranlar.add(restoran);
	}
	
	public void restoranSil(String restoranID) {
		restoranlar.removeIf(r -> r.getRestoranID().equals(restoranID));
	}
	
	public Restoran restoranAra(String restoranID) {
		return restoranlar.stream()
		                  .filter(r -> r.getRestoranID().equals(restoranID))
		                  .findFirst()
		                  .orElse(null);
	}
	
	public List<Restoran> restoranlariListele() {
		return new ArrayList<>(restoranlar);
	}
	
	public List<Restoran> restoranTipiIleAra(RestoranTipi tipi) {
		return restoranlar.stream()
		                  .filter(r -> r.getTipi().equals(tipi))
		                  .collect(Collectors.toList());
	}
	
	public List<Restoran> restoranAdiIleAra(String restoranAdi) {
		return restoranlar.stream()
		                  .filter(r -> r.getAdi().equalsIgnoreCase(restoranAdi))
		                  .collect(Collectors.toList());
	}
	
	public List<Restoran> restoranDurumuIleAra(Durum durum) {
		return restoranlar.stream()
		                  .filter(r -> r.getDurum().equals(durum))
		                  .collect(Collectors.toList());
	}
	
	public void restoranKapasitesiniGuncelle(String restoranID, int yeniKapasite) {
		Restoran restoran = restoranAra(restoranID);
		if (restoran != null) {
			restoran.setKapasite(yeniKapasite);
		}
	}
	
	public boolean restoranIDVarMi(String restoranID) {
		return restoranlar.stream()
                          .anyMatch(r -> r.getRestoranID().equals(restoranID));
	}
}