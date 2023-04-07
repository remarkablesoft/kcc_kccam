<template>
    <div class="video-content">
        <!-- <iframe
            id="youtubeVideo"
            width="100%"
            height="400"
            src="https://www.youtube.com/embed/XUmrLpGNIpM"
            title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
        ></iframe> -->
        <!-- 유튜브 동영상이 들어갑니다. -->
        <youtube :video-id="videoId" ref="youtube" width="100%" height="400px" @playing="playing"></youtube>
    </div>
</template>

<script>
import Vue from "vue";
import VueYoutube from "vue-youtube";

Vue.use(VueYoutube);

export default {
    props: {
        youtubeId: {
            type: String,
            required: false,
        },
    },

    data() {
        return {
            videoId: this.youtubeId,
        };
    },
    created() {},
    mounted() {},
    watch: {},
    computed: {
        player() {
            return this.$refs.youtube.player;
        },
    },
    methods: {
        playVideo() {
            this.player.playVideo();
        },
        playing() {},
        stopVideo() {
            this.player.stopVideo();
        },
    },
};
</script>

<style></style>
