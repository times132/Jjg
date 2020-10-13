<template>
    <b-container>
        <b-row>
            <Carousel/>
        </b-row>

        <b-row class="mt-3" align-v="center">
            <b-col cols="12" sm="6" class="simple-banner">
                <b-row class="a">
                    <b-img src="http://times133.cdn3.cafe24.com/home/aircon.png"/>
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

        <p>
            For a guide and recipes on how to configure / customize this project,<br>
            check out the
            <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
        </p>
        <div>{{greeting}}</div>
    </b-container>
</template>

<script>
    import axios from 'axios'
    import Carousel from '../components/Carousel'

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
            Carousel
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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

</style>
