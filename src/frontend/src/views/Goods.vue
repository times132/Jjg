<template>
    <div>
        <h6>params 카테고리 넘버: {{ $route.params.categorynum }}</h6>
        <h6>props 카테고리 넘버: {{ categorynum }}</h6>
        <h6>데이터 : {{ goods }}</h6>
        <b-table
                small
                hover
                :fields="fields"
                :items="goods"
                responsive="sm"
                @row-clicked="clickRow"
        >
            <template v-slot:cell(title)="data">
                <b>{{ data.value}}</b>
            </template>
        </b-table>

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
                fields: [
                    'bid',
                    { key: 'title', label: '제목'}
                ],
                goods: null,
            }
        },
        created() {
            // console.log(this.$route.params.categorynum)
            this.$store.dispatch("getGoods", this.$route.params.categorynum)
                .then(() => {
                    this.goods = this.$store.state.goods.goods
                })
        },
        methods: {
            clickRow(record) {
                console.log(record.bid)
            }
        }
    }
</script>

<style scoped>

</style>