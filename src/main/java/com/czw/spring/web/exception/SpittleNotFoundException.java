package com.czw.spring.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 将异常映射为http状态码
 * @author Zevi Chan
 * @Date 2016年7月1日
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND,
		reason="Spittle Not Found")
public class SpittleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8491398388451027686L;

}
