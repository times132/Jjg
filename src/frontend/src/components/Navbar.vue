<template>
    <div id="wrap">
        <header class="header-wrap">
            <b-navbar toggleable="md" variant="light">
                <div class="container">
                    <!-- logo and name -->
                    <b-navbar-brand href="/">착한 중고</b-navbar-brand>
                    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
                    <!-- navbar -->
                    <b-collapse id="nav-collapse" is-nav>
                        <b-navbar-nav class="ml-auto">

                            <b-nav-form>
                                <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                            </b-nav-form>

                            <!-- 에어컨 -->
                            <b-nav-item-dropdown text="에어컨" id="/aircon">
                                <b-dropdown-item href="/aircon?type=벽걸이">벽걸이</b-dropdown-item>
                                <b-dropdown-item href="/aircon?type=천장">천장</b-dropdown-item>
                                <b-dropdown-item href="/aircon?type=스텐드">스텐드</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 가전 -->
                            <b-nav-item-dropdown text="가전">
                                <b-dropdown-item href="/appliances?type=티비">TV</b-dropdown-item>
                                <b-dropdown-item href="/appliances?type=냉장고">냉장고</b-dropdown-item>
                                <b-dropdown-item href="/appliances?type=세탁기">세탁기</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 가구 -->
                            <b-nav-item-dropdown text="가구" href="/furniture">
                                <b-dropdown-item href="/furniture?type=장농">장농</b-dropdown-item>
                                <b-dropdown-item href="/furniture?type=서랍">서랍</b-dropdown-item>
                                <b-dropdown-item href="/furniture?type=식탁">식탁</b-dropdown-item>
                            </b-nav-item-dropdown>

                            <!-- 업소용 -->
                            <b-nav-item-dropdown text="업소용" href="/business">
                                <b-dropdown-item href="/business?type=선반">선반</b-dropdown-item>
                                <b-dropdown-item href="/business?type=냉장고">냉장고</b-dropdown-item>
                                <b-dropdown-item href="/business?type=화구">화구</b-dropdown-item>
                            </b-nav-item-dropdown>
                        </b-navbar-nav>
                    </b-collapse>
                </div>
            </b-navbar>
        </header>
        <div></div>
        <div></div>
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