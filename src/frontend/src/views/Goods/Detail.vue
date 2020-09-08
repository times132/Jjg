<template>
    <b-container>
<!--        <b-row>-->
<!--            <b-col>-->
<!--                <b-card class="mb-3">-->
<!--                    <b-row class="no-gutters">-->
<!--                        <b-col cols="5">-->
<!--                            <b-img :src="'http://localhost:9000/display?imageName=' + imageUrl" width="320"></b-img>-->
<!--                        </b-col>-->

<!--                        &lt;!&ndash; 제품 기본 정보 &ndash;&gt;-->
<!--                        <b-col md="auto">-->
<!--                            <div class="muted-info">-->
<!--                                <span class="h6 category text-muted"><small>번호 : {{ gid }}</small></span>-->
<!--                            </div>-->

<!--                            <h2>{{ title }}</h2>-->
<!--                            <h4 class="price">{{ price }}원</h4>-->


<!--                        </b-col>-->
<!--                    </b-row>-->

<!--                    <b-row>-->
<!--                        <b-card-text>-->
<!--                            <viewer v-if="content != null" :initialValue="content"/>-->
<!--                        </b-card-text>-->
<!--                    </b-row>-->
<!--                    &lt;!&ndash; 제품 상세 내용 &ndash;&gt;-->
<!--                </b-card>-->
<!--            </b-col>-->
<!--        </b-row>-->
        <div class="button-group mt-3">
            <b-button squared size="sm">수정</b-button>
            <b-button squared variant="outline-danger" size="sm">삭제</b-button>
            <b-button squared variant="primary" size="sm">목록</b-button>
        </div>


        <b-card no-body class="mt-1">
            <b-row no-gutters>
                <b-col md="5" style="height: 600px">
                    <b-card-img style="max-height: 600px" :src="'http://localhost:9000/display?imageName=' + imageUrl" width="320"></b-card-img>
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
                height: 480
            }
        },
        created() {
            getDetail('/goods/detail', this.gid)
                .then(({data}) => {
                    this.content = data.content
                    this.title = data.title
                    this.price = data.price
                    this.imageUrl = data.image.path + '/' + data.image.uuid + '_' + data.image.fileName
                    console.log(this.imageUrl)
                })

        },
        mounted() {

        },
        methods: {

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