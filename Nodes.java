package com.jingdian.utils;

import java.util.List;
import javax.xml.soap.Node;

/**
 *  ���β˵��ĵ� - layui.tree�������ݸ�ʽ
 */
public class Nodes {
	private String id; //�˵�id
	
	private String name; //�ڵ�����
	
	private boolean spread = false; //�Ƿ�չ��״̬��Ĭ��false��
	
	private String href; //�ڵ����ӣ���ѡ����δ���򲻻���ת
	
	private String menuIcon; //�˵�ͼ��
	
	private String superId; //�����˵�id
	
	private String superName; //�����˵�����
	
	private List<Nodes> children; //ͬnodes�ڵ㣬����������

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
