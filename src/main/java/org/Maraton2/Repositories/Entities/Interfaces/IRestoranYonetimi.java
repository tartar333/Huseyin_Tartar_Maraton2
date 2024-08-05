package org.Maraton2.Repositories.Entities.Interfaces;

import org.Maraton2.Repositories.Entities.Restoran;
import java.util.List;

public interface IRestoranYonetimi {
	void restoranEkle(Restoran restoran);
	void restoranSil(String restoranID);
	Restoran restoranAra(String restoranID);
	List<Restoran> restoranlariListele();
}