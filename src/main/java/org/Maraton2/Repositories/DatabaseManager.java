package org.Maraton2.Repositories;

import org.Maraton2.Repositories.Entities.*;
import org.Maraton2.Repositories.Entities.Enums.Durum;
import org.Maraton2.Repositories.Entities.Enums.RestoranTipi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseManager {
	private List<Restoran> restoranlar;
	private List<Musteri> musteriler;
	private List<Rezervasyon> rezervasyonlar;
	
	public DatabaseManager() {
		restoranlar = new ArrayList<>();
		musteriler = new ArrayList<>();
		rezervasyonlar = new ArrayList<>();
	}
	
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
	
	public boolean musteriEkle(Musteri musteri) {
		musteriler.add(musteri);
		
		return true;
	}
	
	public void musteriSil(String musteriID) {
		musteriler.removeIf(m -> m.getMusteriID().equals(musteriID));
	}
	
	public Musteri musteriAra(String musteriID) {
		return musteriler.stream()
		                 .filter(m -> m.getMusteriID().equals(musteriID))
		                 .findFirst()
		                 .orElse(null);
	}
	
	public List<Musteri> musterileriListele() {
		return new ArrayList<>(musteriler);
	}
	
	public boolean rezervasyonIDVarMi(String rezervasyonID) {
		return rezervasyonlar.stream()
		                     .anyMatch(r -> r.getRezervasyonID().equals(rezervasyonID));
	}
	
	public void rezervasyonYap(Rezervasyon rezervasyon) {
		rezervasyonlar.add(rezervasyon);
	}
	
	public void restoranKapasitesiniGuncelle(String restoranID, int yeniKapasite) {
		Restoran restoran = restoranAra(restoranID);
		if (restoran != null) {
			restoran.setKapasite(yeniKapasite);
		}
	}
	
	public List<Rezervasyon> rezervasyonlariListele() {
		return new ArrayList<>(rezervasyonlar);
	}
	
	public List<Restoran> restoranTipiIleAra(RestoranTipi tipi) {
		return restoranlar.stream()
                          .filter(r -> r.getTipi().equals(tipi))
                          .collect(Collectors.toList());
	}
	
	public List<Musteri> musteriIsimIleAra(String isim) {
		return musteriler.stream()
                          .filter(m -> m.getIsim().equalsIgnoreCase(isim))
                          .collect(Collectors.toList());
	}
	
	public List<Restoran> restoranAdiIleAra(String restoranAdi) {
		return restoranlar.stream()
                          .filter(r -> r.getAdi().equalsIgnoreCase(restoranAdi))
                          .collect(Collectors.toList());
	}
	public List<Restoran> restoranDurumuIleAra(Durum durum) {
		List<Restoran> sonuc = new ArrayList<>();
		for (Restoran restoran : restoranlar) {
			if (restoran.getDurum() == durum) {
				sonuc.add(restoran);
			}
		}
		return sonuc;
	}
}