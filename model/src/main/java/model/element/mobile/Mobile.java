	package model.element.mobile;

import java.awt.image.BufferedImage;

import contract.Direction;
import contract.Permeability;
import model.element.Element;


public abstract class Mobile extends Element implements IMobile{
	
	protected Direction direction;
	
	public Mobile(int posX, int posY, Permeability permeability, BufferedImage sprite, Direction direction, int ID) {
		super(posX, posY, permeability, sprite, ID);
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void move() {
		switch (direction) {
		case UP:
			posY ++;
			break;
		case DOWN:
			posY --;
			break;
		case LEFT:
			posX --;
			break;
		case RIGHT:
			posX ++;
			break;
		default:
			break;
		}
	}


}
