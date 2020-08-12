<template>
    <div>
        <Carousel/>
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
    import Carousel from '../components/Carousel'

    export default {
        data() {
            return {
                greeting: ''
            }
        },
        created() {
            axios.get('http://localhost:9000/api/home')
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

</style>
