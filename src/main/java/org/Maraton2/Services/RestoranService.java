package org.Maraton2.Services;

import org.Maraton2.Enums.Durum;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Models.RestoranModel;
import org.Maraton2.Repositories.RestoranRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestoranService {
	private final List<RestoranModel> restoranlar = new ArrayList<>();
	
	public void restoranEkle(RestoranModel restoranModel) {
		restoranlar.add(restoranModel);
	}
	
	public void restoranSil(String restoranID) {
		restoranlar.removeIf(r -> r.getRestoranID().equals(restoranID));
	}
	
	public Optional<RestoranModel> restoranIDileAra(String restoranID) {
		return restoranlar.stream()
		                  .filter(r -> r.getRestoranID().equals(restoranID))
		                  .findFirst();
	}
	
	public List<RestoranModel> restoranlariListele() {
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
	
	public List<RestoranModel> restoranDurumunaGoreAra(Durum durum) {
		return restoranlar.stream()
                          .filter(r -> r.getDurum().equals(durum))
                          .collect(Collectors.toList());
	}
	
	public List<RestoranModel> restoranTipineGoreAra(RestoranTipi restoranTipi) {
		return restoranlar.stream()
                          .filter(r -> r.getTipi().equals(restoranTipi))
                          .collect(Collectors.toList());
	}
}