<%@ page session="true" import="java.util.*, web.carrito.ernest.model.CD"%>
<html>
<head>
 <title>Music Without Borders</title>
</head>
<body bgcolor="#33CCFF">
 <font face="Times New Roman,Times" size="+3">
  Music Without Borders
 </font>
 <hr><p>
 <center>
 <form name="shoppingForm" action="/webCarrito/ShoppingServlet" method="POST">
 <b>CD:</b> 
 <select name=idCD>
 <%
 Vector products = (Vector) session.getValue("shopping.products");
 if (products != null && (products.size() > 0)) {
	 
	  for (int index=0; index < products.size();index++) {
	   CD anOrder = (CD) products.elementAt(index);
%>
	<option value="<%=anOrder.getId()%>"><%=anOrder.getAlbum()%> | <%=anOrder.getArtist()%> | <%=anOrder.getCountry()%> | <%=anOrder.getPrice()%></option>
<%
	  }
 }
 else{
	 String redirectURL = "/webCarrito/ShoppingServlet";
	 response.sendRedirect(redirectURL);
 }
%> 
 </select>
 <b>Quantity: </b><input type="text" name="qty" SIZE="3" value=1>
 <input type="hidden" name="action" value="ADD">
 <input type="submit" name="Submit" value="Add to Cart">
 </form>
 </center>
 <p>
 <jsp:include page="Cart.jsp" flush="true" />
</body>
</html>