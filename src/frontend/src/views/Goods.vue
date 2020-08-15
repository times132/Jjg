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
                <b>{{ data.value}}</b>
            </template>
        </b-table>

        <b-button v-if="checkAdmin" href="/goods/write" squared variant="outline-secondary">글쓰기</b-button>
    </div>
</template>

<script>
    export default {
        name: "Goods",
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
                    'bid',
                    { key: 'title', label: '제목'}
                ],
                goods: null,
            }
        },
        created() {
            // console.log(this.$route.params.categorynum)
            this.$store.dispatch("getGoods", this.$route.params.categoryNum)
                .then(() => {
                    this.goods = this.$store.state.goods.goods
                })
        },
        methods: {
            clickRow(record) {
                console.log(record.bid)
                this.$router.push(`/goods/${this.categoryNum}/${record.bid}`)
            }
        }
    }
</script>

<style scoped>

</style>