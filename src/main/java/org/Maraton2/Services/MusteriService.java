package org.Maraton2.Services;

import org.Maraton2.Models.Musteri;
import java.util.ArrayList;
import java.util.List;

public class MusteriService {
	private final List<Musteri> musteriler = new ArrayList<>();
	
	public void musteriEkle(Musteri musteri) {
		musteriler.add(musteri);
	}
	
	public Musteri musteriAra(String id) {
		for (Musteri musteri : musteriler) {
			if (musteri.getId().equals(id)) {
				return musteri;
			}
		}
		return null;
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
}