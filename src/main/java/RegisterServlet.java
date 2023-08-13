import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // JDBC connection information
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    /**
     * This servlet handles the registration of new users.
     *
     * @param request The servlet request
     * @param response The servlet response
     * @throws ServletException If the servlet fails to process the request
     * @throws IOException If an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username, password, and email from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Check if the username already exists
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Username already exists
                response.sendRedirect("register.jsp?error=username_exists");
                return;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Save the user data to the database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // Redirect to the login page
        response.sendRedirect("login.jsp");
    }
}