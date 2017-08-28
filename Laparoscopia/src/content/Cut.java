package content;

import java.util.Vector;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * @author Ettore Gorni
 *
 */
public class Cut {

	private static final double DISTANCE = 3; 
	private Vector<Point3D> points = new Vector<>();
	private TriangleMesh mesh = new TriangleMesh();
	
	public Cut() {
		mesh.getTexCoords().addAll(0,0);
	}
	
	/**
	 * @param point to add to the mesh
	 * NB fare attenzione che il punto abbia lo stesso sistemma di riferimento sia per lo spazio che per il taglio
	 * NB il controllo della distanza tra i punti è delegato al chiamante
	 */
	public void addPoint(Point3D point) {

		points.add(point);
		mesh.getPoints().addAll((float)point.getX(), (float)point.getY(), (float)point.getZ());
		if(points.size() > 2) {
			mesh.getFaces().addAll(points.size() -1,0, points.size() -2,0, points.size() -3,0);
		}
		
	}
	
	/**
	 * @param point to add to the mesh
	 * NB fare attenzione che il punto abbia lo stesso sistemma di riferimento sia per lo spazio che per il taglio
	 * NB il controllo della distanza tra i punti è delegato al chiamante
	 */
	public void addPoint(double x, double y, double z) {

		points.add(new Point3D(x,y,z));
		mesh.getPoints().addAll((float)x, (float)y, (float)z);
		if(points.size() > 2) {
			mesh.getFaces().addAll(points.size() -1,0, points.size() -2,0, points.size() -3,0);
		}
		
	}
	
	/**
	 * @return meshView of the cut
	 */
	public MeshView getCut() {
		
        final PhongMaterial blackMaterial = new PhongMaterial();
        blackMaterial.setDiffuseColor(Color.BLACK);
        blackMaterial.setSpecularColor(Color.BLACK);
		
		MeshView cut = new MeshView(mesh);
		cut.setDrawMode(DrawMode.FILL);
		cut.setMaterial(blackMaterial);
		cut.setCullFace(CullFace.NONE);
		
		return cut;
	}
	
	/**
	 * @param point that we want to know if it is close to a mesh point 
	 * we can use this function if we don't want to have too many points close each other
	 * @return true if it is close to one of that point
	 */
	public boolean closeTo(Point3D point) {
		for(int i=0; i<points.size(); i++) {
			if(point.distance(points.get(i)) < DISTANCE)
				return true;
		}
		return false;	
	}
	
}
	
