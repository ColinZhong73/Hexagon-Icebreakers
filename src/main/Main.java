package main;

import java.awt.Color;

import entities.Hexagon;
import entities.Player;

public class Main {
	public static Display dis;
	public static Hexagon[][] board;
	public static Player[] players;
	
	public static void main(String[] args){
		initBoard();
		createPlayers();
		dis = new Display();
		run();
	}
	
	private static void createPlayers(){
		players = new Player[Constants.playerNum];
		for(int i = 0; i < Constants.playerNum; i++){
			players[i] = new Player(i,i);
			Main.board[players[i].getX()][players[i].getY()].setHasPlayer(true);
		}
		for(int i = 0; i < players.length; i++){
			board[players[i].getX()][players[i].getY()].addHealth();
			if(players[i].getX()!=Constants.gridWidth){
				board[players[i].getX()+1][players[i].getY()].addHealth();
			}
			if(players[i].getX()!=0){
				board[players[i].getX()-1][players[i].getY()].addHealth();
			}
			if(players[i].getY()!=Constants.gridHeight){
				board[players[i].getX()][players[i].getY()+1].addHealth();
			}
			if(players[i].getY()!=0){
				board[players[i].getX()][players[i].getY()-1].addHealth();
			}
			if(players[i].getX()%2==0){
				try{
				board[players[i].getX()+1][players[i].getY()+1].addHealth();
				board[players[i].getX()-1][players[i].getY()+1].addHealth();
				}
				catch(IndexOutOfBoundsException e){}
			}
			else if(players[i].getX()%2==1){
				try{
				board[players[i].getX()-1][players[i].getY()-1].addHealth();
				board[players[i].getX()+1][players[i].getY()-1].addHealth();
				}
				catch(IndexOutOfBoundsException e){}
			}
		}
	}
	
	private static void initBoard(){
		int size = Constants.entitySize;
		board = new Hexagon[Constants.gridWidth][Constants.gridHeight];
		for(int i=0;i<Constants.gridWidth;i++){
			for(int j=0;j<Constants.gridHeight;j++){
				if(i%2==1){
					board[i][j] = new Hexagon(25+(i*size*3/2),(int)(size*Constants.sqrt3)*j);
				}
				else{
					board[i][j] = new Hexagon(25+(i*size*3/2),(int)(size*Constants.sqrt3)*j+43);
				}
				if(i==0||j==0||i==Constants.gridWidth-1||j==Constants.gridHeight-1){
					board[i][j].addHealth();
				}
			}
		}
		
	}
	
	private static void run(){
		long timer = System.currentTimeMillis();
		int pn = Constants.playerNum;
		while(true){
			if(System.currentTimeMillis()-timer>=(1000/60)){
				dis.getFrame().repaint();
				timer = System.currentTimeMillis();
			}
			for(int i = 0; i < pn; i++){
				if(timer-players[i].getTime()>=2000&&!players[i].isDead()){
					if(board[players[i].getX()][players[i].getY()].getC()!=Color.BLACK){
						board[players[i].getX()][players[i].getY()].minusHealth();
					}
					if(board[players[i].getX()][players[i].getY()].getC()==Color.BLACK){
						players[i].killed();
					}
					players[i].setTime(System.currentTimeMillis());
				}
			}
		}
	}
}
