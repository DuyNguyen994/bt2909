package Api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Service.LoginService;
import payload.response.BaseResponse;


@WebServlet(name="apiLoginController", urlPatterns = {"/api/login"})
public class ApiLoginController extends HttpServlet {
	private LoginService loginService = new LoginService();
	private Gson gson = new Gson();
	private BaseResponse baseResponse = new BaseResponse();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		boolean isSuccess = loginService.getLogin(email, password);
		
		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Đăng Nhập Thành Công!" : "Đăng Nhập Thất Bại!!!");
		baseResponse.setData(isSuccess);
		
		String dataJson = gson.toJson(baseResponse);
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("/application/json");
		resp.setCharacterEncoding("UTF-8");

		out.print(dataJson);
		out.flush();	
	}
	
}
