package com.remarkablesoft.framework.web.controller;

import com.remarkablesoft.framework.module.log.CommonLogger;
import com.remarkablesoft.framework.module.log.CommonLoggerFactory;

/**
 * 설명 : 기본 컨트롤러
 * 
 * @author james
 * @since 2014. 5. 14.
 *
 */
public class BaseController
{

	/** 컴포넌트의 로깅을 처리하는 객체. */
	private CommonLogger logger = CommonLoggerFactory.getLogger( BaseController.class );

	/**
	 * 로깅 처리를 위한 Log 객체 반환.
	 *
	 * @return log 객체
	 */
	protected CommonLogger log()
	{
		return logger;
	}
}
