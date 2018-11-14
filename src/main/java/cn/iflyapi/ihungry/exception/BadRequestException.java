package cn.iflyapi.ihungry.exception;

/**
 * @author: qfwang
 * @date: 2018-11-11 7:44 PM
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
