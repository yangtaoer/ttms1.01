package cn.tedu.ttms.product.service;

import java.util.Map;

public interface TeamService {
	/**
	 * 分页查询显示数据
	 * @param name
	 * @param pageCurrent
	 * @return 当前页数据和分页信息
	 */
	Map<String,Object> findPageObjects(String name,int pageCurrent);
}
