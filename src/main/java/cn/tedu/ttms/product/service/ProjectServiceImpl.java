package cn.tedu.ttms.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.project.Project;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDao projectDao;
     
	@Override
	public List<Project> findObjects() {
		
		List<Project> list = projectDao.findObjects();
		return list;
	}

	@Override
	public Map<String, Object> findPageObjects(int pageCurrent,String name,Integer valid) {
		
		int pageSize = 2;
		int startIndex = (pageCurrent-1)*pageSize;
		//获取当前页信息
		List<Project> list = projectDao.findPageObjects(name,valid,startIndex, pageSize);
		
		//获取总记录数并封装分页信息
		int rowCount = projectDao.getRowCount(name, valid);
		PageObject pageObject = new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		
		//将当前页信息以及分页信息封装到map
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject",pageObject);
		
		return map;
	}

	@Override
	public void validById(Integer valid, String ids) {
		//1.对数据进行业务验证
		if(valid!=0&&valid!=1)throw new ServiceException("valid值不合法:valid只能为0或1");
		if(StringUtils.isEmpty(ids))throw new ServiceException("ids的值不能为空");//ids==null||ids==""
		//2.对参数进行数据处理	
		String[] idArray = ids.split(",");
		//3.执行业务更新操作
		int rows = projectDao.validById(valid, idArray);
		if(rows==0)throw new ServiceException("修改失败");
	}

	@Override
	public void saveObject(Project entity) {
		if(entity==null) {
			throw new ServiceException("保存对象不能为空!");
		}
		int row = projectDao.insertObject(entity);
		if(row<=0) {
			throw new ServiceException("保存失败!");
		}
	}
	
	@Override
	public  Project findObjectById(Integer id) {
		if(id==null) {
			throw new ServiceException("id不能为空!");
		}
		Project p = projectDao.findObjectById(id);

		if(p==null) {
			throw new ServiceException("对象不存在!");
		}
		return p;
	}
	
	@Override
	public void updateById(Project project) {
		
		Project p = findObjectById(project.getId());
		int row = projectDao.updateObject(project);
	
		if(row<=0) {
			throw new ServiceException("保存失败!");
		}
		
	}

}
