package zhu.zev.com.mvpsample.mvp.model.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import zhu.zev.com.mvpsample.mvp.model.entity.BaseBean;
import zhu.zev.com.mvpsample.mvp.model.entity.LoginBean;

public interface UserService {


    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseBean<LoginBean>> login(@Field("username") String username
            , @Field("password") String pwd);

}
