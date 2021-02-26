package Projekt;
public abstract class Organizm {
	protected int wiek;
	protected int sila;
	protected int inicjatywa;
	protected int polozenieX;
	protected int polozenieY;
	protected boolean czyZyje;
	protected char symbol;
	protected Swiat swiat_t;
	
	abstract public void Akcja();
	abstract public void Kolizja(Organizm organizmAtakujacy);
	abstract public void Rozmnazanie(Organizm drugiRodzic, double prawdopodobienstwo);
	abstract public Organizm Potomstwo(Swiat swiat_t, int x, int y);
	public boolean czyWolneMiejsce() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		int i = 0;
		if (this.polozenieY != 0) {
			if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
				i++;
			}
		}
		else {
			i++;
		}
		if (this.polozenieX < szerokosc - 1) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
				i++;
			}
		}
		else {
			i++;
		}
		if (this.polozenieY != wysokosc - 1) {
			if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
				i++;
			}
		}
		else {
			i++;
		}
		if (this.polozenieX != 0) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
				i++;
			}
		}
		else {
			i++;
		}
		if (i == 4) {
			return false;
		}
		else {
			return true;
		}
	}
	public int getWiek() {
		return this.wiek;
	}
	public int getSila() {
		return this.sila;
	}
	public int getInicjatywa() {
		return this.inicjatywa;
	}
	public int getPolozenieX() {
		return this.polozenieX;
	}
	public int getPolozenieY() {
		return this.polozenieY;
	}
	public boolean getCzyZyje() {
		return this.czyZyje;
	}
	public void setCzyZyje(boolean czyZyje) {
		this.czyZyje = czyZyje;
	}
	public void setSila(int sila) {
		this.sila += sila;
	}
	public void setWiek(int wiek) {
		this.wiek += wiek;
	}
	public char getSymbol() {
		return this.symbol;
	}
	public Swiat getSwiat() {
		return this.swiat_t;
	}
	public Organizm(Swiat swiat, int sila, int inicjatywa, int wiek, char symbol, int x, int y, boolean czyZyje) {
		this.czyZyje = czyZyje;
		this.polozenieY = y;
		this.polozenieX = x;
		this.symbol = symbol;
		this.inicjatywa = inicjatywa;
		this.sila = sila;
		this.wiek = wiek;
		this.swiat_t = swiat;
	};
}
