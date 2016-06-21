package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import contract.IElement;
import contract.IModel;

public class ViewPanelMap extends JPanel{
	
	private ArrayList<IElement> elements;
	IModel model = null;
	boolean t = false;
	IElement temp = null;
	
	/** 
	 * the constructor ViewPanelMap
	 * @param model
	 * 	the model
	 * 	show the actual map
	 */
	public ViewPanelMap(IModel model){
		this.model = model;
		elements = model.loadStage(2);
		this.setLayout(new GridLayout(12, 20));
		this.setBackground(Color.black);
	}
	
	/**
	 * the method updateMap
	 * Update all information about the map
	 */
	public void updateMap(){
		this.removeAll();
		if (model.getFireball().getState() == false && t == false) {
			temp = model.getFireball();
			elements.remove(model.getFireball());
			t = true;
		}
		if (t == true && model.getFireball().getState() == true) {
			elements.add(temp);
			IElement temp = null;
			t = false;
		}
		elements = orderElement();
		int k = 0;
		for (int j = 0; j < 12; j++ ) {
			for (int i = 0; i < 20; i++) {
				if(elements.get(k).getState() == false){
					if (elements.get(k).getID() == 5) {
						k++;
					}
					else {
						elements.remove(elements.get(k));
						updateMap();
					}
				}
				if (elements.get(k).getPosX() == i && elements.get(k).getPosY() == j){
					ImageIcon icon = new ImageIcon(elements.get(k).getSprite());
					JLabel img = new JLabel(icon);
					this.add(img);
					k++;
					if(k == elements.size()-1)
						break;	
				}
				else {
					JPanel pan = new JPanel();
					pan.setBackground(Color.BLACK);
					this.add(pan);
				}
			}
		}
	}

	/**
	 * the method orderElement
	 * @return
	 * 	Arrange the arrayList elements
	 */
	public ArrayList<IElement> orderElement(){
		ArrayList<IElement> orderElement = new ArrayList<IElement>();
		int xMin = 0, yMin = 0, i = elements.size();
		while(orderElement.size() != i){
			for (IElement element : elements) {
					int x = element.getPosX(), y = element.getPosY();
					if(x == xMin && y == yMin){
						orderElement.add(element);
						xMin = x;
						yMin = y;
					}
			}
			xMin++;
			if(xMin == 20){
				xMin = 0;
				yMin++;
			}
		}
		return orderElement;
	}
}
