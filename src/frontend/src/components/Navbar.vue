<template>
    <div id="wrap">
        <header class="header-wrap">
            <b-navbar toggleable="md" variant="light">
                <div class="container">
                    <!-- logo and name -->
<!--                    <b-navbar-brand href="/">착한 중고</b-navbar-brand>-->
                    <router-link class="navbar-brand" to="/">착한 중고</router-link>
                    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                    <!-- navbar -->
                    <b-collapse id="nav-collapse" is-nav>
                        <b-navbar-nav class="ml-auto">

                            <b-nav-form>
                                <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                            </b-nav-form>

                            <!-- 에어컨 -->
                            <li class="nav-item dropdown">
<!--                                <a class="nav-link dropdown-toggle" href="/aircon">에어컨</a>-->
                                <router-link class="nav-link dropdown-toggle" to="/aircon">에어컨</router-link>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item" href="/aircon?type=벽걸이">벽걸이</a>
                                        <a class="dropdown-item" href="/aircon?type=천장">천장</a>
                                        <a class="dropdown-item" href="/aircon?type=스텐드">스텐드</a>
                                    </li>
                                </ul>
                            </li>

                            <!-- 가전 -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="/appliances">가전</a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item" href="/appliances?type=티비">TV</a>
                                        <a class="dropdown-item" href="/appliances?type=냉장고">냉장고</a>
                                        <a class="dropdown-item" href="/appliances?type=세탁기">세탁기</a>
                                    </li>
                                </ul>
                            </li>

                            <!-- 가구 -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="/furniture">가구</a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item" href="/furniture?type=장농">장농</a>
                                        <a class="dropdown-item" href="/furniture?type=서랍">서랍</a>
                                        <a class="dropdown-item" href="/furniture?type=식탁">식탁</a>
                                    </li>
                                </ul>
                            </li>

                            <!-- 업소용 -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="/business">업소용</a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a class="dropdown-item" href="/business?type=선반">선반</a>
                                        <a class="dropdown-item" href="/business?type=냉장고">냉장고</a>
                                        <a class="dropdown-item" href="/business?type=화구">화구</a>
                                    </li>
                                </ul>
                            </li>
                        </b-navbar-nav>
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
    export default {
        name: "Navbar"
    }
</script>

<style>
    .dropdown:hover .dropdown-menu {
        display: block;
        margin-top: 0;
    }
    .nav-link:hover {
        color: #fe696a!important;
        transition-duration: 0.3s;
    }
    .dropdown:hover .nav-link {
        color: #fe696a;
    }
</style>