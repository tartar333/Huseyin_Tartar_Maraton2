package org.Maraton2.Repositories;

import org.Maraton2.Models.Musteri;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MusteriRepository {
	private final List<Musteri> musteriler = new ArrayList<>();
	
	public void musteriEkle(Musteri musteri) {
		musteriler.add(musteri);
	}
	
	public void musteriSil(String musteriID) {
		musteriler.removeIf(m -> m.getId().equals(musteriID));
	}
	
	public Optional<Musteri>musteriIDileAra(String musteriID) {
		return musteriler.stream()
		                 .filter(m -> m.getId().equals(musteriID))
		                 .findFirst();
	}
	
	public List<Musteri> musterileriListele() {
		return new ArrayList<>(musteriler);
	}
	
	public List<Musteri> musteriIsimIleAra(String isim) {
		return musteriler.stream()
		                 .filter(m -> m.getIsim().equalsIgnoreCase(isim))
		                 .collect(Collectors.toList());
	}
	
	public boolean musteriIDVarMi(String musteriID) {
		return musteriler.stream()
		                 .anyMatch(m -> m.getId().equals(musteriID));
	}
}