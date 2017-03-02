/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGUYEN
 */
@WebServlet("/logout")
public class LogOut extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        String scheme = req.getScheme() + "://";
        String serverName = req.getServerName();
        String serverPort = (req.getServerPort() == 80) ? "" : ":" + req.getServerPort();
        String contextPath = req.getContextPath();
        String url = scheme + serverName + serverPort + contextPath + "/index.html";
        resp.sendRedirect(url);
        Count.count=0;
    }
    
}
