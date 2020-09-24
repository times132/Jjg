<template>
    <b-container>
        <h6>params 카테고리 넘버: {{ $route.params.categoryNum }}</h6>
        <h6>props 카테고리 넘버: {{ categoryNum }}</h6>

        <div v-if="isDataFetch">
            <b-row  v-for="i in 3" :key="i">
                <b-col class="goods-list" cols="12" sm="4" v-for="(goods, j) in goods.slice((i-1)*3, i*3)" :key="j">
                    <div>
                        <b-img fluid thumbnail :src="'http://localhost:9000/display?imageName='+thumbnail(goods.image)" @error="$event.target.src=noImage" @click="clickRow(goods.gid)"/>
                        <span>{{goods.title}}</span>
                    </div>
                </b-col>
            </b-row>
        </div>

        <!-- 페이징 -->
        <Pagination :paging-data="pagination" :cri="criteria"/>

        <router-link to="/goods/write">
            <b-button v-if="checkAdmin" squared variant="outline-secondary">글쓰기</b-button>
        </router-link>

    </b-container>
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
                goods: null,
                criteria: {
                    page: this.$route.params.page===undefined ? 1 : this.$route.params.page,
                    pageSize: 9,
                    pageRange: 5,
                    type: '',
                    keyword: ''
                },
                pagination: {},
                isDataFetch: false,
                noImage: require('@/assets/img/no-image.jpg')
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
            clickRow(gid) {
                this.$router.push(`/goods/${this.categoryNum}/${gid}`)
                this.$store.dispatch('setCriteria', this.criteria)
            },
            getList(){
                getGoodsList(this.$route.params.categoryNum, this.criteria)
                    .then(({data}) => {
                        this.pagination = data.pagination
                        this.goods = data.goodsList
                        this.isDataFetch = true
                    })
            },
            thumbnail(value) {
                if (value === null) {
                    return null
                }else {
                    return value.path+'/s_'+value.uuid+'_'+value.fileName
                }
            }
        },
    }
</script>

<style>
    .goods-list{
        /*padding: 0!important;*/
        /*margin: 0 15px!important;*/
    }
    @media (min-width: 1200px) {
        .goods-list img{
            height: 350px;
            width: 100%;
        }
    }
    @media (max-width: 992px){
        .goods-list img{
            height: 290px;
            width: 100%;
        }
    }
    @media (max-width: 768px){
        .goods-list img{
            height: 210px;
            width: 100%;
        }
    }
    @media (max-width: 576px){
        .goods-list img{
            height: 150px;
            width: 100%;
        }
    }
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