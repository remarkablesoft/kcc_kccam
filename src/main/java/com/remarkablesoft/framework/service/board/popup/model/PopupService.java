package com.remarkablesoft.framework.service.board.popup.model;

import java.time.LocalDate;
import java.util.List;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.framework.service.board.popup.vo.PopupInfo;
import com.remarkablesoft.framework.service.board.popup.vo.PopupCnd;



/**
 *
 * 		@주시스템			:	framework-web
 * 		@서브 시스템		:	board - popup
 * 		@프로그램 ID		:	PopupService
 * 		@프로그램 개요		:	Popup Service
 *
 * 		@변경이력
 *      ============================================================================
 *      1.0 2021. 03. 05. : 이균환 - 신규생성
 *      ============================================================================
 */

public interface PopupService {

	/**
	 * 팝업을 저장합니다.
	 *
	 * @param info
	 * @return
	 */
	public PopupInfo insertOrUpdate( PopupInfo info );

	/**
	 * 팝업을 입력합니다.
	 *
	 * @param info
	 * @return
	 */
    public PopupInfo insert( PopupInfo info );

    /**
     * 팝업을 수정합니다.
     *
     * @param info
     * @return
     */
    public PopupInfo update( PopupInfo info );

    /**
     * 팝업을 삭제합니다.
     *
     * @param oid
     * @return
     */
    public int delete( String oid );

    /**
     * 팝업을 가져옵니다.
     * @param cnd
     * @return
     */
    public PopupInfo get( PopupCnd cnd );

    /**
     * 팝업 페이징 리스트를 가져옵니다.
     *
     * @param cnd
     * @return
     */
    public PageList<PopupInfo> list( PopupCnd cnd );

    /**
     * 팝업 리스트를 가져옵니다.
     *
     * @param cnd
     * @return
     */
    public List<PopupInfo> listAll( PopupCnd cnd );

    /**
     * 팝업 사용유무를 변경합니다.
     *
     * @param info
     * @return
     */
    public PopupInfo updateUseYn( PopupInfo info );



    /**
     * 현재 팝업 정보와 이전, 이후 팝업정보를 같이 가져옵니다.
     *
     * @param cnd
     * @return
     */
    public PopupInfo getWithPrevAndNext( PopupCnd cnd );

    /**
	 * useYn 사용중('Y')이고, 오늘 날짜가 포함된 리스트만 가져오기
	 *
	 * @param
	 * @author sirena
	 * @작성일 2021-09-07
	 **/
	public List<PopupInfo> operationList( PopupCnd cnd );
	
	/**
	 * 노출 기간이 지나면, 팝업 노출 여부를 비노출로 변경합니다.
	 * 배치로직에서 사용
	 *
	 * @param
	 * @author sirena
	 * @작성일 2022-01-20
	 **/
	public int updateNotUseStatusTypeFlagBatch( LocalDate now );
}
