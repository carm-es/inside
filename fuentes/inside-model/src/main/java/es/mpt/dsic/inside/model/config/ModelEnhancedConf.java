package es.mpt.dsic.inside.model.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//CARM ### v2.0.7.1
public class ModelEnhancedConf implements ApplicationContextAware {

	private static final String BEAN_NAME="modelEnhancedConf";	
	private static ApplicationContext ac;
	
	private boolean magicMatchNotFoundExceptionTryAscii=false;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.ac = ac;
	}
	
	public boolean isMagicMatchNotFoundExceptionTryAscii() {
		return this.magicMatchNotFoundExceptionTryAscii;
	}
	public void setMagicMatchNotFoundExceptionTryAscii(boolean magicMatchNotFoundExceptionTryAscii) {
		this.magicMatchNotFoundExceptionTryAscii = magicMatchNotFoundExceptionTryAscii;
	}
	public static boolean isActivoMagicMatchNotFoundExceptionTryAscii() {
		return ((ModelEnhancedConf) ac.getBean(BEAN_NAME)).magicMatchNotFoundExceptionTryAscii;
	}	
	
}
//CARM 2.0.7.1 ###