package Zwierzeta;
import java.util.Random;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;

public class Czlowiek extends Zwierze {
	private int PozostalyCzasZdolnosci;
	private int CooldownZdolnosci;
	private boolean czyAktywnaZdolnosc;
	private int ruch;
	private void Niesmiertelnosc() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		if (this.czyNajslabszy()) {
			return;
		}
		while (true) {
			Random random = new Random();
			int x = random.nextInt(4);
			// KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
			if (x == 0) {
				if (this.polozenieY != 0) {
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
						if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX].getSila() <= this.sila) {
							swiat_t.mapa[this.polozenieY - 1][this.polozenieX].Kolizja(this);
						}
						if (!this.czyZyje) {
							this.czyNapewnoSmierc();
						}
					}
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY -= 1;
					}
					break;
				}
			} else if (x == 1) {
				if (this.polozenieX < szerokosc - 1) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1].getSila() <= this.sila) {
							swiat_t.mapa[this.polozenieY][this.polozenieX + 1].Kolizja(this);
						}
						if (!this.czyZyje) {
							this.czyNapewnoSmierc();
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX + 1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 1;
					}
					break;
				}
			} else if (x == 2) {
				if (this.polozenieY != wysokosc - 1) {
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
						if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX].getSila() <= this.sila) {
							swiat_t.mapa[this.polozenieY + 1][this.polozenieX].Kolizja(this);
						}
						if (!this.czyZyje) {
							this.czyNapewnoSmierc();
						}
					}
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY + 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 1;

					}
					break;
				}
			} else if (x == 3) {
				if (this.polozenieX != 0) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1].getSila() <= this.sila) {
							swiat_t.mapa[this.polozenieY][this.polozenieX - 1].Kolizja(this);
						}
						if (!this.czyZyje) {
							this.czyNapewnoSmierc();
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
	private boolean czyNajslabszy() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		int i = 0;
		if (this.polozenieY != 0) {
			if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
				if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX].getSila() > this.sila) {
					i++;
				}
			}
		} else {
			i++;
		}
		if (this.polozenieX < szerokosc - 1) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
				if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1].getSila() > this.sila) {
					i++;
				}
			}
		} else {
			i++;
		}
		if (this.polozenieY != wysokosc - 1) {
			if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
				if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX].getSila() > this.sila) {
					i++;
				}
			}
		} else {
			i++;
		}
		if (this.polozenieX != 0) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
				if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1].getSila() > this.sila) {
					i++;
				}
			}
		} else {
			i++;
		}
		if (i == 4) {
			return true;
		} else {
			return false;
		}
	}
	public boolean AktywujSuperUmiejetnosc() {
		if (!this.czyAktywnaZdolnosc && this.CooldownZdolnosci == 0) {
			this.czyAktywnaZdolnosc = true;
			this.PozostalyCzasZdolnosci = 5;
			return true;
		}
		return false;
	}
	public void KomunikatSuperUmiejetnosci() {
		if (this.CooldownZdolnosci == 1) {
			this.CooldownZdolnosci--;
		}
		if (!this.czyAktywnaZdolnosc && this.CooldownZdolnosci == 0) {
			this.swiat_t.komunikat = "Nieaktywowana";
		} 
		else if (this.CooldownZdolnosci > 1) {
			this.CooldownZdolnosci--;
			this.swiat_t.komunikat = "Nieaktywowana, Cooldown supermocy: "+this.CooldownZdolnosci;
		} 
		else if (this.czyAktywnaZdolnosc) {
			if (this.PozostalyCzasZdolnosci > 1) {
				this.PozostalyCzasZdolnosci--;
				this.swiat_t.komunikat = "Aktywna przez "+this.PozostalyCzasZdolnosci+" tury!";
			} 
			else {
				this.czyAktywnaZdolnosc = false;
				this.CooldownZdolnosci = 5;
				this.swiat_t.komunikat = "Nieaktywowana, Cooldown supermocy: "+this.CooldownZdolnosci;
			}
		}
	}
	@Override
	public void Akcja() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		// KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
		while(true) {
			if (this.ruch == 0) {
				if (this.polozenieY != 0) {
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
						if (this.czyAktywnaZdolnosc
								&& (swiat_t.mapa[this.polozenieY - 1][this.polozenieX].getSila() > this.sila)) {
							Niesmiertelnosc();
						} else {
							swiat_t.mapa[this.polozenieY - 1][this.polozenieX].Kolizja(this);
						}
						if (!this.czyZyje) {
							if (this.czyAktywnaZdolnosc) {
								this.czyNapewnoSmierc();
							} else {
								break;
							}
						}
					}
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY -= 1;
	
					}
					break;
				}
			} else if (this.ruch == 1) {
				if (this.polozenieX < szerokosc - 1) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
						if (this.czyAktywnaZdolnosc
								&& (swiat_t.mapa[this.polozenieY][this.polozenieX + 1].getSila() > this.sila)) {
							Niesmiertelnosc();
						} else {
							swiat_t.mapa[this.polozenieY][this.polozenieX + 1].Kolizja(this);
						}
						if (!this.czyZyje) {
							if (this.czyAktywnaZdolnosc) {
								this.czyNapewnoSmierc();
							} else {
								break;
							}
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX + 1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 1;
					}
					break;
				}
			} else if (this.ruch == 2) {
				if (this.polozenieY != wysokosc - 1) {
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
						if (this.czyAktywnaZdolnosc
								&& (swiat_t.mapa[this.polozenieY + 1][this.polozenieX].getSila() > this.sila)) {
							Niesmiertelnosc();
						} else {
							swiat_t.mapa[this.polozenieY + 1][this.polozenieX].Kolizja(this);
						}
						if (!this.czyZyje) {
							if (this.czyAktywnaZdolnosc) {
								this.czyNapewnoSmierc();
							} else {
								break;
							}
						}
					}
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY + 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 1;
	
					}
					break;
				}
			} else if (this.ruch == 3) {
				if (this.polozenieX != 0) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
						if (this.czyAktywnaZdolnosc
								&& (swiat_t.mapa[this.polozenieY][this.polozenieX - 1].getSila() > this.sila)) {
							Niesmiertelnosc();
						} else {
							swiat_t.mapa[this.polozenieY][this.polozenieX - 1].Kolizja(this);
						}
						if (!this.czyZyje) {
							if (this.czyAktywnaZdolnosc) {
								this.czyNapewnoSmierc();
							} else {
								break;
							}
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
			break;
		}
		KomunikatSuperUmiejetnosci();
	}
	@Override
	public void Kolizja(Organizm Atakujacy) {
		if (this.czyAktywnaZdolnosc && this.sila <= Atakujacy.getSila()) {
			this.Niesmiertelnosc();
		} else if (this.sila > Atakujacy.getSila()) {
			String kom = Atakujacy.getSymbol() + " umiera w ataku na " + this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			Atakujacy.setCzyZyje(false);
			Atakujacy.getSwiat().mapa[Atakujacy.getPolozenieY()][Atakujacy.getPolozenieX()] = null;
		} else if (this.sila <= Atakujacy.getSila()) {
			String kom = this.symbol + " umiera w obronie przez " + Atakujacy.getSymbol();
			this.swiat_t.DodajKomentarz(kom);
			this.czyZyje = false;
			this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		}
	}
	public void czyNapewnoSmierc() {
		if (this.czyAktywnaZdolnosc) {
			this.swiat_t.mapa[this.polozenieY][this.polozenieX] = this;
			this.czyZyje = true;
		}
	}
	public boolean getCzyAktywnaZdolnosc() {
		return this.czyAktywnaZdolnosc;
	}
	public int getCooldownZdolnosc() {
		return this.CooldownZdolnosci;
	}
	public void setRuch(int i) {
		this.ruch = i;
	}
	@Override	
	public Organizm Potomstwo(Swiat swiat_t, int x, int y) {
		return null;
	}
	public Czlowiek(Swiat swiat, int x, int y) {
		super(swiat, 5, 10, 0, '$', x, y, true);
		this.CooldownZdolnosci = 0;
		this.czyAktywnaZdolnosc = false;
		this.PozostalyCzasZdolnosci = 0;
	}
}
