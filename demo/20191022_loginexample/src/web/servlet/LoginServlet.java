package web.servlet;

import dao.UserDao;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/loginServlet")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 设置编码
        req.setCharacterEncoding("utf-8");
        //2 获取请求参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        // 3 封装user对象
        User loginUser=new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        //4 调用userDao的login方法
        UserDao dao=new UserDao();
        User user=dao.login(loginUser);
        if(user==null){
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else{
            //登录成功
            //存储数据
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }



    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
