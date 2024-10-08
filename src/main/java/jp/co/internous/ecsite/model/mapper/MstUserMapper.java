package jp.co.internous.ecsite.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.form.LoginForm;

@Mapper
public interface MstUserMapper {
	@Select(value="SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(LoginForm form);
	//findByUserNameAndPasswordメソッドは
	//アノテーションの()内のSQLの実行結果がMstUser型として扱われる。
	//()内にSQLがない場合、同名のxmlファイルに記述を見に行く。
	
	@Insert("INSERT INTO mst_user (user_name, password, full_name, is_admin) VALUES (#{userName}, #{password}, #{fullName}, #{isAdmin})")
	int insert(MstUser user);
	
	@Select(value="SELECT COUNT(id) FROM mst_user WHERE user_name = #{userName}")
	int countByUserName(String userName);
}
