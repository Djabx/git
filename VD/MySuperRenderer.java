import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypointRenderer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointRenderer;


public class MySuperRenderer implements WaypointRenderer<SwingWaypoint> {
	private Image img ;

	public void paintWaypoint(Graphics2D g, JXMapViewer map, SwingWaypoint waypoint) {
		// TODO Auto-generated method stub
		try {
			//ImageIO.read(DefaultWaypointRenderer.class.getResource(waypoint.text))
			img = ImageIO.read(DefaultWaypointRenderer.class.getResource(waypoint.texte));
		} catch (Exception e) {
			System.out.println("couldn't read standard_waypoint.png"+ " " + e);
		}

		/*Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
		
		int x = (int)point.getX() -img.getWidth(map) / 2;
		int y = (int)point.getY() -img.getHeight(map);*/
		
		g.drawImage(img, 0,0,null);
		
		 return;
	}
	

}
