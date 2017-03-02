/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Euler
 */
@WebServlet("/login")
public class LoggedIn extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberLogin");
        
        HttpSession session = req.getSession(true);
        
        session.setAttribute("username",username);
        session.setAttribute("password", password);
        session.setAttribute("rememberLogin", remember);
        
        PrintWriter out = resp.getWriter();
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html> <html> <head> <title>Welcome page</title> <meta char-set=\"UTF-8\"> </head> <body>");
        html.append("<a style=\"padding-right:20px;\" href=\"login\">Home</a>");
        html.append("<a href=\"index.html\">Log out</a>");
        
        html.append("<p>Logged in succesfully!!!</p> <p>Welcome &nbsp;&nbsp;" + (String) session.getAttribute("username") + "&nbsp;!!!!</p></body> </html>");
        out.println(html);  
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        StringBuilder html = new StringBuilder();
        HttpSession session = req.getSession(false);
                
        html.append("<!DOCTYPE html> <html> <head> <title>Welcome page</title> <meta char-set=\"UTF-8\"> </head> <body>");
        html.append("<a style=\"padding-right:20px;\" href=\"login\">Home</a>");
        html.append("<a href=\"index.html\">Log out</a>");
        
        if(session!=null)
        { 
            if(session.getAttribute("rememberLogin")!=null)
            {
                html.append("<p>Logged in succesfully!!!</p> <p>Welcome &nbsp;&nbsp;" + (String) session.getAttribute("username") + "&nbsp;!!!!</p></body> </html>");
                out.println(html);   
                session.setMaxInactiveInterval(-1);
            }
            else
            { 
                
                //String url="/index.html";
                //getServletContext().getNamedDispatcher(url).forward(req, resp);
                
                String scheme = req.getScheme() + "://";
                String serverName = req.getServerName();
                String serverPort = (req.getServerPort() == 80) ? "" : ":" + req.getServerPort();
                String contextPath = req.getContextPath();
                String url= scheme + serverName + serverPort + contextPath+"/index.html";
                resp.sendRedirect(url);
                session.invalidate();
                //session=null;
            }
           
        }
        else
        {  
              //tring url="/index.html";
              //getServletContext().getNamedDispatcher(url).forward(req, resp);
              String scheme = req.getScheme() + "://";
              String serverName = req.getServerName();
              String serverPort = (req.getServerPort() == 80) ? "" : ":" + req.getServerPort();
              String contextPath = req.getContextPath();
              String url= scheme + serverName + serverPort + contextPath+"/index.html";
              resp.sendRedirect(url);
              
        }
    }
    
       
}
