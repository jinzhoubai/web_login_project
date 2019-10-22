package dao;

import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

/*
操作数据库中的user表的类
 */

public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录方法
     * @paraml loginUser只有用户名和密码
     * @return user包含用户全部数据
     */
    public User login(User loginUser){
        //1 编写sql
        String sql="select * from user where username=? and password = ?";
        //2 调用query方法
        try {
            User user=template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),loginUser.getPassword());


            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }

}
