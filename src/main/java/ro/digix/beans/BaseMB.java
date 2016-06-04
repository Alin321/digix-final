package ro.digix.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ro.digix.controllers.BaseController;

public class BaseMB {
	protected <T> T findBean(String beanName) {
		return BaseController.findBean(beanName);
	}

	protected String getRedirectedURL(String baseURL) {
		return BaseController.getRedirectedURL(baseURL);
	}
	
	public static void addMessageToComponent(String componentId, String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(componentId, new FacesMessage(message));
	}
}
