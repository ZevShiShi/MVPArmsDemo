package zhu.zev.com.mvpsample.mvp.model.entity;

import java.util.List;

/**
 * Created by zev on 2018/11/29.
 */

public class BaseArrayBean<T> {

    /**
     * status : 1
     * msg : 获取成功
     * result : {} 对象
     */
    private int errorCode;
    private String errorMsg;
    private List<T> result;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
