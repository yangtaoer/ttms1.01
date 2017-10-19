package cn.tedu.ttms.common.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


//@Alias("node")   取一个别名
public class Node implements Serializable{
	/**
	 * Node
	 * 1)为一个值对象
	 * 2)用于封装树节点相关信息
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer parentId;
	private String name;
	
	public Node() {
		
	}
	public Node(Integer id, Integer parentId, String name) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Node [id=" + id + ", parentId=" + parentId + ", name=" + name + "]";
	}
	
}
