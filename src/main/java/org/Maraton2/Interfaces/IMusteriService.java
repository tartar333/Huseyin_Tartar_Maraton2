package org.Maraton2.Interfaces;

import org.Maraton2.Models.Musteri;

import java.util.List;

public interface IMusteriService {
	boolean musteriIDVarMi(String musteriID);
	void musteriEkle(Musteri musteri);
	void musteriSil(String musteriID);
	List<Musteri> musterileriListele();
	Musteri musteriIDileAra(String musteriID);
	List<Musteri> musteriIsimIleAra(String isim);
	
}