package jp.co.internous.ecsite.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.internous.ecsite.model.dto.HistoryDto;

@Mapper
public interface TblPurchaseMapper {
	
	List<HistoryDto> findHistory(int userId);
	
	//Mapperのメソッドが呼ばれるとSpringBootの機能により自動的にSQLが実行される
	//MyBatisではSQLをMapper.xmlに書くこともできる。
	//@Update("DELETE FROM mst_goods WHERE id = #{ id }")
	int insert(int userId,int goodsId,String goodsName, int itemCount,int total);

	/*
	 * ORMapperフレームワーク：ObjectRelationMapperの略で、javaオブジェクトとDBカラムをマッピングする。
	 * ORMapperフレームワーク「MyBatis」はSQLの記述を、JavaファイルとXMLファイル両方に書けます。
	 * 以下の理由の場合は、XMLファイルにSQLを書くほうが良いです。
	 * ・SQL分が複数行になる場合
	 * ・DBテーブルを結合する必要がある場合
	 * 
	 *XMLファイル置き場は、Mapper.javaがおかれているパッケージ名と完全一致する場所。
	 *「/ecsite/src/main/resources」配下に作成
	 * */
	
}
