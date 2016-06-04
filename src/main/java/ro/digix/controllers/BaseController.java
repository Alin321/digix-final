package ro.digix.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseController {
	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		final FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}

	public static void addMessageToComponent(String componentId, String message) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(componentId, new FacesMessage(message));
	}

	public static String getRedirectedURL(String baseURL) {
		return baseURL + "?faces-redirect=true";
	}
}
