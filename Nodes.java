package com.jingdian.utils;

import java.util.List;
import javax.xml.soap.Node;

/**
 *  树形菜单文档 - layui.tree返回数据格式
 */
public class Nodes {
	private String id; //菜单id
	
	private String name; //节点名称
	
	private boolean spread = false; //是否展开状态（默认false）
	
	private String href; //节点链接（可选），未设则不会跳转
	
	private String menuIcon; //菜单图标
	
	private String superId; //父级菜单id
	
	private String superName; //父级菜单名称
	
	private List<Nodes> children; //同nodes节点，可无限延伸

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getSuperName() {
		return superName;
	}

	public void setSuperName(String superName) {
		this.superName = superName;
	}

	public List<Nodes> getChildren() {
		return children;
	}

	public void setChildren(List<Nodes> children) {
		this.children = children;
	}

}
