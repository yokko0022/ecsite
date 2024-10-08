package jp.co.internous.ecsite.model.domain;

import java.sql.Timestamp;

public class MstGoods {
		
	private int id;
	private String goodsName;
	private int price;
	private Timestamp createdAt; //import必要「java.sql.Timestamp」
	private Timestamp updatedAt; //import必要「java.sql.Timestamp」
	
	/**	 * @return id	 */
	public int getId() {
		return id;
	}
		/**	 * @param id セットする id	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**	 * @return goodsName	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**	 * @param goodsName セットする goodsName	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**	 * @return price	 */
	
	public int getPrice() {
		return price;
	}
	/**	 * @param price セットする price	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**	 * @return createdAt	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**	 * @param createdAt セットする createdAt	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	/**	 * @return updatedAt	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	/**	 * @param updatedAt セットする updatedAt	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
