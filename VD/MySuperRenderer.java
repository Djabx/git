import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypointRenderer;
import org.jxmapviewer.viewer.WaypointRenderer;

public class MySuperRenderer implements WaypointRenderer<SwingWaypoint> {
	private Image img; // création d'une image "vide"

	public void paintWaypoint(Graphics2D g, JXMapViewer map, SwingWaypoint waypoint) {
		// TODO Auto-generated method stub
		try {
			// ImageIO.read(DefaultWaypointRenderer.class.getResource(waypoint.text))
			img = ImageIO.read(DefaultWaypointRenderer.class.getResource(waypoint.texte));
			// remplissage de l'image vide img par l'image iimporter dans fen1
			// recuperation du chemin absolue via waypoint.texte qui est = au
			// chemin absolue de l'image importé
		} catch (Exception e) {
			System.out.println("couldn't read standard_waypoint.png" + " " + e);
		}

		Point2D point = map.getTileFactory().geoToPixel(waypoint.geo, map.getZoom());

		int x = (int) point.getX() - img.getWidth(map) / 2;
		int y = (int) point.getY() - img.getHeight(map);
		// conversion de la lattitude et longitude en point (pour mettre la
		// photo sur l'ecran)

		g.drawImage(img, x, y, null); // on dessine l'image qui a pour
										// coordonnée les points x et y qui
										// derive de
		// la geolocalisation de l'image importé : waypoint.geo

		return;
	}

}
