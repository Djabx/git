import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.ietf.jgss.GSSContext;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.GpsDescriptor;
import com.drew.metadata.exif.GpsDirectory;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import com.google.googlenav.GoogleNav;
import com.google.gwt.maps.client.*;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.maps.client.MapOptions;
import com.google.googlenav.*;
import com.google.gwt.maps.client.base.*;
import com.google.gwt.maps.client.services.Geocoder;
import com.google.gwt.maps.client.services.GeocoderResult;

import gov.nasa.worldwind.AbstractSceneController;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.OrderedRenderable;
public class Fenetre extends JFrame implements ActionListener, KeyListener  {
	
	
    
	private int FPS_min =0;
	private int FPS_max =100;
	private int FPS_init =50;
	private Image image;
	public static JXMapViewer m = new JXMapViewer();
	private  MenuBar mb = new MenuBar();
	private Menu fic = new Menu("Fichier");
	private Menu aff = new Menu("Affichage");
	private Menu help = new Menu("Aide");

	private MenuItem impor = new MenuItem ("Importer");
	private MenuItem rech = new MenuItem ("Rechercher");
	private MenuItem quit = new MenuItem ("Quitter");
	private MenuItem apro = new MenuItem ("A propos de ... ");

	private CheckboxMenuItem z1 = new CheckboxMenuItem("Zoom");
	private CheckboxMenuItem p1 = new CheckboxMenuItem("Photo");
	private JOptionPane info = new JOptionPane();

	private double lati = 45.1005750;
	private double longee = 3.0778010;
	private int zoooom = 13;

	private JLabel monJLabel;
	
	public FileNameExtensionFilter filtre = new FileNameExtensionFilter( "jpg", "png", "jpeg");
	private JFileChooser gf; 
	private 	File file1; 


	private NumberFormat _format = NumberFormat.getInstance(Locale.US);
	private Number number = null;
	public double lat;
	public double longe;
	private WorldWindow wwd;



	
	public Fenetre() throws ImageProcessingException, IOException{
		
		
        
        
		this.setTitle("Carte des bars de france");
		JPanel pan = new JPanel();     
		pan.setLayout(null);
		addKeyListener(this);
		this.add(pan);
		
		
        
        
		//this.setContentPane(pan);

		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		m.setTileFactory(tileFactory);
		GeoPosition millau = new GeoPosition(lati,longee);
		m.setZoom(zoooom);
		m.setAddressLocation(millau);
		m.convertGeoPositionToPoint(millau);
		m.setSize(20, 60);
		m.setVisible(true);
		this.add(m);
		//pan.add(m);
		
		m.setLayout(null);
	
        
		JSlider zooom = new JSlider (JSlider.VERTICAL,FPS_min,FPS_max,FPS_init);
		pan.add(zooom);
			zooom.setMajorTickSpacing(10);
			zooom.setMinorTickSpacing(1);
			zooom.setBounds(0, 10, 50, 200);
		
			//zooom.setPaintLabels(true);
			//zooom.setPaintTicks(true);
			zooom.setBackground(Color.lightGray);
			zooom.addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent arg0) {
					System.out.println(zooom.getValue());
				}
			});

		/*Menu*/
			
			
		setMenuBar(mb);
		mb.add(fic);
			fic.add(impor);
				impor.addActionListener(this);
			fic.add(rech);
				rech.addActionListener(this);
			fic.add(quit);
				quit.addActionListener(this);

		mb.add(aff);
			aff.add(z1);
				z1.setState(true); //case déja coché
				z1.addItemListener(new ItemListener(){
				
					@Override
				public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(z1.getState());
				if (z1.getState()==false){
					zooom.setVisible(false);
				}
				else{
					zooom.setVisible(true);
				}
			}
		});

		aff.add(p1);

		mb.add(help);
			help.add(apro);
				apro.addActionListener(this);

		Toolkit outil = getToolkit();
		this.setSize(outil.getScreenSize()); // ouvrir en pleins ecran en fcontion de l'ordi mais pouvoir modifier taille
		this.setLocationRelativeTo(null); //centrer la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void paintComponent(Graphics comp) {
		Image img = Toolkit.getDefaultToolkit().getImage("imggg.jpg");
		comp.drawImage(img, 100, 100,10,10, null);
		
		
	}


	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand()=="Rechercher"){
			Recherche re = new Recherche();
		}

		if (e.getActionCommand()=="Quitter"){
			System.exit(0);
		}

		if (e.getActionCommand()=="Importer"){
			gf = new JFileChooser();
			gf.setFileFilter(filtre);
			int valeur = gf.showOpenDialog(this);
			if (valeur == JFileChooser.APPROVE_OPTION){
				file1 = new File(gf.getSelectedFile().getAbsolutePath());
				Metadata r;

				try {
					r = ImageMetadataReader.readMetadata(file1);
					for (Directory directory : r.getDirectories()) {
						for (Tag prop : directory.getTags()) {
							System.out.println(prop);
						}
					}
					Collection<GpsDirectory> gpsDirectories = (Collection) r.getDirectoriesOfType(GpsDirectory.class);
					for (GpsDirectory gpsDirectory : gpsDirectories) {

						GeoLocation geoLocation = gpsDirectory.getGeoLocation();
						GpsDescriptor gpsDesc = new GpsDescriptor(gpsDirectory);

						try {
							number = _format.parse(gpsDesc.getGpsLatitudeDescription());
							lat = Double.parseDouble(number.toString());
							number = _format.parse(gpsDesc.getGpsLongitudeDescription());
							longe = Double.parseDouble(number.toString());
						} catch (ParseException e58) {
						}

						/*System.out.println("Latitude: " + gpsDesc.getGpsLatitudeDescription());
						System.out.println("Longitude : " + gpsDesc.getGpsLongitudeDescription());
						System.out.println("Date : " + gpsDesc.getGpsTimeStampDescription());
						System.out.println("Minute  : " + gpsDesc.getDegreesMinutesSecondsDescription());*/
						
						System.out.println("Latitude:"+ lat+" "+"Longitude:"+longe );
						JLabel monJLabel = new JLabel(); //on crée une image a partir du chemin de l'image importé de taille 80 dans un jpanel qu'on ajoute a la map
						ImageIcon icon = new ImageIcon(new ImageIcon(gf.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				        
						monJLabel.setIcon(icon);
				        GeoPosition posi = new GeoPosition(lat,longe);				        
				        monJLabel.setLocation((int)m.convertGeoPositionToPoint(posi).getX(), (int)m.convertGeoPositionToPoint(posi).getY());
				        
				        
						monJLabel.setSize(80,80);
						m.add(monJLabel);
						

					}
				}
				catch (ImageProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}


		if(e.getActionCommand()=="A propos de ... "){
			info.showMessageDialog(null, "Jérémy Da Silva,Patrice Rico,"
					+ "Cyril Laffite, Nicolas Brugie \n \n Mise en place d'un référencement d'image GéoLocalisé",
					"A propos du projet",
					JOptionPane.INFORMATION_MESSAGE);	
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getKeyCode() == arg1.VK_UP){
			lati=lati+(zoooom*(0.03));
		}
		if(arg1.getKeyCode() == arg1.VK_DOWN){
			lati=lati-(zoooom*(0.03));
		}
		if(arg1.getKeyCode() == arg1.VK_LEFT){
			longe=longe-(zoooom*(0.03));	
		}
		if(arg1.getKeyCode() == arg1.VK_RIGHT){
			longe=longe+(zoooom*(0.03));
		}
		if (arg1.getKeyCode()==107 & zoooom>0 ){
			zoooom=zoooom-1;	
		}
		if (arg1.getKeyCode()==109 & zoooom<15 ){
			zoooom=zoooom+1;
		}

		GeoPosition millauu = new GeoPosition(lati,longe);
		m.setAddressLocation(millauu);
		m.convertGeoPositionToPoint(millauu);
		m.setZoom(zoooom);
	}

	@Override
	public void keyReleased(KeyEvent arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg1) {
		// TODO Auto-generated method stub
	}

}