package org.Maraton2.Repositories.Entities.Interfaces;

import org.Maraton2.Repositories.Entities.Rezervasyon;
import java.util.List;

public interface IRezervasyonYonetimi {
	boolean rezervasyonYap(Rezervasyon rezervasyon);
	boolean rezervasyonSil(String rezervasyonID);
	List<Rezervasyon> rezervasyonlariListele();
}