package org.Maraton2.Repositories.Entities.Interfaces;

import org.Maraton2.Repositories.Entities.Musteri;
import java.util.List;

public interface IMusteriYonetimi {
	boolean musteriEkle(Musteri musteri);
	void musteriSil(String musteriID);
	Musteri musteriAra(String musteriID);
	List<Musteri> musterileriListele();
}