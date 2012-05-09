package jcf.sua.mvc;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jcf.sua.SuaConstants;
import jcf.sua.mvc.view.MciView;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author nolang
 * View 이름으로부터 사용할 view object를 찾아주는 resolver.
 */
public class MciViewResolver implements ViewResolver, Ordered {

	private static final Logger logger = LoggerFactory.getLogger(MciViewResolver.class);

	private Map<String, View> viewMap;
	private View defaultView;
	private int order;

	private InternalResourceViewResolver pageViewResolver;

	public MciViewResolver() {
		this.viewMap = new HashMap<String, View>();
		this.viewMap.put(SuaConstants.STREAMING, new MciView());
	}
	/**
	 * MciContext로부터 modelAndView를 얻어온 후 null이라면 default값인 MciView를,<br/>
	 * 값이 있다면 pageViewResolver가 돌려주는 view object를 return
	 */
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		View view = null;

		if (viewMap != null) {
			view = viewMap.get(viewName);
		}

		if(view == null){
			if (MciRequestContextHolder.get().isMciRequest()) {
				ModelAndView modelAndView = MciRequestContextHolder.get().getDataSetAccessor().getModelAndView();
				
				if(modelAndView == null){
					view = defaultView;
				} else if(viewName != null){
					view = pageViewResolver.resolveViewName(viewName, locale);
				} else {
					view = pageViewResolver.resolveViewName(modelAndView.getViewName(), locale);
				}
			} else {
				if(pageViewResolver != null){
					view = pageViewResolver.resolveViewName(viewName, locale);
				}
			}
		}

		if(logger.isDebugEnabled()){
			logger.trace("[JCF-SUA] {} 뷰생성 작업을 수행합니다. : ViewClass={}, ViewName={}", new Object[]{MciRequestContextHolder.get().getMciChannelType(), view.getClass(), viewName});
		}

		return view;
	}

	/**
	 * View resolver가 여러 개일 때 적용순서를 return
	 */
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setDefaultView(View defaultView) {
		this.defaultView = defaultView;
	}

	public void setViewMap(Map<String, View> viewMap) {
		this.viewMap = viewMap;
	}

	public void setPageViewResolver(
			InternalResourceViewResolver pageViewResolver) {
		this.pageViewResolver = pageViewResolver;
	}
}
