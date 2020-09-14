<template>
    <b-container>
        <div class="button-group mt-3">
            <b-button v-if="compareUser" squared size="sm">수정</b-button>
            <b-button v-if="compareUser" squared variant="outline-danger" size="sm">삭제</b-button>
            <b-button @click="moveList" squared variant="primary" size="sm">목록</b-button>
        </div>

        <b-card no-body class="mt-1">
            <b-row no-gutters>
                <b-col md="5" style="height: 600px">
                    <b-card-img style="max-height: 600px" :src="'http://localhost:9000/display?imageName=' + imageUrl" width="320" @error="$event.target.src=noImage"/>
                </b-col>
                <b-col md="7">
                    <b-card-body>
                        <b-card-text>
                            <h2 class="title">{{title}}</h2>
                            <h4>{{price}}원</h4>
                        </b-card-text>
                    </b-card-body>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    <b-card-text class="content">
                        <viewer v-if="content != null" :initialValue="content"/>
                    </b-card-text>
                </b-col>
            </b-row>
        </b-card>
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
                    if (this.$store.state.login.userInfo.username === this.writer) {
                        return true
                    }
                }
                return false
            }
        },
        data() {
            return {
                gid: this.$route.params.gid,
                title: '',
                price: 0,
                content: null,
                writer: '',
                imageUrl: '',
                imageData: '',
                width: 320,
                height: 480,
                noImage: require('@/assets/img/no-image.jpg')
            }
        },
        created() {
            getDetail('/goods/detail', this.gid)
                .then(({data}) => {
                    this.content = data.content
                    this.title = data.title
                    this.price = data.price
                    this.writer = data.writer

                    if (data.image !== null) this.imageUrl = data.image.path + '/' + data.image.uuid + '_' + data.image.fileName
                })
        },
        mounted() {

        },
        methods: {
            moveList() {
                this.$router.push({name: 'goods', params: {'categoryNum': this.$route.params.categoryNum, 'page': this.getCri.page, 'pageSize': this.getCri.pageSize, 'type': this.getCri.type, 'keyword': this.getCri.keyword}})
            }
        }
    }
</script>

<style scoped>
    .button-group{
        text-align: right;
    }
    .button-group button{
        margin: 0 0.1rem;
    }
    .content{
        border-top: 1px solid rgba(0,0,0,.125)
    }
</style>