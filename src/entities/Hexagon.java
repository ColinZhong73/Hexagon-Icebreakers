package entities;

import java.awt.Color;
import java.awt.Polygon;

import main.Constants;

public class Hexagon extends Polygon{
	private static final long serialVersionUID = 1L;
	private final int sides = 6;
	private final int size = Constants.entitySize;
	private int health;
	private int[] x;
	private int[] y;
	private Color c;
	private boolean hasPlayer;
	
	public Hexagon(int x, int y){
		super();
		this.health = generateHealth();
		this.x = calcXPoints(x);
		this.y = calcYPoints(y);
		createPolygon();
	}
	
	private int[] calcXPoints(int x){
		int[] xPoints = new int[]{x, x+size, x+(size*3/2), x+size, x, x-(size/2)};
		return xPoints;
	}
	
	private int[] calcYPoints(int y){
		double sqrt3 = Constants.sqrt3;
		int[] yPoints = new int[]{y,y,(int)(y+(size*sqrt3/2)),(int)(y+(size*sqrt3)),(int)(y+(size*sqrt3)),(int)(y+(size*sqrt3/2))};
		return yPoints;
	}
	
	private void createPolygon(){
		for(int i=0;i<sides;i++){
			super.addPoint(this.x[i], this.y[i]);
		}
	}
	
	private int generateHealth(){
		int num = (int)(Math.random()*10)+1;
		int health = 0;
		if(num>6){
			health = 3;
		}
		else if(num<=6&&num>3){
			health = 2;
		}
		else if(num<=3&&num>1){
			health = 1;
		}
		else{
			health = 0;
		}
		return health;
	}
	
	public int getX(){
		return x[0];
	}
	
	public int getY(){
		return y[0];
	}
	
	public Color getC(){
		switch(health){
			case 0: c = Color.BLACK; break;
			case 1: c = Color.RED; break;
			case 2: c = Color.YELLOW; break;
			case 3: c = Color.GREEN; break;
			default: health = 3; c = Color.GREEN; break;
		}
		return c;
	}
	
	public void addHealth(){
		health++;
	}
	
	public void minusHealth(){
		health--;
	}

	public boolean isHasPlayer() {
		return hasPlayer;
	}

	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}
}
