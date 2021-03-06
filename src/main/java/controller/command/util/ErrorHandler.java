package controller.command.util;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dmytro Galomko
 */
@WebServlet(urlPatterns = "/errorHandler")
public class ErrorHandler extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(Links.ERROR_PAGE);
        dispatcher.forward(req, resp);
    }

}
