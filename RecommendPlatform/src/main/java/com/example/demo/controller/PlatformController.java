package com.example.demo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.HttpConnectionUtil;
import com.example.demo.dao.FileDAO;
import com.example.demo.dao.QADAO;
import com.example.demo.dao.ReviewDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.service.ListPageService;
import com.example.demo.service.UserService;
import com.example.demo.vo.QAVO;
import com.example.demo.vo.ReviewVO;
import com.example.demo.vo.UserVO;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/home")
public class PlatformController {
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private FileDAO fdao;
	
	@Autowired
	private ReviewDAO rdao;
	
	@Autowired
	private UserService svc;
	
	@Autowired
	private ListPageService lpsvc;
	
	@Autowired
	private QADAO qadao;
	
	@RequestMapping("")
	public String Home(Model m) {
		System.out.println("기본 페이지 실행 중");
		List<ReviewVO> list = rdao.getTopTenReviewList();
		m.addAttribute("topten", list);
		return "Home";
	}
	
	@RequestMapping("/join")
	public @ResponseBody String JoinUser(@RequestParam("id") String id, @RequestParam("pwd") String pwd, 
						@RequestParam("name") String name, @RequestParam("phone") String phone, 
						@RequestParam("email") String email) {
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setUname(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		
		boolean flag = dao.insertUser(vo);
		
		return flag+"";
	}
	
	@RequestMapping("/login")
	public @ResponseBody boolean LoginUser(HttpServletRequest request, @RequestParam("id") String id, @RequestParam("pwd") String pwd) {
		String uid = svc.loginSVC(id, pwd);
		int uno = svc.loginSVC2(id);
		HttpSession session = request.getSession();
		
		if(uid != null) {
			session.setAttribute("uid", uid);
			session.setAttribute("uno", uno);
			return true;
		}
		else {
			session.setAttribute("uid", null);
			session.setAttribute("uno", null);
			return false;
		}
	}
	
	@RequestMapping("/logout")
	public @ResponseBody boolean LogOutUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("uid");
		session.removeAttribute("uno");
		return true;
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("uid");
		UserVO vo = dao.getUserById(id);
		session.setAttribute("user", vo);
		return "MyPage";
	}
	
	@RequestMapping("/mypage/upload")
	public String fileUpload(@RequestParam("files") MultipartFile[] mfiles, HttpServletRequest request, 
			@RequestParam("title") String title, @RequestParam("contents") String contents) {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		
		// qa_bbs table insert
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		
		QAVO tmp_vo = new QAVO();
		tmp_vo.setContents(contents);
		tmp_vo.setId(vo.getId());
		tmp_vo.setTitle(title);
		tmp_vo.setWdate(time1);
		boolean check = qadao.insertQA(tmp_vo);
		
		if (check) {
			System.out.println("삽입 성공");
		}
		else {
			System.out.println("삽입 실패");
		}
		
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/upload");
		boolean flag = false;
		try {
			for(int i = 0;i<mfiles.length;i++) {
				mfiles[i].transferTo(new File(savePath+"/"+mfiles[i].getOriginalFilename()));
				
				String fileName = mfiles[i].getOriginalFilename();
				long fileSize = mfiles[i].getSize();
				flag = fdao.uploadFile(fileName, fileSize, fileName, vo.getUno());
				if(flag) return "MyPage";
			}
		} catch(Exception e) {
			System.out.println("Upload Error");
			return "MyPage";
		}
		return "MyPage";
	}
	
	@RequestMapping("/list/{n}")
	public String listPage(@PathVariable("n") int pn, Model m) {
		PageInfo<ReviewVO> pageInfo = lpsvc.getListPage(pn, 10);
		m.addAttribute("pageInfo",pageInfo);
		return "ListPage";
	}
	
	@RequestMapping("/mylistcheck")
	public @ResponseBody boolean myListCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("uno") != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping("/mylist/{n}")
	public String mylistPage(@PathVariable("n") int pn, Model m, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int uno = (int) session.getAttribute("uno");
		PageInfo<ReviewVO> pageInfo = lpsvc.getMyListPage(pn, 10, uno);
		m.addAttribute("pageInfo",pageInfo);
		return "ListPage";
	}
	
	@RequestMapping("/searchlist/{n}")
	public String searchListPage(@PathVariable("n") int pn, Model m, @RequestParam("searchtext") String text) {
		PageInfo<ReviewVO> pageInfo = lpsvc.searchListPageByName(pn, 10, "%"+text+"%");
		m.addAttribute("pageInfo",pageInfo);
		return "ListPage";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("addressURL") String url, @RequestParam("uno") int uno) {
		HttpConnectionUtil hc = new HttpConnectionUtil();
		try {
			hc.sendWriteSignal("http://127.0.0.1:5000", url, uno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Home";
	}
	
	@RequestMapping("/qa/{n}")
	public String qalistPage(@PathVariable("n") int pn, Model m) {
		PageInfo<QAVO> pageInfo = lpsvc.getQAListPage(pn, 10);
		m.addAttribute("pageInfo",pageInfo);
		return "QandAPage";
	}
}
