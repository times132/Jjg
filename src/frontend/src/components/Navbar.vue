<template>
    <div id="wrap">
        <header class="header-wrap">
            <b-navbar toggleable="md" variant="light">
                <div class="container">
                    <!-- logo and name -->
                    <router-link class="navbar-brand" to="/">착한 중고</router-link>
                    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                    <!-- navbar -->
                    <b-collapse id="nav-collapse" is-nav>
                        <b-navbar-nav class="category">
                            <!-- 매장 -->
                            <b-nav-item-dropdown text="매장" no-caret class="dropdown-menu-center">
                                <b-dropdown-item to="/intro">소개</b-dropdown-item>
                                <b-dropdown-item to="/location">찾아오는 길</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 에어컨 -->
                            <b-nav-item-dropdown text="에어컨" no-caret>
                                <b-dropdown-item to="/aircon/1">벽걸이</b-dropdown-item>
                                <b-dropdown-item to="/aircon/2">천장</b-dropdown-item>
                                <b-dropdown-item to="/aircon/3">스텐드</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 가전 -->
                            <b-nav-item-dropdown text="가전" no-caret>
                                <b-dropdown-item to="/appliances/1">티비</b-dropdown-item>
                                <b-dropdown-item to="/appliances/2">냉장고</b-dropdown-item>
                                <b-dropdown-item to="/appliances/3">세탁기</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 가구 -->
                            <b-nav-item-dropdown text="사무용 가구" no-caret>
                                <b-dropdown-item to="/furniture/1">책상</b-dropdown-item>
                                <b-dropdown-item to="/furniture/2">책상2</b-dropdown-item>
                                <b-dropdown-item to="/furniture/3">책상3</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 업소용 -->
                            <b-nav-item-dropdown text="업소용" no-caret>
                                <b-dropdown-item to="/business?type=선반">선반</b-dropdown-item>
                                <b-dropdown-item to="/business?type=냉장고">냉장고</b-dropdown-item>
                                <b-dropdown-item to="/business?type=화구">화구</b-dropdown-item>
                                <b-dropdown-item to="/me">내정보</b-dropdown-item>
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
        </header>
        <div></div>
        <div></div>
        <router-view/>
    </div>
</template>

<script>
    import store from '../vuex'

    export default {
        name: "Navbar",
        computed: {
            isAuthenticated() {
                return this.$store.state.isAuthenticated
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
    .category {
        margin: auto;
    }
    .category>li{
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