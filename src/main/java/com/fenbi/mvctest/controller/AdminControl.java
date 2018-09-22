package com.fenbi.mvctest.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.AdminRole;
import com.fenbi.mvctest.entity.FenbiResult;
import com.fenbi.mvctest.entity.Function;
import com.fenbi.mvctest.service.AdminRoleService;
import com.fenbi.mvctest.service.AdminService;
import com.fenbi.mvctest.service.FunctionService;
import com.fenbi.mvctest.utils.ExcelExport;
import com.fenbi.mvctest.utils.MD5Utils;


@Controller
@RequestMapping("/admin")
public class AdminControl {
	
	@Autowired
	public AdminService adminService;
	@Autowired
	public AdminRoleService adminRoleService;
	@Autowired
	public FunctionService functionService;
	@Autowired
	public MD5Utils md5util;
	
	/*
	 * @Description: 登录
	* @param username
	* @param password
	* @param response
	* @param session
	* @return
	* @throws UnsupportedEncodingException:
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public FenbiResult login(String username,String password,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		password = md5util.md5(password);
		Admin admin = adminService.login(username, password);
		String indexUrl = "";
		if(admin!=null) {
			session.setAttribute("loginAdmin", admin);
			Cookie cookie = new Cookie("username",URLEncoder.encode(admin.getName(), "utf-8"));
			cookie.setPath("/");
			response.addCookie(cookie);
			//查询用户有哪些功能，以第一个功能显示页面为首页显示
			List<Function> functions = functionService.selectByadminId(admin.getId());
				if(functions.get(0).getId()==1) {
					indexUrl = "index.html";
				}else if(functions.get(0).getId()==2) {
					indexUrl = "user.html";
				}else if(functions.get(0).getId()==3) {
					indexUrl = "course.html";
				}else if(functions.get(0).getId()==4) {
					indexUrl = "teachers.html";
				}else if(functions.get(0).getId()==5) {
					indexUrl = "class.html";
				}
			return new FenbiResult(indexUrl);
		}
		return new FenbiResult(FenbiResult.STATUS_FAIL,"账号或者密码错误",null);
	}
	
	/*
	 * @Description: 注销
	* @param session
	* @param response
	* @return:
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session,HttpServletResponse response) {
		session.invalidate();
		Cookie cookie = new Cookie("username", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/login.html";
	}
	
	/*
	 * @Description: 增
	* @param lesson
	* @param startTimeString
	* @param endTimeString
	* @return:
	 */
	@RequestMapping("/add")
	@ResponseBody
	public FenbiResult addLesson(Admin admin) {
		admin.setCreateTime(new Timestamp(System.currentTimeMillis()));
		admin.setPassword(md5util.md5(admin.getPassword()));
		adminService.insert(admin);
		return new FenbiResult();
	}
	
	/*
	 * @Description: 查
	* @param keyword
	* @param num
	* @return:
	 */
	@RequestMapping("/admins")
	@ResponseBody
	public FenbiResult selectAll(String keyword,@RequestParam(defaultValue="1")int num) {
		List<Admin> admins = adminService.selectAll(keyword, num);
		return new FenbiResult(admins);
	}
	
	/*
	 * @Description: 查记录
	* @param keyword
	* @return:
	 */
	@RequestMapping("/count")
	@ResponseBody
	public FenbiResult adminCount(String keyword) {
		int counts = adminService.selectCount(keyword);
		return new FenbiResult(counts);
	}
	
	/*
	 * @Description: 查某个用户
	* @param id
	* @return:
	 */
	@RequestMapping("/getAdminById")
	@ResponseBody
	public FenbiResult getAdminById(int adminId) {
		Admin admin = adminService.selectById(adminId);
		return new FenbiResult(admin);
	}
	
	/*
	 * @Description: 查某个用户的role
	* @param id
	* @return:
	 */
	@RequestMapping("/getRoleByadminId")
	@ResponseBody
	public FenbiResult getRoleByadminId(int adminId) {
		List<AdminRole> adminRoles = adminRoleService.selectById(adminId);
		return new FenbiResult(adminRoles);
	}
	
	/*
	 * @Description: 查某个用户的function，用于验证权限显示树形目录
	* @param id
	* @return:
	 */
	@RequestMapping("/getAccess")
	@ResponseBody
	public FenbiResult getAccess(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("loginAdmin");
		List<Function> functions = functionService.selectByadminId(admin.getId());
		return new FenbiResult(functions);
	}
	
	/*
	 * @Description: 改
	* @param lesson
	* @param startTimeString
	* @param endTimeString
	* @return:
	 */
	@RequestMapping("/update")
	@ResponseBody
	public FenbiResult updateAdmin(Admin admin) {
		adminService.update(admin);
		return new FenbiResult();
	}
	
	/*
	 * @Description: 改某个用户的roles
	* @param id
	* @return:
	 */
	@RequestMapping("/updateRoles")
	@ResponseBody
	public FenbiResult updateRoles(int adminId,int[] roles) {
		adminRoleService.update(adminId, roles);
		return new FenbiResult();
	}
	
	/*
	 * @Description: 删
	* @param id
	* @return:
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public FenbiResult deleteAdmin(int id) {
		adminService.delete(id);
		return new FenbiResult();
	}
	
	@RequestMapping("/export")
    public void createExcel(HttpServletResponse response){
       List<Admin> admins = adminService.select();
        //excel标题
        String[] title = {"id", "用户名", "密码", "姓名", "邮箱", "在职状态", "创建时间"};
        String[][] objects = new String[admins.size()][title.length];
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);
            objects[i][0] = String.valueOf(admin.getId());
            objects[i][1] = admin.getUsername();
            objects[i][2] = admin.getPassword();
            objects[i][3] = admin.getName();
            objects[i][4] = admin.getEmail();
            objects[i][5] = String.valueOf(admin.getStatus());
            objects[i][6] = String.valueOf(admin.getCreateTime());
        }
        String fileName = "用户信息表";
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelExport.createWorkbook(fileName, title, objects);
        //响应到客户端
        try {
            //设置编码、输出文件格式
            response.reset();
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
