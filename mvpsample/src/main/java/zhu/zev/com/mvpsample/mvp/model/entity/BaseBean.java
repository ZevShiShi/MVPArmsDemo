package zhu.zev.com.mvpsample.mvp.model.entity;

/**
 * Created by zev on 2018/11/29.
 */

public class BaseBean<T> {

    /**
     * status : 1
     * msg : 获取成功
     * result : {} 对象
     */
    private int errorCode;
    private String errorMsg;
    private T data;


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

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", result=" + data +
                '}';
    }
}
