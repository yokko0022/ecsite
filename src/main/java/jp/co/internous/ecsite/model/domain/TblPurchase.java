package jp.co.internous.ecsite.model.domain;

import java.sql.Timestamp;//似たものに、import java.security.Timestamp;とは違う！

public class TblPurchase {
	private int id;
	private int userId;
	private int goodsId;
	private String goodsName;
	private int itemCount;
	private int total;
	private Timestamp createdAt; //import必要「java.sql.Timestamp」
	
	/**	 * @return id	 */
	public int getId() {
		return id;
	}
	/**	 * @param id セットする id	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**	 * @return userId	 */
	public int getUserId() {
		return userId;
	}
	/**	 * @param userId セットする userId	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**	 * @return goodsId	 */
	public int getGoodsId() {
		return goodsId;
	}
	/*	*ram goodsId セットする goodsId	 */
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	/**	 * @return goodsName	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**	 * @param goodsName セットする goodsName	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**	 * @return itemCount	 */
	public int getItemCount() {
		return itemCount;
	}
	/**	 * @param itemCount セットする itemCount	 */
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	/**	 * @return total	 */
	public int getTotal() {
		return total;
	}
	/**	 * @param total セットする total	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	/**	 * @return createdAt	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**	 * @param createdAt セットする createdAt	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
