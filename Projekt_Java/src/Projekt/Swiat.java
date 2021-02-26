package Projekt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import Zwierzeta.*;
import Rosliny.*;

public class Swiat {
	private int szerokosc;
	private int wysokosc;
	private Organizm[][] stworzMape(){
		mapa = new Organizm[20][20];
		return mapa;
	}
	private void SpawnOrganizmow() {
		Random random = new Random();
		for (int i = 0; i < 26; i++) {
			Organizm organism = null;
			int x = random.nextInt(wysokosc);
			int y = random.nextInt(szerokosc);
			if (i == 0) {
				organism = new Czlowiek(this, x, y);
			}
			else if (i < 4) {
				organism = new Wilk(this,x,y);
			}
			else if (i < 7) {
				organism = new Owca(this,x,y);
			}
			else if (i < 10) {
				organism = new Lis(this,x,y);
			}
			else if (i < 13) {
				organism = new Antylopa(this,x,y);
			}
			else if (i < 15) {
				organism = new Zolw(this, x,y);
			}
			else if (i < 17) {
				organism = new Guarana(this, x, y);
			}
			else if (i < 19) {
				organism = new Wilcze_Jagody(this, x, y);
			}
			else if (i < 21) {
				organism = new Mlecz(this, x, y);
			}
			else if (i < 23) {
				organism = new Trawa(this, x, y);
			}
			else if (i < 25) {
				organism = new Barszcz_Sosnowskiego(this, x, y);
			}
			else if (i < 26) {
				organism = new Cyber_Owca(this, x, y);
			}
			if (mapa[y][x] != null) {
				i--;
				continue;
			}
			mapa[y][x] = organism;
			organizmy.add(organism);
		}
	}
	public Organizm[][] mapa;
	public ArrayList<Organizm> organizmy = new ArrayList<>();
	public ArrayList<String> komentarze = new ArrayList<>();
	public String komunikat;
	public void DodajDoOrganizmow(Organizm organizm) {
		this.organizmy.add(organizm);
	}
	public void DodajKomentarz(String kom) {
		this.komentarze.add(kom);
	}
	public boolean ZapiszGre() throws IOException{
		if(this.organizmy.get(0) instanceof Czlowiek) {
			if(((Czlowiek)(this.organizmy.get(0))).getCzyAktywnaZdolnosc() || (((Czlowiek)(this.organizmy.get(0))).getCooldownZdolnosc()>0)){
				return false;
			}
		}
		File file = new File("ZapisGry.txt");
		boolean sukces = file.createNewFile();
		boolean istnieje = file.exists();
		if(!sukces && !istnieje) {
			return false;
		}
		PrintWriter writer = new PrintWriter(file);
		String iloscOrganizmow = String.valueOf(organizmy.size());
		writer.println(iloscOrganizmow);
		for(int i=0;i<organizmy.size();i++) {
			String symbol,wiek,sila,polozenieY,polozenieX;
			Organizm organizm = organizmy.get(i);
			int symbolInt = organizm.getSymbol();
			symbol = String.valueOf(symbolInt);
			wiek = String.valueOf(organizm.getWiek());
			sila = String.valueOf(organizm.getSila());
			polozenieY = String.valueOf(organizm.getPolozenieY());
			polozenieX = String.valueOf(organizm.getPolozenieX());
			writer.println(symbol);
			writer.println(wiek);
			writer.println(sila);
			writer.println(polozenieY);
			writer.println(polozenieX);
		}
		writer.close();
		return true;
	}
	public boolean WczytajGre() throws FileNotFoundException {
		File file = new File("ZapisGry.txt");
		boolean istnieje = file.exists();
		if(!istnieje) {
			return false;
		}
		Scanner wczytaj = new Scanner(file);
		organizmy.removeAll(organizmy);
		this.stworzMape();
		int iloscOrganizmow = wczytaj.nextInt();
		for(int i=0;i<iloscOrganizmow;i++) {
			Organizm organism = null;
	        char symbol = (char)wczytaj.nextInt();
			int wiek = wczytaj.nextInt();
			int sila = wczytaj.nextInt();
			int y = wczytaj.nextInt();
			int x = wczytaj.nextInt();
			if (symbol == '$') {
				organism = new Czlowiek(this, x, y);
			}
			else if (symbol == 'A') {
				organism = new Antylopa(this, x, y);
			}
			else if (symbol == 'B') {
				organism = new Barszcz_Sosnowskiego(this, x, y);
			}
			else if (symbol == 'G') {
				organism = new Guarana(this, x, y);
			}
			else if (symbol == 'J') {
				organism = new Wilcze_Jagody(this, x, y);
			}
			else if (symbol == 'M') {
				organism = new Mlecz(this, x, y);
			}
			else if (symbol == 'T') {
				organism = new Trawa(this, x, y);
			}
			else if (symbol == 'Z') {
				organism = new Zolw(this, x, y);
			}
			else if (symbol == 'L') {
				organism = new Lis(this, x, y);
			}
			else if (symbol == 'W') {
				organism = new Wilk(this, x, y);
			}
			else if (symbol == 'O') {
				organism = new Owca(this, x, y);
			}
			else if (symbol == 'C') {
				organism = new Cyber_Owca(this, x, y);
			}
			organism.setSila(sila-organism.getSila());
			organism.setWiek(wiek - organism.getWiek());
			this.mapa[y][x] = organism;
			this.organizmy.add(organism);
		}
		wczytaj.close();
		return true;
	}
	public void WykonajTure() {
		//Sortowanie wedlug kolejnosci wykonywania akcji
		int k = 0;
		while (organizmy.size() - k > 1) {
			int max = 0;
			for (int i = 1; i < organizmy.size() - k; i++) {
				if (organizmy.get(max).getInicjatywa() > organizmy.get(i).getInicjatywa()) {
					max = i;
				}
			}
			Collections.swap(organizmy, max, (organizmy.size()-k-1));
			k++;
		}
		//Sortowanie wedlug wieku
		for (int i = 1; i < this.organizmy.size(); i++) {
			if (organizmy.get(i - 1).getInicjatywa() == organizmy.get(i).getInicjatywa()) {
				if (organizmy.get(i - 1).getWiek() < organizmy.get(i).getWiek()) {
					Collections.swap(organizmy, i-1, i);
					if (i >= 2) {
						i -= 2;
					}
				}
			}
		}
		for (int i = 0; i < this.organizmy.size(); i++) {
			if (organizmy.get(i).getCzyZyje() == true) {
				this.organizmy.get(i).Akcja();
			}
		}
		//Sprawdzenie czy jakieœ zwierzê nie umar³o , zwiekszenie wieku , zmniejszenie cooldownu superumiejetnosci lub czas aktywnej zdolnosci
		for (int i = 0; i < this.organizmy.size(); i++) {
			this.organizmy.get(i).setWiek(1);
			if (this.organizmy.get(i).getCzyZyje() == false) {
				organizmy.remove(organizmy.get(i));
				i--;
			}
		}
	}
	public int getSzerokosc() {
		return this.szerokosc;
	}
	public int getWysokosc() {
		return this.wysokosc;
	}
	public Swiat() {
		this.szerokosc = 20;
		this.wysokosc = 20;
		mapa = stworzMape();
		SpawnOrganizmow();
	}
}

