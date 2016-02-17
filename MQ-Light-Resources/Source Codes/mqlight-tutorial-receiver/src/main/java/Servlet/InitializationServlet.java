/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.ibm.mqlight.api.Delivery;
import com.ibm.mqlight.api.DestinationAdapter;
import com.ibm.mqlight.api.NonBlockingClient;
import com.ibm.mqlight.api.impl.NonBlockingClientImpl;
import com.ibm.mqlight.api.NonBlockingClientAdapter;
import com.ibm.mqlight.api.StringDelivery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Raguel
 */
@WebServlet(name = "InitializationServlet", urlPatterns = {"/InitializationServlet"})
public class InitializationServlet extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private NonBlockingClient mqlightclient;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) 
        {
            final HttpSession session = request.getSession();
            final ArrayList<String> messages = new ArrayList<String>();
            final String topic = request.getParameter("topic");
            
            mqlightclient = NonBlockingClient.create(null, new NonBlockingClientAdapter<Void>() 
            {
                public void onStarted(NonBlockingClient client, Void context) 
                {
                    client.subscribe(topic,  new DestinationAdapter<Void>() 
                    {
                        public void onMessage(NonBlockingClient client, Void context, Delivery delivery) 
                        {
                            if (delivery.getType() == Delivery.Type.STRING)
                            {
                                messages.add(((StringDelivery)delivery).getData());
                                session.setAttribute("messages", messages);
                                
                                if(!((StringDelivery)delivery).getData().isEmpty())
                                {
                                    for(int j = 0; j < messages.size(); j++)
                                        session.setAttribute("message"+j, messages.get(j));
                                }
                            }
                        }
                    }, null, null);
                }
            }, null);
            
            session.setAttribute("mqlightclient", mqlightclient);
            response.sendRedirect("ReceiveMessagePage.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
