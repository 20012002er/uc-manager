package com.jia16.uc.controller;

import com.jia16.uc.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by lazyb on 2017/7/7.
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index");
        view.addObject("now", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, RedirectAttributes redirectAttributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            logger.info("用户{}进行登录验证", user.getPhone());
            currentUser.login(token);
            logger.info("用户{}验证通过", user.getPhone());
        } catch (AccountException e) {
            logger.info("用户{}验证失败", user.getPhone());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        if (currentUser.isAuthenticated()) {
            return "redirect:/index";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "403";
    }

}
