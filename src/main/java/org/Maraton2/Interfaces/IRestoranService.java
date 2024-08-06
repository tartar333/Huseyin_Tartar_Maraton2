package org.Maraton2.Interfaces;

import org.Maraton2.Models.Restoran;
import org.Maraton2.Enums.RestoranTipi;
import org.Maraton2.Enums.Durum;

import java.util.List;

public interface IRestoranService {
	boolean restoranIDVarMi(String restoranID);
	void restoranEkle(Restoran restoran);
	void restoranSil(String restoranID);
	List<Restoran> restoranlariListele();
	List<Restoran> restoranTipineGoreAra(RestoranTipi restoranTipi);
	List<Restoran> restoranDurumunaGoreAra(Durum durum);
	Restoran restoranIDileAra(String restoranID);
	void kapasiteAzalt(String restoranID);
}