package org.Maraton2.Repositories;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Models.Restoran;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestoranRepository {
	private final List<Restoran> restoranlar = new ArrayList<>();
	
	public void restoranEkle(Restoran restoran) {
		restoranlar.add(restoran);
	}
	
	public void restoranSil(String restoranID) {
		restoranlar.removeIf(r -> r.getRestoranID().equals(restoranID));
	}
	
	public Optional<Restoran> restoranIDileAra(String restoranID) {
		return restoranlar.stream()
		                  .filter(r -> r.getRestoranID().equals(restoranID))
		                  .findFirst();
	}
	
	public List<Restoran> restoranlariListele() {
		return new ArrayList<>(restoranlar);
	}
	
	public boolean restoranIDVarMi(String restoranID) {
		return restoranlar.stream()
		                  .anyMatch(r -> r.getRestoranID().equals(restoranID));
	}
	
	public void kapasiteAzalt(String restoranID) {
		restoranIDileAra(restoranID).ifPresent(restoran -> {
			int mevcutKapasite = restoran.getKapasite();
			if (mevcutKapasite > 0) {
				restoran.setKapasite(mevcutKapasite - 1);
			}
		});
	}
	
	public List<Restoran> restoranDurumunaGoreAra(Durum durum) {
		return restoranlar.stream()
                          .filter(r -> r.getDurum().equals(durum))
                          .collect(Collectors.toList());
	}
	
	public List<Restoran> restoranTipineGoreAra(RestoranTipi restoranTipi) {
		return restoranlar.stream()
                          .filter(r -> r.getTipi().equals(restoranTipi))
                          .collect(Collectors.toList());
	}
}