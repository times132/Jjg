<template>
    <b-container>
        <div class="button-group mt-3">
            <div class="logged-button" v-if="compareUser">
                <b-button @click="modifyGoods" squared size="sm">수정</b-button>
                <b-button @click="showModal" squared variant="outline-danger" size="sm">삭제</b-button>
            </div>
            <div class="basic-button">
                <b-button @click="moveList" squared variant="primary" size="sm">목록</b-button>
            </div>
        </div>

        <b-card no-body class="mt-1">
            <b-row no-gutters>
                <b-col md="5" style="height: 600px">
                    <b-card-img style="max-height: 600px" :src="'http://localhost:9000/display?imageName=' + imageUrl" width="320" @error="$event.target.src=noImage"/>
                </b-col>
                <b-col md="7">
                    <b-card-body>
                        <b-card-text>
                            <h2 class="title">{{goods.title}}</h2>
                            <h4>{{goods.price}}원</h4>
                        </b-card-text>
                    </b-card-body>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    <b-card-text class="content">
                        <viewer v-if="goods.content != null" :initialValue="goods.content"/>
                    </b-card-text>
                </b-col>
            </b-row>
        </b-card>

        <b-modal size="sm" ref="delete-modal" hide-footer>
            <template v-slot:modal-header="{close}">
                <h5></h5>
                <button type="button" aria-label="Close" class="close" @click="close()">x</button>
            </template>

            <div>
                <h6>게시물을 삭제하시겠습니까?</h6>
            </div>

            <div class="modal-button">
                <button class="cancel" @click="hideModal">아니오</button>
                <button class="ok" @click="deleteGoods">예</button>
            </div>
        </b-modal>
    </b-container>
</template>

<script>
    import { deleteGoods, getDetail } from "../../api";

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
            showModal(){
                this.$refs['delete-modal'].show()
            },
            hideModal(){
                this.$refs['delete-modal'].hide()
            },
            moveList() {
                this.$router.push({name: 'goods', params: {'categoryNum': this.$route.params.categoryNum, 'page': this.getCri.page, 'pageSize': this.getCri.pageSize, 'type': this.getCri.type, 'keyword': this.getCri.keyword}})
            },
            modifyGoods() {
                this.$router.push({name: 'modify', params: {goods: this.goods}})
            },
            deleteGoods() {
                this.$refs['delete-modal'].hide()
                deleteGoods(this.goods.gid)
                    .then(({data}) => {
                        if (data == this.goods.gid) {
                            alert("삭제되었습니다.")
                            this.$router.push({name: 'goods', params: {'categoryNum': this.$route.params.categoryNum}})
                        }
                    })
            }
        }
    }
</script>

<style scoped>
    .modal-header .close{
        padding: 0.5rem 1rem;
    }
    .close{
        float: right;
        font-size: 1.25rem;
        font-weight: 700;
        line-height: 1;
        color: #000;
        text-shadow: 0 1px 0 #fff;
        opacity: .5;
    }
    .modal-button{
        margin-top: 1rem;
        float: right;
    }
    .modal-button button{
        margin: 0 0.25rem;
        border-radius: 4px;
        padding: 0.25rem 0.5rem;
    }
    .modal-button .cancel{
        border: 1px solid #408aec;
        background-color: #408aec;
        color: white;
        font-size: 1rem;
    }
    .modal-button .cancel:hover{
        background-color: #377cec;
    }
    .modal-button .ok{
        border: 1px solid #cbc8d3;
        background-color: white;
        color: #408aec;
    }
    .modal-button .ok:hover{
        background-color: #f5f8ff;
    }
    .button-group{
        text-align: right;
    }
    .logged-button{
        float: left;
    }
    .button-group button{
        margin: 0 0.1rem;
    }
    .content{
        border-top: 1px solid rgba(0,0,0,.125)
    }
</style>