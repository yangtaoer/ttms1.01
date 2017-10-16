package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.project.Project;

/**项目持久层对象*/
public interface ProjectDao {
	
	/**查询所有项目信息*/
	List<Project> findObjects();
	
	
	/**
	 * startIndex 分页查询时的起始位置
	 * pageSize每页最多显示多少条记录
	 * @param startIndex
	 * @param pageSize
	 * @Param("name") 查询时用户输入的项目名
	 * @Param("valid")查询时用户输入的状态
	 * 
	 * @return当前页数据
	 */
	//dao中的方法参数多于1个时要使用@Param注解定义参数
	List<Project> findPageObjects(
			@Param("name")String name,
			@Param("valid")Integer valid,
			@Param("startIndex") int startIndex,
			@Param("pageSize") int pageSize);
	
	
	/**
	 * 返回记录总数
	 * @return
	 */
	int getRowCount(
			@Param("name")String name,
			@Param("valid")Integer valid
			);
	
	/**
	 * 禁用/启用项目信息
	 * @param valid
	 * @param ids
	 * @return
	 */
	int validById(@Param("valid")Integer valid,
			      @Param("ids")String[] ids);

}
