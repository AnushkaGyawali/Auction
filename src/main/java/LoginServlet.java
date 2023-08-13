
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
private static final String DB_USERNAME = "root";
private static final String DB_PASSWORD = "password";

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Check if the username and password are valid
    boolean authenticated;
    authenticated = false;
    try {
        authenticated = authenticateUser(username, password);
    } catch (SQLException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (authenticated) {
        // Create a session for the user
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        // Redirect the user to the dashboard page
        response.sendRedirect("dashboard.jsp");
    } else {
        // Redirect the user back to the login page
        response.sendRedirect("login.jsp");
    }
}

private boolean authenticateUser(String username, String password) throws SQLException {
    // Connect to the database
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

    // Prepare the statement
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
    statement.setString(1, username);
    statement.setString(2, password);

    // Execute the statement
    ResultSet resultSet = statement.executeQuery();

    // Check if the user exists
    return resultSet.next();
}}