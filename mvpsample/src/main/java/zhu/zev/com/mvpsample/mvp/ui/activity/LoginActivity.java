package zhu.zev.com.mvpsample.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import zhu.zev.com.mvpsample.R;
import zhu.zev.com.mvpsample.di.component.DaggerLoginComponent;
import zhu.zev.com.mvpsample.mvp.contract.LoginContract;
import zhu.zev.com.mvpsample.mvp.model.entity.BaseBean;
import zhu.zev.com.mvpsample.mvp.model.entity.LoginBean;
import zhu.zev.com.mvpsample.mvp.presenter.LoginPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


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
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private static final String TAG = "LoginActivity";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {
        runOnUiThread(()->ToastUtils.showShort("Loading…………"));
    }

    @Override
    public void hideLoading() {
        ToastUtils.showShort("Hide loading……………………");
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @OnClick(R.id.btnLogin)
    public void onLoginClick(View view) {
        if (ObjectUtils.isNotEmpty(etUsername.getText())
                &&ObjectUtils.isNotEmpty(etPwd.getText())) {
            mPresenter.login(etUsername.getText().toString(), etPwd.getText().toString());
        }
    }

    @Override
    public void onLoginSuccess(BaseBean<LoginBean> data) {
//        ToastUtils.showShort(data.toString());
        LogUtils.dTag(TAG, data.toString());

    }
}
