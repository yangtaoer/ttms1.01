package productService;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.service.TeamService;
import cn.tedu.ttms.project.Team;

public class TestTeamService {
	
	 ClassPathXmlApplicationContext ctx;
	 @Before
     public void init(){
     	ctx = new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");       	
     }
	 
	 @Test
	 public void testTeamPage(){
		 TeamService ts = ctx.getBean("teamServiceImpl",TeamService.class);
		 Map<String,Object> map = ts.findPageObjects("", 1);
		 List<Team> list = (List<Team>) map.get("list");
     	 PageObject pageObject = (PageObject) map.get("pageObject");
     	 System.out.println(list);
	 }
	 
	 
	 
	 @After
     public void destroy(){
     	ctx.close();
     }
}
