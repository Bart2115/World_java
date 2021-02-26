package Projekt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Zwierzeta.Czlowiek;
public class Panel extends JPanel implements ActionListener, KeyListener{
	static JButton bWyjscie = new JButton("Wyjœcie");
	static JButton bTura = new JButton("Nastêpna tura");
	static JButton bZapisz = new JButton("Zapisz");
	static JButton bWczytaj = new JButton("Wczytaj");
	static JButton bSuper = new JButton("Aktywuj super umiejêtnoœæ");
	static Panel okno = new Panel();
	static Swiat swiat_t = new Swiat();
	static Okno jf = new Okno();
	static boolean czyRuch = false;
	static JTextArea komentator = new JTextArea();
	static JTextArea tsuper = new JTextArea();
	
	public Panel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public static void main(String[] args) {
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		jf.add(okno);
		jf.setVisible(true);
		
		komentator.setEditable(false);
		komentator.setBounds(660,31,250,620);
		tsuper.setEditable(false);
		tsuper.setBounds(640,660,300,25);
		okno.add(komentator);	
		okno.add(tsuper);
		
		bWyjscie.setBounds(0,0,150,30);
		bTura.setBounds(150,0,150,30);
		bZapisz.setBounds(300,0,150,30);
		bWczytaj.setBounds(450,0,150,30);
		bSuper.setBounds(600,0,200,30);
		
		okno.add(bWyjscie);
		okno.add(bTura);
		okno.add(bZapisz);
		okno.add(bWczytaj);
		okno.add(bSuper);
		
		bTura.setFocusable(false);
		bZapisz.setFocusable(false);
		bWczytaj.setFocusable(false);
		bSuper.setFocusable(false);
		
		bWyjscie.addActionListener(okno);
		bTura.addActionListener(okno);
		bZapisz.addActionListener(okno);
		bWczytaj.addActionListener(okno);
		bSuper.addActionListener(okno);
		okno.setLayout(null);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if(zrodlo==bWyjscie) {
			System.exit(1);
		}
		else if(zrodlo==bTura) {
			if(swiat_t.organizmy.get(0) instanceof Czlowiek) {
				if(!czyRuch && swiat_t.organizmy.get(0).czyZyje) {
					okno.CzyscKomentarz();
					okno.DodajKomentarz("Cz³owieku, wykonaj ruch za pomoc¹ strza³ek!");
					return;
				}
			}
			swiat_t.WykonajTure();
			okno.CzyscKomentarz();
			for(int i=0;i<swiat_t.komentarze.size();++i) {
				okno.DodajKomentarz(swiat_t.komentarze.get(i));
			}
			if(swiat_t.organizmy.get(0) instanceof Czlowiek) {
				if(swiat_t.organizmy.get(0).czyZyje) {
					okno.DodajSuperKomentarz(swiat_t.komunikat);
					czyRuch = false;	
				}
			}
			else {
				okno.DodajSuperKomentarz("Cz³owiek niestety umar³ :(");
				czyRuch = true;
			}
			swiat_t.komentarze.clear();
			okno.repaint();
		}
		else if(zrodlo==bZapisz) {
			try {
				if(swiat_t.ZapiszGre()) {
					okno.CzyscKomentarz();
					okno.DodajKomentarz("Pomyœlnie zapisano stan gry!");
				}
				else {
					okno.CzyscKomentarz();
					okno.DodajKomentarz("Wyst¹pi³ problem z zapisem!\nAby zapisaæ grê cz³owiek nie mo¿e\n mieæ aktywnej super umiejêtnoœci\n lub posiadaæ na ni¹ cooldownu!\n");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(zrodlo==bWczytaj) {
			try {
				if(swiat_t.WczytajGre() == false) {
					okno.CzyscKomentarz();
					okno.DodajKomentarz("Wyst¹pi³ problem z wczytaniem!\nBrak dostêpu lub brak pliku z zapisem!");
				}
				else {
					okno.CzyscKomentarz();
					okno.DodajKomentarz("Pomyœlnie wczytano stan gry!");
				}
				okno.repaint();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		else if(zrodlo==bSuper) {
			if(swiat_t.organizmy.get(0) instanceof Czlowiek) {
				if(((Czlowiek)(swiat_t.organizmy.get(0))).AktywujSuperUmiejetnosc()) {
					okno.DodajSuperKomentarz("Aktywna przez 5 tur!");
				}
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		for(int i=0; i<20;i++) {
			for(int j=0;j<20;j++) {
				g.setColor(Color.RED);
				g.drawRect((j*31), 30+(i*31), 31, 31);
				if (swiat_t.mapa[i][j] == null) {
					g.setColor(Color.WHITE);
				}
				else {
					if(swiat_t.mapa[i][j].getSymbol() == 'A') {
						g.setColor(Color.RED);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'B') {
						g.setColor(Color.BLUE);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == '$') {
						g.setColor(Color.CYAN);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'G') {
						g.setColor(new Color(189,183,107));
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'L') {
						g.setColor(new Color(138,43,226));
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'M') {
						g.setColor(Color.GREEN);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'O') {
						g.setColor(new Color(127,255,212));
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'T') {
						g.setColor(Color.MAGENTA);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'J') {
						g.setColor(Color.ORANGE);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'W') {
						g.setColor(Color.PINK);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'Z') {
						g.setColor(Color.YELLOW);
					}
					else if(swiat_t.mapa[i][j].getSymbol() == 'C') {
						g.setColor(Color.BLACK);
					}
					
				}
				g.fillRect(1+(j*31), 31+(i*31), 30, 30);
			}
		}
		g.setColor(Color.RED);
		g.fillRect(31, 660, 20, 20);
		g.setColor(Color.BLUE);
		g.fillRect(231, 660, 20, 20);
		g.setColor(Color.CYAN);
		g.fillRect(31, 685, 20, 20);
		g.setColor(new Color(189,183,107));
		g.fillRect(231, 685, 20, 20);
		g.setColor(new Color(138,43,226));
		g.fillRect(31, 710, 20, 20);
		g.setColor(Color.GREEN);
		g.fillRect(231, 710, 20, 20);
		g.setColor(new Color(127,255,212));
		g.fillRect(31, 735, 20, 20);
		g.setColor(Color.MAGENTA);
		g.fillRect(231, 735, 20, 20);
		g.setColor(Color.ORANGE);
		g.fillRect(231, 760, 20, 20);
		g.setColor(Color.PINK);
		g.fillRect(31, 760, 20, 20);
		g.setColor(Color.YELLOW);
		g.fillRect(31, 785, 20, 20);
		g.setColor(Color.BLACK);
		g.fillRect(31, 810, 20, 20);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(swiat_t.organizmy.get(0) instanceof Czlowiek) {
			if(e.getKeyCode()==KeyEvent.VK_UP){
				((Czlowiek)(swiat_t.organizmy.get(0))).setRuch(0);
				czyRuch = true;
			}
			else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				((Czlowiek)(swiat_t.organizmy.get(0))).setRuch(1);
				czyRuch = true;
			}
			else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				((Czlowiek)(swiat_t.organizmy.get(0))).setRuch(2);
				czyRuch = true;
			}
			else if(e.getKeyCode()==KeyEvent.VK_LEFT){
				((Czlowiek)(swiat_t.organizmy.get(0))).setRuch(3);
				czyRuch = true;
			}
			else {
				czyRuch = false;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	public void DodajKomentarz(String kom) {
		komentator.append(kom+"\n");
	}
	public void CzyscKomentarz() {
		komentator.setText("");
	}
	public void DodajSuperKomentarz(String kom) {
		tsuper.setText("");
		tsuper.append(kom+"\n");
	}
}
