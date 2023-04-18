export const URL_CONSTANT = {

	// 메뉴 URL 접두사
	MENU_URL_PREFIX : {
		USER_INTRO            : "/kccam/user/intro/",
		USER_PRODUCT          : "/kccam/user/product/",
		USER_MARKET           : "/kccam/user/market/",
		USER_PRODUCT_SUPPORT  : "/kccam/user/productSupport/",
		USER_CUSTOMER_SUPPORT : "/kccam/user/customerSupport/",

        // MANAGER
		MANAGER_PRODUCT_MGNT : "/kccam/manager/productMgnt/",
        MANAGER_MARKET_MGNT: "/kccam/manager/marketMgnt/",
        MANAGER_DOC_MGNT: "/kccam/manager/documentMgnt/",
        MANAGER_CUSTOMER_SUPPORT_MGNT: "/kccam/manager/customerSupportMgnt/",
	},

	// 메뉴 URL 접미사
	MENU_URL_SUFFIX : {
		INTRO            : {
			KCC            : "intro_kcc?depth=1",
			AM             : "intro_am?depth=1",
			GLOBAL_NETWORK : "intro_globalNetwork?depth=1",
			NEWSROOM       : {
				LIST : "newsroom/newsroom_list?depth=1",
                VIEW: "newsroom/newsroom_view?depth=1",
            },
		},
		PRODUCT          : {
			MAIN                : "product_main?depth=0",
			MATERIAL_VIEW       : "product_materialView",
			CLASSIFICATION_VIEW : "product_classificationView",
			APPLICATION_VIEW    : "product_applicationView",
            PRODUCT_VIEW: "product_view",
		},
		MARKET           : {
			MAIN          : "market_main?depth=0",
			MARKET_LIST   : "market/market_list?depth=1&classification=market",
			MARKET_VIEW   : "market/market_view",
            MARKET_EDIT: "market/market_edit",

			FUNCTION_LIST : "function/function_list?depth=1&classification=function",
			FUNCTION_VIEW : "function/function_view?depth=2",
			FUNCTION_EDIT : "function/function_edit",
		},
		PRODUCT_SUPPORT  : {
			INTEGRATED_SEARCH : "productSupport_integratedSearch?depth=1",
			TECHNICAL_SUPPORT : "productSupport_technicalSupport?depth=1",
            PRODUCT_CATALOG: "productSupport_productCatalog?depth=1",
		},
		CUSTOMER_SUPPORT : {
			INQUIRY : "customerSupport_inquiry?depth=1",
            CONTACT: "customerSupport_contact?depth=1",
		},

        // MANAGER
		PRODUCT_MGNT : {
			MATERIAL : "materialClassification/materialClassification_list",
			MATERIAL_EDIT : "materialClassification/materialClassification_edit",
			PRODUCT_CLASSIFICATION : "productClassification/productClassification_list",
			PRODUCT_CLASSIFICATION_EDIT : "productClassification/productClassification_edit",
			PRODUCT_CLASSIFICATION_ALLVIEW: "productClassification/productClassification_allView",

            PRODUCT_LIST: "productView/productView_list",
			PRODUCT_EDIT : "productView/productView_edit",
            APPLICATION_LIST : "application/application_list",
			APPLICATION_EDIT : "application/application_edit",
            DATASHEET: "datasheet/datasheet_list",

        },
        MARKET_MGNT: {
            MARKET: "market/market_list",
            FUNCTION: "function/function_list",
        },
        DOC_MGNT: {
            ATTACHMENT: "attachment/attachment_list",
	        ATTACHMENT_EDIT : "attachment/attachment_edit",
            CATALOG: "catalog/catalog_list",
	        DOC_SHARE_AUDIT: "docShareAudit/docShareAudit_list",
	        HIST_VISIT : "histVisit/histVisit_list",
        },
        CUSTOMER_SUPPORT_MGNT: {
            PERSONAL: "personalEnquiry/personalEnquiry_list",
	        PERSONAL_EDIT : "personalEnquiry/personalEnquiry_view",
            CONTACT: "contact/contact_list",
            CONTACT_OFFICE_EDIT : "contact/contactOffice_edit",
            CONTACT_MANAGER_EDIT : "contact/contactManager_edit",

            INFORMATION : "information/information_list",
            INFORMATION_EDIT : "information/information_edit",
            SEND_MAIL: "sendGroupMail/sendGroupMail_view",
            NEWSROOM: "newsRoom/newsRoom_list",
			NEWSROOM_EDIT: "newsRoom/newsRoom_edit",
        },
	},

	// API URL 접두사
	API_URL_PREFIX : {
		MATERIAL       : "/kccam/api/material/",
		CLASSIFICATION : "/kccam/api/classification/",
		DOC            : "/kccam/api/doc/",
		PRODUCT        : "/kccam/api/product/",
		DATASHEET      : "/kccam/api/datasheet/",

		BRANCH : "/kccam/api/branch/",
		ONE_TO_ONE : "/kccam/api/onetoone/",
		ONE_TO_ONE_CONFIG : "/kccam/api/onetooneconfig/",
		POSTING : "/kccam/api/posting/",
		INTEGRATED_SEARCH : "/kccam/api/integratedSearch/",

		USER : "/kccam/api/user/",
		HIST_VISIT : "/kccam/api/histVisit/",
		AUDIT_VIEW : "/kccam/api/auditView/",
		PART : "/kccam/api/part/",
		LOGIN_API : "/kccam/api/loginApi/",
		EXCEL: "/kccam/api/excel/",

		DOC_SHARE_AUDIT: "/kccam/api/docShareAudit/",
		NEWSROOM: "/kccam/api/newsroom/",

		POPUP: "/kccam/api/popup/"
	},

	// API URL 접미사
	API_URL_SUFFIX : {
		MATERIAL       : {
			UPDATE : "material_update",
			GET       : "material_get",
			LIST	  : "material_list",
			LIST_ALL  : "material_listAll",
			MENU_LIST : "material_menuList",
		},
		CLASSIFICATION : {
            INSERT: "classification_insert",
            UPDATE: "classification_update",
            //DELETE_FLAG_UPDATE: "classification_deleteFlagUpdate", // 다국어하면서 삭제처리로 변경
			DELETE: "classification_delete",
			GET                : "classification_get",

			LIST               : "classification_list",
			LIST_ALL           : "classification_listAll",
			LIST_ALL_TREE_CHILDREN : "classification_listAllTreeChildren",
			SAME_MATERIAL_LIST : "classification_sameMaterialList",
			GET_BREADCRUMB_MAP : "classification_getBreadcrumbMap",

			GET_INFO_INCLUDE_PRODUCT : "classification_getInfoIncludeProduct",
		},
		PRODUCT : {
			INSERT : "product_insert",
			UPDATE : "product_update",
			GET : "product_get",
			LIST : "product_list",
			LIST_ALL : "product_listAll",

			VIEW_LIST_All : "product_viewListAll",
			TARGET_EXCEPT_LIST : "product_targetExceptList",
			ADD_VIEW_CNT  : "product_addViewCnt",
			REMOVE : "product_remove",
		},
		BRANCH : {
			GET : "branch_get",
			SAVE : "branch_save",
			LIST : "branch_list",
			LIST_ALL : "branch_listAll",
			UPDATE : "branch_update",
			DELETE : "branch_delete",
		},
		ONE_TO_ONE : {
			SAVE : "onetoone_save",
			LIST : "onetoone_list",
			GET : "onetoone_get",
			SEND_MAIL : "onetoone_sendEmail",
		},
		ONE_TO_ONE_CONFIG : {
			SAVE : "onetooneconfig_save",
			DELETE : "onetooneconfig_delete",
			DELETE_FLAG_UPDATE : "onetooneconfig_delete_flag",
			LIST_ALL: "onetooneconfig_listAll",
			LIST_ALL_MGMT : "onetooneconfig_listAll_mgmt",
		},
		POSTING : {
			INSERT_OR_UPDATE : "posting_insertOrUpdate",
			GET : "posting_get",
			GET_WITH_PREV_AND_NEXT : "posting_getWithPrevAndNext",
			VIEW_WITH_PREV_AND_NEXT :  "posting_viewWithPrevAndNext",
			LIST : "posting_list",

			DELETE : "posting_delete",
		},
		INTEGRATED_SEARCH : {
			PRODUCT_LIST : "integratedSearch_productList",
			APPLICATION_LIST : "integratedSearch_applicationList",
			MARKET_LIST : "integratedSearch_marketList",
			FUNCTION_LIST : "integratedSearch_functionList",
		},
		USER : {
			INSERT : "user_insert",
			GET : "user_get",
			UPDATE : "user_update",
			DELETE : "user_delete",
			LIST : "user_list",

			LIST_ALL : "user_listAll"
		},
		DATASHEET : {
			LIST : "datasheet_list",
		},
		HIST_VISIT : {
			INSERT : "histVisit_inset",
			LIST : "histVisit_list",
		},
		AUDIT_VIEW : {
			GROUP_BY_COUNT_LIST : "auditView_groupByCountList",
		},
		PART : {
			LIST_ALL : "part_listAll",
		},
		LOGIN_API : {
			LOGIN : "loginApi_login",
		},
		DOC : {
			GET: "doc_get",
			SAVE:"doc_save",
			DELETE: "doc_delete",
			LIST : "doc_list",
			LIST_ALL : "doc_listALl",
			EXIST_FILE : "doc_existFile",
			USER_REGISTER_AND_SEND_EMAIL_WITH_DOCFILE : "doc_userRegisterAndSendEmailWithDocFile",
			USER_REGISTER_AND_SEND_EMAILS_WITH_DOCFILE : "doc_userRegisterAndSendEmailsWithDocFile",
			DOC_SEND_EMAIL_WITH_DOCLINK : "doc_sendEmailWithDocLink",
		},
		EXCEL : {
			MATERIAL_LIST : "excel_materialList",
			MAKE_WITH_JSON: "excel_makeWithJson",
		},
		DOC_SHARE_AUDIT : {
			INSERT  : "docShareAudit_insert",
			LIST    : "docShareAudit_list",
		},
		NEWSROOM: {
			INSERT_OR_UPDATE        : "newsroom_insertOrUpdate",
			DELETE_FLAG_UPDATE      : "newsroom_deleteFlagUpdate",
			GET                     : "newsroom_get",
			VIEW_WITH_PREV_AND_NEXT : "newsroom_viewWithPrevAndNext",
			LIST                    : "newsroom_list",
		},
		POPUP : {
			OPERATION_LIST : "popupApi_operationList",
			SAVE           : "popupApi_save",
			GET            : "popupApi_get",
			DELETE         : "popupApi_delete"

		},
    },

	KCC_SFA_URL : {
		ITEM_SEARCH : "https://smartbook.kccworld.info/smb2/search/searchProduct_itemSearch.json",
		REL_DOC : "https://smartbook.kccworld.info/smb2/product/productDetail_relDocList.json",
	}
};

