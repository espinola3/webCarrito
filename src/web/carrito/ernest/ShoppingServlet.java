package web.carrito.ernest;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.carrito.ernest.controller.Controller;
import web.carrito.ernest.model.CD;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public Controller controller;
	
	public void init(ServletConfig conf) throws ServletException  {
	    super.init(conf);
	    controller = new Controller();
	  }
	 public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		 System.out.println("get carregar llista");
		 try {
		 HttpSession session = req.getSession(false);		 	
		 Vector products = controller.getAllCds();
		 session.putValue("shopping.products", products);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String url="/EShop.jsp";
	     ServletContext sc = getServletContext();
	     RequestDispatcher rd = sc.getRequestDispatcher(url);
	     rd.forward(req, res);
	 }
	
	  public void doPost (HttpServletRequest req, HttpServletResponse res)
	      throws ServletException, IOException {
	    
		  
		HttpSession session = req.getSession(false);
	    if (session == null) {
	      res.sendRedirect("http://localhost:8000/webCarrito/error.html");
	    }
	   
	    //leemos la buylist de session
		Vector buylist =(Vector)session.getValue("shopping.shoppingcart");
		
		//Recuperamos los parametros action, delindex y id del request que nos llega al servlet
		String action = req.getParameter("action");
		String delindex = req.getParameter("delindex");
		String idString = req.getParameter("idCD");
		String quantity = req.getParameter("qty");
		
	   	//dependiendo de la action que nos llegue, hacemos una cosa u otra
		if (!action.equals("CHECKOUT")) {
		      if (action.equals("DELETE")) {		       
		        int d = (new Integer(delindex)).intValue();
		        buylist.removeElementAt(d);
		      } else if (action.equals("ADD")) {
		    	  try {
					buylist = controller.add(buylist, Integer.valueOf(idString), Integer.valueOf(quantity));
				}  catch (Exception e) {
					System.out.println("error en metodo controller.add");
					e.printStackTrace();
				}
		      }
		      session.putValue("shopping.shoppingcart", buylist);
		      String url="/EShop.jsp";
		      ServletContext sc = getServletContext();
		      RequestDispatcher rd = sc.getRequestDispatcher(url);
		      rd.forward(req, res);
	    } else if (action.equals("CHECKOUT"))  {
	      String amount = controller.checkout(buylist);
	      req.setAttribute("amount",amount);
	      String url="/Checkout.jsp";
	      ServletContext sc = getServletContext();
	      RequestDispatcher rd = sc.getRequestDispatcher(url);
	      rd.forward(req,res);
	    }
	  }
	

}
