package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import contract.Direction;
import contract.IElement;
import contract.IModel;
import contract.Permeability;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public class Model implements IModel {
	public ArrayList <Wall> vWallList;
	public ArrayList <Wall> hWallList;
	public ArrayList <Wall> rWallList;
	public ArrayList <CrystalBall> cBallList;
	public ArrayList <Purse> purseList;
	public ArrayList <Gate> gateCList;
	public ArrayList <Gate> gateOList;
	public ArrayList <Monster> monster1List;
	public ArrayList <Monster> monster2List;
	public ArrayList <Monster> monster3List;
	public ArrayList <Monster> monster4List;
	public ArrayList <Hero> rLorannList;
	public ArrayList <IElement> elements;
	/**
	 * Instantiates a new model.
	 */
	public Model() {
		ImageLoader.loadImage();
	}
	
	public ArrayList<IElement> loadStage(int Stage){
		vWallList = new ArrayList <Wall>();
		hWallList = new ArrayList <Wall>();
		rWallList = new ArrayList <Wall>();
		cBallList = new ArrayList <CrystalBall>();
		purseList = new ArrayList <Purse>();
		gateCList = new ArrayList <Gate>();
		gateOList = new ArrayList <Gate>();
		monster1List = new ArrayList <Monster>();
		monster2List = new ArrayList <Monster>();
		monster3List = new ArrayList <Monster>();
		monster4List = new ArrayList <Monster>();
		rLorannList = new ArrayList <Hero>();
		elements = new ArrayList <IElement>();
		
		int numStage = 2;
		DBConnection instance = DBConnection.getInstance();
		final String sql = "{call seeStage" + numStage + "}";
		CallableStatement call;
		ResultSet rs;
		try {
			call = instance.getConnection().prepareCall(sql);
			call.execute();
			rs = call.getResultSet();
			
		} catch (SQLException e) {
			// TODO Auto-generated catc block
			e.printStackTrace();
			return elements;
		}
		
		try {
			while (rs.next()){
				switch (rs.getInt("id_elements")) {
				case 1:
					// horizontal wall
					hWallList.add(new Wall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.horizontal_bone));
					elements.add(new Wall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.horizontal_bone));
					break;
				case 2:
					// vertical wall
					vWallList.add(new Wall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.vertical_bone));
					elements.add(new Wall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.vertical_bone));

					break;
				case 3:
					// round wall
					rWallList.add(new Wall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.bone));
					elements.add(new Wall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.bone));
					break;
				case 4:
					// round wall
					cBallList.add(new CrystalBall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.crystal_ball));
					elements.add(new CrystalBall(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.crystal_ball));
					break;
				case 5:
					// round wall
					purseList.add(new Purse(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.purse));
					elements.add(new Purse(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.purse));
					break;
				case 6:
					// round wall
					gateCList.add(new Gate(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.gate));
					elements.add(new Gate(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.gate));
					break;
				case 7:
					// round wall
					gateOList.add(new Gate(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.gate));
					elements.add(new Gate(rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.gate));
					break;
				case 8:
					// round wall
					monster1List.add(new Arrbarr(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_1));
					elements.add(new Arrbarr(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_1));
					break;
				case 9:
					// round wall
					monster2List.add(new Cargyv(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_2));
					elements.add(new Cargyv(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_2));
					break;
				case 10:
					// round wall
					monster3List.add(new Kyrac(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_3));
					elements.add(new Kyrac(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_3));
					break;
				case 11:
					// round wall
					monster4List.add(new Maarcg(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_4));
					elements.add(new Maarcg(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.monster_4));
					break;
				case 12:
					Hero hero = new Hero(Direction.UP ,rs.getInt("coord_X"), rs.getInt("coord_Y"), ImageLoader.player);
					rLorannList.add(hero);
					elements.add(hero);
					break;
				default:
					break;
				}

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return elements;
		
	}
	
	private ArrayList<Mobile> getMobile(){
		return null;
	}
	
	public void monsterMove(){
		
	}
	
	public void playerMove(Direction direction){
		for(Hero object : rLorannList){
			object.setDirection(direction);
			IElement goalPosition = getElementCoordinates(object, "move");
			if(goalPosition != null){
				switch(goalPosition.getPermeability()){
				case PENETRABLE: move(object, goalPosition.getPosX(), goalPosition.getPosY());
					break;
				case BLOCKING:
					break;
				case KILLING:
					break;
				case KILLER:
					break;
				default: move(object, goalPosition.getPosX(), goalPosition.getPosY());
					break;
				}
			}
		}
	}
	
	private void move(Mobile mobile, int x, int y){
		mobile.setPosX(x);
		mobile.setPosY(y);
		System.out.println("");
		System.out.println("");
		System.out.println("");

		System.out.println(mobile.getPosX());
		System.out.println(mobile.getPosY());
		System.out.println(elements.get(26).getPosX());
		System.out.println(elements.get(26).getPosY());
	}
	
	private IElement getElementCoordinates(Mobile mobile, String order) {
		int x = mobile.getPosX() ,y = mobile.getPosY();
		switch(mobile.getDirection()){
		case UP: y = mobile.posY - 1; 
		break;
	case DOWN: y = mobile.posY + 1;
		break;
	case LEFT: x = mobile.posX - 1;
		break;
	case RIGHT: x = mobile.posX + 1;
		break;
	case UPPER_RIGHT: y = mobile.posY - 1; 
					  x = mobile.posX + 1;
		break;
	case UPPER_LEFT: y = mobile.posY - 1; 
					 x = mobile.posX - 1;
		break;
	case BOTTOM_RIGHT: y = mobile.posY + 1;
					   x = mobile.posX + 1;
		break;
	case BOTTOM_LEFT: y = mobile.posY + 1;
					  x = mobile.posX - 1;
		break; 
		}
		for(IElement goalPosition : elements)
			if(goalPosition.getPosX() == x && goalPosition.getPosY() == y){
				return goalPosition;
			}
		System.out.println(mobile.getPosX());
		System.out.println(mobile.getPosY());
		if(order == "move")
			move(mobile, x, y);
		else{
			castFireball(fireballDirection(mobile.getDirection()), x, y);
			
		}
		return null;
	}

	public void playerShot() {
		for(Hero object : rLorannList){
			Direction playerDir = object.getDirection();
			IElement goalPosition = getElementCoordinates(object, "attack");
			if(goalPosition.getPermeability() == Permeability.KILLER){
				
			}
			
		}
	}
	
	public void castFireball(Direction direction, int x, int y){
		Fireball.getInstance(direction, x, y, ImageLoader.fireball[0]);
	}
	
	private Direction fireballDirection(Direction playerDir){
		switch(playerDir){
		case BOTTOM_LEFT: return Direction.UPPER_RIGHT;
		case BOTTOM_RIGHT:return Direction.UPPER_LEFT;
		case DOWN: return Direction.UP;
		case LEFT: return Direction.RIGHT;
		case RIGHT:return Direction.LEFT;
		case UP: return Direction.DOWN;
		case UPPER_LEFT: return Direction.BOTTOM_RIGHT;
		case UPPER_RIGHT: return Direction.BOTTOM_RIGHT;
		}
		return null;
	}
}


