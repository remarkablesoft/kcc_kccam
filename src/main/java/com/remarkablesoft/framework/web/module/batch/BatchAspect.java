package com.remarkablesoft.framework.web.module.batch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.remarkablesoft.framework.annotation.Batch;
import com.remarkablesoft.framework.module.log.LogConstants;
import com.remarkablesoft.framework.module.log.LogContext;
import com.remarkablesoft.framework.module.log.LogUtils;
import com.remarkablesoft.framework.service.SystemConstants;
import com.remarkablesoft.framework.service.SystemFactory;
import com.remarkablesoft.framework.service.mgnt.batch.model.BatchProcessService;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessCnd;
import com.remarkablesoft.framework.service.mgnt.batch.vo.BatchProcessInfo;
import com.remarkablesoft.framework.util.DateUtils;


/**
 * 		@주시스템			:  framework-web
 * 		@서브 시스템		:  web-module-batch
 * 		@프로그램 ID		:  BatchAspect
 * 		@프로그램 개요 	:  배치모듈시 @Batch가 붙은 메소드는 DB에 해당 상황을 저장
 *
 * 		@변경이력
 *      ============================================================================
 * 		1.0  2019. 6. 12.	:	james	-	신규생성
 * 		============================================================================
 */
@Aspect
public class BatchAspect {

	private static Logger logger = LoggerFactory.getLogger("BATCH");
	private static Logger errlogger = LoggerFactory.getLogger("ERROR");

	@Autowired
	BatchProcessService batchProcessService;

	@Around( value = "@within(com.remarkablesoft.framework.annotation.Batch) || @annotation(com.remarkablesoft.framework.annotation.Batch)" )
	public Object batchProcess( ProceedingJoinPoint joinPoint ) throws Throwable {

		long lStartTime = System.currentTimeMillis();
		Object result = null;
		boolean isCallSuccess = false;
		Throwable exception = null;
		String batchName = "";

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Batch batch = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getAnnotation( Batch.class );

        boolean isExist = false;

        if ( batch != null ) {

        		// 배치주기가 Day인것은 같은날에 두번이상 입력되는 것을 막음
        		// 만약 같은 시간에 다른 서버가 서로 확인시 아직 완료처리를 하지 않았다면 두번 돌수 있으므로
        		// 먼저 Insert를 하고 난 후 Update 처리하는게 좀 더 나아보임.
        		if ( SystemConstants.BATCH_PERIOD_DAY.getKey().equals( batch.period() )) {

        				batchName = batch.value();
        				String date = DateUtils.getCurrentDate( DateUtils.DF_YYYYMMDD_DASH );
        				
        				logger.error( batchName + " 배치 체크일자 : " + date );
        				
        				BatchProcessCnd cnd = new BatchProcessCnd();
        				cnd.setFromDate( date );
        				cnd.setToDate( date );
        				cnd.setBatchName( batchName );
        				cnd.setExecuteYn( SystemConstants.FLAG_YES );	// 실행여부를 확인해야함

        				isExist = batchProcessService.exist( cnd );

        				if ( isExist) {
       						logger.error(  batchName + "는 이미 실행했기 때문에 Pass" );
        				}

        		}
        }



		try {

			if ( !isExist) {
				result = joinPoint.proceed();
    		}

			isCallSuccess = true;

		}
		catch (Throwable e) {

			exception = e;
			throw e;
		}
		finally {


			String apiName = joinPoint.getSignature().getName();
			String startTime = LogUtils.getDateString(lStartTime);
			String className = LogUtils.getClassName(joinPoint);
			long elapseTime =  new Long(System.currentTimeMillis() - lStartTime);


			BatchProcessInfo info = SystemFactory.getBatchProcessInfo();
			info.setBatchName( batchName );
			info.setClassName( className );
			info.setApiName( apiName );
			info.setThreadName( Thread.currentThread().getName() );
			info.setStartTime( startTime );
			info.setElapsedTime( elapseTime );
			info.setSuccessYn( SystemConstants.FLAG_NO );
			info.setExecuteYn( (isCallSuccess ? SystemConstants.FLAG_YES : SystemConstants.FLAG_NO) );


			int nTotalCnt = 0;
			int nSuccessCnt = 0;

			if ( result instanceof BatchProcessInfo) {

					nTotalCnt = ((BatchProcessInfo) result).getTotalCnt();
					nSuccessCnt = ((BatchProcessInfo) result).getSuccessCnt();

					info.setTotalCnt( nTotalCnt );
					info.setSuccessCnt( nSuccessCnt );
					info.setFailCnt( ((BatchProcessInfo) result).getFailCnt() );
					info.setSuccessYn( ((BatchProcessInfo) result).getSuccessYn() );
					info.setDescr( ((BatchProcessInfo) result).getDescr());
			}
			else if ( result == null) {
					info.setDescr(  batchName + "는 이미 실행했기 때문에 Pass" );
			}
			else {
					info.setDescr( "BatchProcessInfo 객체 사용안함, BatchProcessInfo 사용하도록 배치모듈 변경해야함" );
			}

			batchProcessService.insert( info );


			LogContext.put(LogConstants.LOG_API_StartTime, startTime);
			LogContext.put(LogConstants.LOG_API_Name, apiName);
			LogContext.put(LogConstants.LOG_API_ClassName, className);
			LogContext.put(LogConstants.LOG_API_Params, LogUtils.getParamsString(joinPoint.getArgs()));
			LogContext.put(LogConstants.LOG_API_Result, (isCallSuccess ? LogConstants.LOG_Success : LogConstants.LOG_Fail));
			LogContext.put(LogConstants.LOG_API_ElapseTime, elapseTime);
			LogContext.put(LogConstants.LOG_API_Return, LogUtils.getReturnString(result));
			LogContext.put(LogConstants.LOG_ALREADY_EXIST, (isExist ? "Already Exist" : "New"));
			LogContext.put(LogConstants.LOG_TOTAL_CNT, nTotalCnt);
			LogContext.put(LogConstants.LOG_SUCCESS_CNT, nSuccessCnt);


			if ( !isCallSuccess ) {

				String errorMsg = exception != null ? exception.getMessage() : "";
				LogContext.put(LogConstants.LOG_ErrorMsg, errorMsg);

				errlogger.error("", exception);
			}

			logger.info("");
		}

		return result;
	}
}
