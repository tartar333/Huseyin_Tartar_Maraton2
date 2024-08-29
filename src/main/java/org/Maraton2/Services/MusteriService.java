package org.Maraton2.Services;

import org.Maraton2.Models.MusteriModel;
import org.Maraton2.Repositories.MusteriRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MusteriService {
	private final List<MusteriModel> musteriler = new ArrayList<>();

	public void musteriEkle(MusteriModel musteriModel) {
		musteriler.add(musteriModel);
	}
	
	public void musteriSil(String musteriID) {
		musteriler.removeIf(m -> m.getId().equals(musteriID));
	}
	
	public MusteriModel musteriIDileAra(String musteriID) {
		return musteriler.stream()
		                 .filter(m -> m.getId().equals(musteriID))
		                 .findFirst()
		                 .orElse(null);
	}
	
	public List<MusteriModel> musterileriListele() {
		return new ArrayList<>(musteriler);
	}
	
	public List<MusteriModel> musteriIsimIleAra(String isim) {
		return musteriler.stream()
		                 .filter(m -> m.getAdi().equalsIgnoreCase(isim))
		                 .collect(Collectors.toList());
	}
	
	public boolean musteriIDVarMi(String musteriID) {
		return musteriler.stream()
		                 .anyMatch(m -> m.getId().equals(musteriID));
	}
}