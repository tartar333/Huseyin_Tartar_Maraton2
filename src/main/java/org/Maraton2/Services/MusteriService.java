package org.Maraton2.Services;

import org.Maraton2.Models.Musteri;
import org.Maraton2.Repositories.MusteriRepository;

import java.util.ArrayList;
import java.util.List;

public class MusteriService {
	private final MusteriRepository musteriRepository;
	private final List<Musteri> musteriler = new ArrayList<>();
	
	public MusteriService() {
		this.musteriRepository = new MusteriRepository();
	}
	
	public Musteri musteriIDileAra(String musteriID) {
		return musteriRepository.musteriIDileAra(musteriID);
	}
	
	public void musteriEkle(Musteri musteri) {
		musteriRepository.musteriEkle(musteri);
	}
	
	
	public Musteri musteriAra(String musteriID) {
		return musteriRepository.musteriIDileAra(musteriID);
	}
	
	
	public void musteriSil(String id) {
		musteriRepository.musteriSil(id);
	}
	
	public List<Musteri> musteriIsimIleAra(String isim) {
		return musteriRepository.musteriIsimIleAra(isim);
	}
	
	
	public List<Musteri> musterileriListele() {
		return musteriRepository.musterileriListele();
	}
	
	public boolean musteriIDVarMi(String musteriID) {
		return musteriRepository.musteriIDVarMi(musteriID);
	}
}