package jp.co.internous.ecsite.model.domain;

public class MstUser {
	/**	 * フィールド名はMySQL名称と一致させる。*/
	private int id;
	private String userName;
	private String password;
	private String fullName;
	private int isAdmin;
		
	
	public int getId() {/**	 * @return id	 ローワーキャメルケースでMySQL名称と一致させる。*/
		return id;
	}
	public void setId(int id) {/**	 * @param id セットする id	 */
		this.id = id;
	}
	
	public String getUserName() {/**	 * @return user_Name	 */
		return userName;
	}	
	public void setUserName(String userName) {/**	 * @param user_Name セットする user_Name	 */
		this.userName = userName;
	}
	
	public String getPassword() {/**	 * @return password	 */
		return password;
	}
	public void setPassword(String password) {/**	 * @param password セットする password	 */
		this.password = password;
	}
	
	public String getFullName() {/**	 * @return fullName	 */
		return fullName;
	}
	public void setFullName(String fullName) {/**	 * @param fullName セットする full_Name	 */
		this.fullName = fullName;
	}

	public int getIsAdmin() {/**	 * @return isAdmin	 */
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {/**	 * @param isAdmin セットする isAdmin	 */
		this.isAdmin = isAdmin;
	} 
	
	
}
