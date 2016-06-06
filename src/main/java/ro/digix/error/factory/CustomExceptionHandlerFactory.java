package ro.digix.error.factory;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import ro.digix.error.CustomExceptionHandler;

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	private final ExceptionHandlerFactory parent;

	// this injection handles jsf
	public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {

		final ExceptionHandler handler = new CustomExceptionHandler(parent.getExceptionHandler());

		return handler;
	}

}