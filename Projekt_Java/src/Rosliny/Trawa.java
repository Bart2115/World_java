package Rosliny;
import Projekt.Roslina;
import Projekt.Swiat;

public class Trawa extends Roslina {
	@Override
	public Trawa Potomstwo(Swiat swiat_t, int x, int y) {
		return new Trawa(swiat_t, x, y);
	}
	public Trawa(Swiat swiat, int x, int y){
		super(swiat, 0, 0, 0, 'T', x, y, true, 0.3);
	}
}
