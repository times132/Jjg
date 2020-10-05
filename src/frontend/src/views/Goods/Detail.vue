<template>
    <b-container>
        <div class="button-group mt-3">
            <b-button @click="modifyGoods" v-if="compareUser" squared size="sm">수정</b-button>
            <b-button v-if="compareUser" squared variant="outline-danger" size="sm">삭제</b-button>
            <b-button @click="moveList" squared variant="primary" size="sm">목록</b-button>
        </div>

        <div class="mt-1">
            <b-row no-gutters>
                <b-col md="5" class="goods-img-container">
                    <div class="goods-detail">
                        <b-card-img class="goods-detail-img" :src="'http://localhost:9000/display?imageName=' + imageUrl" width="320" @error="$event.target.src=noImage"/>
                    </div>
                </b-col>
                <b-col md="7">
                        <h2 class="title">{{goods.title}}</h2>
                        <h4>{{goods.price}}원</h4>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    <viewer v-if="goods.content != null" :initialValue="goods.content"/>
                </b-col>
            </b-row>
        </div>
    </b-container>
</template>

<script>
    import { getDetail } from "../../api";

    export default {
        name: "Detail",
        computed: {
            getCri() {
                return this.$store.getters.getCriteria
            },
            compareUser(){
                if (this.$store.getters.getIsAuth){
                    if (this.$store.state.login.userInfo.username === this.goods.writer) {
                        return true
                    }
                }
                return false
            }
        },
        data() {
            return {
                goods: {
                    gid: this.$route.params.gid,
                    categoryItem: null,
                    title: '',
                    price: 0,
                    content: null,
                    writer: '',
                    image: null
                },
                imageUrl: '',
                imageData: '',
                width: 320,
                height: 480,
                noImage: require('@/assets/img/no-image.jpg')
            }
        },
        created() {
            getDetail('/goods/detail', this.goods.gid)
                .then(({data}) => {
                    this.goods.content = data.content
                    this.goods.title = data.title
                    this.goods.price = data.price
                    this.goods.writer = data.writer
                    this.goods.categoryItem = data.categoryItem

                    if (data.image !== null) {
                        this.goods.image = data.image
                        this.imageUrl = data.image.path + '/' + data.image.uuid + '_' + data.image.fileName
                    }
                })
        },
        mounted() {

        },
        methods: {
            moveList() {
                this.$router.push({name: 'goods', params: {'categoryNum': this.$route.params.categoryNum, 'page': this.getCri.page, 'pageSize': this.getCri.pageSize, 'type': this.getCri.type, 'keyword': this.getCri.keyword}})
            },
            modifyGoods() {
                this.$router.push({name: 'modify', params: {goods: this.goods}})
            }
        }
    }
</script>

<style scoped>
    .goods-img-container{
        height: 480px;
        overflow: hidden;
    }
    .goods-detail {
        height: 100%;
        display:flex;
        align-items: center;
    }
    .goods-detail-img{
        height: auto;
        width: 100%;
        align-self: center;
    }
    .button-group{
        text-align: right;
    }
    .button-group button{
        margin: 0 0.1rem;
    }
    .content{
        border-top: 1px solid rgba(0, 0, 0, 0.12)
    }
</style>