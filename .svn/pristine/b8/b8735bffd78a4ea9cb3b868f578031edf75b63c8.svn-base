package com.remarkablesoft.framework.web.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.remarkablesoft.framework.service.mgnt.category.model.CategoryService;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryCnd;
import com.remarkablesoft.framework.service.mgnt.category.vo.CategoryInfo;
import com.remarkablesoft.framework.web.controller.BaseController;

@RestController
@CrossOrigin
@RequestMapping( "/category" )
public class CategoryAPIController extends BaseController {

		@Autowired
		CategoryService categoryService;

		@RequestMapping( value = "/categoryApi_listAllTreeChilds" )
		public List<CategoryInfo> listAllTreeChilds (@RequestBody CategoryCnd cnd ) throws Exception {

				return categoryService.listAllTreeChilds( cnd );
		}

		@RequestMapping( value = "/categoryApi_listAll" )
		public List<CategoryInfo> listAll (@RequestBody CategoryCnd cnd ) throws Exception {

				return categoryService.listAll( cnd );
		}


		@RequestMapping( value = "/categoryApi_move" )
		public void move( @RequestParam String nodeOid, @RequestParam String targetOid, @RequestParam String direction ) throws Exception {

				categoryService.move(nodeOid, targetOid, direction);
		}

		@RequestMapping( value = "/categoryApi_save" )
		public CategoryInfo save( @RequestBody CategoryInfo categoryInfo ) throws Exception {

				CategoryInfo newCategoryInfo =  categoryService.insertOrUpdate( categoryInfo );

				return newCategoryInfo;
		}


		@RequestMapping( value = "/categoryApi_delete" )
		public void delete( @RequestBody String oid ) throws Exception {

				categoryService.delete( oid );
		}

}
