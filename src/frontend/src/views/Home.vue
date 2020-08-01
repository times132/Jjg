<template>
    <div>
        <b-carousel
                id="carousel-fade"
                style="text-shadow: 0px 0px 2px #000"
                fade
                controls
                indicators
                img-width="1024"
                img-height="480"
        >
            <b-carousel-slide
                    caption="First slide"
                    img-src="https://picsum.photos/1024/480/?image=10"
            ></b-carousel-slide>
            <b-carousel-slide
                    caption="Second Slide"
                    img-src="https://picsum.photos/1024/480/?image=12"
            ></b-carousel-slide>
            <b-carousel-slide
                    caption="Third Slide"
                    img-src="https://picsum.photos/1024/480/?image=22"
            ></b-carousel-slide>
        </b-carousel>
        <p>
            For a guide and recipes on how to configure / customize this project,<br>
            check out the
            <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
        </p>
        <div>{{greeting}}</div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                greeting: ''
            }
        },
        created() {
            axios.get('http://localhost:9000/api/home')
                .then(result => this.greeting = result.data)
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
</style>
