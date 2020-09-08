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
          }
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
            // this.$store.dispatch("getGoods", this.$route.params.categoryNum, this.criteria)
            //     .then(() => {
            //         this.goods = this.$store.state.goods.goods
            //     })
            getGoodsList(this.$route.params.categoryNum, this.criteria)
                .then(({data}) => {
                    this.goods = data.content
                    this.pagination = data

                    console.log(data)
                })
        },
        methods: {
            clickRow(record) {
                this.$router.push(`/goods/${this.categoryNum}/${record.gid}`)
            },
        }
    }
</script>

<style scoped>

</style>