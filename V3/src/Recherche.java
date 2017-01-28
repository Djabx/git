import javax.swing.JOptionPane;


public class Recherche  {	
	public Recherche(){
		JOptionPane reche = new JOptionPane();
		String re = reche.showInputDialog(null, "Veuillez entrer une ville de votre choix", "Recherche", JOptionPane.QUESTION_MESSAGE);
	}
}
