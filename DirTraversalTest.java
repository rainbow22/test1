
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;

public final class DirTraversalTest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_FILE_SIZE = 1024;
	private static final String DIR = "/home/student/downloads/";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		// validate the file name here
		String fileName = request.getParameter("fName");
		boolean isValid = ESAPI.validator().isValidFileName("HelloServlet", fileName, false);
		if (!isValid) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		byte data[] = new byte[MAX_FILE_SIZE];
		int numBytes = 0;
		int totalBytesRead = 0;
		FileInputStream fis = null;
		OutputStream out = null;
		
		try {
			fis = new FileInputStream (DIR + fileName);
			while (numBytes != -1 && totalBytesRead < MAX_FILE_SIZE) {
		        numBytes = fis.read(data, totalBytesRead, MAX_FILE_SIZE - totalBytesRead);
		        totalBytesRead += numBytes;
		    }
			
			response.setContentType("text/html");
			out = response.getOutputStream();
			out.write(data, 0, totalBytesRead);
		} catch(IOException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			fis.close();
			out.close();
		}
	}
}
