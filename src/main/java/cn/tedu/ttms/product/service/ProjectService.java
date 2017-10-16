package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.project.Project;
/**项目管理业务层接口对象
 * 1）负责业务验证
 * 2）负责事务处理
 * 3）负责日志处理
 * 4）负责缓存处理
 * 5）负责权限处理
 * @author admin
 *
 */
public interface ProjectService {
	/**查询所有项目信息*/
      List<Project> findObjects();  
      
      
      /**
       * @param pageCurrent 当前页
       * @return 获取当前页数据及分页信息
       */
     Map<String,Object> findPageObjects(int pageCurrent,String name,Integer valid);
      
     
     /**
      * 
      * @param valid 禁用/启用状态
      * @param ids 由多个id构成的字符串
      */
     void validById(Integer valid,String ids);
}
