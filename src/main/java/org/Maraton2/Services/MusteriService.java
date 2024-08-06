package org.Maraton2.Services;

import org.Maraton2.Interfaces.IMusteriService;
import org.Maraton2.Models.Musteri;
import org.Maraton2.Repositories.MusteriRepository;

import java.util.ArrayList;
import java.util.List;

public class MusteriService implements IMusteriService {
	private final MusteriRepository musteriRepository;
	
	public MusteriService(MusteriRepository musteriRepository) {
		this.musteriRepository = musteriRepository;
	}
	
	public Musteri musteriIDileAra(String musteriID) {
		return musteriRepository.musteriIDileAra(musteriID);
	}
	
	public void musteriEkle(Musteri musteri) {
		musteriRepository.musteriEkle(musteri);
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