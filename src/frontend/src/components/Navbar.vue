<template v-slot:header>
    <div>
        <b-navbar toggleable="md" variant="light">
            <div class="container">
                <!-- logo and name -->
                <router-link class="navbar-brand" to="/">착한 중고</router-link>
                <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                <!-- navbar -->
                <b-collapse id="nav-collapse" is-nav>
                    <b-navbar-nav class="categoryItem">
                        <!-- 매장 -->
                        <b-nav-item-dropdown text="매장" no-caret class="dropdown-menu-center">
                            <b-dropdown-item to="/intro">소개</b-dropdown-item>
                            <b-dropdown-item to="/location">찾아오는 길</b-dropdown-item>
                        </b-nav-item-dropdown>

                        <!-- 에어컨 -->
                        <b-nav-item-dropdown text="에어컨" no-caret>
                            <b-dropdown-item to="/goods/011">스텐드</b-dropdown-item>
                            <b-dropdown-item to="/goods/012">벽걸이</b-dropdown-item>
                            <b-dropdown-item to="/goods/013">천장</b-dropdown-item>
                        </b-nav-item-dropdown>

                        <!-- 가전 -->
                        <b-nav-item-dropdown text="가전" no-caret>
                            <b-dropdown-item to="/goods/021">냉장고</b-dropdown-item>
                            <b-dropdown-item to="/goods/022">세탁기</b-dropdown-item>
                            <b-dropdown-item to="/goods/023">TV</b-dropdown-item>
                        </b-nav-item-dropdown>

                        <!-- 업소용 -->
                        <b-nav-item-dropdown text="업소용" no-caret>
                            <b-dropdown-item to="/goods/031">선반</b-dropdown-item>
                            <b-dropdown-item to="/goods/032">냉장/냉동고</b-dropdown-item>
                            <b-dropdown-item to="/goods/033">화구</b-dropdown-item>
                            <b-dropdown-item to="/me">내정보</b-dropdown-item>
                        </b-nav-item-dropdown>

                        <!-- 가구 -->
                        <b-nav-item-dropdown text="사무용" no-caret>
                            <b-dropdown-item to="/goods/041">사무용</b-dropdown-item>
                            <b-dropdown-item to="/goods/042">사무용2</b-dropdown-item>
                            <b-dropdown-item to="/goods/043">사무용3</b-dropdown-item>
                        </b-nav-item-dropdown>

                    </b-navbar-nav>

                    <b-navbar-nav class="ml-auto">
                        <b-nav-form>
                            <b-form-input id="search" size="sm" class="mr-sm-2" placeholder="검색"></b-form-input>
                        </b-nav-form>
                    </b-navbar-nav>

                    <a href="" v-if="isAuthenticated" @click.prevent="onClickLogout">logout</a>
                    <p v-if="!isAuthenticated" class="h4 mb-0 pb-1 pt-1">
                        <b-link class="login" to="/login"><b-icon icon="person-circle"></b-icon></b-link>
                    </p>

                </b-collapse>
            </div>
        </b-navbar>
    </div>
</template>

<script>
    import store from '../vuex'

    export default {
        name: "Navbar",
        computed: {
            isAuthenticated() {
                return this.$store.state.login.isAuthenticated
            }
        },
        methods: {
            onClickLogout() {
                store.dispatch('logout')
                    .then(() => this.$router.push('/'))
                    .catch(() => {})
            }
        }
    }
</script>

<style>
    .navbar-brand{
        font-weight: bold;
        font-size: 1.5rem!important;
    }
    .nav-item:hover .dropdown-menu {
        display: block;
        margin-top: 0;
    }
    .nav-item:hover .nav-link{
        color: #fe696a!important;
        transition-duration: 0.3s;
    }
    .dropdown-item:hover {
        color: #fe696a!important;
        background: none!important;
    }
    .dropdown-menu {
        right: auto;
        left: 50%!important;
        -webkit-transform: translate(-50%, 0);
        -o-transform: translate(-50%, 0);
        transform: translate(-50%, 0);
    }
    .form-inline {
        justify-content: center;
    }
    .categoryItem {
        margin: auto;
    }
    .categoryItem>li{
        margin: 0 0.75rem;
    }
    #search {
        text-align: center;
    }
    .login {
        color: black;
    }
    .login:hover {
        text-decoration: none;
        color: black;
    }
</style>