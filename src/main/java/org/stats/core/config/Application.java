package org.stats.core.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
	private AnnotationConfigApplicationContext context;
	private static Application instance;
	
	public static Application getInstance() {
		if(null == instance) {
			instance = new Application();
		}
		return instance;
	}

	private Application() {}
	
	public void init() {
		this.context = new AnnotationConfigApplicationContext();
		this.context.register(ApplicationConfiguration.class);
		this.context.refresh();
	}
	
	public <T> T getBean(Class<T> clazz) {
		return this.context.getBean(clazz);
	}
	
	public void exit() {
		this.context.close();
		System.exit(0);
	}
	
}
