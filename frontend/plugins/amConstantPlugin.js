import { AM_CONSTANT } from "~/constant/amConstant.js";
import { URL_CONSTANT } from "~/constant/urlConstant.js";
import { MENU_CONSTANT } from "~/constant/menuConstant";

export default ( context, inject ) => {

	inject( "amConstant", AM_CONSTANT );
	inject( "urlConstant", URL_CONSTANT );
	inject( "menuConstant", MENU_CONSTANT );
	
}