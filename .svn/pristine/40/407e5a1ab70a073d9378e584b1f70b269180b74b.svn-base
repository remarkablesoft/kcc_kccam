package com.remarkablesoft.site.kccam.service.newsroom.model.impl;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.newsroom.model.NewsroomService;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * <pre>
 * 설명 : 뉴스룸 서비스 구현 객체
 * </pre>
 *
 * @author zero
 * @since 2021. 6. 17.
 *
 */
@Service
@Transactional
public class NewsroomServiceImpl implements NewsroomService {
		
		@Autowired
		protected NewsroomBLO newsroomBLO;
		
		@Override
		public NewsroomInfo insertOrUpdate( NewsroomInfo info ) {
				return newsroomBLO.insertOrUpdate( info );
		}
		
		@Override
		public int deleteFlagUpdate( NewsroomInfo info ) {
				return newsroomBLO.deleteFlagUpdate( info );
		}
		
		@Override
		public NewsroomInfo get( NewsroomCnd cnd ) {
				return newsroomBLO.get( cnd );
		}
		
		@Override
		public NewsroomInfo getWithPrevAndNext( NewsroomCnd cnd ) {
				return newsroomBLO.getWithPrevAndNext( cnd );
		}
		
		@Override
		public NewsroomInfo viewWithPrevAndNext( NewsroomCnd cnd ) {
				return newsroomBLO.viewWithPrevAndNext( cnd );
		}
		
		@Override
		public PageList<NewsroomInfo> list( NewsroomCnd cnd ) {
				return newsroomBLO.list( cnd );
		}
		
		@Override
		public List<NewsroomInfo> listAll( NewsroomCnd cnd ) {
				return newsroomBLO.listAll( cnd );
		}
}
