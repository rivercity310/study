package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RedirectView implements View {
    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private final String redirectName;

    public RedirectView(String redirectName) {
        this.redirectName = redirectName;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = redirectName.substring(DEFAULT_REDIRECT_PREFIX.length());
        response.sendRedirect(path);
    }
}
