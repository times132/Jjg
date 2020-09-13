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
        <Pagination :paging-data="pagination" :cri="criteria"/>

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
                pagination: {}
            }
        },
        created() {
            this.getList()
        },
        watch: {
            criteria: {
                deep: true,
                handler() {
                    this.getList()
                }
            }
        },
        methods: {
            clickRow(record) {
                this.$router.push(`/goods/${this.categoryNum}/${record.gid}`)
            },
            getList(){
                getGoodsList(this.$route.params.categoryNum, this.criteria)
                    .then(({data}) => {
                        this.pagination = data.pagination
                        this.goods = data.goodsList
                    })
            }
        },
    }
</script>

<style>
    .pagination{
        justify-content: center;
    }
    .pagination span{
        margin: 0 0.2rem;
        font-size: 1.25rem;
    }
    .pagination span:hover:not(.active){
        cursor: pointer;
        background-color: #ddd;
    }
    .active{
        font-weight: bold;
        text-decoration: underline;
    }
</style>