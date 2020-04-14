/**
 *
 */

 
// Guy Cohen - 205579808, Yarin Abraham - 208401166

package utilities;

import java.util.ArrayList;
import java.util.Random;

import components.Junction;
import components.Map;
import components.Road;
import components.VehicleType;
import game.Driving;
import components.Route;

/**
 * @author krsof
 *
 */
public class Program {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Map map=new Map(5);
		map.addJunction(new Junction("Test", new Point(5,10)));
		System.out.println(map.getJunctions());
		map.removeJunction(map.getJunctions().get(5));
		System.out.println(map.getJunctions());
		
		Driving d=new Driving(5,4,20);
		System.out.println(d.getVehicles());
		d.addVehicles();
		System.out.println(d.getVehicles());
		System.out.println();
		
		for(int i=0;i<d.getVehicles().size();i++) {
			d.getVehicles().get(i).move();
		}
	}
}
