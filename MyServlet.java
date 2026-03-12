

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.dao.EmpDao;
import com.emp.entity.Employee;



public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String password=request.getParameter("pass");
		String email=request.getParameter("email");
		
		Employee e=new Employee();
		e.setName(name);
		e.setPassword(password);
		e.setEmail(email);
		int status=EmpDao.save(e);
		if(status>0)
		{
			out.print("Record added");
			
		}else {
			out.print("Failed");
			request.getRequestDispatcher("index.html").include(request, response);
		}
	}

}
