package com.remarkablesoft.site.kccam.service.newsroom.model;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomCnd;
import com.remarkablesoft.site.kccam.service.newsroom.vo.NewsroomInfo;

import java.util.List;

public interface NewsroomService {
		
		NewsroomInfo insertOrUpdate( NewsroomInfo info );
		
		int deleteFlagUpdate( NewsroomInfo info );
		
		NewsroomInfo get( NewsroomCnd cnd );
		
		NewsroomInfo getWithPrevAndNext( NewsroomCnd cnd );
		
		NewsroomInfo viewWithPrevAndNext( NewsroomCnd cnd );
		
		PageList<NewsroomInfo> list( NewsroomCnd cnd );
		
		List<NewsroomInfo> listAll( NewsroomCnd cnd );
		
}
