package cn.tedu.ttms.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.TeamDao;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamDao teamDao;
	
	
	
	public Map<String, Object> findPageObjects(String name, int pageCurrent) {
		//1.获取当前页数据
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<Map<String,Object>> list = teamDao.findPageObjects(name, startIndex, pageSize);
		
		//2.计算并封装分页信息
		PageObject p = new PageObject();
		int rowCount = teamDao.getRowCount(name);
		p.setRowCount(rowCount);
		p.setPageCurrent(pageCurrent);
		p.setStartIndex(startIndex);
		p.setPageSize(pageSize);
		//3.封装当前页数据,及分页信息
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", p);
		return map;
	}

}
