package Projekt;
import java.util.Random;
public abstract class Zwierze extends Organizm {
	@Override
	public void Akcja() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		while (true) {
			Random random = new Random();
			int x = random.nextInt(4);
			//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
			if (x == 0) {
				if(this.polozenieY != 0){
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY -= 1;
						
					}
					break;
				}
			}
			else if (x == 1) {
				if (this.polozenieX < szerokosc-1) {
					if(swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null){
						swiat_t.mapa[this.polozenieY][this.polozenieX+1].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX+1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX+1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 1;
					}
					break;
				}
			}
			else if (x == 2) {
				if (this.polozenieY != wysokosc-1) {
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY+1][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY+1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY+1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 1;
						
					}
					break;
				}
			}
			else if (x == 3) {
				if (this.polozenieX != 0) {
					if(swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX-1].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX -= 1;
					}
					break;
				}
			}
		}
	}
	@Override
	public void Kolizja(Organizm Atakujacy) {
		if (Atakujacy.getSymbol() == this.symbol) {
			this.Rozmnazanie(Atakujacy, 1);
			return;	
		}
		if (this.sila > Atakujacy.getSila()) {
			String kom = Atakujacy.getSymbol() + " umiera w ataku na " + this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			Atakujacy.setCzyZyje(false);
			Atakujacy.getSwiat().mapa[Atakujacy.getPolozenieY()][Atakujacy.getPolozenieX()] = null;
		}
		else if (this.sila <= Atakujacy.getSila()) {
			String kom = this.symbol + " umiera w obronie przez " + Atakujacy.getSymbol();
			this.swiat_t.DodajKomentarz(kom);
			this.czyZyje = false;
			this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		}
	}
	@Override
	public void Rozmnazanie(Organizm drugiRodzic, double prawdopodobienstwo) {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		if (this.wiek <= 10 && drugiRodzic.getWiek() <= 10) {
			return;
		}
		prawdopodobienstwo *= 100;
		Random random = new Random();
		int x = random.nextInt(100);
		if (prawdopodobienstwo < x) {
			return;
		}
		if (this.czyWolneMiejsce()) {
			String kom = "Urodzi³ siê nowy "+this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			Organizm organism;
			while (true) {
				x = random.nextInt(4);
				//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
				if (x == 0) {
					if (this.polozenieY != 0) {
						if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX, this.polozenieY - 1);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = organism;
						}
						break;
					}
				}
				else if (x == 1) {
					if (this.polozenieX < szerokosc - 1) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX + 1, this.polozenieY);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY][this.polozenieX + 1] = organism;
						}
						break;
					}
				}
				else if (x == 2) {
					if (this.polozenieY != wysokosc - 1) {
						if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX, this.polozenieY + 1);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY + 1][this.polozenieX] = organism;
						}
						break;
					}
				}
				else if (x == 3) {
					if (this.polozenieX != 0) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX - 1, this.polozenieY);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = organism;
						}
						break;
					}
				}
			}
		}
		else if (drugiRodzic.czyWolneMiejsce()) {
			String kom = "Urodzi³ siê nowy "+this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			Organizm organism;
			while (true) {
				x = random.nextInt(4);
				//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
				if (x == 0) {
					if (drugiRodzic.getPolozenieY() != 0) {
						if (swiat_t.mapa[drugiRodzic.getPolozenieY() - 1][drugiRodzic.getPolozenieX()] == null) {
							organism = drugiRodzic.Potomstwo(drugiRodzic.getSwiat(), drugiRodzic.getPolozenieX(), drugiRodzic.getPolozenieY() - 1);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[drugiRodzic.getPolozenieY() - 1][drugiRodzic.getPolozenieX()] = organism;
						}
						break;
					}
				}
				else if (x == 1) {
					if (drugiRodzic.polozenieX < szerokosc - 1) {
						if (swiat_t.mapa[drugiRodzic.getPolozenieY()][drugiRodzic.getPolozenieX() + 1] == null) {
							organism = drugiRodzic.Potomstwo(drugiRodzic.getSwiat(), drugiRodzic.getPolozenieX() + 1, drugiRodzic.getPolozenieY());
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[drugiRodzic.getPolozenieY()][drugiRodzic.getPolozenieX() + 1] = organism;
						}
						break;
					}
				}
				else if (x == 2) {
					if (drugiRodzic.getPolozenieY() != wysokosc - 1) {
						if (swiat_t.mapa[drugiRodzic.getPolozenieY() + 1][drugiRodzic.getPolozenieX()] == null) {
							organism = drugiRodzic.Potomstwo(drugiRodzic.getSwiat(), drugiRodzic.getPolozenieX(), drugiRodzic.getPolozenieY() + 1);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[drugiRodzic.getPolozenieY() + 1][drugiRodzic.getPolozenieX()] = organism;
						}
						break;
					}
				}
				else if (x == 3) {
					if (drugiRodzic.polozenieX != 0) {
						if (swiat_t.mapa[drugiRodzic.polozenieY][drugiRodzic.polozenieX - 1] == null) {
							organism = drugiRodzic.Potomstwo(drugiRodzic.getSwiat(), drugiRodzic.polozenieX - 1, drugiRodzic.polozenieY);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[drugiRodzic.polozenieY][drugiRodzic.polozenieX - 1] = organism;
						}
						break;
					}
				}
			}
		}
	}
	public Zwierze(Swiat swiat, int sila, int inicjatywa, int wiek, char symbol, int x, int y, boolean czyZyje) {
		super(swiat,sila,inicjatywa,wiek,symbol,x,y,czyZyje);
	}
}
