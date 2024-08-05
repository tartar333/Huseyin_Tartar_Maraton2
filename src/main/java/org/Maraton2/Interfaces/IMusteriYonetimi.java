package org.Maraton2.Interfaces;

import org.Maraton2.Models.Musteri;
import java.util.List;

public interface IMusteriYonetimi {
	boolean musteriEkle(Musteri musteri);
	void musteriSil(String musteriID);
	Musteri musteriAra(String musteriID);
	List<Musteri> musterileriListele();
}