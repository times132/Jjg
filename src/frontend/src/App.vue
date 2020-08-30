<template>
    <div id="app">
        <Nav id="header"/>

        <router-view id="main" :key="$route.fullPath"/>

        <Footer id="footer"/>
    </div>
</template>

<script>
    import Nav from './components/Navbar'
    import Footer from './components/Footer'

    export default {
        name: 'App',
        data: function() {
            return {
                name: 'Park'
            }
        },
        components: {
            Footer,
            Nav
        },
    }
</script>

<style>
    html {
        height: 100%;
    }
    body {
        margin: 0;
        height: 100%;
    }
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        min-height: 100%;
        position: relative;
        padding-bottom: 87px;
    }
</style>
