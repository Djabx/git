import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.GpsDescriptor;
import com.drew.metadata.exif.GpsDirectory;


public class fen1 extends JFrame implements ActionListener, KeyListener{
	private MenuBar mb = new MenuBar();
	private Menu fic = new Menu("Fichier");
	private Menu aff = new Menu("Affichage");
	private Menu help = new Menu("Aide");

	private MenuItem impor = new MenuItem("Importer");
	private MenuItem rech = new MenuItem("Rechercher");
	private MenuItem quit = new MenuItem("Quitter");
	private MenuItem apro = new MenuItem("A propos de ... ");
	private JOptionPane info = new JOptionPane();

	private JFileChooser gf;

	private File file1; 
	public double lat;
	public double longi;

	
	public fen1() throws ImageProcessingException, IOException {

		this.setTitle("Carte des bars de france"); // titre de la fenetre
		Toolkit outil = getToolkit();
		this.setSize(outil.getScreenSize()); //mettre la fenetre au format de l'ecran a l'ouverture
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    this.setLocationRelativeTo(null);
		
	    JPanel pan = new JPanel();
		pan.setLayout(null);
		addKeyListener(this);

		setMenuBar(mb);
		mb.add(fic);
		fic.add(impor);
		impor.addActionListener(this);
		fic.add(rech);
		rech.addActionListener(this);
		fic.add(quit);
		quit.addActionListener(this);
		
		mb.add(help);
		help.add(apro);
		apro.addActionListener(this);
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand() == "A propos de ... ") {
			info.showMessageDialog(null,
					"Jérémy Da Silva,Patrice Rico,"
							+ "Cyril Laffite, Nicolas Brugie \n \n Mise en place d'un référencement d'image GéoLocalisé",
					"A propos du projet", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (arg0.getActionCommand() == "Quitter") {
			System.exit(0);
		}
		if (arg0.getActionCommand() == "Importer") { //quand on clique sur le bouton importation
			gf = new JFileChooser();                 // creation nouvelle boite de dialogue 
			int valeur = gf.showOpenDialog(this);    

			if (valeur == JFileChooser.APPROVE_OPTION) {
				file1 = new File(gf.getSelectedFile().getAbsolutePath());
				Metadata r; //creation d'un metadata
				try {
					r = ImageMetadataReader.readMetadata(file1); //extraction des metadata a partir de files 1
					for (Directory directory : r.getDirectories()) {
						for (Tag prop : directory.getTags()) {
							System.out.println(prop);
						}
					}
					Collection<GpsDirectory> gpsDirectories = (Collection) r.getDirectoriesOfType(GpsDirectory.class);
					for (GpsDirectory gpsDirectory : gpsDirectories) {

						GeoLocation geoLocation = gpsDirectory.getGeoLocation();
						GpsDescriptor gpsDesc = new GpsDescriptor(gpsDirectory);

						lat = geoLocation.getLatitude(); //lattitude de files1
						longi = geoLocation.getLongitude(); //longitude de files1
						
				        GeoPosition ge = new GeoPosition(lat,longi); //création d'une geoposition
				        System.out.println(ge);
				        WayPoints.waypoints.add(new SwingWaypoint(file1.getAbsolutePath(),ge));
				        //ajout d'un point a la liste waypoints
				        //la liste  waypoints contient des swingwaypoints
				        //les swingwaypoints contiennt chacun un texte (ici le chemin absolue de l'image) et une geoposition
				        
				        System.out.println(WayPoints.waypoints);
						

				        System.out.println();
					}
					
				} catch (ImageProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}
		}
}
