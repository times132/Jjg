<template>
    <div>
        <h6>params 카테고리 넘버: {{ $route.params.categoryNum }}</h6>
        <h6>props 카테고리 넘버: {{ categoryNum }}</h6>
        <b-table
                small
                hover
                :fields="fields"
                :items="goods"
                responsive="sm"
                @row-clicked="clickRow"
        >
            <template v-slot:cell(title)="data">
                <b>{{ data.value }}</b>
            </template>
        </b-table>

        <!-- 페이징 -->
        <Pagination :paging-data="pagination"/>

        <router-link to="/goods/write">
            <b-button v-if="checkAdmin" squared variant="outline-secondary">글쓰기</b-button>
        </router-link>

    </div>
</template>

<script>
    import { getGoodsList } from "../../api";
    import Pagination from "../../components/Pagination";

    export default {
        name: "Goods",
        components: {
            Pagination
        },
        computed: {
            checkAdmin() {
                if (!this.$store.getters.getAuth.includes('ROLE_ADMIN'))
                    return false
                return true
            },
        },
        props: {
            categoryNum: {
                type: String,
                default: ''
            }
        },
        data() {
            return {
                fields: [
                    'gid',
                    { key: 'title', label: '제목'}
                ],
                goods: null,
                criteria: {
                    page: 1,
                    pageSize: 5,
                    type: '',
                    keyword: ''
                },
                // pagination: {
                //     realEndPage: 0,
                //     startPage: 0,
                //     nowEndPage: 0,
                //     nowPage: 0,
                //     total: 0,
                //     size: 0,
                //     prev: false,
                //     next: false,
                // }
                pagination: {}
            }
        },
        created() {
            console.log("list created")
            // this.$store.dispatch("getGoods", this.$route.params.categoryNum, this.criteria)
            //     .then(() => {
            //         this.goods = this.$store.state.goods.goods
            //     })

            getGoodsList(this.$route.params.categoryNum, this.criteria)
                .then(({data}) => {
                    console.log(data)
                    this.goods = data.goodsList
                    this.pagination = data.pagination
                })

            // this.$store.dispatch("getGoods", {categoryNum: this.$route.params.categoryNum, criteria: this.criteria})
            //     .then((data) => {
            //         console.log(data)
            //         this.goods = data
            //     })
            // console.log(this.$store.state.goods.pagination)
        },
        methods: {
            clickRow(record) {
                this.$router.push(`/goods/${this.categoryNum}/${record.gid}`)
            },
            // getList(){
            //     getGoodsList(this.$route.params.categoryNum, this.criteria)
            //         .then(({data}) => {
            //             this.pagination = data.pagination
            //             this.goods = data.goodsList
            //
            //             console.log(data.pagination)
            //         })
            // }
        },
    }
</script>

<style scoped>

</style>