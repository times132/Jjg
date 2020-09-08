<template>
    <div class="pagination">
        <ul class="pagination">
<!--            <li class="page-item" v-if="pagination.prev">-->
<!--                <router-link to="#">이전</router-link>-->
<!--            </li>-->
        </ul>
    </div>

</template>

<script>
    export default {
        name: "Pagination",
        props: {
            pagingData: Object
        },
        data() {
          return {
              pagination: this.pagingData
          }
        },
        created() {
            // this.pagination.nowPage = pagination.number+1
            // this.pagination.realEndPage = pagination.totalPages
            // this.pagination.total = pagination.totalElements
            // this.pagination.size = pagination.size
            // this.pagination.nowEndPage = Math.ceil(pagination.number+1 / pagination.size) * pagination.size
            // this.pagination.startPage = this.pagination.nowEndPage - this.pagination.size + 1
            // if (this.pagination.realEndPage < this.pagination.nowEndPage) this.pagination.nowEndPage = this.pagination.realEndPage
            // this.pagination.prev = this.pagination.startPage > 1
            // this.pagination.next = this.pagination.nowEndPage < this.pagination.realEndPage
            console.log(this.pagination.size)
            console.log(this.pagination.number)
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>