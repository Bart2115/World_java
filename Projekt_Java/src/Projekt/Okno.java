package Projekt;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Okno extends JFrame{
	public Okno() {
		this.setTitle("180275 Projekt2");
		this.setSize(975,900);
		this.setResizable(false);
		JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, lsuper, lautor;
		l1 = new JLabel(" \"A\" - Antylopa");
		l1.setBounds(60,645,200,50);	
		l2 = new JLabel(" \"B\" - Barszcz Sosnowskiego");
		l2.setBounds(260,645,200,50);
		l3 = new JLabel(" \"$\" - Cz³owiek");
		l3.setBounds(60,670,200,50);
		l4 = new JLabel(" \"G\" - Guarana");
		l4.setBounds(260,670,200,50);
		l5 = new JLabel(" \"L\" - Lis");
		l5.setBounds(60,695,200,50);
		l6 = new JLabel(" \"M\" - Mlecz");
		l6.setBounds(260,695,200,50);
		l7 = new JLabel(" \"O\" - Owca");
		l7.setBounds(60,720,200,50);
		l8 = new JLabel(" \"T\" - Trawa");
		l8.setBounds(260,720,200,50);
		l9 = new JLabel(" \"J\" - Wilcze Jagody");
		l9.setBounds(260,745,200,50);
		l10 = new JLabel(" \"W\" - Wilk");
		l10.setBounds(60,745,200,50);
		l11 = new JLabel(" \"Z\" - ¯ó³w");
		l11.setBounds(60,770,200,50);
		l12 = new JLabel(" \"C\" - Cyber Owca");
		l12.setBounds(60,795,200,50);
		lsuper = new JLabel("Super umiejêtnoœæ: ");
		lsuper.setBounds(500,645,200,50);
		lautor = new JLabel("Bart³omiej Buklewski 180275");
		lautor.setBounds(750,800,200,50);
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(l5);
		this.add(l6);
		this.add(l7);
		this.add(l8);
		this.add(l9);
		this.add(l10);
		this.add(l11);
		this.add(l12);
		this.add(lsuper);
		this.add(lautor);
	}
}
