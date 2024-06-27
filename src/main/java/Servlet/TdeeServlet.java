package Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class TdeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the form
            int weight = Integer.parseInt(request.getParameter("weight"));
            int height = Integer.parseInt(request.getParameter("height"));
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            double activityLevel = Double.parseDouble(request.getParameter("activityLevel"));

            // Calculate TDEE
            double tdee;
            if (gender.equals("male")) {
                tdee = (10 * weight) + (6.25 * height) - (5 * age) + 5;
            } else {
                tdee = (10 * weight) + (6.25 * height) - (5 * age) - 161;
            }
            tdee *= activityLevel;

            // Display the result
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Your Total Daily Energy Expenditure (TDEE) is:</h2>");
            out.println("<p>" + tdee + " calories/day</p>");
            out.println("</body></html>");
        } catch (NumberFormatException e) {
            // Handle the error, maybe by displaying a user-friendly message
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Error: Please enter valid numeric values for weight, height, age, and activity level</h2>");
            out.println("</body></html>");
        }
    }
}
