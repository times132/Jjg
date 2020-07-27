<template>
    <div>
        <h6>params 카테고리 넘버: {{ $route.params.categorynum }}</h6>
        <h6>props 카테고리 넘버: {{ categorynum }}</h6>
        <h6>데이터 : {{ $store.state.goods.goods }}</h6>
        <b-table striped hover :items="$store.state.goods.goods"></b-table>

        <b-button href="/goods/write" squared variant="outline-secondary">글쓰기</b-button>
    </div>
</template>

<script>
    export default {
        name: "Goods",
        props: {
            categorynum: {
                type: String,
                default: ''
            }
        },
        data() {
            return {
                goods: null,
            }
        },
        created() {
            console.log(this.$route.params.categorynum)
            this.$store.dispatch("getGoods", this.$route.params.categorynum)
                .then()
        },
        methods: {
            write() {

            }
        }
    }
</script>

<style scoped>

</style>