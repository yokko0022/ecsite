package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.domain.MstGoods;
import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;
import jp.co.internous.ecsite.model.mapper.MstGoodsMapper;
import jp.co.internous.ecsite.model.mapper.MstUserMapper;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private MstGoodsMapper goodsMapper;
	
	@RequestMapping("/")
	public String index() {
		return "admintop";
	}
	
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model model) {
		
		MstUser user = userMapper.findByUserNameAndPassword(form);
		//「userMapper.findByUserNameAndPassword(form)」はMstUserMapperインターフェースに定義されているメソッドだ
		//findByUserNameAndPasswordメソッドは
		//アノテーションの()内のSQLの実行結果がMstUser型として扱われる。
		//()内にSQLがない場合、同名のxmlファイルに記述を見に行く。
		
		//System.out.println("userName::::"+user.getUser_Name());//userName::::null
		if(user == null) {
			/*
			 * model.addAttributeメソッドで画面に渡したいデータをModelオブジェクトに追加
			 * 【構文】　model.addAttribute("属性名", 渡したいデータ);
			 * 「return」で遷移先のパスを指定
			 * return "forward:/ecsite/admin/";
			 * 
			 * 遷移先は「src/main/resources/templates」より後のパスで、拡張子は省く
			 * フォワードはforward:を遷移先の前につけるだけで、
			 * ---「今回は、そのまま「/ecsite/admin/」に遷移する。」---
			 * 
			 * 例：/localhost:8080/index?msg=TEST　のURLから、/localhost:8080/hello?msg=TESTに飛ぶ例
			 * 　 msgに渡されたパラメータの値が、そのままhelloに渡されて、TESTという値が表示される。
			 * 
			 * メソッド内の第一引数「"errMessage",」これは以下と対応している。
			 * 「admintop.html」で以下を記述。
			 * <p class="error" th:text="${errMessage}"></p>
			 * 1.「th:text」でコントローラークラスからの値を受け取る
			 * 【構文】　th:text="${Modelオブジェクトに追加した属性名}"
			 * */
			/*
			 * ここでは、Springにおけるリダイレクトとフォワードについて
			 * Springにおけるリダイレクトとフォワードは、以下の違いがある。
			 * フォワード：URLは変わりません。パラメータも継承されます。
			 * リダイレクト：URLは変わります。パラメータも継承されません。
			 * */
			model.addAttribute("errMessage", "ユーザー名またはパスワードが違います。");
			return "forward:/ecsite/admin/";
		}
		
		if(user.getIsAdmin() == 0) {
			model.addAttribute("errMessage", "管理者ではありません。");
			return "forward:/ecsite/admin/";
		}
		
		List<MstGoods> goods = goodsMapper.findAll();
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("goods", goods);
				
		return "welcome";
	}

	@PostMapping("/goodsMst")
	public String goodsMst(LoginForm f, Model m) {
		m.addAttribute("userName", f.getUserName());
		//ここにコンソール出力させてみる
		System.out.println("userNameここ:"+ f.getUserName());
		m.addAttribute("password", f.getPassword());
		
		return "goodsmst";
	}
	
	@PostMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		MstGoods goods = new MstGoods();
		//System.out.println("goods::::"+goods.getGoodsName());//goods::::null
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		
		goodsMapper.insert(goods);
		
		return "forward:/ecsite/admin/welcome";
	}
	
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		//「ajax」を使用した方式での処理＝REST処理という。
		//成功＝「"1"」、失敗＝「"-1"」String型であること。分かりやすければ100と999でもよい。
		try{
			goodsMapper.deleteById(f.getId());
		}catch(IllegalArgumentException e) {
			return "-1";
		}
		
		return "1";
	}
}

