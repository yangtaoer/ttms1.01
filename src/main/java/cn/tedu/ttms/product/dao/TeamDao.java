package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeamDao {
	List<Map<String,Object>> findPageObjects(@Param("name") String name,
											@Param("startIndex") Integer startIndex,
											@Param("pageSize") Integer pageSize);
	int getRowCount(@Param("name")String name);
}
