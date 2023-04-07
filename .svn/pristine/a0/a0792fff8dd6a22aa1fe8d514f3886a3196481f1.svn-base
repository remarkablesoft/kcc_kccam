<template>
    <!-- sidebar -->
    <div class="sidebar">
        <ul class="lnb">
            <li class="lnb-title" v-text="sideName"></li>
            <!--  -->
            <li v-for="(item, index) in list" :key="index"  :class="item.active">
                <nuxt-link :to="localePath(item.url)" class="link">
                    <span v-text="item.name"></span>
                    <span class="material-icons ar">keyboard_arrow_right</span>
                </nuxt-link>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    props: {
        topMenuKeyUrl: {
            type: String,
            required: true,
        },
        sideName: {
            type: String,
            required: true,
        },
    },
    mounted() {
        this.getSideMenuUrlList();
        // console.log("topMenuKeyUrl :", this.topMenuKeyUrl);
    },
    watch: {
        topMenuKeyUrl() {
            // 값이 바뀔때 리스트를 넣어줘야함
            this.getSideMenuUrlList();
        },
	    $route() {
		    this.getMenuActiveByPath();
	    }
    },

    data() {
        return {
            sideTit: "",
            list: [],
        };
    },
    methods: {
        // 리스트에 잘라논 통로를 넣음
        getSideMenuUrlList() {
	        let name = this.$menuConstant.MANAGER_SIDE_MENU_NAME_MAP[this.topMenuKeyUrl];
            let url = this.$menuConstant.MANAGER_SIDE_MENU_URL_MAP[this.topMenuKeyUrl];

            let list = this.valueInspection(name, url);
	        this.list = list;
        },
	    /**
	     * 현재 위치를 파악해 SideBar 에 Active 효과를 줍니다.
	     */
	    getMenuActiveByPath() {
		    const keyUrl = this.$route.fullPath;
		    let delimeter = keyUrl.split('/');
		    let arrMenuLink = delimeter.slice(0,-1);
		    let menuKey = arrMenuLink[arrMenuLink.length-1];
		    menuKey = "/" + menuKey + "/";

            this.list.forEach( ( item ) => {
		    	item.active = "";

		    	if(item.url.indexOf(menuKey) > -1) {
				    item.active = "active";
			    }
		    });
	    },

        // 각각의 key가 같은지 확인합니다
        valueInspection(name, url) {
            const _self = this;
            if (_self.$common.isEmpty(name) || _self.$common.isEmpty(url)) {
                return "";
            }

            let list = [];

            let nameKeys = Object.keys(name);
            let urlKeys = Object.keys(url);

            _.each(nameKeys, function(key, index) {
                if (_self.$common.isEmpty(nameKeys) || _self.$common.isEmpty(urlKeys)) {
                    return;
                }

                // 키가 같다면 setMenuObj를 실행합니다
                if (key === urlKeys[index]) {
                    let result = {};
                    result.name = name[key];
                    result.url = url[key];

                    let thisUrl = _self.$route.fullPath;

                    if (result.url === thisUrl) {
                        result.active = "active";
                    } else {
                        result.active = "";
                    }
                    list.push(result);
                }
            });

            return list;
        },

        getMenuActiveByClick(item) {
            // _.each(this.list, function(listItem) {
            //     listItem.active = "";
            // });
			//
            // item.active = "active";
        },
    },
};
</script>

<style></style>
