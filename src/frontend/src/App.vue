<template>
    <div id="app">
        <slot name="header">
            <Nav id="header"/>
        </slot>

        <slot id="content">
            <router-view id="main" :key="$route.fullPath"/>
        </slot>

        <slot name="footer">
            <Footer id="footer"/>
        </slot>

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
        min-height: 100%;
        position: relative;
        padding-bottom: 87px;
    }
    html {
        height: 100%;
    }
    body {
        margin: 0;
        height: 100%;
    }
    #footer {
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        background:grey;
        color: white;
    }
</style>
