<template>
    <b-container class="mb-5">
        <b-row>
            <naver-maps
                    :width="width"
                    :height="height"
                    :map-options="mapOptions"
                    :init-layers="initLayers"
                    @load="onLoad">
                <naver-info-window
                        class="info-window"
                        :isOpen="info"
                        :marker="marker">
                    <div class="info-window-container">
                        <h4>{{shopInfo}}</h4>
                    </div>
                </naver-info-window>
                <naver-marker :lat="33.497950" :lng="126.537930" @click="onMarkerClicked" @load="onMarkerLoaded"/>
            </naver-maps>
        </b-row>
    </b-container>

</template>

<script>
    export default {
        name: "Location",
        data() {
            return {
                windowWidth: 0,
                width: 600,
                height: 600,
                info: false,
                map: null,
                marker: null,
                mapOptions: {
                    zoom: 17,
                    lat: 33.497826,
                    lng: 126.538032
                },
                initLayers: ['BACKGROUND', 'BACKGROUND_DETAIL', 'POI_KOREAN', 'TRANSIT', 'ENGLISH', 'CHINESE', 'JAPANESE'],
                icon: require('@/assets/map-marker.png'),
            }
        },
        computed: {
            shopInfo() {
                return '제주 중고쇼핑몰'
            }
        },
        mounted() {
            window.addEventListener('resize', () => {
                this.windowWidth = window.innerWidth
                if (window.innerWidth < 600) {
                    this.map.setSize({width: 400, height: 400})
                } else{
                    this.map.setSize({width: 600, height: 600})
                }
            })
        },
        methods: {
            onLoad(vue){
                this.map = vue
            },
            onMarkerClicked() {
                this.info = !this.info
            },
            onMarkerLoaded(vue) {
                vue.marker.setIcon(this.icon)
                this.marker = vue.marker
            }
        }

    }
</script>

<style scoped>

</style>