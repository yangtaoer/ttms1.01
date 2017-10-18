package productService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.service.ProjectService;
import cn.tedu.ttms.project.Project;

public class TestProjectService {
	    ClassPathXmlApplicationContext ctx;
	
        @Before
        public void init(){
        	ctx = new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");       	
        }
        
        @Test
        public void testFindObjects(){
        	//1.获得ProjectService对象
        	ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);//应该是实现类的名字,而且默认首字母小写
        	//2.执行ProjectService对象的findObject方法
        	List<Project> list = projectService.findObjects();
        	//3.验证结果是否正确
        	Assert.assertNotEquals(0, list.size());
        	//4.输出执行结果
        	System.out.println(list);
        }
        
        
        @Test
        public void testFindPageObjects(){
        	//获得projectService对象
        	ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
        	
        	//分页查询数据
        	Map<String,Object> map = projectService.findPageObjects(1,"环球",1);//假设当前页面为2
        	List<Project> list = (List<Project>) map.get("list");
        	PageObject pageObject = (PageObject) map.get("pageObject");
        	
        	//对数据进行模拟测试
        	Assert.assertEquals(1, list.size());
        	Assert.assertEquals(1, pageObject.getPageCount());
        	System.out.println(list);
        }
        
        
        @Test
        public void testValidById(){
        	//获得projectService对象
        	ProjectService projectService = ctx.getBean("projectServiceImpl",ProjectService.class);
        	Integer valid = 1;
        	String ids="1,3,4";
        	projectService.validById(valid, ids);        	
        }
        
        @Test
        public void testSaveObject(){
        	ProjectService ps = ctx.getBean("projectServiceImpl",ProjectService.class);
        	Project p = new Project();
        	p.setId(5);
        	p.setCode("rrrrr");
        	p.setBeginDate(new Date(System.currentTimeMillis()));
        	p.setCreatedTime(new Date(System.currentTimeMillis()));
        	p.setCreatedUser("user");
        	p.setEndDate(new Date(System.currentTimeMillis()));
        	p.setModifiedTime(new Date(System.currentTimeMillis()));
        	p.setName("火星游");
        	p.setNote("tt-20170901-CN");
        	p.setValid(1);
        	p.setModifiedUser("you");
        	ps.saveObject(p);
        	
        }
        
        @Test
        public void testUpdateObject(){
        	ProjectService ps = ctx.getBean("projectServiceImpl",ProjectService.class);
        	Project p = new Project();
        	p.setId(5);
        	p.setCode("tt-20170809-CN-BJ-001");
        	p.setBeginDate(new Date(System.currentTimeMillis()));
        	p.setCreatedTime(new Date(System.currentTimeMillis()));
        	p.setCreatedUser("user");
        	p.setEndDate(new Date(System.currentTimeMillis()));
        	p.setModifiedTime(new Date(System.currentTimeMillis()));
        	p.setName("火星游");
        	p.setNote("火星游。。。");
        	p.setValid(1);
        	p.setModifiedUser("you");
        	ps.updateById(p);
        	
        }
        
        
        @After
        public void destroy(){
        	ctx.close();
        }
}
