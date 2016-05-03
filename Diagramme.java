/*
 *	Date de Creative : 12/12/2015
 *  Creative by LI Rui
 *  All the annotations are written by French, when I have time, I'll change all of them into English
 *  It is a test of the class
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Diagramme	extends WindowAdapter implements ActionListener{
	private		JFrame		frame		=	new		JFrame  ("Error");
	private		JFrame		fcamembert	=	new		JFrame	("Diagramme de Camembert");
	private		JFrame		fpilier		=	new		JFrame	("Diagramme de Pilier");
	private		JPanel		panel		=	new		JPanel	();
	private		JLabel		label		=	new		JLabel	();
	private		JButton		button		=	new		JButton ("Valider");
	private 	Control		ctrl		=	new		Control ();
	static		Diagramme	dig			=	new		Diagramme ();
	static		int	[]		p			=	new		int[5];	
	/**
	 *  override method of superclass WindowAdapter
	 */
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	/**
	 *  override the void method of the superinterface ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String	s	=	e.getActionCommand();
		// afficher le diagramme de Camembert
		if (s.equals("Camembert")) {
			boolean		b	=	false;
			b = dig     .	obtenuParametres ();
			if (b == true) {
				this	.	faireDiagramme(1);
			}
		} 
		// afficher le diagramme de pilier
		if (s.equals("Pilier")) {
			boolean		b	=	false;
			b = dig     .	obtenuParametres ();
			if (b == true) {
				this	.	faireDiagramme(0);
			}
		}
		// tous sont vide
		if (s.equals("Annuler")) {
			ctrl.setchi.setText(null);
			ctrl.setfra.setText(null);
			ctrl.setjap.setText(null);
			ctrl.setesp.setText(null);
			ctrl.setaut.setText(null);
		}
		// valider l'erreur
		if (s.equals("Valider")) {
			frame . setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(false);
		}
	}
	/**
	 * faire le diagramme 
	 * */
	void faireDiagramme (int t) {
		// si t = 1, if peut faire le diagramme de Camembert
		if (t == 1) {
			this . fcamembert . setSize  (550, 550);
			this . fcamembert . add			("Center", new Camembert());
			this . fcamembert . setResizable	(false);
			this . fcamembert . setVisible		(true);
		}
		// si t = 0, if peut faire le diagramme de pilier
		if (t == 0) {
			this . fpilier . setSize  (550, 550);
			this . fpilier . add			("Center", new Pilier());
			this . fpilier . setResizable	(false);
			this . fpilier . setVisible		(true);
		}
	}
	/**
	 * pour dire l'erreur
	 * */
	void erreur (String s) {
		frame	.	add(panel);
		panel	.	add("Center", label);
		panel	.	add("South"	, button);
		label	.	setText(s);
		frame   .	setSize(260, 100);
		frame	.	setResizable(false);
		frame	.	setVisible(true);
	}
	/**
	 * pour obtenir les parametres de TextField
	 * */
	boolean obtenuParametres () {
		if (ctrl.setchi.getText().equals("") || ctrl.setfra.getText().equals("") || ctrl.setjap.getText().equals("") || ctrl.setesp.getText().equals("") || ctrl.setaut.getText().equals("")) {
			this . erreur("Saisir le nombre, si'l vous plait.");
			return false;
		}else {
			p[0]	=	new Integer(ctrl.setchi.getText());
			p[1]	=	new Integer(ctrl.setfra.getText());
			p[2]	=	new	Integer(ctrl.setjap.getText());
			p[3]	=	new Integer(ctrl.setesp.getText());
			p[4]	=	new Integer(ctrl.setaut.getText());
			return true;
		}
	}
	/**
	 * 
	 * */
	void run () {
		this .  button  	. addActionListener(this);
		ctrl .  		  	  addWindowListener(this);
		ctrl .  camembert 	. addActionListener(this);
		ctrl .  pilier 		. addActionListener(this);
		ctrl .  annuler 	. addActionListener(this);
		ctrl .  faireGui();	
	}
 	/**
	 * main fonction
	 * */
	public static void main (String [] args) {
		dig	.	run();
	}
}
//
//	Fin de class Diagramme
//
/**
 * il a hérité la classe JPanel pour peindre le diagramme de pilier
 * */
class Pilier extends JPanel	{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * parametres 
	 * */
	private		Color []	    col		=	{Color.red, Color.blue, Color.green, Color.gray, Color.orange};
	private		String[]		pay		=	{"chinois", "francais", "japonais", "espagnol", "autres"};
	private		int[]			x		=	{120, 200, 280, 360, 440};	//	initial x point
	private		double			s		=	0;	
	private		double[]		pro		=	new		double[5]; // proportion
	private		double			max		=	999999.99;
	private		int				num		=	0;
	private		int[]			hei		=	new		int[5];	
	/**
	 * faire le pilier
	 * */
	public void paint (Graphics g)  {
		g.drawLine(80, 400, 525, 400);
		g.drawLine(80, 400, 80, 80);
		for (int i=0;i<5;i++) {
			s	=	s + Diagramme.p[i];
		}
		for (int i=0;i<5;i++) {
			pro[i]	=	Diagramme.p[i] / s;
		}
		max	=	pro[0];
		for (int i=1;i<5;i++) {
			if (pro[i] > max) {
				max		=	pro[i];
				num		=	i;
			}
		}
		hei[num]	=	300;
		for (int i=0;i<5;i++) {
			hei[i]	=	(int)(pro[i]/max * 300.0);
		}
		for (int i=0;i<5;i++) {  
			g.setColor(col[i]);
			g.fillRect(x[i], 400-hei[i], 40, hei[i]);
			g.setColor(Color.black);
			g.drawString(pay[i], x[i], 420 );
			g.drawString("  "+Diagramme.p[i], x[i], 440 );
		}
		s	=	0;
	}
}
//
// Fin de class Pilier
//
/**
 * il a hérité la classe JPanel pour peindre le diagramme de Camembert
 * */
class Camembert extends JPanel {
	/**
	 * 
	 */
	private 	static 			final long serialVersionUID = 1L;
	/**
	 * parametres 
	 * */
	private		double			s 		= 	0;
	private		double[]		anger	=	new		double[5];
	private		int[]			intan	=	new		int[5];
	private		Color []	    col		=	{Color.red, Color.blue, Color.green, Color.gray, Color.orange};
	private		String[]		pay		=	{"chinois", "francais", "japonais", "espagnol", "autres"};
	private		int[]			varian	=	new		int[5];
	private		int[]			trian	=	new		int[5];
	/**
	 * faire le Camembert
	 * */
	public void paint (Graphics g) {
		// sum de p[]
		for (int i=0;i<5;i++) {
			s	=	s	+	Diagramme.p[i];
		}
		// proportion
		for (int i=0;i<5;i++) {
			anger[i]	=	(double) (Diagramme.p[i] * 360.0 / s);
			intan[i]	=	Diagramme.p[i] * 360 / (int)s;
			double	x = (anger[i]-intan[i]);
			if ( x >= 0.5) {
				intan[i] = intan[i] + 1;
			}
		}
		// varian[] pour calculer le position d'intial de chaque secteur
		varian[0]	=	0;
		for (int i=1;i<5;i++) {
			varian[i] =	intan[i-1] + varian[i-1];
		}
		//	trian[] pour calculer le position d'ecriture
		trian[0] =	intan[0] / 2;
		for (int i=1;i<5;i++) {
			trian[i]	=	varian[i] + intan[i] / 2;
		}
		for (int i=0;i<5;i++) {
			g.setColor(col[i]);
			g.fillArc(75, 75, 400, 400, varian[i], intan[i]);
			g.setColor(Color.black);
			double a =  Math.cos(trian[i]*Math.PI/180);
			double b =  Math.sin(trian[i]*Math.PI/180);
			g.drawString (pay[i], 275 + (int)(a * 220), (int)(275 - b * 220));
			g.drawString ("  "+Diagramme.p[i], 275 + (int)(a * 220), (int)(295 - b * 220));
		}
		s	=	0;
	}
}
// 
// Fin de class Camembert
//
 class Control extends JFrame  implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************************** parametres de class *******************************/
	private		JPanel		panel0		=	new		JPanel		();
	private		JPanel		panel1		=	new 	JPanel		();
	private		JPanel		panel2		=	new		JPanel		();
	private		JLabel		title		=	new		JLabel		("Logiciel de diagramme");
	private		JLabel		chinois		=	new		JLabel		("Saisir le nombre de chinois");
	public      JTextField	setchi		=	new		JTextField	();
	private		JLabel		francais	=	new		JLabel		("Saisir le nombre de francais");
	public 		JTextField	setfra		=	new		JTextField	();
	private		JLabel		japonais	=	new		JLabel		("Saisir le nombre de japonais");
	public		JTextField	setjap		=	new		JTextField	();
	private		JLabel		espangol	=	new		JLabel		("Saisir le nombre de espangol");
	public		JTextField	setesp		=	new		JTextField	();
	private		JLabel		autrepays 	=	new		JLabel		("Saisir le nombre des autres");
	public		JTextField	setaut		=	new		JTextField	();
	public		JButton		camembert		=	new		JButton 	("Camembert");
	public		JButton		pilier		=	new		JButton		("Pilier");
	public		JButton		annuler		=	new		JButton 	("Annuler");

	/**
	 * faire le graphique
	 * */
	public void faireGui () {
		// nom de frame
		this	.setTitle("Debut de Programmation");
		// JTextField
		setchi.addKeyListener(this);
		setfra.addKeyListener(this);
		setjap.addKeyListener(this);
		setesp.addKeyListener(this);
		setaut.addKeyListener(this);
		// panel0
		panel0	.add("South",title);
		// panel1
		GridBagLayout		gbl		=	new		GridBagLayout 		();	
		GridBagConstraints 	gbc 	= 	new   	GridBagConstraints	();
		panel1	.setLayout(gbl);
		panel1	.setSize(260, 280);
		/**
		 * 
		 */
		gbc.gridx		=	0;
		gbc.gridy		=	0;
		gbc.ipadx		=	60;
		panel1	.add(chinois, 	gbc);
		gbc.gridx		=	1;
		gbc.gridy		=	0;
		gbc.ipadx		=	40;
		panel1	.add(setchi, 	gbc);
		gbc.gridx		=	0;
		gbc.gridy		=	1;
		gbc.ipadx		=	60;
		panel1	.add(francais, 	gbc);
		gbc.gridx		=	1;
		gbc.gridy		=	1;
		gbc.ipadx		=	40;
		panel1	.add(setfra, 	gbc);
		gbc.gridx		=	0;
		gbc.gridy		=	2;
		gbc.ipadx		=	60;
		panel1	.add(japonais, 	gbc);
		gbc.gridx		=	1;
		gbc.gridy		=	2;
		gbc.ipadx		=	40;
		panel1	.add(setjap, 	gbc);
		gbc.gridx		=	0;
		gbc.gridy		=	3;
		gbc.ipadx		=	60;
		panel1	.add(espangol, 	gbc);
		gbc.gridx		=	1;
		gbc.gridy		=	3;
		gbc.ipadx		=	40;
		panel1	.add(setesp, 	gbc);
		gbc.gridx		=	0;
		gbc.gridy		=	4;
		gbc.ipadx		=	60;
		panel1	.add(autrepays, gbc);
		gbc.gridx		=	1;
		gbc.gridy		=	4;
		gbc.ipadx		=	40;
		panel1	.add(setaut, 	gbc); 
		// panel2
		panel2	.setSize  (260, 100);	
		panel2	.add(camembert);
		panel2	.add(pilier);
		panel2	.add(annuler);
		// frame
		this	.setSize(320, 350);
		this	.setLayout(new BorderLayout());
		this	.add	 ("North" , panel0);
		this	.add 	 ("Center", panel1);
		this	.add 	 ("South" , panel2);
		this	.setResizable(false);
		this	.setVisible(true);
	}
	/**
	 * vous pouvez seulement saisir le nombre 
	 * */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int		keyChar		=		e.getKeyChar();				
		if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
			
		}else{
			e.consume(); 
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}   
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}
//
// Fin de class
//