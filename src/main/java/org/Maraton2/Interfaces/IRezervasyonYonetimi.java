package org.Maraton2.Interfaces;

import org.Maraton2.Models.Rezervasyon;
import java.util.List;

public interface IRezervasyonYonetimi {
	boolean rezervasyonYap(Rezervasyon rezervasyon);
	boolean rezervasyonSil(String rezervasyonID);
	List<Rezervasyon> rezervasyonlariListele();
}