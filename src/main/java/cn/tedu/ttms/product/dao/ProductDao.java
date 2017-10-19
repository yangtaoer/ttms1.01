package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

public interface ProductDao {
	List<Map<String,Object>> findObjects();
	
	/**
	 * 判断此id下是否有子元素
	 * @param id
	 * @return 
	 */
	int hasChilds(Integer id);
	int deleteObject (Integer id);
}
