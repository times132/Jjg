<template>
    <div class="pagination">
        <ul class="pagination">
            <li v-if="pagination.prev" class="page-item">
                <router-link to="#">
                    이전
                </router-link>
            </li>

            <li v-for="index in pagination.endPage" :key="index" class="page-item">
                <router-link to="#">{{index}}</router-link>
            </li>

            <li v-if="pagination.next" class="page-item">
                <router-link to="#">
                    다음
                </router-link>
            </li>
        </ul>
    </div>

</template>

<script>
    export default {
        name: "Pagination",
        props: ['pagingData'],
        data() {
          return {
              pagination: this.pagingData,

          }
        },
        created() {
            console.log("pagination created")
        },
        watch: {
            pagingData: function (value) {
                this.pagination = value
            }
        }
    }
</script>

<style scoped>

</style>