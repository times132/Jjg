<template>
    <ToastViewer sub-url="/goods/detail" :bid="bid"/>
</template>

<script>
    import ToastViewer from "../../components/ToastViewer";

    export default {
        name: "Detail",
        components: {
            ToastViewer
        },
        data() {
            return {
                bid: this.$route.params.bid
            }
        },
        mounted() {

        }
    }
</script>

<style scoped>

</style>