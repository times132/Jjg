<template>
    <b-container>
        <b-row>
            <Carousel/>
        </b-row>

        <b-row class="mt-3 mb-3" align-v="center">
            <b-col cols="12" sm="6" class="simple-category">
                <b-row class="sub">
                    <div>
                        <router-link to="/goods/01">
                            <b-img src="http://times133.cdn3.cafe24.com/goods/aircon.jpg"/>
                        </router-link>
                        <span>에어컨</span>
                    </div>
                    <div>
                        <router-link to="/goods/02">
                            <b-img src="http://times133.cdn3.cafe24.com/goods/appliances.jpg"/>
                        </router-link>
                        <span>생활가전</span>
                    </div>
                </b-row>

            </b-col>
            <b-col cols="12" sm="6" class="simple-category">
                <b-row class="sub">
                    <div>
                        <router-link to="/goods/03">
                            <b-img src="http://times133.cdn3.cafe24.com/goods/commercial-appliances.jpg"/>
                        </router-link>
                        <span>업소용</span>
                    </div>
                    <div>
                        <router-link to="/goods/04">
                            <b-img src="http://times133.cdn3.cafe24.com/goods/office.jpg"/>
                        </router-link>
                        <span>사무용</span>
                    </div>
                </b-row>
            </b-col>
        </b-row>

        <b-row>
            <b-col cols="12" md="7">
                <b-img fluid src="http://times133.cdn3.cafe24.com/home/bottom.jpg"/>
            </b-col>

            <b-col cols="12" md="5" class="map-div ml-auto">
                <Map :size='mapSize'/>
            </b-col>
        </b-row>

    </b-container>
</template>

<script>
    import Carousel from '../components/Carousel'
    import Map from '../components/Maps'

    export default {
        components: {
            Carousel,
            Map
        },
        data() {
            return {
                mapSize: 0
            }
        },
        created() {
            const windowWidth = window.innerWidth
            if (windowWidth >= 1200) {
                this.mapSize = 450
            } else if(992 <= windowWidth && windowWidth < 1200) {
                this.mapSize = 375
            } else if(768 <= windowWidth && windowWidth < 992){
                this.mapSize = 275
            } else{
                this.mapSize = 345
            }
            console.log(this.mapSize)
        }
    }
</script>

<style scoped>
    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
    .a{
        display: flex;
        padding: 0.75rem 0;
    }
    .simple-category{
        padding: 0 0;
    }
    .simple-category div{
        margin: 0 auto;
    }
    .simple-category img{
        width: 7.5rem;
        height: 7.5rem;
        display: block;
        border-radius: 50%;
        margin-bottom: 0.5rem;
    }
    .simple-category span{
        font-weight: bold;
    }
    .map-div{
        overflow: hidden;
    }
</style>
