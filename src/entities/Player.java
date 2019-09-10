package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.Constants;
import main.Main;

public class Player {
	
	private int x;
	private int y;
	private boolean dead = false;
	private long time = System.currentTimeMillis();
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g){
		if(dead){
			return;
		}
		double sqrt3 = Constants.sqrt3;
		int size = Constants.entitySize;
		g.setColor(Color.BLUE);
		if(x%2==1){
			g.fillOval(x*75+10, (int)(sqrt3*size)*y, 80, (int)(sqrt3*size));
		}
		else if(x%2==0){
			g.fillOval(x*75+10, (int)(sqrt3*size)*y+(int)(sqrt3*size/2), 80, (int)(sqrt3*size));
		}
	}
	
	public void moveX(int x){
		if(dead){
			return;
		}
		this.x+=x;
		if(this.x<0){
			this.x=0;
		}
		if(this.x>Constants.gridWidth-1){
			this.x=Constants.gridWidth-1;
		}
	}
	
	public void moveY(int y){
		if(dead){
			return;
		}
		this.y+=y;
		if(this.y<0){
			this.y=0;
		}
		if(this.y>Constants.gridHeight-1){
			this.y=Constants.gridHeight-1;
		}
	}
	
	public void move(int num){
		int oldX = x;
		int oldY = y;
		switch(num){
			case 1: moveX(-1); if(getX()%2==0){moveY(-1);}break;
			case 2: moveY(-1);break;
			case 3: moveX(1); if(getX()%2==0){moveY(-1);}break;
			case 4: moveX(-1); if(getX()%2==1){moveY(1);}break;
			case 5: moveY(1);break;
			case 6: moveX(1); if(getX()%2==1){moveY(1);}break;
			default: break;
		}
		if(Main.board[x][y].getC()==Color.BLACK){
			Main.board[oldX][oldY].setHasPlayer(false);
			killed();
		}
		if(!dead&&!Main.board[x][y].isHasPlayer()){
			if(oldX!=x||oldY!=y){
				Main.board[x][y].setHasPlayer(true);
				Main.board[oldX][oldY].setHasPlayer(false);
				if(Main.board[oldX][oldY].getC()!=Color.BLACK){
					Main.board[oldX][oldY].minusHealth();
				}
				time = System.currentTimeMillis();
			}
		}
		else{
			x = oldX;
			y = oldY;
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public void killed(){
		this.x = -1;
		this.y = -1;
		dead = true;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
