package com.remarkablesoft.site.kccam.service.classification.model.impl;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.classification.model.ClassificationService;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationCnd;
import com.remarkablesoft.site.kccam.service.classification.vo.ClassificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ClassificationServiceImpl implements ClassificationService {

	@Autowired
	protected ClassificationBLO classificationBLO;

	@Override
	public ClassificationInfo insert( ClassificationInfo info ) {
		return classificationBLO.insert( info );
	}

	@Override
	public ClassificationInfo get( ClassificationCnd cnd ) {
		return classificationBLO.get( cnd );
	}

	@Override
	public PageList<ClassificationInfo> list( ClassificationCnd cnd ) {
		return classificationBLO.list( cnd );
	}

	@Override
	public List<ClassificationInfo> listAll( ClassificationCnd cnd ) {
		return classificationBLO.listAll( cnd );
	}

	@Override
	public List<ClassificationInfo> listAllTreeChildren(ClassificationCnd cnd) {

		return classificationBLO.listAllTreeChildren( cnd );
	}

	@Override
	public List<ClassificationInfo> sameMaterialList( ClassificationCnd cnd ) {
		return classificationBLO.sameMaterialList( cnd );
	}

	@Override
	public Map<String, List<ClassificationInfo>> getBreadcrumbMap( ClassificationCnd cnd ) {
		return classificationBLO.getBreadcrumbMap( cnd );
	}

	@Override
	public ClassificationInfo getInfoIncludeProduct( ClassificationCnd cnd ) {
		return classificationBLO.getInfoIncludeProduct( cnd );
	}

	@Override
	public ClassificationInfo update(ClassificationInfo info) {
		return classificationBLO.update( info );
	}

    @Override
    public int delete( ClassificationCnd cnd ) {

    		return classificationBLO.delete( cnd );
    }


}
