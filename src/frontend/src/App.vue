<template>
  <div id="app">
    <Nav/>
    <img alt="Vue logo" src="./assets/logo.png">
<!--    <HelloWorld msg="당신의 Vue.js 앱에 오신걸 환영합니다."/>-->
    {{ name }}
    <Footer/>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import Nav from './components/Navbar'
import Footer from './components/footer'
import request from 'request'

export default {
  name: 'App',
  data: function() {
    return {
      name: 'Park'
    }
  },
  components: {
    Footer,
    // HelloWorld,
    Nav
  },
  mounted() {
    request('http://localhost:9000/api/home', function (error, response, body) {
      window.console.log('error: ', error);
      window.console.log('statusCode: ', response && response.statusCode);
      window.console.log('body: ', body)
    })
  }
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
