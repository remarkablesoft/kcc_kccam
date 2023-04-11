const serverUrl = "http://localhost:7070";
const features = ["fetch", "Object.entries", "IntersectionObserver"].join("%2C");

import webpack from "webpack";

export default {
    ssr: false,
    // Target: https://go.nuxtjs.dev/config-target
    target: "static",

    // Global page headers: https://go.nuxtjs.dev/config-head
    head: {
        htmlAttrs: {
            lang: "ko",
        },
        // title: "KCC AM",
        meta: [
            { charset: "utf-8" },
            { "http-equiv": "X-UA-Compatible", content: "IE=edge,chrome=1" },
            { "http-equiv": "pragma", content: "no-cache" },
            { "http-equiv": "cache-control", content: "no-cache" },
            { "http-equiv": "expires", content: "0" },
            { name: "viewport", content: "width=device-width, initial-scale=1" },
	        process.env.NUXT_ENV_STAGE !== 'real' ? { name: 'robots', content: 'noindex' } : null,
            {
                name: "keywords",
                content:
                    "KCC, KCC Advanced Materials, KCC materials, Product, Market, 건축, 모바일, 반도체, 선박/해양플랜트, 일반산업, 자동차, 전기전자/가전, EMC, Adhesive, Metalized Ceramics, Ceramic Substrates, Glass Fiber",
            },
            {
                hid: "description",
                name: "description",
                content:
                    "KCC 첨단소재(Advanced materials)는 반도체,모바일,자동차,전기전자/가전,건축,선박 등 다양한 산업에서 완벽한 성능을 구현 노력",
            },
            // { name: "google-site-verification", content: "jyVzBemZTV44zZBoFeUfdHR2IAh9k6ARhjqdtAVfYt8" }, // 구글 검색

            // 검색엔진 최적화(SEO)
            // sns, Naver 블로그, 카카오톡 미리보기 설정
            { property: "og:type", content: "website" },
            {
                property: "og:description",
                content:
                    "KCC 첨단소재(Advanced materials)는 반도체,모바일,자동차,전기전자/가전,건축,선박 등 다양한 산업에서 완벽한 성능을 구현 노력",
            },
            { property: "og:image", content: "/logo_am_thumb.png" }, // 대표 이미지(로고)
            { property: "og:url", content: "https://www.kccmaterials.co.kr" },

            // 트위터 미리보기 설정
            { name: "twitter:card", content: "summary" }, // 트위터 카드 타입(요약정보, 사진, 비디오)
            {
                name: "twitter:description",
                content:
                    "KCC 첨단소재(Advanced materials)는 반도체,모바일,자동차,전기전자/가전,건축,선박 등 다양한 산업에서 완벽한 성능을 구현 노력",
            },
            { name: "twitter:image", content: "/logo_am_thumb.png" }, // 대표 이미지(로고)
            { name: "twitter:domain", content: "https://www.kccmaterials.co.kr" },
        ],
        link: [
            { rel: "icon", type: "image/x-icon", href: "/favicon.ico" },
            {
                // default icon style :: material icons
                rel: "stylesheet",
                type: "text/css",
                href:
                    "https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp",
            },
            { rel: "canonical", href: "https://kccmaterials.com/kccam/user/main/user_main" },
        ],
        script: [
            { src: `https://polyfill.io/v3/polyfill.min.js?features=${features}`, body: true },
            /* 구글 Analytics start */
            { src: "https://www.googletagmanager.com/gtag/js?id=G-2Z73P0CTFS" },
            { src: "https://www.googletagmanager.com/gtag/js?id=G-6XHEBWGMBT" },
            { src: "https://www.googletagmanager.com/gtag/js?id=G-9ZLN145LG0" },
            { src: "https://www.googletagmanager.com/gtag/js?id=G-WXYKWJ0WEG" },
            { src: "/googleAnalytics/gaKccDomestic.js" },
            { src: "/googleAnalytics/gaKccInternational.js" },
            { src: "/googleAnalytics/gaRemarkDomestic.js" },
            { src: "/googleAnalytics/gaRemarkInternational.js" },
            /* 구글 Analytics end */
        ],
    },
    router: {
        mode: "history",
    },
    build: {
        // analyze: true,
        postcss: {
            plugins: {
                "postcss-preset-env": {
                    autoprefixer: {
                        grid: true,
                    },
                },
            },
        },
        babel: {
            presets({ isServer }) {
                console.log("isServer :  " + isServer);
                const targets = isServer ? { node: "current" } : { ie: 10 };
                return [[require.resolve("@nuxt/babel-preset-app"), { targets }]];
            },
        },
        transpile: ["vue-clamp", "resize-detector", "dropzone-nuxt", "vue2-dropzone", "vuebar", "vue-froala", "vue-slide-up-down", "ufo"],

        plugins: [
            new webpack.ProvidePlugin({
                // global modules
                $: "jquery",
                _: "lodash",
            }),
        ],
    },
    // Global CSS: https://go.nuxtjs.dev/config-css
    css: [
        "~/assets/fonts/Gothic-A1/style.css", // common font
        "~/assets/fonts/icon/fontello/css/fontello.css", // common font icon
        "~/assets/css/lib/element-ui/element-ui.css", // common element-ui css
        "~/assets/scss/style.scss", // common style css ( user/manager )
    ],

    // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
    plugins: [
        { src: "~/plugins/disableLogs.js" },
        "~/plugins/axiosConfig.js",
        { src: "~/plugins/common.js" },
        { src: "~/plugins/amCommon.js" },
        { src: "~/plugins/amConstantPlugin.js" },
        { src: "~/plugins/vue-awesome-swiper.js", ssr: false },
        "~/plugins/persistedState.client.js", // store 저장용"
        { src: "~/plugins/vue-froala.js", ssr: false },
        { src: "~/plugins/vuebar.js", ssr: false },
        { src: "~/plugins/vue-slide-up-down.js", ssr: false },
        { src: "~/plugins/animated-scroll-to.js", ssr: false },
    ],

    // Auto import components: https://go.nuxtjs.dev/config-components
    components: true,

    // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
    buildModules: [
	    '@nuxtjs/dotenv',
    ],

    // Modules: https://go.nuxtjs.dev/config-modules

    modules: [
        "@nuxtjs/axios",
        "@nuxtjs/style-resources",
        "nuxt-element-ui",
        "dropzone-nuxt",
        "sweetalert",
        [
            "nuxt-gmaps",
            {
                key: "AIzaSyA6hbc8rJ49sC5DRwubXTgbLPSDufTs4GM",
            },
        ],
        [
            "nuxt-i18n",
            {
                // 다국어 lib
                detectBrowserLanguage: {
                    //브라우저 language detection 옵션
                    useCookie: true,
                    cookieKey: "kccam_i18n",
                    onlyOnRoot: true, // recommended
                },
                // differentDomains      : true,
                langDir: "language/",
                defaultLocale: "ko",
                locales: [
                    {
                        code: "en",
                        name: "ENG",
                        file: "en-EN.js",
                        // domain : 'www.kccmaterials.com',
                    },
                    {
                        code: "ko",
                        name: "KOR",
                        file: "ko-KO.js",
                        // domain : 'www.kccmaterials.co.kr',
                    },
                    {
                        code: "cn",
                        name: "CHN",
                        file: "cn-CN.js",
                    },
                ],
            },
        ],
        "@nuxtjs/gtm",
    ],
    gtm: {
        id: "G-9ZLN145LG0",
        enabled: true,
        pageTracking: true,
        pageViewEventName: "routerView",
        scriptURL: "https://www.googletagmanager.com/gtag/js",
    },
    styleResources: {
        sass: ["~/assets/scss/style.scss"],
    },
    elementUI: {
        locale: "ko",
    },
    // Build Configuration: https://go.nuxtjs.dev/config-build

	dotenv: { filename : '/env/.env' + process.env.NUXT_ENV_STAGE},
    axios: {
        proxy: true,
    },
    proxy: {
        "/kccam/api": serverUrl,
        "/storage": serverUrl,
        "/thumbnail": serverUrl,
    },
    generate: {
        dir: "../src/main/resources/static",
    },
};
