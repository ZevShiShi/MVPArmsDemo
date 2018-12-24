package zhu.zev.com.mvpsample.mvp.presenter;

import android.app.Application;

import com.blankj.utilcode.util.ToastUtils;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import zhu.zev.com.mvpsample.app.utils.RxUtils;
import zhu.zev.com.mvpsample.mvp.contract.LoginContract;
import zhu.zev.com.mvpsample.mvp.model.entity.BaseBean;
import zhu.zev.com.mvpsample.mvp.model.entity.LoginBean;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/24/2018 14:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void login(String username, String pwd) {
        mModel.login(username, pwd)
                .retryWhen(new RetryWithDelay(3, 2))
//                .doOnSubscribe(disposable -> mRootView.showLoading())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doAfterTerminate(() -> mRootView.hideLoading())
//                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseBean<LoginBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean<LoginBean> loginBeanBaseBean) {
                        ToastUtils.showShort(loginBeanBaseBean+"");
                        if (loginBeanBaseBean != null) {
                            mRootView.onLoginSuccess(loginBeanBaseBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        ToastUtils.showShort(t.toString());
                    }
                });

    }
}
