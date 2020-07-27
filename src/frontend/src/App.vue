<template>
    <div id="app">
        <Nav :key="$route.fullPath"/>

        <Footer/>
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
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
</style>
