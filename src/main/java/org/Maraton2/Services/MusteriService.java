package org.Maraton2.Services;

import org.Maraton2.Models.Musteri;
import org.Maraton2.Repositories.MusteriRepository;

import java.util.ArrayList;
import java.util.List;

public class MusteriService {
	private final List<Musteri> musteriler = new ArrayList<>();
	public Musteri musteriIDileAra(String musteriID) {
		return musteriler.stream()
		                     .filter(musteri -> musteri.getId().equals(musteriID))
		                     .findFirst()
		                     .orElse(null);
	}
	public void musteriEkle(Musteri musteri) {
		musteriler.add(musteri);
	}

	
	public Musteri musteriAra(String musteriID) {
		
		return musteriIDileAra(musteriID);
	}

	
	public void musteriSil(String id) {
		musteriler.removeIf(musteri -> musteri.getId().equals(id));
	}
	
	public List<Musteri> musteriIsimIleAra(String isim) {
		List<Musteri> sonuc = new ArrayList<>();
		for (Musteri musteri : musteriler) {
			if (musteri.getIsim().equalsIgnoreCase(isim)) {
				sonuc.add(musteri);
			}
		}
		return sonuc;
	}
	
	
	public List<Musteri> musterileriListele() {
		return new ArrayList<>(musteriler);
	}
	
	public boolean musteriIDVarMi(String musteriID) {
		return musteriler.stream()
                         .anyMatch(m -> m.getId().equals(musteriID));
	}
}