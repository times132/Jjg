<template>
    <b-container>
        <b-row>
            <Carousel/>
        </b-row>

        <b-row class="mt-3" align-v="center">
            <b-col cols="12" sm="6" class="simple-banner">
                <b-row class="a">
                    <b-img src="http://times133.cdn3.cafe24.com/home/aircon.jpg"/>
                    <b-img src="http://times133.cdn3.cafe24.com/home/appliances.jpg"/>
                </b-row>

            </b-col>
            <b-col cols="12" sm="6" class="simple-banner">
                <b-row class="a">
                    <b-img src="http://times133.cdn3.cafe24.com/home/commercial-appliances.jpg"/>
                    <b-img src="http://times133.cdn3.cafe24.com/home/office.jpg"/>
                </b-row>
            </b-col>
        </b-row>

        <b-row>
            <b-col cols="12" md="7">
                <div class="test-banner">
                    기타배너
                </div>
            </b-col>

            <b-col cols="12" md="5" class="map-div">
                <Map :map-width='320' :map-height='320'/>
            </b-col>
        </b-row>


        <div>{{greeting}}</div>
    </b-container>
</template>

<script>
    import axios from 'axios'
    import Carousel from '../components/Carousel'
    import Map from '../components/Maps'

    export default {
        data() {
            return {
                greeting: ''
            }
        },
        created() {
            axios.get('/api/home')
                .then(result => this.greeting = result.data)
        },
        components: {
            Carousel,
            Map
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
    .simple-banner{
        background-color: #c9cac6;
        height: 9rem;
    }
    .simple-banner img{
        width: 7.5rem;
        height: 7.5rem;
        display: block;
        margin: 0 auto;
        border-radius: 50%;
    }
    .test-banner{
        background-color: #7ec699;
        height: 360px;
    }
    .map-div{
        overflow: hidden;
        background-color: #7f7f7f;
    }
</style>
