import swal from "sweetalert";
import CryptoJS from "crypto-js";
import { AM_CONSTANT } from "~/constant/amConstant.js";

// jquery에 있던것을 가져옴.
function isEmptyObject( obj ) {
	var name;

	for ( name in obj ) {
		return false;
	}
	return true;
}

function isEmpty( value ) {
	return isEmptyObject( value ) || value == null || value.length == 0;
}

const common = {
	name : "common",

	// 공백 및 null 체크
	isEmpty( value ) {
		return isEmptyObject( value ) || value == null || value.length == 0 || value == undefined || value == "";
	},

	// 데이터가 존재
	isNotEmpty( value ) {
		return !isEmpty( value );
	},

	// 날짜 분까지 format
	formatDateTimeMin( value ) {
		if ( isEmpty( value ) || typeof value !== "string" ) {
			return;
		}

		value = value.substring( 0, 16 );

		do value = value.replace( "-", "." );
		while ( -1 < value.indexOf( "-" ) );

		return value;
	},

	formatTimeMinSec( value ) {
		if ( isEmpty( value ) || typeof value !== "string" ) {
			return;
		}

		value = value.substring( 11, value.indexOf( "." ) );

		do value = value.replace( "-", "." );
		while ( -1 < value.indexOf( "-" ) );

		return value;
	},

	// YYYY-MM-DD format
	formatDate( value ) {
		if ( isEmpty( value ) || typeof value !== "string" ) {
			return;
		}

		value = value.substring( 0, 10 );
		return value;
	},

	// Date Type 을 받아 YYYY-MM-DD 형태의 String 으로 Return
	formatDateToString( value ) {

		let year = value.getFullYear();
		let month = value.getMonth() + 1;
		let date = value.getDate();

		if ( month < 10 ) {
			month = "0" + month;
		}

		if ( value.getDate() < 10 ) {
			date = "0" + value.getDate();
		}

		return year + "-" + month + "-" + date;
	},

	formatDateToString2( value ) {
		let date = $nuxt.$common.leadingZeros( value.getFullYear(), 4 ) + "-" + $nuxt.$common.leadingZeros( value.getMonth() + 1, 2 ) + "-" + $nuxt.$common.leadingZeros( value.getDate(), 2 ) + " ";

		let time = $nuxt.$common.leadingZeros( value.getHours(), 2 ) + ":" + $nuxt.$common.leadingZeros( value.getMinutes(), 2 );
		//+ ':' + $nuxt.$common.leadingZeros( value.getSeconds(), 2 );

		return date + time;
	},

	formatBytes( bytes, decimals ) {
		if ( bytes === 0 ) return '0 Bytes';
		var k = 1000,
			dm = decimals + 1 || 3,
			sizes = [ 'Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB' ],
			i = Math.floor( Math.log( bytes ) / Math.log( k ) );
		return parseFloat( ( bytes / Math.pow( k, i ) ).toFixed( dm ) ) + ' ' + sizes[ i ];
	},

	leadingZeros( n, digits ) {
		var zero = "";
		n = n.toString();

		if ( n.length < digits ) {
			for ( let i = 0; i < digits - n.length; i++ ) zero += "0";
		}
		return zero + n;
	},

	// 천단위 콤마처리
	numberWithComma( data ) {
		if ( !isNaN( data ) && data !== null && data !== 0 ) {
			let exp = /./;
			if ( exp.test( data ) ) {
				return String( data ).replace( /\B(?=(\d{3})+(?!\d))/g, "," ) || 0;
			}
			return data || 0;
		}
		return data || 0;
	},
	//sweetAlert
	swalWithOptions( name, contents, state, confirmButton ) {
		let modeState = false;
		if ( "warning" === state ) {
			modeState = true;
		}
		return swal( {
			title      : name,
			text       : contents,
			icon       : state,
			dangerMode : modeState,
			buttons    : {
				confirm : null || undefined === confirmButton ? $nuxt.$t(`user_shareModal_btnConfirm`) : confirmButton,
				cancel  : $nuxt.$t(`user_shareModal_btnCancel`),
			},
		} );
	},

	/**
	 *  @param: pathName : String
	 *          popName : String
	 *          width : Number
	 *          height : Number
	 *          pos : Boolean
	 */
	openNewWindow( pathName, popName, width, height, pos ) {
		let windowWidth = window.screen.width;
		let windowHeight = window.screen.availHeight;

		if ( popName == null ) {
			popName = "사용자 정의 팝업";
		}
		if ( width == null ) {
			width = windowWidth;
		}
		if ( height == null ) {
			height = windowHeight;
		}

		let popupWidth = width;
		let popupHeight = height;
		let xPos = windowWidth - width;
		let yPos = windowHeight - height;
		xPos += window.screenLeft;

		if ( pos == false || pos == null ) {
			xPos = 0;
		}

		let feature = "left=" + xPos + ", top=0, width=" + popupWidth + ", height=" + popupHeight + ", resizable=no, scrollbars=yes";
		window.open( pathName, popName, feature );
	},

	confirmSwal( title, contents, state ) {
		return swal( {
			title   : title,
			text    : contents,
			icon    : state,
			buttons : {
				confirm : $nuxt.$t(`user_shareModal_btnConfirm`),
			},
		} );
	},

	youtubeThumbnail( url ) {
		if ( url.indexOf( "?v=" ) != -1 ) {
			let arrUrl = url.split( "?v=" );
			return "https://img.youtube.com/vi/" + arrUrl[ 1 ] + "/hqdefault.jpg";
		}
	},

	deepObjectCopy( obj ) {
		return JSON.parse( JSON.stringify( obj ) );
	},

	isNumber( num ) {
		let regex = /^[0-9]$/g;

		if ( regex.test( num ) ) {
			num = num.replace( /,/g, "" );
			// console.log("isNaN(num)", isNaN(num));
			return isNaN( num ) ? false : true;
		}
		return false;
	},

	//이메일 형식 유효성 검사 -- 형식에 맞으면 false
	emailValidation( data ) {
		let result = true;
		if ( null != data && 0 < data.length ) {
			const emailExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

			if ( emailExp.test( data ) ) {
				result = false;
			}
			else {
				result = true;
			}
		}
		return result;
	},

	//휴대폰 번호 형식 유효성 검사
	phoneValidation( data1, data2, data3 ) {
		if ( null != data1 && null != data2 && null != data3 && 10 <= data1.length + data2.length + data3.length ) {
			const phone1Exp = /^01([0|1|6|7|8|9]?)$/;
			const phone2Exp = /^([0-9]{3,4})$/;
			const phone3Exp = /^([0-9]{4,4})$/;

			if ( "010" === data1 ) {
				if ( phone1Exp.test( data1 ) && phone3Exp.test( data2 ) && phone3Exp.test( data3 ) ) {
					return false;
				}
			}
			else if ( phone1Exp.test( data1 ) && phone2Exp.test( data2 ) && phone3Exp.test( data3 ) ) {
				return false;
			}
			return true;
		}
		return true;
	},

	//이름 형식 유효성 검사 - 이름 사이 공백 허용
	nameValidation( data ) {
		if ( null != data && 0 < data.length ) {
			const nameExp = /^[a-zA-Z가-힣]+\s?[a-zA-Z가-힣]+\s?[a-zA-Z가-힣]+$/;

			if ( nameExp.test( data ) ) {
				return false;
			}
			return true;
		}
		return true;
	},

	// 암호화 처리
	encryptAesValue( data ) {
		//console.log ( "encryptAesValue1 : " + data );

		const parsedBase64Key = CryptoJS.enc.Base64.parse( AM_CONSTANT.ENCRYPT.YEK );

		let encryptedData = CryptoJS.AES.encrypt( data, parsedBase64Key, {
			mode    : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7,
		} );
		//console.log ( "encryptAesValue2 : " + encryptedData );
		return encryptedData.toString();
	},

	// 복호화 처리
	decryptAesValue( data ) {
		//console.log ( "decryptAesValue1 : " + data );

		if ( isEmpty( data ) ) {
			return "";
		}

		const parsedBase64Key = CryptoJS.enc.Base64.parse( AM_CONSTANT.ENCRYPT.YEK );

		let decryptedData = CryptoJS.AES.decrypt( data, parsedBase64Key, {
			mode    : CryptoJS.mode.ECB,
			padding : CryptoJS.pad.Pkcs7,
		} );

		let decryptedText = decryptedData.toString( CryptoJS.enc.Utf8 );

		//console.log ( "decryptAesValue2 : " + decryptedText );
		return decryptedText;
	},

	//모바일인지 확인
	isMobile() {
		let result = false;
		let isios = /(ipod|iphone|ipad)/i.test( navigator.userAgent ); //ios
		let isipad = /(ipad)/i.test( navigator.userAgent ); //ipad
		let isandroid = /android/i.test( navigator.userAgent ); //android

		let detectMob = window.innerWidth <= 1024;

		if ( isandroid || isios || isipad || detectMob ) {
			result = true;
		}

		// console.log( "checkMobile", result )
		return result;
	},

	//BITSUM , 해당 필드에 해당 값이 있는지 체크하여 true, false 반환
	getCheck( value, field ) {
		if ( ( value & field ) != 0 ) {
			return true;
		}

		return false;
	},

	sortJSON( data, key, type ) {
		if ( type == undefined ) {
			type = "asc";
		}
		return data.sort( function ( a, b ) {
			let x = a[ key ];
			let y = b[ key ];
			if ( type == "desc" ) {
				return x > y ? -1 : x < y ? 1 : 0;
			}
			else if ( type == "asc" ) {
				return x < y ? -1 : x > y ? 1 : 0;
			}
		} );
	},

	/* 목록에서 No를 setting 해줍니다.*/
	settingNo( listCount, startIndex, index ) {
		return listCount - ( startIndex + index ) + 1;
	},

	//json 키 값을 변경합니다.
	renameKey( obj, oldKey, newKey ) {
		obj[ newKey ] = obj[ oldKey ];
		delete obj[ oldKey ];
	},

	/* 페이지에서 맨 마지막 페이지를 가져옵니다. */
	getLastPage( listCount, pageSize ) {
		if ( listCount % pageSize === 0 ) {
			return parseInt( listCount / pageSize );
		}
		return parseInt( listCount / pageSize + 1 );
	},

	//ie의 경우 new Date( "YYYY-MM-DD HH:MM:SS" )가 안되어서 포맷에 맞게 '2019/09/09 17:22' 로 변환
	ieDateConversion( value ) {
		return value.replace( "-", "/" ); // '2019/09/09 17:22'
	},

	checkBrowser() {
		var agent = navigator.userAgent.toLowerCase();

		if ( ( navigator.appName == "Netscape" && navigator.userAgent.search( "Trident" ) != -1 ) || agent.indexOf( "msie" ) != -1 ) {
			return "ie";
		}
		else if ( agent.indexOf( "safari" ) != -1 ) {
			return "safari";
		}
		else if ( agent.indexOf( "chrome" ) != -1 ) {
			return "chrome";
		}
	},

	// URL을 파싱하여 URL의 마지막을 가져옵니다.
	getLastUrlPath( url ) {
		if ( isEmpty( url ) ) {
			return "";
		}

		let urlArr = [];
		if ( url.indexOf( "?" ) !== -1 ) {
			urlArr = url.split( "?" );
			url = urlArr[ 0 ];
		}

		urlArr = url.split( "/" );

		return urlArr[ urlArr.length - 1 ];
	},

	// URL 정보의 파라미터에서 원하는 키값의 Value값을 얻습니다.
	getUrlParamValue( url, key ) {
		if ( isEmpty( url ) || url.indexOf( "?" ) === -1 ) {
			return "";
		}

		const urlParam = url.split( "?" )[ 1 ];
		const paramArr = urlParam.split( "&" );
		let paramObj = {};
		_.each( paramArr, function ( param ) {
			let paramKeyValArr = param.split( "=" );
			paramObj[ paramKeyValArr[ 0 ] ] = paramKeyValArr[ 1 ];
		} );

		return paramObj[ key ];
	},

	// 이미지 에러 처리
	imageError(e) {
        e.target.src = require("~/assets/images/contents/sample/contents_catalog_no_image.png");
    },
};

export default ( context, inject ) => {
	inject( "common", common );
};
