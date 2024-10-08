package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.domain.MstGoods;
import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.dto.HistoryDto;
import jp.co.internous.ecsite.model.form.CartForm;
import jp.co.internous.ecsite.model.form.HistoryForm;
import jp.co.internous.ecsite.model.form.LoginForm;
import jp.co.internous.ecsite.model.form.RegisterForm;
import jp.co.internous.ecsite.model.mapper.MstGoodsMapper;
import jp.co.internous.ecsite.model.mapper.MstUserMapper;
import jp.co.internous.ecsite.model.mapper.TblPurchaseMapper;

@Controller
@RequestMapping("/ecsite")
public class IndexController {

	@Autowired
	private MstGoodsMapper goodsMapper;
/**/
	@Autowired
	private MstUserMapper userMapper;
	
/***/
	@Autowired
	private TblPurchaseMapper purchaseMapper;

	private Gson gson = new Gson();
/**/
	@GetMapping("/")
	public String index(Model model) {
		List<MstGoods> goods = goodsMapper.findAll();
		model.addAttribute("goods", goods);

		return "index";//「return "index"」によりindex.htmlい遷移する

	}
/**/
	@ResponseBody//
	@PostMapping("/api/login")
	public String loginApi(@RequestBody LoginForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f);
		
		
		if(user == null) {
			user = new MstUser();
			user.setFullName("ゲスト");//MstUser.javaのメソッドを確認。「_」が自動でついていたりする。
		}
		//System.out.println("user.getUserName()::"+user.getUserName());//デッド・コード?
		return gson.toJson(user);
	}
/***/
	@ResponseBody//
	@PostMapping("/api/purchase")
	public int purchaseApi(@RequestBody CartForm f) {
		
		//System.out.println("CartForm f::"+f);//?
		
		f.getCartList().forEach((c) -> {
			int total = c.getPrice() * c.getCount();
			purchaseMapper.insert(f.getUserId(), c.getId(), c.getGoodsName(), c.getCount(), total);
		});
		System.out.println("「f.getCartList().size()＝＝」"+f.getCartList().size());
		return f.getCartList().size();
	}
/***/
	@ResponseBody//
	@PostMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm f) {
		int userId = f.getUserId();
		List<HistoryDto> history = purchaseMapper.findHistory(userId);
		
		System.out.println("history::"+history);//?
		
		return gson.toJson(history);
	}

/**/
	@GetMapping("/signup")
	public String signup() {

		return "signup";//「return "signup"」によりsignup.htmlに遷移する
	
	}

/**/
	@PostMapping("/register")
	public String register(RegisterForm f, Model m) {
		//入力値を残す。
		m.addAttribute("fullName", f.getFullName());
		m.addAttribute("userName", f.getUserName());
		m.addAttribute("password", f.getPassword());
		
		//入力ボックスに何もないときメッセージを出す。
		if (f.getFullName() == "" || f.getUserName() == "" || f.getPassword() == "") {
			m.addAttribute("errMessage", "全ての項目を入力してください。");
			return "signup";
		}
		
		//カウントする。
		
		int exist = userMapper.countByUserName(f.getUserName());
		if(exist > 0) {
			m.addAttribute("errMessage", "このユーザー名はすでに使用されています。");
			return "signup";
		}
		
		MstUser user = new MstUser();
		user.setFullName(f.getFullName());
		user.setUserName(f.getUserName());
		user.setPassword(f.getPassword());
		user.setIsAdmin(0);
		
		userMapper.insert(user);
		
		return "redirect:/ecsite/";
	}
	
	
	
}
