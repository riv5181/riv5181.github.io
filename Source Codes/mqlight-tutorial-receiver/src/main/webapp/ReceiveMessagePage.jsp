<%-- 
    Document   : SendMessagePage
    Created on : Feb 14, 2016, 5:07:29 PM
    Author     : Raguel
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%final ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");%>
<html>
    <head>
        <title>Refresh Me</title>
    </head>
        <form action="ReturnServlet">
            <input type="submit" value="Re-Initialize"/>
        </form>
        <h1>MESSAGES APPEAR BELOW</h1>
        <br/>
        <%for (int j = 0; j < messages.size(); j++){%>
          <p> <%=session.getAttribute("message"+j)%> </p>
          <p>-----------------------------------</p>
        <%}%>
        
</html>
