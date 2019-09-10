package com.jingdian.utils;


/**
 *session������
 **/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
 
public class SessionUtil {
	/**
	 * ȫ��ɾ��id��ʾ
	 */
	public static String GLOB_DELETE_ID_VAL = "globDeleteIdVal";
 
	/**
	 * ��ȡrequest
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes == null ? null : requestAttributes
				.getRequest();
	}
 
	/**
	 * ��ȡsession
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession(false);
	}
 
	/**
	 * ��ȡ��ʵ·��
	 * 
	 * @return
	 */
	public static String getRealRootPath() {
		return ((HttpSession) getRequest()).getServletContext().getRealPath("/");
	}
 
	/**
	 * ��ȡip
	 * 
	 * @return
	 */
	public static String getIp() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRemoteAddr();
		}
		return null;
	}
 
	/**
	 * ��ȡsession�е�Attribute
	 * 
	 * @param name
	 * @return
	 */
	public static Object getSessionAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getSession().getAttribute(name);
	}
 
	/**
	 * ����session��Attribute
	 * 
	 * @param name
	 * @param value
	 */
	public static void setSessionAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.getSession().setAttribute(name, value);
		}
	}
 
	/**
	 * ��ȡrequest�е�Attribute
	 * 
	 * @param name
	 * @return
	 */
	public static Object getRequestAttribute(String name) {
		HttpServletRequest request = getRequest();
		return request == null ? null : request.getAttribute(name);
	}
 
	/**
	 * ����request��Attribute
	 * 
	 * @param name
	 * @param value
	 */
	public static void setRequestAttribute(String name, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			request.setAttribute(name, value);
		}
	}
 
	/**
	 * ��ȡ������path
	 * 
	 * @return
	 */
	public static String getContextPath() {
		return getRequest().getContextPath();
	}
 
	/**
	 * ɾ��session�е�Attribute
	 * 
	 * @param name
	 */
	public static void removeSessionAttribute(String name) {
		getRequest().getSession().removeAttribute(name);
	}
 
}