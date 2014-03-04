
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.*;
import org.owasp.esapi.Encoder;

public final class EncoderTest extends HttpServlet {
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
		throws IOException, ServletException {
		
		Encoder e = ESAPI.encoder();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>Sample Application Servlet Page</title>");
		writer.println("</head>");
		writer.println("<body bgcolor=white>");

		writer.println("<table border=\"0\">");
		writer.println("<tr>");
		writer.println("<td>");
		writer.println("<img src=\"images/tomcat.gif\">");
		writer.println("</td>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<td>");
		writer.println("<h1>Sample Application Servlet</h1>");
		writer.println("Welcome " + e.encodeForHTML(request.getParameter("fName")));
		writer.println("<br><br><form name=\"test\">This is a sample form that echos the supplied parameters.");
		writer.println("<br>First Name: <input type=\"text\" value=" + e.encodeForHTMLAttribute(request.getParameter("fName")) + ">");
		writer.println("<br>Last Name: <input type=\"text\" value=" + e.encodeForHTMLAttribute(request.getParameter("lName")) + ">");
		writer.println("<br><br>Your First Name is"); 
		writer.println("<script language=\"javascript\">");
		writer.println("var str=\'" + e.encodeForJavaScript(request.getParameter("fName")) + "\';");
		writer.println("document.write(str.length);");
		writer.println("</script>");
		writer.println("characters long.");
		writer.println("</td>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
