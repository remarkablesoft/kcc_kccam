package com.remarkablesoft.site.kccam.service.material.model.impl;

import com.remarkablesoft.framework.module.page.PageList;
import com.remarkablesoft.site.kccam.service.material.model.MaterialService;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialCnd;
import com.remarkablesoft.site.kccam.service.material.vo.MaterialInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	protected MaterialBLO materialBLO;
	
	public MaterialInfo update( MaterialInfo info ) {
		return materialBLO.update( info );
	}
	
	public MaterialInfo get( MaterialCnd cnd ) {
		return materialBLO.get( cnd );
	}
	
	public List<MaterialInfo> listAll( MaterialCnd cnd ) {
		return materialBLO.listAll( cnd );
	}
	
	@Override
	public PageList<MaterialInfo> list(MaterialCnd cnd) {
		return materialBLO.list(cnd);
	}
	
	@Override
	public List<MaterialInfo> menuList( MaterialCnd cnd ) {
		return materialBLO.menuList( cnd );
	}
	
}
