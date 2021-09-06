package cn.silently9527.crocodile.config.security;

import com.gitee.starblues.grape.core.security.CurrentUserService;
import com.gitee.starblues.grape.core.security.model.UserDetailsModel;
import com.gitee.starblues.grape.repository.databases.entity.User;
import com.gitee.starblues.grape.utils.HttpUtils;
import com.gitee.starblues.grape.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * 授权成功监听者
 * @author starBlues
 * @version 1.0
 */
@Component
public class AuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final ExecutorService executorService;
    private final CurrentUserService currentUserService;

    public AuthSuccessListener(@Qualifier("system") ExecutorService executorService,
                               CurrentUserService currentUserService) {
        this.executorService = executorService;
        this.currentUserService = currentUserService;
    }


    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent auth) {
        String ip = HttpUtils.getRemoteAddress();
        Object principal = auth.getAuthentication().getPrincipal();
        if(principal instanceof UserDetailsModel){
            UserDetailsModel userDetailsModel = (UserDetailsModel) principal;
            User user = userDetailsModel.getUser();
            if(user == null){
                return;
            }
            executorService.execute(()->{
                currentUserService.loginSuccess(user.getUserId(), user.getUsername(),
                        ip, TimeUtil.getNowTimeStamp());
            });
        }
    }
}
