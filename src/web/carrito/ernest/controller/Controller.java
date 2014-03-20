package web.carrito.ernest.controller;

import java.util.Vector;

import web.carrito.ernest.model.CD;
import web.carrito.ernest.model.DatabaseController;

// controlador 
public class Controller {

	
	public DatabaseController databaseController;
	
	public Controller(){
		databaseController = new DatabaseController();				
	}
		
	public Vector add(Vector oldBuyList, int id, int quantity) throws Exception{
		
		 Vector buylist= oldBuyList;
		   
		       //any previous buys of same cd?
		        boolean match=false;
		        CD aCD = databaseController.selectCD(id);
		        aCD.setQuantity(quantity);
		        if (buylist==null) {
		          //add first cd to the cart
		          buylist = new Vector(); //first order
		          buylist.addElement(aCD);
		        } else { // not first buy
		          for (int i=0; i< buylist.size(); i++) {
		            CD cd = (CD) buylist.elementAt(i);		            
		            if (cd.getAlbum().equals(aCD.getAlbum())) {
		              cd.setQuantity(cd.getQuantity()+aCD.getQuantity());
		              buylist.setElementAt(cd,i);
		              match = true;
		            } //end of if name matches
		          } // end of for
		          if (!match) 
		            buylist.addElement(aCD);
		        }
		    
		  return buylist;
	}
	
	public String checkout(Vector buylist) {
		float total =0;
	      for (int i=0; i< buylist.size();i++) {
	        CD anOrder = (CD) buylist.elementAt(i);
	        float price= anOrder.getPrice();
	        int qty = anOrder.getQuantity();
	        total += (price * qty);
	      }
	      total += 0.005;
	      String amount = new Float(total).toString();
	      int n = amount.indexOf('.');
	      amount = amount.substring(0,n+3);
		return amount;
	}
	
	public Vector getAllCds() throws Exception{
		return databaseController.selectCDs();
	}
}
