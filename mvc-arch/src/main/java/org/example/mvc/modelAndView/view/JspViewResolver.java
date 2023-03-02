package org.example.mvc.modelAndView.view;

public class JspViewResolver implements ViewResolver {
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(RedirectView.DEFAULT_REDIRECT_PREFIX))
            return new RedirectView(viewName);

        return new JspView(viewName + ".jsp");
    }
}
