<%-- 
    Document   : SendMessagePage
    Created on : Feb 14, 2016, 5:07:29 PM
    Author     : Raguel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Message Here</title>
    </head>
    <body>
        <form action="SendMessageServlet">
            <textarea rows="4" cols="50" name="sentMessage" placeholder="Place Text Here"></textarea>
            <br/>
            Topic: <input type="text" name="topicSend" value="public"/>
            <br/>
            <input type="submit" value="Send"/>
        </form>
        <form action="ReturnServlet">
            <input type="submit" value="Back"/>
        </form>
    </body>
</html>
