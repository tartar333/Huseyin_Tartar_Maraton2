package org.Maraton2.Interfaces;

import org.Maraton2.Models.Restoran;
import java.util.List;

public interface IRestoranYonetimi {
	void restoranEkle(Restoran restoran);
	void restoranSil(String restoranID);
	Restoran restoranAra(String restoranID);
	List<Restoran> restoranlariListele();
}