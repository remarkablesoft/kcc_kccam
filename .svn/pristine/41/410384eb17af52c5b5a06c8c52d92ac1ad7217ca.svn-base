import { AM_CONSTANT } from "~/constant/amConstant";
import { URL_CONSTANT } from "~/constant/urlConstant";

export const amCommon = {

	name : 'amCommon',

	/**
	 * URL로 기준이 될 키워드를 찾습니다.
	 *
	 * @param url		사이트 URL
	 * @param stdIndex	URL split 이후 키워드 기준으로 사용할 index
	 * @returns {string}
	 */
	getUrlKeyword ( url, stdIndex ) {

		if ( url === "" || url === undefined ) {
			return "";
		}

		if ( url.indexOf( "?" ) !== -1 ) {
			url = url.split[0];
		}

		return url.split( "/" )[stdIndex];

	},

	/**
	 * 외부 링크로 파일을 다운로드합니다.
	 *
	 * @param outLinkUrl
	 * @param fileName
	 */
	downloadExternalLinkFile( outLinkUrl, fileName ) {

		var xhr = new XMLHttpRequest();
		xhr.open( 'GET', outLinkUrl, true );
		xhr.responseType = 'blob';

		xhr.onprogress = function ( event ) {
			if ( event.lengthComputable ) {
				var percentComplete = ( event.loaded / event.total ) * 100;
				//yourShowProgressFunction(percentComplete);
			}
		};

		xhr.onload = function ( event ) {
			if ( this.status == 200 ) {
				_saveBlob( this.response, fileName );
			}
			else {
				//yourErrorFunction()
			}
		};

		xhr.onerror = function ( event ) {
			//yourErrorFunction()
		};

		xhr.send();


		function _saveBlob( response, fileName ) {
			if ( navigator.msSaveBlob ) {
				//OK for IE10+
				navigator.msSaveBlob( response, fileName );
			}
			else {
				_html5Saver( response, fileName );
			}
		}

		function _html5Saver( blob, fileName ) {
			var a = document.createElement( "a" );
			document.body.appendChild( a );
			a.style = "display: none";
			var url = window.URL.createObjectURL( blob );
			a.href = url;
			a.download = fileName;
			a.click();
			document.body.removeChild( a );
		}

	}

}

export default ( context, inject ) => {

	inject( "amCommon", amCommon );

};
