export const AM_CONSTANT = {

	AM_I18N_COOKIE_KEY : "kccam_i18n",
	OUTER_FILE : "외부파일",

	LANG : {
		KO : "ko",
		EN : "en",
		CN : "cn"
	},

    SEARCH_QUERY_TYPE: {
        TITLE: "FWPOSQ01",
	    TITLE_PRODUCT: "FWPOSQ08",
	    TITLE_OR_TITLE_PRODUCT: "FWPOSQ09",
    },

    YN: {
        Y: "Y",
        N: "N",
    },

    CATEGORY_TYPE: {
        PRODUCT: "AMCFPRDT",
        APPLICATION: "AMCFAPPL",
        MARKET: "AMCFMRKT",
        FUNCTION: "AMCFFUNC",
    },

    CATEGORY_TYPE_MAP: {
        product: "AMCFPRDT",
        application: "AMCFAPPL",
        market: "AMCFMRKT",
        function: "AMCFFUNC",
    },

    ROOT_CATEGORY_OID: {
        PRODUCT: "00000000001",
        APPLICATION: "00000000002",
        MARKET: "00000000003",
        FUNCTION: "00000000004",
    },

    CLASSIFICATION: {
        PRODUCT_CLASSIFICATION: "Product Classification",
        APPLICATION: "Application",
        MARKET: "Market",
        FUNCTION: "Function",
    },

    MENU_NAME: {
        INTRO: {
            TITLE: "KCC 소개",
            KCC: "KCC 소개",
            AM: "KCC Advanced Materials",
            GLOBAL_NETWORK: "글로벌 네트워크",
            NEWSROOM: "뉴스룸",
        },
        PRODUCT: {
            TITLE: "제품",
        },
        MARKET: {
            TITLE: "마켓",
            MARKET_MAIN: "마켓 메인",
            FUNCTION_MAIN: "Function 메인",
        },
        PRODUCT_SUPPORT: {
            TITLE: "제품지원",
            INTEGRATED_SEARCH: "통합검색",
            TECHNICAL_SUPPORT: "기술지원",
            PRODUCT_CATALOG: "제품 카탈로그",
        },
        CUSTOMER_SUPPORT: {
            TITLE: "고객지원",
            INQUIRY: "1:1 문의",
            CONTACT: "Contact",
        },
    },

	MANAGER_MENU_NAME: {
		PRODUCT_MGNT: {
			MATERIAL: "소재구분 관리",
			PRODUCT_CLASSIFICATION: "제품구분 관리",
			PRODUCT_DETAIL: "제품상세 관리",
			APPLICATION: "Application 관리"
			// DATASHEET: "Datasheet 관리",
		},
		MARKET_MGNT: {
			MARKET: "Market 관리",
			FUNCTION: "Function 관리",
		},
		DOC_MGNT: {
			DOC: "첨부문서 관리",
			CATALOG: "카탈로그 관리",
			DOC_SHARE_AUDIT : "다운/공유 이력",
			HIST_VISIT : "화면 조회 이력",
		},
		POPUP_MGNT: {
			POPUP: "팝업 관리",
		},
		CUSTOMER_SUPPORT_MGNT: {
			PERSONAL: "1:1 문의 관리",
			CONTACT: "Contact 관리",
			INFORMATION: "고객정보 관리",
			SEND_MAIL: "단체 메일 발송",
			NEWSROOM: "Newsroom 관리",
		},
	},

    CONTACT_AREA: {
        KOREA: "AMCA00KO",
        CHINA: "AMCA00CH",
        JAPAN: "AMCA00JP",
    },

    CONTACT_AREA_MAP: {
        AMCA00KO: "대한민국",
        AMCA00CH: "중국",
        AMCA00JP: "일본",
    },

    ONE_TO_ONE_CONFIG_TYPE: {
        QUESTION: "AMCTQUST",
        DETAIL_QUESTION: "AMCTDQST",
        RECEIVER_EMAIL: "AMCTRCEM",
    },

	ONE_TO_ONE_ENQUIRY_TYPE : {
		TITLE : "문의제목",
		CONFIG_QUESTION : "문의종류",
		EMAIL : "문의자 이메일",
	},

    OBJECT_TYPE: {
		MATERIAL: "AMMT",
		PRODUCT: "AMPR",
		CLASSIFICATION: "AMCL",
		DATASHEET: "AMDS",
		ONE_TO_ONE: "AMOT", // 1대1 문의

		CLASSIFICATION_PRODUCT: "AMCP",
		CLASSIFICATION_APPLICATION: "AMCA",
		CLASSIFICATION_MARKET: "AMCM",
		CLASSIFICATION_FUNCTION: "AMCF",
		NEWSROOM: "AMNR", //뉴스룸
    },

	OBJECT_TYPE_MAP : {
		product: "AMCP",
		application: "AMCA",
		market: "AMCM",
		function: "AMCF",
	},

	FILE_TYPE : {
		CONTACT_OFFICE_IMG : "AMCOIMG", // Contact Office Image
		MATERIAL_MAIN_IMG : "KAMT00MI",
	},

    USER_TYPE: {
        CONTACT_MANAGER: "AMUTCTMG", // Contact 매니저
        CLIENT: "AMUTCLNT", // 고객
    },

    GMAP_DATA: {
        KEY: "AIzaSyA6hbc8rJ49sC5DRwubXTgbLPSDufTs4GM", // google Map API KEY
    },

	SFA_DOC_TYPE : {
		"A" : "회사공통문서",
		"B" : "제품소개자료",
		"C" : "기술/품질자료",
		"D" : "인증서/성적서",
		"E" : "제품교육자료",
	},

	SFA_DOC_KIND_KEY : {
		CATALOG : "B02",
	},

	SFA_DOC_KIND : {
		"A01" : "사업자등록증",
		"A02" : "공장등록증",
		"A05" : "회사소개서",
		"A08" : "납세증명서",

		"B02" : "카탈로그",
		"B04" : "제품제안서/기술자료집",
		"B05" : "자제승인서",
		"B06" : "영업기초지식",
		"B07" : "제품지식/정보",

		"B09" : "제품비교자료",
		"B11" : "시공사례(제출용)",
		"B12" : "경쟁제품비교",

		"C01" : "데이터시트",
		"C02" : "MSDS",
		"C03" : "도장사양서",
		"C04" : "시방서",

		"D01" : "인증서",
		"D02" : "공인성적서",
		"D03" : "주택공사용성적서",
		"D04" : "실내공기질 시험확인서",
		"D06" : "시험성적서",

		"D07" : "성과대비표",
		"D08" : "제품보증서",

		"E01" : "제품교육자료",
		"E02" : "제품소개자료(사보자료)",
		"E03" : "하자처리/김리메뉴얼",
		"E04" : "제품교육동영상"
	},

	RELEASE_YN : {
		Y : "노출",
		N : "비노출"
	},

	ENCRYPT: {
		YEK: "UkVtYVJrYUJsRVNvRnRAQA=="
	},

	DOMAIN: {
		COM : "https://www.kccmaterials.com",
		CO_KR : "https://www.kccmaterials.co.kr"
	},

	DOC_SHARE_TYPE: {
		SHARE_TYPE_SHARE           : "FWDS000S",
		SHARE_TYPE_DOWNLOAD        : "FWDS000D",
		SHARE_TYPE_EXCEL_DATASHEET : "FWDS000E",
	},

	FLAG_YN : {

		YES : "Y",
		NO  : "N",
	},

	// 팝업 (시작)
	POPUP_TYPE: {

		VIEW : {

			LIST : {

				KEY : "L",
				VALUE : "리스트",
			},
			GENERAL : {
				KEY : "G",
				VALUE : "일반",
			}
		},

		CONTENTS : {

			IMAGE : "I",
			EDITOR : "E"
		},

		LINK : {

			NEW : "N",
			PAGE : "P",
		},
	},

	// 쿠키 용도로 활용
	POPUP_HIDE_TODAY: "POPUP_HT_",
	POPUP_HIDE_TODAY_LIST : "POPUP_HT_LIST",

	// 조직 변경 모달 쿠키
	CHANGE_ORG_HIDE_TODAY : "CHANGE_ORG_HT",

	// 링크 타입
	POPUP_LINK_TYPE : {
		NEW : "N",
		PAGE_MOVE : "P",
	},
	// 팝업 (종료) -->

	MATERIAL_MARKET_MAP: {
		"EMC" : {
			"ko" : "반도체",
			"en" : "Semiconductor",
			"cn" : "半導體",
		},
		"Ceramic Substrates" : {
			"ko" : "반도체",
			"en" : "Semiconductor",
			"cn" : "半導體",
		},
		"Adhesive" : {
			"ko" : "반도체",
			"en" : "Semiconductor",
			"cn" : "半導體",
		},
		"Metalized Ceramics" : {
			"ko" : "전기전자/가전",
			"en" : "Electrical/Electronic Appliances",
			"cn" : " 電器/電子設備",
		},
		"Glass Fiber" : {
			"ko" : "일반산업",
			"en" : "General Industry",
			"cn" : "一般工業",
		},
	},

};
